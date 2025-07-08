package org.team4639.lib.control.zieglernichols;

import org.team4639.lib.functional.DoubleConsumer2;
import org.team4639.lib.functional.DoubleSupplier2;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

/**
 * Performs Ziegler-Nichols PID Tuning on a system by oscillating between two setpoints,
 * gradually increasing the kP value until a consistent oscillation is reached. It then calculates and
 * outputs optimal PID gains using a lookup table.
 */
public class ZieglerNicholsPIDRoutine {
    double setpoint1;
    double setpoint2;
    double kpStep;
    DoubleConsumer outputConsumer;
    DoubleSupplier measurementSupplier;

    public ZieglerNicholsPIDRoutine(double setpoint1, double setpoint2, double kpStep, DoubleConsumer outputConsumer, DoubleSupplier measurementSupplier) {

    }
}
