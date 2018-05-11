package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Then<GivenType> {
  
  private Supplier<GivenType> givenSupplier;
  private BooleanSupplier whenSupplier;

//  public Then(BooleanSupplier whenSupplier) {
//    this.whenSupplier = whenSupplier;
//  }
  
  public Then(Supplier<GivenType> givenSupplier, BooleanSupplier whenSupplier) {
    this.givenSupplier = givenSupplier;
    this.whenSupplier = whenSupplier;
  }
  
  public Else<GivenType> then(Consumer<GivenType> thenConsumer) {
    return new Else<>(givenSupplier, whenSupplier, thenConsumer);
  }

//  public Else<GivenType> then(Runnable thenRunnable) {
//    return new Else<>(givenSupplier, whenSupplier, thenRunnable);
//  }
  
  public <ThenType> ElseReturn<GivenType, ThenType> thenReturn(ThenType input) {
    return new ElseReturn<>(givenSupplier, whenSupplier, () -> input);
  }
  
  public <ThenType> ElseReturn<GivenType, ThenType> thenReturn(Supplier<ThenType> thenSupplier) {
    return new ElseReturn<>(givenSupplier, whenSupplier, thenSupplier);
  }
  
  public <ThenType> ElseReturn<GivenType, ThenType> thenReturn(Function<GivenType, ThenType> thenFunction) {
    return new ElseReturn<>(givenSupplier, whenSupplier, thenFunction);
  }
  
}
