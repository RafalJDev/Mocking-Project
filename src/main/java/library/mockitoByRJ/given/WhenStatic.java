package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;

public interface WhenStatic {
  
  static <EmptyType> ThenWhenStatic<EmptyType> when(BooleanSupplier whenSupplier) {
    return new ThenWhenStatic(whenSupplier);
  }
  
  static <EmptyType> ThenWhenStatic<EmptyType> when(Boolean whenBoolean) {
    return when(() -> whenBoolean);
  }
}
