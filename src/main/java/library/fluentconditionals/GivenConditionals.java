package library.fluentconditionals;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GivenConditionals extends BaseConditionals {
  
  private String input;
  
  protected GivenConditionals(String input) {
    super(false);
    this.input = input;
  }
  
  public static GivenConditionals given(String input) {
    return new GivenConditionals(input);
  }
  
  public static GivenConditionals given(Supplier<String> supplier) {
    return new GivenConditionals(supplier.get());
  }
  
  public GivenConditionals when(boolean b) {
    condition = b;
    return this;
  }
  
  public GivenConditionals when(BooleanSupplier booleanSupplier) {
    condition = booleanSupplier.getAsBoolean();
    return this;
  }
  
  public GivenConditionals then(Consumer<String> consumer) {
    if (condition) {
      consumer.accept(input);
    }
    return this;
  }
  
  public GivenConditionals orElse(Consumer<String> consumer) {
    if (!condition) {
      consumer.accept(input);
    }
    return this;
  }
  
  public static Consumer<String> doNothing() {
    return s -> {
    };
  }
  
  public String getInput() {
    return input;
  }
}