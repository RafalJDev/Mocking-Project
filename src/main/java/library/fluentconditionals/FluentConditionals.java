package library.fluentconditionals;

import library.FluentConditionalsWrapper;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class FluentConditionals {
  
  public static Runnable doNothing = () -> {
  };
  
  private IntSupplier intSupplier;
  
  private boolean condition;
  
  protected FluentConditionals(boolean condition) {
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
  
  public int orElse(IntSupplier intSupplier) {
    if (!condition) {
      return intSupplier.getAsInt();
    }
    return this.intSupplier.getAsInt();
  }
  
  public int orElse(int input) {
    if (!condition) {
      return input;
    }
    return intSupplier.getAsInt();
  }
  
  public void orElseThrow(RuntimeException e) {
    if (!condition) {
      throw e;
    }
  }
  
  public void orElseThrow(Supplier<RuntimeException> supplier) {
    if (!condition) {
      throw supplier.get();
    }
  }
  
  public FluentConditionalsWrapper thenReturn(IntSupplier intSupplier) {
    this.intSupplier = intSupplier;
    
    return new FluentConditionalsWrapper(this);
  }
  
  public boolean isCondition() {
    return condition;
  }
  
  public IntSupplier getIntSupplier() {
    return intSupplier;
  }
}
