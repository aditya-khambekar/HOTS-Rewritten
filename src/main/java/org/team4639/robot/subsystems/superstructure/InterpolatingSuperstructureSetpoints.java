package org.team4639.robot.subsystems.superstructure;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;
import org.team4639.robot.subsystems.superstructure.superstructurestate.SuperstructureState;

/** Holds interpolating tree maps for superstructure setpoints on all 4 levels of the reef. */
public class InterpolatingSuperstructureSetpoints {
  /**
   * Maps distance from the desired reef branch and the superstructure state required to place a
   * coral there.
   */
  private final InterpolatingTreeMap<Double, SuperstructureState> L1Map =
      new InterpolatingTreeMap<>(
          InverseInterpolator.forDouble(),
          (startValue, endValue, t) ->
              SuperstructureState.of(
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getElevatorProportion().in(Value),
                              endValue.getElevatorProportion().in(Value),
                              t)),
                  Rotation2d.fromDegrees(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWristRotation().getDegrees(),
                              endValue.getWristRotation().getDegrees(),
                              t)),
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWheelDutyCycle().in(Value),
                              endValue.getWheelDutyCycle().in(Value),
                              t))));

  /**
   * Maps distance from the desired reef branch and the superstructure state required to place a
   * coral there.
   */
  private final InterpolatingTreeMap<Double, SuperstructureState> L2Map =
      new InterpolatingTreeMap<>(
          InverseInterpolator.forDouble(),
          (startValue, endValue, t) ->
              SuperstructureState.of(
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getElevatorProportion().in(Value),
                              endValue.getElevatorProportion().in(Value),
                              t)),
                  Rotation2d.fromDegrees(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWristRotation().getDegrees(),
                              endValue.getWristRotation().getDegrees(),
                              t)),
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWheelDutyCycle().in(Value),
                              endValue.getWheelDutyCycle().in(Value),
                              t))));

  /**
   * Maps distance from the desired reef branch and the superstructure state required to place a
   * coral there.
   */
  private final InterpolatingTreeMap<Double, SuperstructureState> L3Map =
      new InterpolatingTreeMap<>(
          InverseInterpolator.forDouble(),
          (startValue, endValue, t) ->
              SuperstructureState.of(
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getElevatorProportion().in(Value),
                              endValue.getElevatorProportion().in(Value),
                              t)),
                  Rotation2d.fromDegrees(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWristRotation().getDegrees(),
                              endValue.getWristRotation().getDegrees(),
                              t)),
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWheelDutyCycle().in(Value),
                              endValue.getWheelDutyCycle().in(Value),
                              t))));

  /**
   * Maps distance from the desired reef branch and the superstructure state required to place a
   * coral there.
   */
  private final InterpolatingTreeMap<Double, SuperstructureState> L4Map =
      new InterpolatingTreeMap<>(
          InverseInterpolator.forDouble(),
          (startValue, endValue, t) ->
              SuperstructureState.of(
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getElevatorProportion().in(Value),
                              endValue.getElevatorProportion().in(Value),
                              t)),
                  Rotation2d.fromDegrees(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWristRotation().getDegrees(),
                              endValue.getWristRotation().getDegrees(),
                              t)),
                  Value.of(
                      Interpolator.forDouble()
                          .interpolate(
                              startValue.getWheelDutyCycle().in(Value),
                              endValue.getWheelDutyCycle().in(Value),
                              t))));
}
