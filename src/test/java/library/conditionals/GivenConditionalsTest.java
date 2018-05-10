package library.conditionals;

import org.testng.annotations.Test;

import java.util.function.Supplier;

import static library.conditionals.GivenConditionals.doNothing;
import static library.conditionals.GivenConditionals.given;
import static org.testng.Assert.*;

@Test
public class GivenConditionalsTest {
  
  public void givenString_whenTrue_thenCheckInput() {
    GivenConditionals<String> givenConditionals = given(getThisString())
        .when(true)
        .then((s) -> printFirstChar(s))
        .orElse((s) -> printLastChar(s));
  
  
    boolean condition = givenConditionals.isCondition();
    assertTrue(condition);
    
    String input = givenConditionals.getInput();
    assertEquals(input, getThisString());
  }
  
  public void givenStringAsMethodReference_whenTrueAsMethodReference() {
    GivenConditionals<String> givenConditionals = given(() -> getAString())
        .when(() -> sthTrue())
        .then((s) -> printFirstChar(s))
        .orElse(s -> printLastChar(s));
    
    boolean condition = givenConditionals.isCondition();
    assertTrue(condition);
    
    String input = givenConditionals.getInput();
    assertEquals(input, getAString());
  }
  
  public void givenString_whenFalse_then() {
    GivenConditionals<String> givenConditionals = given(() -> getAString())
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
  
  public void givenString_whenTrue_thenStringLength() {
    int result1 = given(getGreetings())
        .when(() -> sthTrue())
        .thenReturn(String::length)
        .orElse(String::hashCode);
    
    assertEquals(result1, getGreetings().length());
  }
  
  public void givenString_whenTrue_thenHighNumber() {
    int result2 = given(() -> getAString())
        .when(() -> sthTrue())
        .thenReturn(() -> getHighNumber())
        .orElse(String::hashCode);
    
    assertEquals(result2, getHighNumber());
  }
  
  public void givenString_whenFalse_thenElseInt() {
    int number = 666;
    int result3 = given(getGreetings())
        .when(() -> sthFalse())
        .thenReturn(String::length)
        .orElse(number);
    
    assertEquals(result3, number);
  }
  
  public void givenString_whenFalse_thenLowNumber() {
    int result4 = given(getGreetings())
        .when(() -> sthFalse())
        .thenReturn(String::length)
        .orElse(() -> getLowNumber());
    
    assertEquals(result4, getLowNumber());
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void givenString_whenFalse_thenException() {
    int result5 = given(getGreetings())
        .when(() -> sthFalse())
        .thenReturn(String::length)
        .orElseThrow(new RuntimeException());
  }
  
  public void givenString_whenTrue_thenStringLength_withElseThrow() {
    int result5 = given(getGreetings())
        .when(() -> sthTrue())
        .thenReturn(String::length)
        .orElseThrow(new RuntimeException());
    
    assertEquals(result5, getGreetings().length());
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void givenString_whenFalse_thenExceptionReference() {
    int result5 = given(getGreetings())
        .when(() -> sthFalse())
        .thenReturn(String::length)
        .orElseThrow(RuntimeException::new);
  }
  
  public void givenString_whenTrue_thenStringLength_withElseThrowReference() {
    int result5 = given(getGreetings())
        .when(() -> sthTrue())
        .thenReturn(String::length)
        .orElseThrow(RuntimeException::new);
    
    assertEquals(result5, getGreetings().length());
  }
  
  public void givenString_whenFalse_thenStringHashCode_functionVersion() {
    int result5 = given(getGreetings())
        .when(() -> sthFalse())
        .thenReturn(String::length)
        .orElse(s -> getHashCodeFunction(s));
    
    assertEquals(result5, getHashCodeFunction(getGreetings()));
  }
  
  public void givenString_whenTrue_thenLowNumber_supplierVersion() {
    int result5 = given(getGreetings())
        .when(() -> sthTrue())
        .thenReturn(String::length)
        .orElse(() -> getLowNumber());
    
    assertEquals(result5, getGreetings().length());
  }
  
  public void givenString_whenTrue_thenLowNumber_variableVersion() {
    int result5 = given(getGreetings())
        .when(() -> sthTrue())
        .thenReturn(String::length)
        .orElse(getLowNumber());
    
    assertEquals(result5, getGreetings().length());
  }
  
  public void givenSomeClass_whenTrue_thenMessageLowNumber() {
    String message = given(SomeClass::new)
        .when(() -> sthTrue())
        .thenReturn(someClass -> getMessageForLowNumber())
        .orElse(() -> getMessageForHighNumber());
    
    assertEquals(message, getMessageForLowNumber());
  }
  
  public void givenSomeClass_whenFalse_thenMessageHighNumber() {
    String message = given(SomeClass::new)
        .when(() -> sthFalse())
        .thenReturn(someClass -> getMessageForLowNumber())
        .orElse(() -> getMessageForHighNumber());
    
    assertEquals(message, getMessageForHighNumber());
  }
  
  public void givenSomeClass_whenTrue_thenHighMessage() {
    Supplier<SomeClass> aNew = SomeClass::new;
    
    AnotherClass object = given(aNew)
        .when(() -> sthTrue())
        .thenReturn(someClass -> extractMessageForHighNumber(someClass))
        .orElse(someClass -> extractMessageForLowNumber(someClass));
    
    assertEquals(object.message, getMessageForHighNumber());
  }
  
  private int getHashCodeFunction(String s) {
    return s.hashCode();
  }
  
  private String getGreetings() {
    return "Greetings";
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
  
  private String getMessageForHighNumber() {
    return "I'm so high";
  }
  
  private String getMessageForLowNumber() {
    return "I'm so low";
  }
  
  private AnotherClass extractMessageForHighNumber(SomeClass someClass) {
    return new AnotherClass(someClass.getMessageForHighNumber());
  }
  
  private AnotherClass extractMessageForLowNumber(SomeClass someClass) {
    return new AnotherClass(someClass.getMessageForLowNumber());
  }
  
  private class AnotherClass {
    final String message;
    
    AnotherClass(String message) {
      this.message = message;
    }
  }
  
  private class SomeClass {
    String getMessageForHighNumber() {
      return "I'm so high";
    }
    
    String getMessageForLowNumber() {
      return "I'm so low";
    }
  }
}

