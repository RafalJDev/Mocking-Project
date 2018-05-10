package client.code;

import java.util.function.Consumer;

import static library.conditionals.WhenConditionals.when;

//Task 5
public class IfElseThenReturnGeneric {
  
  public static void main(String[] args) {
    String string =
        when(TestHelper::somethingIsTrue)
            .thenReturn("Yay")
            .orElse("Nah");
    System.out.println(string);//Yay
    
    SomeClass customObject =
        when(TestHelper::somethingIsTrue)
            .thenReturn(new SomeClass())
            .orElse(SomeClass::new);
    System.out.println(customObject);//SomeClass@723279cf
    
    SomeClass customObject2 =
        when(!TestHelper.somethingIsTrue())
            .thenReturn(SomeClass::new)
            .orElseThrowE(RuntimeException::new);
    //exception thrown
    
    //TODO DELTETE THIS, ADDING JUST FOR TEST
    Consumer<String> stringConsumer = s -> System.out.println();
  }
}
