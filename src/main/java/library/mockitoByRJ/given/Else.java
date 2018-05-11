package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Else<GivenType> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;
  private Consumer<GivenType> thenConsumer;
  
  Else(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Consumer<GivenType> thenConsumer) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenConsumer = thenConsumer;
  }
  
  Else(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Runnable thenRunnable) {
    this(givenSupplier, whenSupplier, givenType -> thenRunnable.run());
  }
  
  Else(BooleanSupplier whenSupplier, Runnable thenRunnable) {
    this(() -> null, whenSupplier, givenType -> thenRunnable.run());
  }
  
  public void orElse(Runnable runnable) {
    if (whenSupplier.getAsBoolean()) {
      thenConsumer.accept(givenSupplier.get());
      return;
    }
    runnable.run();
  }
  
  public void orElse(Consumer<GivenType> elseConsumer) {
    orElse(() -> elseConsumer.accept(givenSupplier.get()));
  }
  
  public <Ex extends Throwable> void orElseThrow(Supplier<Ex> exceptionSupplier) throws Ex {
    if (whenSupplier.getAsBoolean()) {
      thenConsumer.accept(givenSupplier.get());
      return;
    }
    throw exceptionSupplier.get();
  }
  
  public <Ex extends Throwable> void orElseThrowE(Ex exception) throws Ex {
    orElseThrow(() -> exception);
  }
  
  public <Ex extends Throwable> void orElseThrow(Function<String, Ex> function, String message) throws Ex {
    orElseThrow(() -> function.apply(message));
    
  }
}
