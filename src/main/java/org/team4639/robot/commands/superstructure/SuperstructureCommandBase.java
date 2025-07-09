package org.team4639.robot.commands.superstructure;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public abstract class SuperstructureCommandBase extends Command {
  public abstract SuperstructureCommandState getCommandState();

  public abstract String getName();

  @Override
  public void execute() {
    super.execute();
    SmartDashboard.putString("Superstructure State", getCommandState().toString());
    SmartDashboard.putString("Superstructure Command", getName());
  }
}
