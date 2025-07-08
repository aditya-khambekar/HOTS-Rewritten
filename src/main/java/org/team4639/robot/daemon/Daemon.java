package org.team4639.robot.daemon;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.measure.MutTime;
import org.team4639.lib.annotation.PackagePrivate;
import org.team4639.robot.constants.TargetPositions;

import java.util.Objects;

import static edu.wpi.first.units.Units.Seconds;

public class Daemon {
  private static volatile Daemon instance;
  private boolean on = false;
  private ChassisSpeeds joystickChassisSpeeds = new ChassisSpeeds();
  private MutTime timeOfLastJoystickUpdate = Seconds.mutable(Double.POSITIVE_INFINITY);

  private TargetPositions HP = TargetPositions.HP_RIGHT;

  public static synchronized Daemon getInstance() {
    return instance = Objects.requireNonNullElseGet(instance, Daemon::new);
  }

  private Daemon() {}

  public void update() {
    if (timeOfLastJoystickUpdate.mut_plus(0.02, Seconds).gte(Seconds.of(0.05))) joystickChassisSpeeds = new ChassisSpeeds();
  }

  public void on() {
    on = true;
  }

  public void off() {
    on = false;
  }

  public boolean isOn() {
    return on;
  }

  public void toggle() {
    on = !on;
  }

  public TargetPositions getHP() {
    return HP;
  }

  @PackagePrivate void setJoystickControlSpeeds(ChassisSpeeds speeds){
    joystickChassisSpeeds = speeds;
    timeOfLastJoystickUpdate.mut_replace(0, Seconds);
  }

  public ChassisSpeeds getJoystickChassisSpeeds() {
    return joystickChassisSpeeds;
  }
}
