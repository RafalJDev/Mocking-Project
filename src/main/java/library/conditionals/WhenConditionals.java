package library.conditionals;

import library.conditionals.elseCondition.ElseConditionals;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class WhenConditionals extends BaseConditionals {
  
  public WhenConditionals(boolean whenBoolean) {
    super(whenBoolean);
  }
  
  public static WhenConditionals when(boolean b) {
    return new WhenConditionals(b);
  }
  
  public static WhenConditionals when(BooleanSupplier runnable) {
    return new WhenConditionals(runnable.getAsBoolean());
  }
  
  public <T> ElseConditionals<T> thenReturn(Supplier<T> supplier) {
    return new ElseConditionals(this, supplier.get());
  }
  
  public <T> ElseConditionals<T> thenReturn(T input) {
    return new ElseConditionals(this, input);
  }
}
