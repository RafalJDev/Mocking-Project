package library.fluentconditionals;

import java.util.function.BooleanSupplier;

public class FluentConditionals {
  
  public static Runnable doNothing = () -> {
  };
  
  private boolean condition;
  
  private FluentConditionals(boolean condition) {
    this.condition = condition;
  }
  
  public static FluentConditionals when(boolean b) {
    return new FluentConditionals(b);
  }
  
  public static FluentConditionals when(BooleanSupplier runnable) {
    
    return new FluentConditionals(runnable.getAsBoolean());
  }
  
  public FluentConditionals then(Runnable runnable) {
    if (condition) {
      runnable.run();
    }
    return this;
  }
  
  public void orElse(Runnable runnable) {
    if (!condition) {
      runnable.run();
    }
  }
  
  public boolean isCondition() {
    return condition;
  }
}
