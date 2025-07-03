package org.team4639.robot.statemachine;

import org.team4639.lib.statebased.State;

public class VisionDemoStates {
    public static State DEMO_ON;
    public static State DEMO_OFF;

    public void init() {
        DEMO_ON = new State("DEMO_ON");
        DEMO_OFF = new State("DEMO_OFF");

        //TODO: these
    }
}
