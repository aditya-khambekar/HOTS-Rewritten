package org.team4639.robot.auto;

import static org.team4639.robot.auto.AutoGenerator.Location;
import static org.team4639.robot.auto.AutoGenerator.compileAuto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.team4639.robot.robot.Subsystems;

public final class AutoFactory {
  public static Command RS_F_E_D_C() {
    return compileAuto(
        Location.RS,
        Location.F,
        Location.RHP,
        Location.E,
        Location.RHP,
        Location.D,
        Location.RHP,
        Location.C);
  }

  public static Command RS_F_E_D() {
    return compileAuto(
        Location.RS,
        Location.F,
        Location.RHP,
        Location.E,
        Location.RHP,
        Location.D);
  }

  public static Command RS_E_D_C() {
    return compileAuto(
        Location.RS,
        Location.E,
        Location.RHP,
        Location.D,
        Location.RHP,
        Location.C);
  }

  public static Command LS_I_J_K_L() {
    return compileAuto(
        Location.LS,
        Location.I,
        Location.LHP,
        Location.J,
        Location.LHP,
        Location.K,
        Location.LHP,
        Location.L);
  }

  public static Command LS_I_J_K() {
    return compileAuto(
        Location.LS,
        Location.I,
        Location.LHP,
        Location.J,
        Location.LHP,
        Location.K);
  }

  public static Command LS_J_K_L() {
    return compileAuto(
        Location.LS,
        Location.J,
        Location.LHP,
        Location.K,
        Location.LHP,
        Location.L);
  }

  public static Command MS_G_ALGH_ALGSC1_ALIJ_ALGSC2() {
    return compileAuto(
        Location.MS,
        Location.G,
        Location.ALGH,
        Location.ALGSC1,
        Location.ALIJ,
        Location.ALGSC2);
  }

  public static Command TEST_1MTR() {
    try {
      var path = PathPlannerPath.fromChoreoTrajectory("TEST-1MTR");
      return Commands.runOnce(
              () -> Subsystems.drive.setPose(path.getStartingHolonomicPose().orElse(new Pose2d())))
          .andThen(AutoBuilder.followPath(path));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
