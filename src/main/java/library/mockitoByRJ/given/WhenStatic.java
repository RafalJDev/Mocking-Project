package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;

public class WhenStatic {
  
  public static <EmptyType> ThenWhenStatic<EmptyType> when(BooleanSupplier whenSupplier) {
    return new ThenWhenStatic(whenSupplier);
  }
  
  public static <EmptyType> ThenWhenStatic<EmptyType> when(Boolean whenBoolean) {
    return new ThenWhenStatic(() -> whenBoolean);
  }
}
