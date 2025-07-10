package org.team4639.lib.oi;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Button {
    private final Trigger trigger;
    private final double HOLD_THRESHOLD_MS = 300;

    private boolean wasPressed = false;
    private boolean isCurrentlyHeld = false;
    private boolean wasHeld = false;
    private boolean pressConsumed = false;
    private double pressStartTime = 0;

    private double pressElapsedTime = 0;

    public Button(Trigger trigger) {
        this.trigger = trigger;
        new Trigger(() -> true).whileTrue(Commands.run(this::update));
    }

    public void update() {
        boolean currentlyPressed = trigger.getAsBoolean();
        double currentTime = Timer.getFPGATimestamp() * 1000;

        //start press
        if (currentlyPressed && !wasPressed) {
            pressStartTime = currentTime;
            isCurrentlyHeld = false;
            wasHeld = false;
        }

        if (currentlyPressed && !isCurrentlyHeld) {
            double pressDuration = currentTime - pressStartTime;
            if (pressDuration > HOLD_THRESHOLD_MS) {
                isCurrentlyHeld = true;
            }
        }

        if (isCurrentlyHeld){
            wasHeld = true;
        }

        isCurrentlyHeld = false;

        wasPressed = currentlyPressed;

        if (trigger.getAsBoolean()) {
            pressElapsedTime = currentTime - pressStartTime;
        }
    }

    public boolean isPressed() {
        return !trigger.getAsBoolean() && wasPressed && !wasHeld;
    }

    public boolean isHeld() {
        return trigger.getAsBoolean() && wasHeld;
    }
}
