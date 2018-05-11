package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Then<GivenType> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;
  
  Then(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
  }
  
  public Else<GivenType> then(Consumer<GivenType> thenConsumer) {
    return new Else<>(givenSupplier, whenSupplier, thenConsumer);
  }
  
  public Else<GivenType> then(Runnable thenRunnable) {
    return new Else<>(givenSupplier, whenSupplier, thenRunnable);
  }
  
  public <Ex extends Throwable> ElseAfterThrow<GivenType, Ex> thenThrowE(Ex thenException) {
    return thenThrow(() -> thenException);
  }
  
  public <Ex extends Throwable> ElseAfterThrow<GivenType, Ex> thenThrow(Supplier<Ex> thenSupplier) {
    return new ElseAfterThrow<>(givenSupplier, whenSupplier, thenSupplier);
  }
  
  public <Ex extends Throwable> ElseAfterThrow<GivenType, Ex> thenThrow(Function<String, Ex> thenFunction, String message) {
    return new ElseAfterThrow<>(givenSupplier, whenSupplier, () -> thenFunction.apply(message));
  }
  
  public <ThenType> ElseAfterReturn<GivenType, ThenType> thenReturn(ThenType input) {
    return thenReturn(() -> input);
  }
  
  public <ThenType> ElseAfterReturn<GivenType, ThenType> thenReturn(Supplier<ThenType> thenSupplier) {
    return new ElseAfterReturn<>(givenSupplier, whenSupplier, thenSupplier);
  }
  
  public <ThenType> ElseAfterReturn<GivenType, ThenType> thenReturn(Function<GivenType, ThenType> thenFunction) {
    return new ElseAfterReturn<>(givenSupplier, whenSupplier, thenFunction);
  }
  
}
