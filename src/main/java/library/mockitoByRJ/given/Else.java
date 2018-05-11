package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Else<GivenType> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;
  private Consumer<GivenType> thenConsumer;
  
  public Else(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Consumer<GivenType> thenConsumer) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenConsumer = thenConsumer;
  }
  
  public Else(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier, Runnable thenRunnable) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
    this.thenConsumer = givenType -> thenRunnable.run();
  }
  
  public Else(BooleanSupplier whenSupplier, Runnable thenRunnable) {
    this.whenSupplier = whenSupplier;
    this.thenConsumer = givenType -> thenRunnable.run();
  }
  
  public Else(BooleanSupplier whenSupplier, Consumer<GivenType> thenConsumer) {
    this.whenSupplier = whenSupplier;
    this.thenConsumer = thenConsumer;
  }
  
  public void orElse(Consumer<GivenType> elseConsumer) {
    if (whenSupplier.getAsBoolean()) {
      thenConsumer.accept(givenSupplier.get());
      return;
    }
    elseConsumer.accept(givenSupplier.get());
  }
  
  public void orElse(Runnable runnable) {
    if (whenSupplier.getAsBoolean()) {
      thenConsumer.accept(givenSupplier.get());
      return;
    }
    runnable.run();
  }
  
  public <Ex extends Throwable> void orElseThrow(Supplier<Ex> exceptionSupplier) throws Ex {
    if (!whenSupplier.getAsBoolean()) {
      throw exceptionSupplier.get();
    }
    thenConsumer.accept(givenSupplier.get());
  }
  
  public <Ex extends Throwable> void orElseThrowE(Ex exception) throws Ex {
    if (!whenSupplier.getAsBoolean()) {
      throw exception;
    }
    thenConsumer.accept(givenSupplier.get());
  }
  
  public <Ex extends Throwable> void orElseThrow(Function<String, Ex> function, String message) throws Ex {
    if (!whenSupplier.getAsBoolean()) {
      throw function.apply(message);
    }
    thenConsumer.accept(givenSupplier.get());
  }
}
