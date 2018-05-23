package library.mockitoByRJ.given;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class When<GivenType> {
  
  private final Supplier<GivenType> givenSupplier;
  
  When(GivenType input) {
    this.givenSupplier = () -> input;
  }
  
  When(Supplier<GivenType> givenSupplier) {
    this.givenSupplier = givenSupplier;
  }
  
  public Then<GivenType> when(BooleanSupplier whenSupplier) {
    return new Then<>(givenSupplier, whenSupplier);
  }
  
  public Then<GivenType> when(Boolean whenBoolean) {
    return when(() -> whenBoolean);
  }
}
