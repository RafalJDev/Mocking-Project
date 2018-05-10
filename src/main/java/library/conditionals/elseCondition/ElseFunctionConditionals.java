package library.conditionals.elseCondition;

import library.conditionals.BaseConditionals;
import library.conditionals.GivenConditionals;

import java.util.function.Function;
import java.util.function.Supplier;

public class ElseFunctionConditionals<T, R> /*extends ElseConditionals*/ {
  
  private GivenConditionals<T> givenConditionals;
  
  private Function<T, R> thenFunction;
  
  private T input;
  
  public ElseFunctionConditionals(GivenConditionals<T> givenConditionals,
                                  Function<T, R> function) {
//    super(givenConditionals, null);
    this.givenConditionals = givenConditionals;
    this.thenFunction = function;
    this.input = givenConditionals.getInput();
  }

//  public ElseFunctionConditionals(GivenConditionals<T> givenConditionals,
//                                  T input) {
////    super(givenConditionals, null);
//    this.givenConditionals = givenConditionals;
//    this.input = input;
//  }
  
  public R orElse(Function<T, R> function) {
    if (givenConditionals.isCondition()) {
      return thenFunction.apply(input);
    }
    return function.apply(input);
  }
  
  public R orElse(Supplier<R> supplier) {
    T givenInput = givenConditionals.getInput();
    if (givenConditionals.isCondition()) {
      return thenFunction.apply(givenInput);
    }
    return supplier.get();
  }
  
  public R orElse(R input) {
    T givenInput = givenConditionals.getInput();
    if (givenConditionals.isCondition()) {
      return thenFunction.apply(givenInput);
    }
    return input;
  }
  
  public R orElseThrow(RuntimeException e) {
    if (!givenConditionals.isCondition()) {
      throw e;
    }
    return thenFunction.apply(input);
  }
  
  public R orElseThrow(Supplier<RuntimeException> supplier) {
    if (!givenConditionals.isCondition()) {
      throw supplier.get();
    }
    return thenFunction.apply(input);
  }
  
  
}