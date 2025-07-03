package org.team4639.robot.commands.pathfinding;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import java.util.List;
import java.util.function.Supplier;

import org.team4639.robot.constants.Targets;
import org.team4639.robot.constants.Targets.TargetPositions;

public class ReefPathLocations {
  public static final ReefPathLocations AB =
      new ReefPathLocations(
          Targets.TargetPositions.REEF_AB,
          Targets.TargetPositions.REEF_A,
          Targets.TargetPositions.REEF_B,
          () -> ReefPathLocations.KL,
          () -> ReefPathLocations.CD,
          "AB");
  public static final ReefPathLocations CD =
      new ReefPathLocations(
          Targets.TargetPositions.REEF_CD,
          Targets.TargetPositions.REEF_C,
          Targets.TargetPositions.REEF_D,
          () -> ReefPathLocations.AB,
          () -> ReefPathLocations.EF,
          "CD");
  public static final ReefPathLocations EF =
      new ReefPathLocations(
          Targets.TargetPositions.REEF_EF,
          Targets.TargetPositions.REEF_E,
          Targets.TargetPositions.REEF_F,
          () -> ReefPathLocations.CD,
          () -> ReefPathLocations.GH,
          "EF");
  public static final ReefPathLocations GH =
      new ReefPathLocations(
          Targets.TargetPositions.REEF_GH,
          Targets.TargetPositions.REEF_G,
          Targets.TargetPositions.REEF_H,
          () -> ReefPathLocations.EF,
          () -> ReefPathLocations.IJ,
          "GH");
  public static final ReefPathLocations IJ =
      new ReefPathLocations(
          Targets.TargetPositions.REEF_IJ,
          Targets.TargetPositions.REEF_I,
          Targets.TargetPositions.REEF_J,
          () -> ReefPathLocations.GH,
          () -> ReefPathLocations.KL,
          "IJ");
  public static final ReefPathLocations KL =
      new ReefPathLocations(
          Targets.TargetPositions.REEF_KL,
          Targets.TargetPositions.REEF_K,
          Targets.TargetPositions.REEF_L,
          () -> ReefPathLocations.IJ,
          () -> ReefPathLocations.AB,
          "KL");

  public static List<ReefPathLocations> entries = List.of(AB, CD, EF, GH, IJ, KL);

  protected TargetPositions center;
  protected TargetPositions left;
  protected TargetPositions right;
  protected Supplier<ReefPathLocations> rightReef;
  protected Supplier<ReefPathLocations> leftReef;
  protected String name;

  public ReefPathLocations(
      TargetPositions center,
      TargetPositions left,
      TargetPositions right,
      Supplier<ReefPathLocations> leftReef,
      Supplier<ReefPathLocations> rightReef,
      String name) {
    this.center = center;
    this.left = left;
    this.right = right;
    this.leftReef = leftReef;
    this.rightReef = rightReef;
    this.name = name;
  }

  public Pose2d getPoseOfStartPath() {
    return center.getPose().transformBy(new Transform2d(-0.9, 0, Rotation2d.kZero));
  }

  public static ReefPathLocations getClosest(Pose2d pose) {
    var nearestPose = pose.nearest(entries.stream().map(x -> x.getPoseOfStartPath()).toList());
    return entries.stream().filter(x -> x.getPoseOfStartPath().equals(nearestPose)).findAny().get();
  }
}
