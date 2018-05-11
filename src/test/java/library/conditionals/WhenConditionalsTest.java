package library.conditionals;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static library.mockitoByRJ.when.WhenStatic.*;

import static library.conditionals.BaseConditionals.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.*;

@Test
public class WhenConditionalsTest {
  
  @Mock
  Runnable runnable;
  
  @Mock
  Runnable elseRunnable;
  
  @Test
  public void when_true_thenNothing() {
    when(true);
  }
  
  @Test
  public void when_methodReference_sthFalse_thenFalse() {
  
    when(() -> sthFalse());
  
    //TODO
  }
  
  @Test
  public void when_methodReference_sthTrue_thenTrue() {
  
    when(() -> sthTrue());
  
    //TODO
  }
  
  //TODO
//  @Test
//  public void when_true_thenVerifyTimes_withMockito() {
//
//    runnable = mock(Runnable.class);
//    elseRunnable = mock(Runnable.class);
//
//    when(true)
//        .then(runnable)
//        .orElse(elseRunnable);
//
//    verify(runnable, times(1)).run();
//    verify(elseRunnable, times(0)).run();
//  }
  
  @Test
  public void when_false_thenVerifyTimes_withMockito() {
    
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
  
    when(false)
        .then(runnable)
        .orElse(elseRunnable);
    
    verify(runnable, times(0)).run();
    verify(elseRunnable, times(1)).run();
  
    Runnable doNothing = BaseConditionals.doNothing;
    
    assertNotNull(doNothing);
  }
  
  //TODO then for static when
//  @Test
//  public void check_doNothing_() {
//
//    runnable = mock(Runnable.class);
//    elseRunnable = mock(Runnable.class);
//
//    when(true)
//        .then(runnable)
//        .orElse(elseRunnable);
//
//    verify(runnable, times(1)).run();
//  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_boolean() throws Throwable {
    runnable = mock(Runnable.class);
  
    when(false)
        .then(runnable)
        .orElseThrowE(new RuntimeException());
  
    verify(runnable, times(0)).run();
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_methodReference() throws Throwable {
    runnable = mock(Runnable.class);
  
    when(() -> sthFalse())
        .then(runnable)
        .orElseThrowE(new RuntimeException());
  
    verify(runnable, times(0)).run();
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_methodReferenceForFalse_methodReferenceForException() throws Throwable {
    runnable = mock(Runnable.class);
  
    when(() -> sthFalse())
        .then(runnable)
        .orElseThrow(RuntimeException::new);
  
    verify(runnable, times(0)).run();
  }
  
  //TODO
//  @Test
//  public void orElseThrow_methodReferenceForTrue_newException() throws Throwable {
//    runnable = mock(Runnable.class);
//
//    when(() -> sthTrue())
//        .then(runnable)
//        .orElseThrowE(new RuntimeException());
//
//    verify(runnable, times(0)).run();
//  }
  
  //TODO
//  @Test
//  public void orElseThrow_methodReferenceForTrue_methodReferenceForException() throws Throwable {
//    runnable = mock(Runnable.class);
//
//    when(() -> sthTrue())
//        .then(runnable)
//        .orElseThrow(RuntimeException::new);
//
//    verify(runnable, times(1)).run();
//  }

//  public void orElse_intReturn_whenTrue_methodReferenceAsParameter_() {
//
//    runnable = mock(Runnable.class);
//
//    int result1 = when(() -> sthTrue())
//        .thenReturn(() -> getHighNumber())
//        .orElse(() -> getLowNumber());
//
//    verify(runnable, times(0)).run();
//
//    assertEquals(result1, 1000);
//  }
//
//  public void orElse_intReturn_whenFalse_methodReferenceAsParameter() {
//
//    runnable = mock(Runnable.class);
//
//    int result1 = when(() -> sthFalse())
//        .thenReturn(() -> getHighNumber())
//        .orElse(() -> getLowNumber());
//
//    verify(runnable, times(0)).run();
//
//    assertEquals(result1, 123);
//  }
//
//  public void orElse_intReturn_whenTrue_valueAsParameter() {
//
//    runnable = mock(Runnable.class);
//
//    int result1 = when(() -> sthTrue())
//        .thenReturn(() -> getHighNumber())
//        .orElse(345);
//
//    verify(runnable, times(0)).run();
//
//    assertEquals(result1, 1000);
//  }
//
//  public void orElse_intReturn_whenFalse_valueAsParameter() {
//
//    runnable = mock(Runnable.class);
//
//    int result1 = when(() -> sthFalse())
//        .thenReturn(() -> getHighNumber())
//        .orElse(345);
//
//    verify(runnable, times(0)).run();
//
//    assertEquals(result1, 345);
//  }
//
//  public void orElseThrow_whenTrue_thenReturnedLowNumber() {
//
//    int result3 = when(() -> sthTrue())
//        .thenReturn(() -> getLowNumber())
//        .orElseThrowE(new RuntimeException());
//
//    assertEquals(result3, getLowNumber());
//  }
//
//  @Test(expectedExceptions = RuntimeException.class)
//  public void orElseThrow_whenFalse_thenExceptionAsMethodReference() {
//
//    int result4 = when(() -> sthFalse())
//        .thenReturn(() -> getLowNumber())
//        .orElseThrowE(RuntimeException::new);
//  }
//
//  public void orElseThrow_whenTrue_thenExceptionAsMethodReference() {
//
//    int result4 = when(() -> sthTrue())
//        .thenReturn(() -> getLowNumber())
//        .orElseThrowE(RuntimeException::new);
//
//    assertEquals(result4, getLowNumber());
//  }
//
//  @Test
//  public void whenTrue_forString_addedGenericHandling_() {
//    String string =
//        when(() -> sthTrue())
//            .thenReturn(getYay())
//            .orElse(getNah());
//
//    assertEquals(string, getYay());
//  }
//
//  @Test
//  public void whenFalse_forString_addedGenericHandling_() {
//    String string =
//        when(() -> sthFalse())
//            .thenReturn(getYay())
//            .orElse(getNah());
//
//    assertEquals(string, getNah());
//  }
//
//  @Test
//  public void whenTrue_thenReturnSomeClass() {
//    SomeClass customObject =
//        when(() -> sthTrue())
//            .thenReturn(new SomeClass())
//            .orElse(SomeClass::new);
//
//    assertTrue(customObject instanceof SomeClass);
//  }
//
//  @Test(expectedExceptions = RuntimeException.class)
//  public void whenFalse_thenRuntimeException() {
//    when(() -> sthFalse())
//        .thenReturn(SomeClass::new)
//        .orElseThrowE(RuntimeException::new);
//  }
  
  @Test(expectedExceptions = CustomException.class)
  public void whenFalse_elseWithoutMessage_thenException_() throws Throwable {
    when(false)
        .then(doNothing)
        .orElseThrow(CustomException::new);
  }
  
  //TODO
//  public void whenTrue_elseWithoutMessage_thenDoNothing() throws Throwable {
//    when(true)
//        .then(doNothing)
//        .orElseThrow(CustomException::new);
//  }

//  public void whenTrue_thenDoNothing() throws Throwable {
//    when(() -> sthTrue())
//        .then(doNothing)
//        .orElseThrow(IllegalArgumentException::new, "Exception message");
//  }
//
//  @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Exception message")
//  public void whenFalse_thenOutOfMemoryException() throws Throwable{
//    when(() -> sthFalse())
//        .then(doNothing)
//        .orElseThrow(IllegalArgumentException::new, "Exception message");
//  }

//  @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "This was expected")
//  public void whenTrue_thenRuntimeException() {
//    when(() -> sthTrue())
//        .thenThrow(RuntimeException::new, "This was expected");
//  }
//
//  public void whenFalse_thenNothing() {
//    when(() -> sthFalse())
//        .thenThrow(RuntimeException::new, "This was expected");
//  }
  
  private String getGreetings() {
    return "Greetings";
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
  
  private String getYay() {
    return "Yay";
  }
  
  private String getNah() {
    return "Nah";
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
  
  static class CustomException extends Exception {
    public CustomException() {
    }
    
    public CustomException(String message) {
      super(message);
    }
  }
}