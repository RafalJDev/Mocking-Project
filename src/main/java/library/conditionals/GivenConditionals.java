package library.conditionals;

import library.conditionals.elseCondition.ElseFunctionConditionals;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class GivenConditionals<T> extends BaseConditionals {
  
  private T input;
  
  protected GivenConditionals(T input) {
    super(false);
    this.input = input;
  }
  
  public static <T> GivenConditionals<T> given(T input) {
    return new GivenConditionals(input);
  }
  
  public static <T> GivenConditionals<T> given(Supplier<T> supplier) {
    return new GivenConditionals(supplier.get());
  }
  
  public static <T> Consumer<T> doNothing() {
    return s -> {
    };
  }
  
  public GivenConditionals<T> when(boolean b) {
    condition = b;
    return this;
  }
  
  public GivenConditionals<T> when(BooleanSupplier booleanSupplier) {
    condition = booleanSupplier.getAsBoolean();
    return this;
  }
  
  public GivenConditionals<T> then(Consumer<T> consumer) {
    if (condition) {
      consumer.accept(input);
    }
    return this;
  }
  
  public <Return> ElseFunctionConditionals<T, Return> thenReturn(Function<T, Return> function) {
    return new ElseFunctionConditionals(this, function);
  }
  
  public <R> ElseFunctionConditionals<T, R> thenReturn(Supplier<R> supplier) {
    Function<T, R> function = t -> supplier.get();
    return thenReturn(function);
  }
  
  public GivenConditionals<T> orElse(Consumer<T> consumer) {
    if (!condition) {
      consumer.accept(input);
    }
    return this;
  }
  
  
  public T getInput() {
    return input;
  }
}