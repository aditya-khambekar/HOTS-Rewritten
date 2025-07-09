package org.team4639.robot.constants.reefscape;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;

/** Holds pose utilities relevant to our robot's interactions with the field */
public class FieldPoseUtil {
  static double reefLeftDistance = 0.2;
  static double reefRightDistance = 0.2;

  public static Pose2d ReefRelativeRightOf(Pose2d pose) {
    return pose.transformBy(new Transform2d(0, -reefRightDistance, Rotation2d.kZero));
  }

  public static Pose2d ReefRelativeLeftOf(Pose2d pose) {
    return pose.transformBy(new Transform2d(0, reefLeftDistance, Rotation2d.kZero));
  }
}
