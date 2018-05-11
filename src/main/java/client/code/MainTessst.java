package client.code;

import static library.mockitoByRJ.given.Given.given;

public class MainTessst {
  
  public static void main(String[] args) throws Exception {

//    given(TestHelper::getAString)
//        .when(ApiCleanup::veryComplexCondition)
//        .then(TestHelper::printFirstChar)
//        .orElse(s -> System.out.println("KURWA"));

//    given(TestHelper::getAString)
//        .when(ApiCleanup::veryComplexCondition)
//        .then(TestHelper::printFirstChar);


//    given(TestHelper::getAString)
//        .when(false)
//        .then(TestHelper::printFirstChar)
//        .orElseThrow(new ArrayIndexOutOfBoundsException());
    
    String s = given(TestHelper::getAString)
        .when(true)
        .thenReturn("2")
        .orElse(() -> "1");
    System.out.println(s);
    
    given(() -> "swfwef")
        .when(() -> false)
        .then(System.out::println)
        .orElse(() -> System.out.println("sfwefwgwgwggeg"));

//    when(true)
//        .then(TestHelper::printFirstChar)
//    .orElse(o -> System.out.println(o));
//    given(getGreetings())
//        .when(() -> sthTrue())
  }
}
