package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ElseAfterThrow<GivenType, Ex extends Throwable> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;
  private Supplier<Ex> thenSupplier;
  
  ElseAfterThrow(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Supplier<Ex> thenSupplier) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenSupplier = thenSupplier;
  }
  
  ElseAfterThrow(BooleanSupplier whenSupplier, Supplier<Ex> thenSupplier) {
    this(() -> null, whenSupplier, thenSupplier);
  }
  
  public void orElse(Consumer<GivenType> elseConsumer) throws Ex {
    if (whenSupplier.getAsBoolean()) {
      throw thenSupplier.get();
    }
    elseConsumer.accept(givenSupplier.get());
  }
  
  public void orElse(Runnable runnable) throws Ex {
    if (whenSupplier.getAsBoolean()) {
      throw thenSupplier.get();
    }
    runnable.run();
  }
  
  public <Ex2 extends Throwable> void orElseThrowE(Ex2 elseException) throws Ex, Ex2 {
    if (whenSupplier.getAsBoolean()) {
      throw thenSupplier.get();
    }
    throw elseException;
  }
  
  public <Ex2 extends Throwable> void orElseThrow(Supplier<Ex2> elseExceptionSupplier) throws Ex, Ex2 {
    if (whenSupplier.getAsBoolean()) {
      throw thenSupplier.get();
    }
    throw elseExceptionSupplier.get();
  }
  
  public <Ex2 extends Throwable> void orElseThrow(Function<String, Ex2> elseExceptionFunction, String message) throws Ex, Ex2 {
    if (whenSupplier.getAsBoolean()) {
      throw thenSupplier.get();
    }
    throw elseExceptionFunction.apply(message);
  }
}
