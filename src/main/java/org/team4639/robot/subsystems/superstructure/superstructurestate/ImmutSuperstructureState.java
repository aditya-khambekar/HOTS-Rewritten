package org.team4639.robot.subsystems.superstructure.superstructurestate;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Dimensionless;

public class ImmutSuperstructureState extends SuperstructureState {
  private final Dimensionless elevatorProportion;
  private final Rotation2d wristRotation;
  private final Dimensionless wheelDutyCycle;

  protected ImmutSuperstructureState(
      Dimensionless elevatorProportion, Rotation2d wristRotation, Dimensionless wheelDutyCycle) {
    this.elevatorProportion = elevatorProportion;
    this.wristRotation = wristRotation;
    this.wheelDutyCycle = wheelDutyCycle;
  }

  @Override
  public Dimensionless getElevatorProportion() {
    return elevatorProportion;
  }

  @Override
  public Dimensionless getWheelDutyCycle() {
    return wheelDutyCycle;
  }

  @Override
  public Rotation2d getWristRotation() {
    return wristRotation;
  }
}
