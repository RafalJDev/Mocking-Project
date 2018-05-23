package library.mockitoByRJ.given;

import java.util.function.Supplier;

public interface Given {
  
  static <GivenType> When<GivenType> given(Supplier<GivenType> supplier) {
    return new When<>(supplier);
  }
  
  static <GivenType> When<GivenType> given(GivenType input) {
    return given(() -> input);
  }
}