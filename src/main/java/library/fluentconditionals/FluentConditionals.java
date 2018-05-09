package library.fluentconditionals;

import library.FluentConditionalsWrapper;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class FluentConditionals {

//  public static Runnable doNothing = () -> {};
//
//  private boolean condition;
//
//  protected FluentConditionals(boolean condition) {
//    this.condition = condition;
//  }
//
//  public static FluentConditionals when(boolean b) {
//    return new FluentConditionals(b);
//  }
//
//  public static FluentConditionals when(BooleanSupplier runnable) {
//    return new FluentConditionals(runnable.getAsBoolean());
//  }
//
//  public FluentConditionals then(Runnable runnable) {
//    if (condition) {
//      runnable.run();
//    }
//    return this;
//  }
//
//  public void orElse(Runnable runnable) {
//    if (!condition) {
//      runnable.run();
//    }
//  }
//
//  public void orElseThrowE(RuntimeException e) {
//    if (!condition) {
//      throw e;
//    }
//  }
//
//  public void orElseThrowE(Supplier<RuntimeException> supplier) {
//    if (!condition) {
//      throw supplier.get();
//    }
//  }
//
//  public <T> FluentConditionalsWrapper<T> thenReturn(Supplier<T> supplier) {
//    return new FluentConditionalsWrapper<>(this, supplier.get());
//  }
//
//  public <T> FluentConditionalsWrapper<T> thenReturn(T input) {
//    return new FluentConditionalsWrapper<>(this, input);
//  }
//
//  public boolean isCondition() {
//    return condition;
//  }
//
}
