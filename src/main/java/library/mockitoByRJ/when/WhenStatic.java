package library.mockitoByRJ.when;

import java.util.function.BooleanSupplier;

public class WhenStatic {
  
  public static ThenWhenStatic when(BooleanSupplier whenSupplier) {
    return new ThenWhenStatic(whenSupplier);
  }
  
  public static ThenWhenStatic when(Boolean whenBoolean) {
    return new ThenWhenStatic(() -> whenBoolean);
  }
}
