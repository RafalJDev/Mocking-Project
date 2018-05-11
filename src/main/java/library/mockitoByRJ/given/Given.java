package library.mockitoByRJ.given;

import java.util.function.Supplier;

public class Given {
  
  public static <GivenType> When<GivenType> given(Supplier<GivenType> supplier) {
    return new When<>(supplier);
  }
  
  public static <GivenType> When<GivenType> given(GivenType input) {
    return given(() -> input);
  }
}