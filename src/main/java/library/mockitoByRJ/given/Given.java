package library.mockitoByRJ.given;

import java.util.function.Supplier;

public class Given {
  
  public static <GivenType> When<GivenType> given(GivenType input) {
    return new When<>(input);
  }
  
  public static <GivenType> When<GivenType> given(Supplier<GivenType> supplier) {
    return new When<>(supplier);
  }
}