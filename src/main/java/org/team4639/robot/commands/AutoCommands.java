package org.team4639.robot.commands;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.List;
import org.team4639.robot.commands.superstructure.SuperstructureCommand;
import org.team4639.robot.constants.reefscape.TargetPositions;
import org.team4639.robot.robot.Subsystems;
import org.team4639.robot.subsystems.superstructure.Superstructure;
import org.team4639.robot.subsystems.superstructure.SuperstructureSetpoints;

public class AutoCommands {
  public static Command scoreL4(Pose2d pose) {
    return (Subsystems.reefTracker.setCurrentReefPoseCommand(pose))
        .andThen(
            ((Subsystems.drive
                        .defer(() -> DriveCommands.PIDToReef(pose))
                        .deadlineFor(SuperstructureCommands.elevatorReady()))
                    .andThen(
                        Subsystems.drive
                            .defer(DriveCommands::stopWithX)
                            .alongWith(SuperstructureCommands.l4()))
                    .until(Subsystems.wrist::doesNotHaveCoral))
                .andThen(
                    SuperstructureCommands.idle()
                        .until(() -> Subsystems.elevator.getPercentage().lte(Value.of(0.5)))))
        .finallyDo(Subsystems.reefTracker::scoreL4Raw);
  }

  public static Command scoreBarge(Pose2d pose) {
    return (Subsystems.drive
            .defer(() -> DriveCommands.PIDTo(pose))
            .deadlineFor(SuperstructureCommands.algaeStow()))
        .andThen(
            (SuperstructureCommands.barge().withExecutionTimeout(Seconds.of(0.75)).flashOnDone())
                .deadlineFor(Subsystems.drive.defer(DriveCommands::stopWithX)));
  }

  public static Command elevatorReady() {
    return new SuperstructureCommand(
            SuperstructureSetpoints.AUTO_ELEVATOR_L4_READY, "AUTO_ELEVATOR_L4_READY")
        .flashOnDone();
  }

  /** Intended to be used after reef center align */
  // TODO: move this somewhere more appropriate since its used in teleop too
  public static Command algaeIntakeSequence() {
    return Subsystems.drive
        .defer(
            () ->
                DriveCommands.PIDTo(
                    Subsystems.drive
                        .getPose()
                        .nearest(
                            List.of(
                                TargetPositions.REEF_AB.getPose(),
                                TargetPositions.REEF_CD.getPose(),
                                TargetPositions.REEF_EF.getPose(),
                                TargetPositions.REEF_GH.getPose(),
                                TargetPositions.REEF_IJ.getPose(),
                                TargetPositions.REEF_KL.getPose()))
                        .transformBy(
                            new Transform2d(Inches.of(10), Inches.zero(), Rotation2d.kZero))))
        .deadlineFor(SuperstructureCommands.algaeIntake())
        .andThen(
            Subsystems.drive
                .defer(
                    () ->
                        DriveCommands.PIDTo(
                            Subsystems.drive
                                .getPose()
                                .nearest(
                                    List.of(
                                        TargetPositions.REEF_AB.getPose(),
                                        TargetPositions.REEF_CD.getPose(),
                                        TargetPositions.REEF_EF.getPose(),
                                        TargetPositions.REEF_GH.getPose(),
                                        TargetPositions.REEF_IJ.getPose(),
                                        TargetPositions.REEF_KL.getPose()))
                                .transformBy(
                                    new Transform2d(
                                        Inches.of(-5), Inches.zero(), Rotation2d.kZero))))
                .andThen(
                    (DriveCommands.stopWithX().alongWith(SuperstructureCommands.algaeStow()))
                        .until(
                            () -> Superstructure.atPosition(SuperstructureSetpoints.ALGAE_STOW))));
  }
}
