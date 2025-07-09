package org.team4639.robot.subsystems.superstructure;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Rotation2d;
import org.team4639.robot.subsystems.superstructure.superstructurestate.SuperstructureState;

public final class SuperstructureSetpoints {
  public static final SuperstructureState IDLE =
      SuperstructureState.of(Value.of(0.04), Rotation2d.fromDegrees(150), Value.zero());

  public static final SuperstructureState CORAL_STOW =
      SuperstructureState.of(Value.of(0.04), Rotation2d.fromDegrees(150), Value.zero());

  public static final SuperstructureState HP =
      SuperstructureState.of(Value.of(0.048), Rotation2d.fromDegrees(55), Value.zero());

  public static final SuperstructureState PROCESSOR =
      SuperstructureState.of(Value.of(0.15), Rotation2d.fromDegrees(100), Value.zero());

  public static final SuperstructureState ELEVATOR_READY =
      SuperstructureState.of(Value.of(0.496), Rotation2d.fromDegrees(200), Value.zero());

  public static final SuperstructureState AUTO_ELEVATOR_L4_READY =
      SuperstructureState.of(Value.of(0.7), Rotation2d.fromDegrees(230), Value.zero());

  public static final SuperstructureState L1 =
      SuperstructureState.of(Value.of(0.15), Rotation2d.fromDegrees(215), Value.zero());

  public static final SuperstructureState L2 =
      SuperstructureState.of(Value.of(0.2893), Rotation2d.fromDegrees(215), Value.zero());

  public static final SuperstructureState L3 =
      SuperstructureState.of(Value.of(0.496), Rotation2d.fromDegrees(215), Value.zero());

  public static final SuperstructureState L4 =
      SuperstructureState.of(Value.of(0.85635), Rotation2d.fromDegrees(230), Value.zero());

  public static final SuperstructureState L1_NO_OUTTAKE =
      SuperstructureState.of(Value.of(0.15), Rotation2d.fromDegrees(215), Value.zero());

  public static final SuperstructureState L2_NO_OUTTAKE =
      SuperstructureState.of(Value.of(0.2893), Rotation2d.fromDegrees(215), Value.zero());

  public static final SuperstructureState L3_NO_OUTTAKE =
      SuperstructureState.of(Value.of(0.496), Rotation2d.fromDegrees(215), Value.zero());

  public static final SuperstructureState L4_NO_OUTTAKE =
      SuperstructureState.of(Value.of(0.85635), Rotation2d.fromDegrees(230), Value.zero());

  public static final SuperstructureState L2_ALGAE =
      SuperstructureState.of(Value.of(0.32), Rotation2d.fromDegrees(135), Value.zero());

  public static final SuperstructureState L3_ALGAE =
      SuperstructureState.of(Value.of(0.8), Rotation2d.fromDegrees(135), Value.zero());

  public static final SuperstructureState ALGAE_STOW =
      SuperstructureState.of(Value.of(0.32), Rotation2d.fromDegrees(135), Value.zero());

  public static final SuperstructureState BARGE =
      SuperstructureState.of(Value.of(0.95), Rotation2d.fromDegrees(150), Value.zero());

  public static final SuperstructureState GROUND_INTAKE =
      SuperstructureState.of(Value.of(0.0), Rotation2d.fromDegrees(135), Value.zero());

  public static final SuperstructureState HOMING =
      SuperstructureState.of(Value.of(0.0), Rotation2d.fromDegrees(135), Value.zero());

  public static final SuperstructureState HP_LOWER =
      SuperstructureState.of(Value.of(0.048), Rotation2d.fromDegrees(40), Value.zero());

  public static final SuperstructureState HOMING_READY =
      SuperstructureState.of(Value.of(0.1), Rotation2d.fromDegrees(135), Value.zero());

  public static final SuperstructureState REJECT_CORAL =
      SuperstructureState.of(Value.of(0), Rotation2d.fromDegrees(150), Value.zero());

  public static final SuperstructureState REJECT_ALGAE =
      SuperstructureState.of(Value.of(0), Rotation2d.fromDegrees(135), Value.zero());
}
