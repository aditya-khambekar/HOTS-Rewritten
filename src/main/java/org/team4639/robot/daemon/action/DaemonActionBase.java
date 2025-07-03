package org.team4639.robot.daemon.action;

public abstract class DaemonActionBase {
    public abstract void update();
    public abstract void init();
    public abstract boolean isFinished();
}
