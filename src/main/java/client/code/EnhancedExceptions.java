package client.code;

import static library.conditionals.GivenConditionals.given;
import static library.conditionals.WhenConditionals.doNothing;
import static library.conditionals.WhenConditionals.when;

//Task 9
public class EnhancedExceptions {
  
  public static void main(String[] args) throws CustomException {
    when(false)
        .then(doNothing)
        .orElseThrow(CustomException::new);
//        Exception in thread "main" EnhancedExceptions$CustomException
    
    Integer integer = given("Greetings")
        .when(TestHelper::somethingIsTrue)
        .thenReturn(String::hashCode)
        .orElseThrow(CustomException::new, "Exception message");
    System.out.println(integer);
    
    when(TestHelper::somethingIsTrue)
        .then(doNothing)
        .orElseThrow(OutOfMemoryError::new, "Exception message");
    
    when(TestHelper::somethingIsTrue)
        .thenThrow(RuntimeException::new, "This was expected");
    //Exception in thread "main" java.lang.RuntimeException: This was expected
  }
  
  static class CustomException extends Exception {
    public CustomException() {
    }
    
    public CustomException(String message) {
      super(message);
    }
  }
}
