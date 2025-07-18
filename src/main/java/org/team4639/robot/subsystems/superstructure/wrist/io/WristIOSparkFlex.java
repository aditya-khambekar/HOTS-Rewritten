package org.team4639.robot.subsystems.superstructure.wrist.io;

import static edu.wpi.first.units.Units.*;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WristIOSparkFlex extends WristIO {
  SparkFlex sparkFlex;
  ProfiledPIDController wristPIDController;

  public WristIOSparkFlex(int ID) {
    sparkFlex = new SparkFlex(ID, SparkLowLevel.MotorType.kBrushless);

    wristPIDController =
        new ProfiledPIDController(40, 0, 0, new TrapezoidProfile.Constraints(60, 180));

    SmartDashboard.putData("Wrist PID Controller", wristPIDController);

    // TODO: reset PID
  }

  @Override
  public void updateInputs(WristIOInputs inputs) {
    inputs.motorCurrent.mut_replace(Amps.of(sparkFlex.getOutputCurrent()));
    inputs.motorPosition.mut_replace(Rotations.of(sparkFlex.getAbsoluteEncoder().getPosition()));
    inputs.motorTemperature.mut_replace(Celsius.of(sparkFlex.getMotorTemperature()));
    inputs.motorVelocity.mut_replace(
        RotationsPerSecond.of(sparkFlex.getAbsoluteEncoder().getVelocity() / 60.));
  }

  @Override
  public void setDutyCycleOutput(Dimensionless percent) {
    sparkFlex.set(percent.in(Value));
  }

  @Override
  public void setPosition(Angle position) {
    wristPIDController.setGoal(position.in(Rotations));
    sparkFlex.set(wristPIDController.calculate(sparkFlex.getAbsoluteEncoder().getPosition()));
  }

  @Override
  public void setVoltage(Voltage voltage) {
    sparkFlex.setVoltage(voltage);
  }
}
