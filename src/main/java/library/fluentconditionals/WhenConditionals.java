package library.fluentconditionals;

import java.util.function.BooleanSupplier;

public class WhenConditionals extends BaseConditionals {
  
  public WhenConditionals(boolean whenBoolean) {
    super(whenBoolean);
  }
  
  public static BaseConditionals when(boolean b) {
    return new BaseConditionals(b);
  }
  
  public static BaseConditionals when(BooleanSupplier runnable) {
    return new BaseConditionals(runnable.getAsBoolean());
  }
  
}
