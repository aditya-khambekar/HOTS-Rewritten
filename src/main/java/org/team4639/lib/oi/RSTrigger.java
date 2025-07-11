package org.team4639.lib.oi;

import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.function.BooleanSupplier;

public class RSTrigger extends Trigger {

  /**
   * Creates a new trigger based on the given condition.
   *
   * @param loop The loop instance that polls this trigger.
   * @param condition the condition represented by this trigger
   */
  public RSTrigger(EventLoop loop, BooleanSupplier condition) {
    super(loop, condition);
  }

  /**
   * Creates a new trigger based on the given condition.
   *
   * <p>Polled by the default scheduler button loop.
   *
   * @param condition the condition represented by this trigger
   */
  public RSTrigger(BooleanSupplier condition) {
    super(condition);
  }

  public Trigger andNot(BooleanSupplier trigger) {
    return this.and(() -> !trigger.getAsBoolean());
  }

  public Trigger orNot(BooleanSupplier trigger) {
    return this.or(() -> !trigger.getAsBoolean());
  }

  public RSTrigger of(BooleanSupplier condition) {
    return new RSTrigger(condition);
  }
}
