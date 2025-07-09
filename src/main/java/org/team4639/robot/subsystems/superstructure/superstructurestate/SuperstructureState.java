package org.team4639.robot.subsystems.superstructure.superstructurestate;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Dimensionless;

public abstract class SuperstructureState {
  public abstract Dimensionless getElevatorProportion();

  public abstract Dimensionless getWheelDutyCycle();

  public abstract Rotation2d getWristRotation();

  public static SuperstructureState of(
      Dimensionless elevatorProportion, Rotation2d wristRotation, Dimensionless wheelDutyCycle) {
    return new ImmutSuperstructureState(elevatorProportion, wristRotation, wheelDutyCycle);
  }

  public static MutSuperstructureState mutable(
      Dimensionless elevatorProportion, Rotation2d wristRotation, Dimensionless wheelDutyCycle) {
    return new MutSuperstructureState(elevatorProportion, wristRotation, wheelDutyCycle);
  }

  public MutSuperstructureState mutableCopy() {
    return mutable(getElevatorProportion(), getWristRotation(), getWheelDutyCycle());
  }
}
