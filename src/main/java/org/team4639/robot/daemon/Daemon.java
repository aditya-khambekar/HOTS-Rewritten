package org.team4639.robot.daemon;

import java.util.Objects;

public class Daemon {
    private static volatile Daemon instance;
    private boolean on = false;

    public static synchronized Daemon getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, Daemon::new);
    }

    private Daemon(){
        
    }

    public void update() {

    }

    public void on(){
        on = true;
    }

    public void off(){
        on = false;
    }

    public void toggle() {
        on = !on;
    }
}
