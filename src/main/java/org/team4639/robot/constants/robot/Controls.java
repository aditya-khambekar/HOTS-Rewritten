package org.team4639.robot.constants.robot;

import static org.team4639.robot.robot.RobotContainer.driver;
import static org.team4639.robot.robot.RobotContainer.operator;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.team4639.robot.constants.reefscape.FieldPoseUtil;
import org.team4639.robot.statemachine.competition.ReefscapeStates;
import org.team4639.robot.statemachine.demo.VisionDemo;

public final class Controls {
  public static final Trigger EMERGENCY = driver.y();

  public static final Trigger ALIGN_LEFT = driver.povLeft();
  public static final Trigger ALIGN_RIGHT = driver.povRight();

  public static final Trigger RESET_GYRO = driver.ab();

  public static final Trigger L1_CORAL = driver.leftTrigger().or(operator.leftTrigger());
  public static final Trigger L2_CORAL = driver.leftBumper().or(operator.leftBumper());
  public static final Trigger L3_CORAL = driver.rightBumper().or(operator.leftBumper());
  public static final Trigger L4_CORAL = driver.rightTrigger().or(operator.rightTrigger());

  public static final Trigger PROCESSOR = driver.leftTrigger().or(operator.leftTrigger());
  public static final Trigger ALGAE_INTAKE =
      driver
          .leftBumper()
          .or(operator.leftBumper())
          .or(driver.rightBumper())
          .or(operator.leftBumper());

  public static final Trigger BARGE_SCORE = driver.rightTrigger().or(operator.rightTrigger());

  public static final Trigger MICRO_ADJUSTMENTS_OUTTAKE =
      ReefscapeStates.getInstance().MICROADJUSTMENTS.and(operator.a());

  // TODO: figure out what to do with these
  // Microadjustment triggers
  public static final Trigger ELEVATOR_UP = operator.povUp();
  public static final Trigger ELEVATOR_DOWN = operator.povDown();

  public static final Trigger WRIST_UP = operator.povLeft();
  public static final Trigger WRIST_DOWN = operator.povRight();

  private static final Trigger HP_ALIGN = driver.x();

  public static final Trigger LEFT_HP = HP_ALIGN.and(FieldPoseUtil::closerToLeftStation);
  public static final Trigger RIGHT_HP = HP_ALIGN.and(FieldPoseUtil::closerToRightStation);

  public static final Trigger DEFENSE_TOGGLE = driver.rightStick();

  public static final class VisionDemoControls {
    public static final Trigger DEMO_ON =
        driver.x().and(VisionDemo.DEMO_ON.getTrigger().or(VisionDemo.DEMO_OFF.getTrigger()));
  }
}
