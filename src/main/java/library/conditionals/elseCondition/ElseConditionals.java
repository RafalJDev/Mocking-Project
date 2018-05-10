package library.conditionals.elseCondition;

import library.conditionals.BaseConditionals;

import java.util.function.Function;
import java.util.function.Supplier;

public class ElseConditionals<T> {
  
  private BaseConditionals baseConditionals;
  
  private T input;
  
  public ElseConditionals(BaseConditionals baseConditionals,
                          T input) {
    this.baseConditionals = baseConditionals;
    this.input = input;
  }
  
  public T orElseThrowE(RuntimeException e) {
    baseConditionals.orElseThrowE(e);
    return input;
  }
  
  public T orElseThrowE(Supplier<RuntimeException> supplier) {
    baseConditionals.orElseThrowE(supplier);
    return this.input;
  }
  
  public T orElse(Supplier<T> supplierInput) {
    if (baseConditionals.isCondition()) {
      return input;
    }
    return supplierInput.get();
  }
  
  public T orElse(T input) {
    if (baseConditionals.isCondition()) {
      return this.input;
    }
    return input;
  }
  
}
