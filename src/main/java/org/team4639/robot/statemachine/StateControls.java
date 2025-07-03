package org.team4639.robot.statemachine;

import org.team4639.lib.statebased.State;
import org.team4639.lib.statebased.StateFactory;

/**
 * This is where we can control which state machine is being used and the teleop and auto start
 * behavior.
 */
public class StateControls {

  /**
   * There should only be one set of states initialized here. All states intended to be used in the
   * same run should be stored in the same class and thus can be initialized in one method.
   */
  public static void initStateMachine() {
    ReefscapeStates.init();
  }

  public static State getAutoStartState() {
    return StateFactory.none();
  }

  public static State getTeleopStartState() {
    return ReefscapeStates.determineState();
  }
}
