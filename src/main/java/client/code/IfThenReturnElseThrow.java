package client.code;

import static library.conditionals.WhenConditionals.when;

//Task 4
public class IfThenReturnElseThrow {
  
  public static void main(String[] args) {
  
    when(TestHelper::somethingIsTrue)
        .thenReturn(TestHelper::getLowNumber)
        .orElseThrowE(new RuntimeException());
    
    int result3 = when(TestHelper::somethingIsTrue)
        .thenReturn(TestHelper::getLowNumber)
        .orElseThrowE(new RuntimeException());
    System.out.println(result3);//1
    
    int result4 = when(!TestHelper.somethingIsTrue())
        .thenReturn(TestHelper::getLowNumber)
        .orElseThrowE(RuntimeException::new);
    //exception thrown
  }
}
