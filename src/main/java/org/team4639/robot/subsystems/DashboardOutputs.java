package org.team4639.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team4639.robot.constants.robot.Controls;
import org.team4639.robot.robot.Subsystems;

public final class DashboardOutputs extends SubsystemBase {
  public DashboardOutputs() {
    SmartDashboard.putString("Reef Level", "NONE");
  }

  public Command displayUpcomingReefLevel() {
    return run(() -> {
          var recommended = Subsystems.reefTracker.getRecommendedLevel();
          var level = "NONE";
          if (Controls.L4_CORAL.getAsBoolean()) level = "L4";
          else if (Controls.L3_CORAL.getAsBoolean()) level = "L3";
          else if (Controls.L2_CORAL.getAsBoolean()) level = "L2";
          else if (Controls.L1_CORAL.getAsBoolean()) level = "L1";
          else if (recommended != 0) level = "L" + recommended;
          SmartDashboard.putString("Reef Level", level);
        })
        .finallyDo(() -> SmartDashboard.putString("Reef Level", "NONE"));
  }

  public int upcomingReefLevel() {
    var recommended = Subsystems.reefTracker.getRecommendedLevel();
    if (Controls.L4_CORAL.getAsBoolean()) return 4;
    else if (Controls.L3_CORAL.getAsBoolean()) return 3;
    else if (Controls.L2_CORAL.getAsBoolean()) return 2;
    else if (Controls.L1_CORAL.getAsBoolean()) return 1;
    else return recommended;
  }
}
