package org.team4639.robot.commands.superstructure;

import static edu.wpi.first.units.Units.Percent;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.MutTime;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.function.BooleanSupplier;
import org.team4639.lib.util.RotationUtil;
import org.team4639.robot.robot.Subsystems;
import org.team4639.robot.subsystems.superstructure.Superstructure;
import org.team4639.robot.subsystems.superstructure.superstructurestate.SuperstructureState;
import org.team4639.robot.subsystems.superstructure.elevator.ElevatorConstants;
import org.team4639.robot.subsystems.superstructure.wrist.WristConstants;

public class SuperstructureCommand extends SuperstructureCommandBase {
  private SuperstructureState setpoint;
  private SuperstructureCommandState state;
  private final BooleanSupplier endCondition;
  private Dimensionless holdPosition;
  private final MutTime timeOfExecutingAction;
  private Time executingActionTimeout = Seconds.of(Double.POSITIVE_INFINITY);
  private final String name;
  private boolean flash;

    /**
   * Commands the superstructure to go to a specific state
   *
   * @param setpoint the setpoint that the superstructure is commanded to
   * @param endCondition the condition to end this command. Ex. has coral, doesn't have coral, or
   *     can always return false for a command that doesn't end by itself. This is checked only once
   *     the command has reached the EXECUTING_ACTION state.
   */
  public SuperstructureCommand(
      SuperstructureState setpoint, BooleanSupplier endCondition, String name) {
    addRequirements(
        Subsystems.elevator, Subsystems.wrist, Subsystems.roller, Subsystems.superstructure);
    this.setpoint = setpoint;
    this.state = SuperstructureCommandState.TO_SAFE_ANGLE;
    this.endCondition = endCondition;
    holdPosition = Value.zero();
    timeOfExecutingAction = Seconds.mutable(0);
    this.name = name;
    setName(name);
  }

  public SuperstructureCommand(SuperstructureState setpoint, String name) {
    this(setpoint, () -> false, name);
  }

  @Override
  public void initialize() {
    setState(SuperstructureCommandState.TO_SAFE_ANGLE);
    if (Superstructure.atPosition(Superstructure.getSuperstructureState(), setpoint))
      setState(SuperstructureCommandState.EXECUTING_ACTION);
  }

  @Override
  public void execute() {
    super.execute();
    SmartDashboard.putBoolean("Elevator At Setpoint", elevatorAtSetpoint());
    if (Superstructure.atPosition(Superstructure.getSuperstructureState(), setpoint))
      setState(SuperstructureCommandState.EXECUTING_ACTION);
    switch (state) {
      case TO_SAFE_ANGLE -> {
        if (elevatorAtSetpoint()) setState(SuperstructureCommandState.TO_WRIST_SETPOINT);
        if (Superstructure.isWristAtSafeAngle())
          setState(SuperstructureCommandState.TO_ELEVATOR_SETPOINT);
        if (RotationUtil.boundedBy(
                Subsystems.wrist.getWristAngle(),
                WristConstants.SAFE_TRANSITION_RANGE_INTERIOR.getFirst(),
                WristConstants.SAFE_TRANSITION_RANGE_INTERIOR.getSecond())
            && RotationUtil.boundedBy(
                setpoint.getWristRotation(),
                WristConstants.SAFE_TRANSITION_RANGE_INTERIOR.getFirst(),
                WristConstants.SAFE_TRANSITION_RANGE_INTERIOR.getSecond()))
          setState(SuperstructureCommandState.TO_ELEVATOR_SETPOINT);
          Pair<Rotation2d, Rotation2d> safeTransitionRange =
            Superstructure.getEffectiveExteriorSafeZone();
        Subsystems.wrist.setWristSetpoint(
            RotationUtil.nearest(
                setpoint.getWristRotation(),
                RotationUtil.min(safeTransitionRange.getFirst(), safeTransitionRange.getSecond())
                    .plus(Rotation2d.fromDegrees(2)),
                RotationUtil.max(safeTransitionRange.getFirst(), safeTransitionRange.getSecond())
                    .minus(Rotation2d.fromDegrees(2))));
        Subsystems.elevator.setPercentageRaw(holdPosition);
        Subsystems.roller.setVelocity(RotationsPerSecond.zero());
      }
      case TO_ELEVATOR_SETPOINT -> {
        if (elevatorAtSetpoint()) setState(SuperstructureCommandState.TO_WRIST_SETPOINT);
        if (Superstructure.isWristAtSafeAngle()) {
          Subsystems.wrist.setWristSetpoint(setpoint.getWristRotation());
        } else {
          Subsystems.wrist.setWristDutyCycle(Percent.zero());
        }
        Subsystems.elevator.setPercentageRaw(setpoint.getElevatorProportion());
        Subsystems.roller.setVelocity(RotationsPerSecond.zero());
      }
      case TO_WRIST_SETPOINT -> {
          if (Superstructure.atPosition(Superstructure.getSuperstructureState(), setpoint))
          setState(SuperstructureCommandState.EXECUTING_ACTION);

        Subsystems.wrist.setWristSetpoint(setpoint.getWristRotation());
        Subsystems.elevator.setPercentageRaw(setpoint.getElevatorProportion());
        ;
        Subsystems.roller.setVelocity(RotationsPerSecond.zero());
      }
      case EXECUTING_ACTION -> {
          timeOfExecutingAction.mut_plus(0.02, Seconds);
        if (endCondition.getAsBoolean()) setState(SuperstructureCommandState.DONE);
        if (timeOfExecutingAction.gte(executingActionTimeout))
          setState(SuperstructureCommandState.DONE);
        if (flash) Subsystems.limelightFlash.flash();
        Subsystems.wrist.setWristSetpoint(setpoint.getWristRotation());
        Subsystems.elevator.setPercentageRaw(setpoint.getElevatorProportion());
        Subsystems.roller.setDutyCycle(setpoint.getWheelDutyCycle());
      }
      case DONE -> {
        // at this point the command will be ended, but we do these just to make sure nothing
        // strange happens.
        Subsystems.wrist.setWristSetpoint(setpoint.getWristRotation());
        Subsystems.elevator.setPercentageRaw(setpoint.getElevatorProportion());
        ;
        Subsystems.roller.setDutyCycle(setpoint.getWheelDutyCycle());
      }
      case STOPPED -> {
        Subsystems.wrist.setWristDutyCycle(Value.zero());
        Subsystems.elevator.elevatorStop();
        Subsystems.roller.setDutyCycle(Value.zero());
      }
      default -> throw new IllegalArgumentException("Unexpected state: " + state);
    }
  }

  public SuperstructureCommand flashOnDone() {
    this.flash = true;
    return this;
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
  }

  @Override
  public boolean isFinished() {
    return state == SuperstructureCommandState.DONE;
  }

  private boolean elevatorAtSetpoint() {
    return MathUtil.isNear(
        Subsystems.elevator.getPercentage().baseUnitMagnitude(),
        setpoint.getElevatorProportion().baseUnitMagnitude(),
        Math.abs(ElevatorConstants.elevatorTolerance.baseUnitMagnitude()));
  }

  private void setHoldPosition() {
    holdPosition = Subsystems.elevator.getPercentage();
  }

  private void setState(SuperstructureCommandState state) {
    this.state = state;
    setHoldPosition();
  }

  public SuperstructureCommand withExecutionTimeout(Time time) {
    this.executingActionTimeout = time;
    return this;
  }

  @Override
  public SuperstructureCommandState getState() {
    return state;
  }

  @Override
  public String getName() {
    return name;
  }
}
