package org.team4639.robot.constants.reefscape;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import org.team4639.lib.util.AllianceFlipUtil;

public enum TargetPositions {
  REEF_AB(FieldConstants.Reef.centerFaces[0].transformBy((FieldConstants.fromReef))),
  REEF_CD(FieldConstants.Reef.centerFaces[5].transformBy((FieldConstants.fromReef))),
  REEF_EF(FieldConstants.Reef.centerFaces[4].transformBy((FieldConstants.fromReef))),
  REEF_GH(FieldConstants.Reef.centerFaces[3].transformBy((FieldConstants.fromReef))),
  REEF_IJ(FieldConstants.Reef.centerFaces[2].transformBy((FieldConstants.fromReef))),
  REEF_KL(FieldConstants.Reef.centerFaces[1].transformBy((FieldConstants.fromReef))),

  REEF_A(ReefscapePoseUtil.ReefRelativeLeftOf(REEF_AB.getPose())),
  REEF_B(ReefscapePoseUtil.ReefRelativeRightOf(REEF_AB.getPose())),
  REEF_C(ReefscapePoseUtil.ReefRelativeLeftOf(REEF_CD.getPose())),
  REEF_D(ReefscapePoseUtil.ReefRelativeRightOf(REEF_CD.getPose())),
  REEF_E(ReefscapePoseUtil.ReefRelativeLeftOf(REEF_EF.getPose())),
  REEF_F(ReefscapePoseUtil.ReefRelativeRightOf(REEF_EF.getPose())),
  REEF_G(ReefscapePoseUtil.ReefRelativeLeftOf(REEF_GH.getPose())),
  REEF_H(ReefscapePoseUtil.ReefRelativeRightOf(REEF_GH.getPose())),
  REEF_I(ReefscapePoseUtil.ReefRelativeLeftOf(REEF_IJ.getPose())),
  REEF_J(ReefscapePoseUtil.ReefRelativeRightOf(REEF_IJ.getPose())),
  REEF_K(ReefscapePoseUtil.ReefRelativeLeftOf(REEF_KL.getPose())),
  REEF_L(ReefscapePoseUtil.ReefRelativeRightOf(REEF_KL.getPose())),

  PROCESSOR(FieldConstants.Processor.centerFace.transformBy(FieldConstants.fromProcessor)),

  HP_LEFT(FieldConstants.CoralStation.leftCenterFace.transformBy(FieldConstants.fromCoralStation)),
  HP_RIGHT(
      FieldConstants.CoralStation.rightCenterFace.transformBy(FieldConstants.fromCoralStation)),

  BARGE_FARCAGE(
      new Pose2d(FieldConstants.Barge.farCage, Rotation2d.kZero)
          .transformBy(FieldConstants.fromBarge)),
  BARGE_MIDDLECAGE(
      new Pose2d(FieldConstants.Barge.middleCage, Rotation2d.kZero)
          .transformBy(FieldConstants.fromBarge)),
  BARGE_CLOSECAGE(
      new Pose2d(FieldConstants.Barge.closeCage, Rotation2d.kZero)
          .transformBy(FieldConstants.fromBarge)),

  // Opponent positions
  OPP_REEF_AB(AllianceFlipUtil.flip(REEF_AB.Pose)),
  OPP_REEF_CD(AllianceFlipUtil.flip(REEF_CD.Pose)),
  OPP_REEF_EF(AllianceFlipUtil.flip(REEF_EF.Pose)),
  OPP_REEF_GH(AllianceFlipUtil.flip(REEF_GH.Pose)),
  OPP_REEF_IJ(AllianceFlipUtil.flip(REEF_IJ.Pose)),
  OPP_REEF_KL(AllianceFlipUtil.flip(REEF_KL.Pose)),

  OPP_REEF_A(AllianceFlipUtil.flip(REEF_A.Pose)),
  OPP_REEF_B(AllianceFlipUtil.flip(REEF_B.Pose)),
  OPP_REEF_C(AllianceFlipUtil.flip(REEF_C.Pose)),
  OPP_REEF_D(AllianceFlipUtil.flip(REEF_D.Pose)),
  OPP_REEF_E(AllianceFlipUtil.flip(REEF_E.Pose)),
  OPP_REEF_F(AllianceFlipUtil.flip(REEF_F.Pose)),
  OPP_REEF_G(AllianceFlipUtil.flip(REEF_G.Pose)),
  OPP_REEF_H(AllianceFlipUtil.flip(REEF_H.Pose)),
  OPP_REEF_I(AllianceFlipUtil.flip(REEF_I.Pose)),
  OPP_REEF_J(AllianceFlipUtil.flip(REEF_J.Pose)),
  OPP_REEF_K(AllianceFlipUtil.flip(REEF_K.Pose)),
  OPP_REEF_L(AllianceFlipUtil.flip(REEF_L.Pose)),

  OPP_PROCESSOR(AllianceFlipUtil.flip(PROCESSOR.Pose)),

  OPP_HP_LEFT(AllianceFlipUtil.flip(HP_LEFT.Pose)),
  OPP_HP_RIGHT(AllianceFlipUtil.flip(HP_RIGHT.Pose)),

  OPP_BARGE_FARCAGE(AllianceFlipUtil.flip(BARGE_FARCAGE.Pose)),
  OPP_BARGE_MIDDLECAGE(AllianceFlipUtil.flip(BARGE_MIDDLECAGE.Pose)),
  OPP_BARGE_CLOSECAGE(AllianceFlipUtil.flip(BARGE_CLOSECAGE.Pose));

  TargetPositions(Pose2d pose, Pose2d leftPose, Pose2d rightPose) {
    this.leftPose = leftPose;
    this.rightPose = rightPose;
    this.Pose = pose;
  }

  TargetPositions(Pose2d pose) {
    this(
        pose,
        ReefscapePoseUtil.ReefRelativeLeftOf(pose),
        ReefscapePoseUtil.ReefRelativeRightOf(pose));
  }

  public Pose2d getPose() {
    return Pose;
  }

  protected final Pose2d Pose;
  public final Pose2d leftPose, rightPose;
}
