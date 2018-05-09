package library;

import library.fluentconditionals.BaseConditionals;

import java.util.function.Supplier;

public class FluentConditionalsWrapper<T> {
  
  private BaseConditionals fluentConditionals;
  
  private T input;
  
  public FluentConditionalsWrapper(BaseConditionals baseConditionals,
                                   T input) {
    this.fluentConditionals = baseConditionals;
    this.input = input;
  }
  
  public T orElseThrowE(RuntimeException e) {
    fluentConditionals.orElseThrowE(e);
    return input;
  }
  
  public T orElseThrowE(Supplier<RuntimeException> supplier) {
    fluentConditionals.orElseThrowE(supplier);
    return this.input;
  }
  
  public T orElse(Supplier<T> supplierInput) {
    if (fluentConditionals.isCondition()) {
      return input;
    }
    return supplierInput.get();
  }
  
  public T orElse(T input) {
    if (fluentConditionals.isCondition()) {
      return this.input;
    }
    return input;
  }
}
