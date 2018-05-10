package client.code;

import static library.conditionals.WhenConditionals.when;

//Task 2
public class IfExecuteElseThrow {
  
  public static void main(String[] args) {
    
    when(TestHelper::somethingIsTrue)
        .then(TestHelper::printBar)
        .orElseThrowE(new RuntimeException());
    //'Bar' printed to console
    
    when(TestHelper::somethingIsTrue)
        .then(TestHelper::printBar)
        .orElseThrowE(RuntimeException::new);
    //'Bar' printed to console
    
    when(TestHelper::somethingIsTrue)
        .then(TestHelper::printBar)
        .orElseThrowE(TestHelper::createException);
    //'Bar' printed to console
    
    when(!TestHelper.somethingIsTrue())
        .then(TestHelper::printFoo)
        .orElseThrowE(TestHelper::createException);
    //exception thrown
  }
}
