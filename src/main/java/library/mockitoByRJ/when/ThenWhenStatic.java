package library.mockitoByRJ.when;

import library.mockitoByRJ.given.Else;

import java.util.function.BooleanSupplier;

public class ThenWhenStatic {
  
  private BooleanSupplier whenSupplier;
  
  public ThenWhenStatic(BooleanSupplier whenSupplier) {
    this.whenSupplier = whenSupplier;
  }
  
  public Else then(Runnable thenRunnable) {
    return new Else(whenSupplier, thenRunnable);
  }
  
}
