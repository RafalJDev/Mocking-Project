package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThenWhenStatic<EmptyType> {
  
  private BooleanSupplier whenSupplier;
  
  ThenWhenStatic(BooleanSupplier whenSupplier) {
    this.whenSupplier = whenSupplier;
  }
  
  public Else<EmptyType> then(Runnable thenRunnable) {
    return new Else<>(whenSupplier, thenRunnable);
  }
  
  public <Ex extends Throwable> ElseAfterThrow<EmptyType, Ex> thenThrow(Supplier<Ex> thenSupplier) {
    return new ElseAfterThrow<>(whenSupplier, thenSupplier);
  }
  
  public <Ex extends Throwable> ElseAfterThrow<EmptyType, Ex> thenThrowE(Ex thenException) {
    return thenThrow(() -> thenException);
  }
  
  public <Ex extends Throwable> ElseAfterThrow<EmptyType, Ex> thenThrow(Function<String, Ex> thenFunction, String message) {
    return thenThrow(() -> thenFunction.apply(message));
  }
  
  public <ThenType> ElseAfterReturn<EmptyType, ThenType> thenReturn(Supplier<ThenType> thenSupplier) {
    return new ElseAfterReturn<>(whenSupplier, thenSupplier);
  }
  
  public <ThenType> ElseAfterReturn<EmptyType, ThenType> thenReturn(ThenType input) {
    return thenReturn(() -> input);
  }
}
