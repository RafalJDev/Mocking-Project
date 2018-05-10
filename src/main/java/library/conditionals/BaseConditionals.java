package library.conditionals;

import java.util.function.Supplier;

public class BaseConditionals {
  
  public static Runnable doNothing = () -> {
  };
  
  protected boolean condition;
  
  protected BaseConditionals(boolean condition) {
    this.condition = condition;
  }
  
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

//  public <T> ElseConditionals<T> thenReturn(Supplier<T> supplier) {
//    return new ElseConditionals<>(this, supplier.get());
//  }

//  public <T> ElseConditionals<T> thenReturn(T input) {
//    return new ElseConditionals(this, input);
//  }
  
  public boolean isCondition() {
    return condition;
  }
  
  
}
