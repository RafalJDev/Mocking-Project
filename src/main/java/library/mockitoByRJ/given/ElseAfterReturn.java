package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class ElseAfterReturn<GivenType, ThenType> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;
  private Supplier<ThenType> thenSupplier;
  
  ElseAfterReturn(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Supplier<ThenType> thenSupplier) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenSupplier = thenSupplier;
  }
  
  ElseAfterReturn(BooleanSupplier whenSupplier, Supplier<ThenType> thenSupplier) {
    this(() -> null, whenSupplier, thenSupplier);
  }
  
  ElseAfterReturn(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Function<GivenType, ThenType> thenFunction) {
    this(givenSupplier, whenSupplier, () -> thenFunction.apply(givenSupplier.get()));
  }
  
  public ThenType orElse(Supplier<ThenType> elseConsumer) {
    if (whenSupplier.getAsBoolean()) {
      return thenSupplier.get();
    }
    return elseConsumer.get();
  }
  
  public ThenType orElse(ThenType input) {
    return orElse(() -> input);
  }
  
  public ThenType orElse(Function<GivenType, ThenType> elseFunction) {
    return orElse(() -> elseFunction.apply(givenSupplier.get()));
  }
  
  public <Ex extends Throwable> ThenType orElseThrow(Supplier<Ex> exceptionSupplier) throws Ex {
    if (whenSupplier.getAsBoolean()) {
      return thenSupplier.get();
    }
    throw exceptionSupplier.get();
  }
  
  public <Ex extends Throwable> ThenType orElseThrowE(Ex exception) throws Ex {
    return orElseThrow(() -> exception);
  }
  
  public <Ex extends Throwable> ThenType orElseThrow(Function<String, Ex> exceptionFunction, String message) throws Ex {
    return orElseThrow(() -> exceptionFunction.apply(message));
  }
}
