package org.team4639.robot.constants.robot;

import org.team4639.lib.annotation.Untuned;

// TODO: get IDS
public final class IDs {
  public static final class Drivetrain {
    // Front Left
    public static final int FRONT_LEFT_DRIVE = 7;
    public static final int FRONT_LEFT_AZIMUTH = 8;
    public static final int FRONT_LEFT_ENCODER = 12;
    // Front Right
    public static final int FRONT_RIGHT_DRIVE = 5;
    public static final int FRONT_RIGHT_AZIMUTH = 6;
    public static final int FRONT_RIGHT_ENCODER = 11;
    // Back Left
    public static final int BACK_LEFT_DRIVE = 3;
    public static final int BACK_LEFT_AZIMUTH = 4;
    public static final int BACK_LEFT_ENCODER = 10;
    // Back Right
    public static final int BACK_RIGHT_DRIVE = 1;
    public static final int BACK_RIGHT_AZIMUTH = 2;
    public static final int BACK_RIGHT_ENCODER = 9;
  }

  public static final class Superstructure {
    public static final int ELEVATOR_LEFT_FOLLOWER = 21;
    public static final int ELEVATOR_RIGHT_LEADER = 22;
    @Untuned public static final int ROLLER = 23;
    @Untuned public static final int WRIST = 24;
    @Untuned public static final int WRIST_LASERCAN = 9;
  }
}
