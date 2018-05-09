package library.fluentconditionals;

import library.FluentConditionalsWrapper;

import java.util.function.Supplier;

public class BaseConditionals {
  
  public static Runnable doNothing = () -> {
  };
  
  boolean condition;
  
  protected BaseConditionals(boolean condition) {
    this.condition = condition;
  }

//  public static BaseConditionals when(boolean b) {
//    return new BaseConditionals(b);
//  }
//
//  public static BaseConditionals when(BooleanSupplier runnable) {
//    return new BaseConditionals(runnable.getAsBoolean());
//  }
  
  public BaseConditionals then(Runnable runnable) {
    if (condition) {
      runnable.run();
    }
    return this;
  }
  
  public BaseConditionals orElse(Runnable runnable) {
    if (!condition) {
      runnable.run();
    }
    return this;
  }
  
  public BaseConditionals orElseThrowE(RuntimeException e) {
    if (!condition) {
      throw e;
    }
    return this;
  }
  
  public BaseConditionals orElseThrowE(Supplier<RuntimeException> supplier) {
    if (!condition) {
      throw supplier.get();
    }
    return this;
  }
  
  public <T> FluentConditionalsWrapper<T> thenReturn(Supplier<T> supplier) {
    return new FluentConditionalsWrapper<>(this, supplier.get());
  }
  
  public <T> FluentConditionalsWrapper<T> thenReturn(T input) {
    return new FluentConditionalsWrapper<>(this, input);
  }
  
  public boolean isCondition() {
    return condition;
  }
  
  
}
