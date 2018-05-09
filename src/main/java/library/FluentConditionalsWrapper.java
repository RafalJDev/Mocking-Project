package library;

import library.fluentconditionals.FluentConditionals;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class FluentConditionalsWrapper {
  
  FluentConditionals fluentConditionals;
  
  public FluentConditionalsWrapper(FluentConditionals fluentConditionals) {
    this.fluentConditionals = fluentConditionals;
  }
  
  public int orElseThrow(RuntimeException e) {
    fluentConditionals.orElseThrow(e);
    IntSupplier intSupplier = fluentConditionals.getIntSupplier();
    return intSupplier.getAsInt();
  }
  
  public int orElseThrow(Supplier<RuntimeException> supplier) {
    fluentConditionals.orElseThrow(supplier);
    
    IntSupplier intSupplier = fluentConditionals.getIntSupplier();
    return intSupplier.getAsInt();
  }
  
  public int orElse(IntSupplier intSupplier) {
    int result = fluentConditionals.orElse(intSupplier);
    return result;
  }
  
  public int orElse(int input) {
    int result = fluentConditionals.orElse(input);
    return result;
  }
}
