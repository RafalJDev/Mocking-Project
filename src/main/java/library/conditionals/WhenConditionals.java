package library.conditionals;

import library.conditionals.elseCondition.ElseConditionals;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class WhenConditionals extends BaseConditionals {
  
  private WhenConditionals(boolean whenBoolean) {
    super(whenBoolean);
  }
  
  public static WhenConditionals when(boolean b) {
    return new WhenConditionals(b);
  }
  
  public static WhenConditionals when(BooleanSupplier runnable) {
    return new WhenConditionals(runnable.getAsBoolean());
  }
  
  public <T> ElseConditionals<T> thenReturn(Supplier<T> supplier) {
    return new ElseConditionals<>(this, supplier.get());
  }
  
  public <T> ElseConditionals<T> thenReturn(T input) {
    return new ElseConditionals<>(this, input);
  }
  
  public <Ex extends Throwable> void thenThrow(Function<String, Ex> exception, String message) throws Ex {
    if (condition) {
      throw exception.apply(message);
    }
  }
}
