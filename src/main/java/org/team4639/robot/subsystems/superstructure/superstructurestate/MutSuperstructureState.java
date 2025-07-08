package org.team4639.robot.subsystems.superstructure.superstructurestate;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Dimensionless;

public class MutSuperstructureState extends SuperstructureState {
    private Dimensionless elevatorProportion;
    private Rotation2d wristRotation;
    private Dimensionless wheelDutyCycle;

    public MutSuperstructureState(Dimensionless elevatorProportion, Rotation2d wristRotation, Dimensionless wheelDutyCycle) {
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

    public void setElevatorProportion(Dimensionless elevatorProportion) {
        this.elevatorProportion = elevatorProportion;
    }

    public void setWristRotation(Rotation2d wristRotation) {
        this.wristRotation = wristRotation;
    }

    public void setWheelDutyCycle(Dimensionless wheelDutyCycle) {
        this.wheelDutyCycle = wheelDutyCycle;
    }
}
