package org.team4639.lib.util;

import static edu.wpi.first.units.Units.Meter;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.units.measure.Distance;

public class PoseUtil {

  public static Distance getDistance(Pose2d one, Pose2d other) {
    return Meter.of(one.getTranslation().getDistance(other.getTranslation()));
  }
}
