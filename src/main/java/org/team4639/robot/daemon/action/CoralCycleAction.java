package org.team4639.robot.daemon.action;

import org.team4639.lib.statebased.StateMachine;

public class CoralCycleAction extends DaemonActionBase {

  @Override
  public void update() {
    switch (StateMachine.getState().getName()) {
      case "IDLE":
        break;
      case "CORAL_STOW":
        break;
      case "HP_LEFT", "HP_RIGHT", "INTAKE_NODIR":
        break;
      case "HP_LOWER":
        break;
      case "PATHFIND_TO_REEF":
        break;
      case "CHOOSE_CORAL_LEVEL":
        break;
      case "L1_CORAL_SCORE", "L2_CORAL_SCORE", "L3_CORAL_SCORE", "L4_CORAL_SCORE":
        break;
      case "HOMING_READY", "HOMING":
        break;
    }
  }

  @Override
  public void init() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'init'");
  }

  @Override
  public boolean isFinished() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isFinished'");
  }
}
