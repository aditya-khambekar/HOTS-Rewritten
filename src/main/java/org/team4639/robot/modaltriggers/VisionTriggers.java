package org.team4639.robot.modaltriggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.team4639.robot.subsystems.vision.VisionUpdates;

public final class VisionTriggers {
  public static final Trigger ACTIVE = new Trigger(VisionUpdates.getInstance()::isVisionActive);
}
