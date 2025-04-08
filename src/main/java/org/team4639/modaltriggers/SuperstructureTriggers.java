package org.team4639.modaltriggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public class SuperstructureTriggers {
  // TODO: based on new end effector design, how will this be determined
  public static Trigger hasCoral = new Trigger(() -> false);

  /** Triggers automatic motion to the intake setpoint. */
  public static Trigger intake =
      hasCoral.negate().and(DriveTriggers.closeToLeftStation.or(DriveTriggers.closeToRightStation));

  /** Triggers automatically raising the elevator to a halfway point in preparation for scoring. */
  public static Trigger raiseElevator =
      hasCoral.and(
          (DriveTriggers.closeToLeftStation.or(DriveTriggers.closeToRightStation)).negate());
}
