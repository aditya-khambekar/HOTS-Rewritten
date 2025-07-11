package org.team4639.robot.modaltriggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.team4639.robot.constants.reefscape.FieldConstants;
import org.team4639.robot.robot.Subsystems;

public final class DriveTriggers {
  public static final Trigger CLOSE_TO_LEFT_STATION =
      new Trigger(
              () ->
                  Subsystems.drive
                          .getPose()
                          .getTranslation()
                          .getDistance(FieldConstants.HPStation.leftCenterFace.getTranslation())
                      < .75)
          .and(VisionTriggers.ACTIVE);

  public static final Trigger CLOSE_TO_RIGHT_STATION =
      new Trigger(
              () ->
                  Subsystems.drive
                          .getPose()
                          .getTranslation()
                          .getDistance(FieldConstants.HPStation.rightCenterFace.getTranslation())
                      < .75)
          .and(VisionTriggers.ACTIVE);
}
