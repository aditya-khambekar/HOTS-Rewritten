package org.team4639.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.team4639.robot.constants.reefscape.TargetPositions;

/** Tracks reef. */
public final class ReefTracker extends SubsystemBase {
  private Map<Pose2d, boolean[]> reefs = new HashMap<Pose2d, boolean[]>();
  private Pose2d currentReefPose = new Pose2d();

  public ReefTracker() {}

  public void scoreL1Raw() {
    reefs.get(currentReefPose)[0] = true;
  }

  public void scoreL2Raw() {
    reefs.get(currentReefPose)[1] = true;
  }

  public void scoreL3Raw() {
    reefs.get(currentReefPose)[2] = true;
  }

  public void scoreL4Raw() {
    reefs.get(currentReefPose)[3] = true;
  }

  public int getRecommendedLevel() {
    var reef = reefs.get(currentReefPose);
    if (!reef[3]) return 4;
    if (!reef[2]) return 3;
    if (!reef[1]) return 2;
    if (!reef[0]) return 1;
    return 0;
  }

  public void setCurrentReefPose(Pose2d pose) {
    currentReefPose = pose;
  }

  public Command setCurrentReefPoseCommand(Pose2d pose) {
    return runOnce(() -> setCurrentReefPose(pose));
  }

  public void resetReefTracker() {
    reefs.put(TargetPositions.REEF_A.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_B.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_C.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_D.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_E.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_F.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_G.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_H.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_I.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_J.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_K.getPose(), Arrays.copyOf(new boolean[0], 4));
    reefs.put(TargetPositions.REEF_L.getPose(), Arrays.copyOf(new boolean[0], 4));
  }

  @Override
  public void periodic() {
    SmartDashboard.putBooleanArray(
        "Current Reef", reefs.getOrDefault(currentReefPose, new boolean[] {false}));
  }
}
