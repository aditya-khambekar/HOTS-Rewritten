package org.team4639.robot.modaltriggers;

import edu.wpi.first.math.geometry.Quaternion;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.team4639.robot.robot.RobotContainer;

public class IOTriggers {
  public static final Trigger JOYSTICK_MOVEMENT =
      new Trigger(
          () ->
              new Quaternion(
                          RobotContainer.driver.getLeftY(),
                          RobotContainer.driver.getLeftX(),
                          RobotContainer.driver.getRightY(),
                          RobotContainer.driver.getRightX())
                      .norm()
                  != 0);
}
