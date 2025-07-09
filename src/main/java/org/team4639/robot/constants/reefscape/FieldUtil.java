package org.team4639.robot.constants.reefscape;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.util.Units;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Holds constants and methods that define our robot-specific interactions with the field. */
public final class FieldUtil {
  // change this to tune how far the align tries to go from the reef face
  protected static final Transform2d fromReef =
      new Transform2d(Units.inchesToMeters(29.25), 0, Rotation2d.k180deg);
  protected static final Transform2d fromProcessor =
      new Transform2d(Units.inchesToMeters(25), 0, Rotation2d.k180deg);
  // change this to tune how far the align tries to go from the intake station
  protected static final Transform2d fromCoralStation =
      new Transform2d(Units.inchesToMeters(16), 0, Rotation2d.kZero);
  protected static final Transform2d fromBarge =
      new Transform2d(Units.inchesToMeters(-39), 0, Rotation2d.kZero);

  public static Pose2d getClosestBranchPosition(Pose2d currentPose) {
    List<Pose2d> branches = new ArrayList<>();
    for (Map<FieldConstants.ReefLevel, Pose2d> x : FieldConstants.Reef.branchPositions2d) {
      branches.addAll(x.values());
    }

    return currentPose.nearest(branches);
  }

  public static Rotation2d getRotationToClosestBranchPosition(Pose2d currentPose) {
    var closestBranchPosition = getClosestBranchPosition(currentPose);
    return Rotation2d.fromRadians(
        Math.atan2(
            closestBranchPosition.getY() - currentPose.getY(),
            closestBranchPosition.getX() - currentPose.getX()));
  }
}
