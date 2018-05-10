package library.conditionals;

import java.util.function.Function;
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
  
  public <Ex extends Throwable> BaseConditionals orElseThrow(Supplier<Ex> supplier) throws Ex {
    if (!condition) {
      throw supplier.get();
    }
    return this;
  }
  
  public <Ex extends Throwable> BaseConditionals orElseThrow(Function<String, Ex> function, String message) throws Ex {
    if (!condition) {
      throw function.apply(message);
    }
    return this;
  }
  
  public boolean isCondition() {
    return condition;
  }
  
}