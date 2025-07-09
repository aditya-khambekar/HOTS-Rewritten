package org.team4639.robot.statemachine.competition;

import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Objects;
import org.team4639.lib.statebased.State;
import org.team4639.lib.statebased.StateFactory;
import org.team4639.robot.commands.AutoCommands;
import org.team4639.robot.commands.DriveCommands;
import org.team4639.robot.commands.LEDCommands;
import org.team4639.robot.commands.SuperstructureCommands;
import org.team4639.robot.commands.superstructure.MicroAdjustmentCommand;
import org.team4639.robot.constants.reefscape.FieldUtil;
import org.team4639.robot.constants.reefscape.TargetPositions;
import org.team4639.robot.constants.robot.Controls;
import org.team4639.robot.modaltriggers.DriveTriggers;
import org.team4639.robot.robot.RobotContainer;
import org.team4639.robot.robot.Subsystems;
import org.team4639.robot.statemachine.StatesBase;
import org.team4639.robot.subsystems.superstructure.Superstructure;
import org.team4639.robot.subsystems.superstructure.SuperstructureSetpoints;

/** State machine used in competition */
public final class ReefscapeStates implements StatesBase {
  public State IDLE;
  public State NONE = StateFactory.none();
  public State HP_LEFT;
  public State HP_RIGHT;
  public State HP_NODIR;
  public State INTAKE_LOWER;
  public State CORAL_STOW;
  public State ALIGN_ALGAE;
  public State ALGAE_INTAKE;
  public State CORAL_SCORE_ALIGN_LEFT;
  public State CORAL_SCORE_ALIGN_RIGHT;
  public State ALGAE_STOW;
  public State ALGAE_SCORE;
  public State CHOOSE_CORAL_LEVEL;
  public State L1_CORAL_SCORE;
  public State L2_CORAL_SCORE;
  public State L3_CORAL_SCORE;
  public State L4_CORAL_SCORE;
  public State HOMING_READY;
  public State HOMING;
  public State REJECT_CORAL;
  public State REJECT_ALGAE;
  public State MICROADJUSTMENTS;

  private static volatile StatesBase instance;

  public static synchronized StatesBase getInstance() {
    return instance = Objects.requireNonNullElseGet(instance, ReefscapeStates::new);
  }

  private ReefscapeStates() {}

  public void init() {
    IDLE =
        new State("IDLE")
            .whileTrue(SuperstructureCommands.IDLE)
            .onTrigger(DriveTriggers.closeToRightStation, () -> HP_RIGHT)
            .onTrigger(DriveTriggers.closeToLeftStation, () -> HP_LEFT)
            .withEndCondition(Controls.intake, () -> HP_NODIR)
            .withEndCondition(Subsystems.wrist::hasCoral, () -> CORAL_STOW)
            .withEndCondition(Controls.LEFT_HP, this::pathFindToHPLeft)
            .withEndCondition(Controls.RIGHT_HP, this::pathFindToHPRight)
            .withEndCondition(
                Controls.REEF_AB, () -> this.pathFindToReefAlgae(TargetPositions.REEF_AB))
            .withEndCondition(
                Controls.REEF_CD, () -> this.pathFindToReefAlgae(TargetPositions.REEF_CD))
            .withEndCondition(
                Controls.REEF_EF, () -> this.pathFindToReefAlgae(TargetPositions.REEF_EF))
            .withEndCondition(
                Controls.REEF_GH, () -> this.pathFindToReefAlgae(TargetPositions.REEF_GH))
            .withEndCondition(
                Controls.REEF_IJ, () -> this.pathFindToReefAlgae(TargetPositions.REEF_IJ))
            .withEndCondition(
                Controls.REEF_KL, () -> this.pathFindToReefAlgae(TargetPositions.REEF_KL));

    HP_LEFT =
        new State("HP_LEFT")
            .whileTrue(DriveCommands.HPStationAlignLeft(), SuperstructureCommands.HP)
            .withEndCondition(DriveTriggers.closeToLeftStation.negate(), () -> INTAKE_LOWER)
            .onTrigger(Controls.secondIntake, () -> INTAKE_LOWER)
            .onEmergency(() -> IDLE);

    HP_RIGHT =
        new State("HP_RIGHT")
            .whileTrue(DriveCommands.HPStationAlignRight(), SuperstructureCommands.HP)
            .withEndCondition(DriveTriggers.closeToRightStation.negate(), () -> INTAKE_LOWER)
            .onTrigger(Controls.secondIntake, () -> INTAKE_LOWER)
            .onEmergency(() -> IDLE);

    HP_NODIR =
        new State("INTAKE_NODIR")
            .whileTrue(SuperstructureCommands.HP)
            .onTrigger(Controls.secondIntake, () -> INTAKE_LOWER);

    INTAKE_LOWER =
        new State("INTAKE_LOWER")
            .whileTrue(SuperstructureCommands.HP_LOWER)
            .withEndCondition(Subsystems.wrist::hasCoral, () -> CORAL_STOW)
            .onEmergency(() -> IDLE);

    CORAL_STOW =
        new State("CORAL_STOW")
            .whileTrue(
                SuperstructureCommands.CORAL_STOW,
                DriveCommands.joystickDriveAtAngle(
                    () -> -RobotContainer.driver.getLeftY(),
                    () -> -RobotContainer.driver.getLeftX(),
                    () -> FieldUtil.getRotationToClosestBranchPosition(Subsystems.drive.getPose())),
                LEDCommands.hasCoral())
            .onTrigger(Controls.alignLeft, () -> CORAL_SCORE_ALIGN_LEFT)
            .onTrigger(Controls.alignRight, () -> CORAL_SCORE_ALIGN_RIGHT)
            .onTrigger(Controls.REEF_A, () -> pathFindToReef(TargetPositions.REEF_A))
            .onTrigger(Controls.REEF_B, () -> pathFindToReef(TargetPositions.REEF_B))
            .onTrigger(Controls.REEF_C, () -> pathFindToReef(TargetPositions.REEF_C))
            .onTrigger(Controls.REEF_D, () -> pathFindToReef(TargetPositions.REEF_D))
            .onTrigger(Controls.REEF_E, () -> pathFindToReef(TargetPositions.REEF_E))
            .onTrigger(Controls.REEF_F, () -> pathFindToReef(TargetPositions.REEF_F))
            .onTrigger(Controls.REEF_G, () -> pathFindToReef(TargetPositions.REEF_G))
            .onTrigger(Controls.REEF_H, () -> pathFindToReef(TargetPositions.REEF_H))
            .onTrigger(Controls.REEF_I, () -> pathFindToReef(TargetPositions.REEF_I))
            .onTrigger(Controls.REEF_J, () -> pathFindToReef(TargetPositions.REEF_J))
            .onTrigger(Controls.REEF_K, () -> pathFindToReef(TargetPositions.REEF_K))
            .onTrigger(Controls.REEF_L, () -> pathFindToReef(TargetPositions.REEF_L))
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> IDLE)
            .onEmergency(() -> REJECT_CORAL);

    CORAL_SCORE_ALIGN_LEFT =
        new State("CORAL_SCORE_ALIGN_LEFT")
            .withDeadline(DriveCommands.reefAlignLeft(), () -> CHOOSE_CORAL_LEVEL)
            .whileTrue(
                SuperstructureCommands.ELEVATOR_READY,
                Subsystems.dashboardOutputs.displayUpcomingReefLevel(),
                LEDCommands.aligning())
            .onTrigger(Controls.alignRight, () -> this.CORAL_SCORE_ALIGN_RIGHT)
            .onTrigger(Controls.REEF_A, () -> pathFindToReef(TargetPositions.REEF_A))
            .onTrigger(Controls.REEF_B, () -> pathFindToReef(TargetPositions.REEF_B))
            .onTrigger(Controls.REEF_C, () -> pathFindToReef(TargetPositions.REEF_C))
            .onTrigger(Controls.REEF_D, () -> pathFindToReef(TargetPositions.REEF_D))
            .onTrigger(Controls.REEF_E, () -> pathFindToReef(TargetPositions.REEF_E))
            .onTrigger(Controls.REEF_F, () -> pathFindToReef(TargetPositions.REEF_F))
            .onTrigger(Controls.REEF_G, () -> pathFindToReef(TargetPositions.REEF_G))
            .onTrigger(Controls.REEF_H, () -> pathFindToReef(TargetPositions.REEF_H))
            .onTrigger(Controls.REEF_I, () -> pathFindToReef(TargetPositions.REEF_I))
            .onTrigger(Controls.REEF_J, () -> pathFindToReef(TargetPositions.REEF_J))
            .onTrigger(Controls.REEF_K, () -> pathFindToReef(TargetPositions.REEF_K))
            .onTrigger(Controls.REEF_L, () -> pathFindToReef(TargetPositions.REEF_L))
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> IDLE)
            .onEmergency(() -> CORAL_STOW)
            .onAccelerationLimit(() -> CORAL_STOW);

    CORAL_SCORE_ALIGN_RIGHT =
        new State("CORAL_SCORE_ALIGN_RIGHT")
            .withDeadline(DriveCommands.reefAlignRight(), () -> CHOOSE_CORAL_LEVEL)
            .whileTrue(
                SuperstructureCommands.ELEVATOR_READY,
                Subsystems.dashboardOutputs.displayUpcomingReefLevel(),
                LEDCommands.aligning())
            .onTrigger(Controls.alignLeft, () -> this.CORAL_SCORE_ALIGN_LEFT)
            .onTrigger(Controls.REEF_A, () -> pathFindToReef(TargetPositions.REEF_A))
            .onTrigger(Controls.REEF_B, () -> pathFindToReef(TargetPositions.REEF_B))
            .onTrigger(Controls.REEF_C, () -> pathFindToReef(TargetPositions.REEF_C))
            .onTrigger(Controls.REEF_D, () -> pathFindToReef(TargetPositions.REEF_D))
            .onTrigger(Controls.REEF_E, () -> pathFindToReef(TargetPositions.REEF_E))
            .onTrigger(Controls.REEF_F, () -> pathFindToReef(TargetPositions.REEF_F))
            .onTrigger(Controls.REEF_G, () -> pathFindToReef(TargetPositions.REEF_G))
            .onTrigger(Controls.REEF_H, () -> pathFindToReef(TargetPositions.REEF_H))
            .onTrigger(Controls.REEF_I, () -> pathFindToReef(TargetPositions.REEF_I))
            .onTrigger(Controls.REEF_J, () -> pathFindToReef(TargetPositions.REEF_J))
            .onTrigger(Controls.REEF_K, () -> pathFindToReef(TargetPositions.REEF_K))
            .onTrigger(Controls.REEF_L, () -> pathFindToReef(TargetPositions.REEF_L))
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> IDLE)
            .onEmergency(() -> CORAL_STOW)
            .onAccelerationLimit(() -> CORAL_STOW);

    ALIGN_ALGAE =
        new State("ALIGN_ALGAE")
            .withDeadline(DriveCommands.reefAlignClosest(), () -> ALGAE_INTAKE)
            .whileTrue(SuperstructureCommands.ALGAE_INTAKE)
            .onEmergency(() -> IDLE)
            .onAccelerationLimit(() -> IDLE);

    ALGAE_INTAKE =
        new State("ALGAE_INTAKE")
            .withDeadline(AutoCommands.algaeIntakeSequence(), () -> ALGAE_STOW)
            .onEmergency(() -> IDLE)
            .onAccelerationLimit(() -> IDLE);

    ALGAE_STOW =
        new State("ALGAE_STOW")
            .whileTrue(SuperstructureCommands.ALGAE_STOW)
            .onEmergency(() -> REJECT_ALGAE);

    ALGAE_SCORE =
        new State("ALGAE_SCORE")
            .whileTrue(SuperstructureCommands.BARGE)
            .onEmergency(() -> ALGAE_STOW)
            .onAccelerationLimit(() -> ALGAE_STOW);

    CHOOSE_CORAL_LEVEL =
        new State("CHOOSE_CORAL_LEVEL")
            .whileTrue(
                SuperstructureCommands.HOLD, Subsystems.drive.run(() -> Subsystems.drive.stop()))
            .withEndCondition(Controls.alignLeft, () -> CORAL_SCORE_ALIGN_LEFT)
            .withEndCondition(Controls.alignRight, () -> CORAL_SCORE_ALIGN_RIGHT)
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> IDLE)
            .withEndCondition(
                () -> Subsystems.dashboardOutputs.upcomingReefLevel() == 1, () -> L1_CORAL_SCORE)
            .withEndCondition(
                () -> Subsystems.dashboardOutputs.upcomingReefLevel() == 2, () -> L2_CORAL_SCORE)
            .withEndCondition(
                () -> Subsystems.dashboardOutputs.upcomingReefLevel() == 3, () -> L3_CORAL_SCORE)
            .withEndCondition(
                () -> Subsystems.dashboardOutputs.upcomingReefLevel() == 4, () -> L4_CORAL_SCORE);

    L1_CORAL_SCORE =
        new State("L1_CORAL_SCORE")
            .whileTrue(SuperstructureCommands.L1)
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> HOMING_READY)
            .onEmergency(() -> CORAL_STOW)
            .onAccelerationLimit(() -> CORAL_STOW);

    L2_CORAL_SCORE =
        new State("L2_CORAL_SCORE")
            .whileTrue(SuperstructureCommands.L2)
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> HOMING_READY)
            .onEmergency(() -> CORAL_STOW)
            .onAccelerationLimit(() -> CORAL_STOW);

    L3_CORAL_SCORE =
        new State("L3_CORAL_SCORE")
            .whileTrue(SuperstructureCommands.L3)
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> HOMING_READY)
            .onEmergency(() -> CORAL_STOW)
            .onAccelerationLimit(() -> CORAL_STOW);

    L4_CORAL_SCORE =
        new State("L4_CORAL_SCORE")
            .whileTrue(SuperstructureCommands.L4)
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> HOMING_READY)
            .onEmergency(() -> CORAL_STOW)
            .onAccelerationLimit(() -> CORAL_STOW);

    HOMING_READY =
        new State("HOMING_READY")
            .whileTrue(SuperstructureCommands.HOMING_READY)
            .withEndCondition(
                () ->
                    Superstructure.atPosition(
                        Superstructure.getSuperstructureState(),
                        SuperstructureSetpoints.HOMING_READY),
                () -> HOMING)
            .onEmergency(() -> IDLE);

    HOMING =
        new State("HOMING")
            .withDeadline(SuperstructureCommands.HOMING, () -> IDLE)
            .onEmergency(() -> IDLE);

    REJECT_CORAL =
        new State("REJECT_CORAL")
            .whileTrue(SuperstructureCommands.REJECT_CORAL)
            .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> IDLE);

    REJECT_ALGAE =
        new State("REJECT_ALGAE")
            .whileTrue(SuperstructureCommands.REJECT_ALGAE)
            .withTimeout(Seconds.of(0.5), () -> IDLE);

    MICROADJUSTMENTS =
        new State("MICROADJUSTMENTS")
            .whileTrue(new MicroAdjustmentCommand(), DriveCommands.stopWithX())
            .onEmergency(() -> CORAL_STOW);
  }

  /** Gets the state at the start of auto */
  @Override
  public State getAutoStartState() {
    return StateFactory.none();
  }

  /** Gets the state at the start of teleop */
  @Override
  public State getTeleopStartState() {
    return determineState();
  }

  public State pathFindToReef(TargetPositions reef) {
    var pose = reef.getPose();
    return new State("PATHFIND_TO_REEF")
        .withDeadline(DriveCommands.pathFindToReef(pose), () -> CHOOSE_CORAL_LEVEL)
        .whileTrue(
            SuperstructureCommands.ELEVATOR_READY,
            Subsystems.dashboardOutputs.displayUpcomingReefLevel())
        .withEndCondition(Subsystems.wrist::doesNotHaveCoral, () -> IDLE)
        .onEmergency(() -> CORAL_STOW);
  }

  public State pathFindToHPLeft() {
    return new State("PATHFIND_TO_HP_LEFT")
        .withDeadline(DriveCommands.pathFindToHP(TargetPositions.HP_LEFT.getPose()), () -> HP_LEFT)
        .whileTrue(SuperstructureCommands.HP)
        .onEmergency(() -> IDLE);
  }

  public State pathFindToHPRight() {
    return new State("PATHFIND_TO_HP_RIGHT")
        .withDeadline(
            DriveCommands.pathFindToHP(TargetPositions.HP_RIGHT.getPose()), () -> HP_RIGHT)
        .whileTrue(SuperstructureCommands.HP)
        .onEmergency(() -> IDLE);
  }

  public State pathFindToReefAlgae(TargetPositions reefCenter) {
    return new State("PATHFIND_TO_REEF_CENTER")
        .withDeadline(DriveCommands.pathFindToReefCenter(reefCenter.getPose()), () -> ALGAE_INTAKE)
        .whileTrue(SuperstructureCommands.algaeIntake(reefCenter.getPose()))
        .onEmergency(() -> IDLE);
  }

  public State determineState() {
    return switch (SmartDashboard.getString("Superstructure State", "")) {
      case "IDLE" -> IDLE;
      case "HP" -> HP_NODIR;
      case "HP_LOWER" -> INTAKE_LOWER;
      case "L4" -> L4_CORAL_SCORE;
      default -> Subsystems.wrist.hasCoral() ? CORAL_STOW : IDLE;
    };
  }
}
