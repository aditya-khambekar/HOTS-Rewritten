package org.team4639.robot.statemachine.routine;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import org.team4639.robot.subsystems.superstructure.superstructurestate.SuperstructureState;

import java.util.HashSet;
import java.util.Set;

import static edu.wpi.first.units.Units.Value;

public class InterpolatingStructureSetpointRoutine {

    private static Set<String> entries = new HashSet<>();

    private static String getStateConstructor(SuperstructureState superstructureState) {
        return "SuperstructureState.of(Value.of("
                + superstructureState.getElevatorProportion().in(Value)
                + "), Rotation2d.fromDegrees("
                + superstructureState.getWristRotation().getDegrees()
                + "), Value.of("
                + superstructureState.getWheelDutyCycle().in(Value)
                + "))";
    }

    private static String getMapEntry(String mapName, double key, SuperstructureState superstructureState) {
        return mapName + ".put(" + key + ", " + getStateConstructor(superstructureState) + ")";
    }

    public static void _registerEntry(int level, double distance, SuperstructureState superstructureState) {
        entries.add(getMapEntry("L"+level+"Map", distance, superstructureState));
    }

    public static InstantCommand registerEntry(int level, double distance, SuperstructureState superstructureState){
        return new InstantCommand(() -> _registerEntry(level, distance, superstructureState));
    }
}
