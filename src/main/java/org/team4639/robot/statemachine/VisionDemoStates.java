package org.team4639.robot.statemachine;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import org.team4639.lib.statebased.State;
import org.team4639.lib.unit.Units2;
import org.team4639.robot.commands.DriveCommands;
import org.team4639.robot.commands.SuperstructureCommands;
import org.team4639.robot.constants.Controls;
import org.team4639.robot.constants.TargetPositions;

public class VisionDemoStates {
  public static State DEMO_ON;
  public static State DEMO_OFF;

  public void init() {
    DEMO_ON =
        new State("DEMO_ON")
            .whileTrue(
                SuperstructureCommands.IDLE,
                DriveCommands.PIDToUnending(
                    TargetPositions.REEF_AB
                        .getPose()
                        .transformBy(
                            new Transform2d(
                                Units2.inchesToMeters.convert(-36), 0, Rotation2d.kZero))))
            .withEndCondition(Controls.VisionDemoControls.DEMO_ON.negate(), () -> DEMO_OFF);
    DEMO_OFF =
        new State("DEMO_OFF")
            .whileTrue(SuperstructureCommands.IDLE)
            .onTrigger(Controls.VisionDemoControls.DEMO_ON, () -> DEMO_ON);
  }
}
