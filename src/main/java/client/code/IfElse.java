package client.code;

import static library.conditionals.WhenConditionals.doNothing;
import static library.conditionals.WhenConditionals.when;

//Task 1
public class IfElse {
  
  public static void main(String[] args) {
    
    when(TestHelper.somethingIsTrue())
        .then(TestHelper::printBar)
        .orElse(TestHelper::printFoo);
    //'Bar' printed to console
    
    when(TestHelper::somethingIsTrue)
        .then(TestHelper::printBar)
        .orElse(TestHelper::printFoo);
    //'Bar' printed to console
    
    
    when(!TestHelper.somethingIsTrue())
        .then(TestHelper::printBar)
        .orElse(doNothing);
    //nothing printed to console
  }
}
