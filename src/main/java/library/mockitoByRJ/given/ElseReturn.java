package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class ElseReturn<GivenType, ThenType> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;
  private Supplier<ThenType> thenSupplier;
  
  public ElseReturn(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Supplier<ThenType> thenSupplier) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenSupplier = thenSupplier;
  }
  
  public ElseReturn(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Function<GivenType, ThenType> thenFunction) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenSupplier = () -> thenFunction.apply(this.givenSupplier.get());
  }
  
  public ThenType orElse(ThenType input) {
    if (whenSupplier.getAsBoolean()) {
      return thenSupplier.get();
    }
    return input;
  }
  
  public ThenType orElse(Supplier<ThenType> elseConsumer) {
    if (whenSupplier.getAsBoolean()) {
      return thenSupplier.get();
    }
    return elseConsumer.get();
  }
  
  public ThenType orElse(Function<GivenType, ThenType> elseFunction) {
    if (whenSupplier.getAsBoolean()) {
      return thenSupplier.get();
    }
    return elseFunction.apply(givenSupplier.get());
  }
  
  public <Ex extends Throwable> ThenType orElseThrowE(Ex exception) throws Ex {
    if (!whenSupplier.getAsBoolean()) {
      throw exception;
    }
    return thenSupplier.get();
  }
  
  public <Ex extends Throwable> ThenType orElseThrow(Supplier<Ex> exceptionSupplier) throws Ex {
    if (!whenSupplier.getAsBoolean()) {
      throw exceptionSupplier.get();
    }
    return thenSupplier.get();
  }
  
  public <Ex extends Throwable> ThenType orElseThrow(Function<String, Ex> exceptionFunction, String message) throws Ex {
    if (!whenSupplier.getAsBoolean()) {
      throw exceptionFunction.apply(message);
    }
    return thenSupplier.get();
  }
}
