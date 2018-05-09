package library.fluentconditionals;

import client.code.TestHelper;
import org.testng.annotations.Test;

import static library.fluentconditionals.GivenConditionals.doNothing;
import static library.fluentconditionals.GivenConditionals.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class GivenConditionalsTest {
  
  private GivenConditionals givenConditionals;
  
  private static void printFirstChar(String string) {
    System.out.println(string.charAt(0));
  }
  
  private static void printLastChar(String string) {
    System.out.println(string.charAt(string.length() - 1));
  }
  
  private static int getLowNumber() {
    return 123;
  }
  
  private static int getHighNumber() {
    return 1000;
  }
  
  private static boolean sthTrue() {
    return true;
  }
  
  private static boolean sthFalse() {
    return false;
  }
  
  public void givenString_whenTrue_thenCheckInput() {
    givenConditionals = given(getThisString())
        .when(true)
        .then((s) -> printFirstChar(s))
        .orElse((s) -> printLastChar(s));
    
    
    boolean condition = givenConditionals.isCondition();
    assertTrue(condition);
    
    String input = givenConditionals.getInput();
    assertEquals(input, getThisString());
  }
  
  public void givenStringAsMethodReference_whenTrueAsMethodReference() {
    givenConditionals = given(() -> getAString())
        .when(() -> sthTrue())
        .then((s) -> printFirstChar(s))
        .orElse(s -> printLastChar(s));
    
    boolean condition = givenConditionals.isCondition();
    assertTrue(condition);
    
    String input = givenConditionals.getInput();
    assertEquals(input, getAString());
  }
  
  public void givenString_whenFalse_then() {
    givenConditionals = given(() -> getAString())
        .when(() -> sthFalse())
        .then(s -> printFirstChar(s))
        .orElse(doNothing());
    
    boolean condition = givenConditionals.isCondition();
    assertFalse(condition);
    
    String input = givenConditionals.getInput();
    assertEquals(input, getAString());
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void givenString_whenFalse_thenReferencedException() {
    given(() -> getAString())
        .when(() -> sthFalse())
        .then(s -> printFirstChar(s))
        .orElseThrowE(RuntimeException::new);
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void givenString_whenFalse_thenNewException() {
    given(() -> getAString())
        .when(() -> sthFalse())
        .then(s -> printFirstChar(s))
        .orElseThrowE(new RuntimeException());
  }
  
  private String getAString() {
    return "a string";
  }
  
  private String getThisString() {
    return "This";
  }
  
  private String getYay() {
    return "Yay";
  }
  
  private String getNah() {
    return "Nah";
  }
}