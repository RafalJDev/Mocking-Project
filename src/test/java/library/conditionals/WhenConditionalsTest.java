package library.conditionals;

import org.mockito.Mock;
import org.testng.annotations.Test;

import static library.conditionals.WhenConditionals.when;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@Test
public class WhenConditionalsTest {
  
  @Mock
  Runnable runnable;
  
  @Mock
  Runnable elseRunnable;
  
  @Test
  public void when_true_thenTrue() {
  
    BaseConditionals result = when(true);
    
    assertTrue(result.isCondition());
  }
  
  @Test
  public void when_methodReference_sthFalse_thenFalse() {
  
    BaseConditionals result = when(() -> sthFalse());
    
    assertFalse(result.isCondition());
  }
  
  @Test
  public void when_methodReference_sthTrue_thenTrue() {
  
    BaseConditionals result = when(() -> sthTrue());
    
    assertTrue(result.isCondition());
  }
  
  @Test
  public void when_true_thenVerifyTimes_withMockito() {
    
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
  
    when(true)
        .then(runnable)
        .then(runnable)
        .orElse(elseRunnable);
    
    verify(runnable, times(2)).run();
    verify(elseRunnable, times(0)).run();
  }
  
  @Test
  public void when_false_thenVerifyTimes_withMockito() {
    
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
  
    when(false)
        .then(runnable)
        .then(runnable)
        .orElse(elseRunnable);
    
    verify(runnable, times(0)).run();
    verify(elseRunnable, times(1)).run();
  
    Runnable doNothing = BaseConditionals.doNothing;
    
    assertNotNull(doNothing);
  }
  
  @Test
  public void check_doNothing_() {
    runnable = mock(Runnable.class);
  
    when(true)
        .then(runnable)
        .then(runnable)
        .orElse(elseRunnable);
    
    verify(runnable, times(2)).run();
  
    Runnable doNothing = BaseConditionals.doNothing;
    
    assertNotNull(doNothing);
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_boolean() {
    runnable = mock(Runnable.class);
  
    when(false)
        .then(runnable)
        .then(runnable)
        .orElseThrowE(new RuntimeException());
    
    verify(runnable, times(2)).run();
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_methodReference() {
    runnable = mock(Runnable.class);
  
    when(() -> sthFalse())
        .then(runnable)
        .then(runnable)
        .orElseThrowE(new RuntimeException());
    
    verify(runnable, times(2)).run();
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_methodReferenceForFalse_methodReferenceForException() {
    runnable = mock(Runnable.class);
  
    when(() -> sthFalse())
        .then(runnable)
        .then(runnable)
        .orElseThrowE(RuntimeException::new);
    
    verify(runnable, times(2)).run();
  }
  
  @Test
  public void orElseThrow_methodReferenceForTrue_newException() {
    runnable = mock(Runnable.class);
  
    when(() -> sthTrue())
        .then(runnable)
        .then(runnable)
        .orElseThrowE(new RuntimeException());
    
    verify(runnable, times(2)).run();
  }
  
  @Test
  public void orElseThrow_methodReferenceForTrue_methodReferenceForException() {
    runnable = mock(Runnable.class);
  
    when(() -> sthTrue())
        .then(runnable)
        .then(runnable)
        .orElseThrowE(RuntimeException::new);
    
    verify(runnable, times(2)).run();
  }
  
  public void orElse_intReturn_whenTrue_methodReferenceAsParameter_() {
    
    runnable = mock(Runnable.class);
  
    int result1 = when(() -> sthTrue())
        .thenReturn(() -> getHighNumber())
        .orElse(() -> getLowNumber());
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 1000);
  }
  
  public void orElse_intReturn_whenFalse_methodReferenceAsParameter() {
    
    runnable = mock(Runnable.class);
  
    int result1 = when(() -> sthFalse())
        .thenReturn(() -> getHighNumber())
        .orElse(() -> getLowNumber());
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 123);
  }
  
  public void orElse_intReturn_whenTrue_valueAsParameter() {
    
    runnable = mock(Runnable.class);
  
    int result1 = when(() -> sthTrue())
        .thenReturn(() -> getHighNumber())
        .orElse(345);
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 1000);
  }
  
  public void orElse_intReturn_whenFalse_valueAsParameter() {
    
    runnable = mock(Runnable.class);
  
    int result1 = when(() -> sthFalse())
        .thenReturn(() -> getHighNumber())
        .orElse(345);
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 345);
  }
  
  public void orElseThrow_whenTrue_thenReturnedLowNumber() {
    
    int result3 = when(() -> sthTrue())
        .thenReturn(() -> getLowNumber())
        .orElseThrowE(new RuntimeException());
    
    assertEquals(result3, getLowNumber());
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_whenFalse_thenExceptionAsMethodReference() {
  
    int result4 = when(() -> sthFalse())
        .thenReturn(() -> getLowNumber())
        .orElseThrowE(RuntimeException::new);
  }
  
  public void orElseThrow_whenTrue_thenExceptionAsMethodReference() {
    
    int result4 = when(() -> sthTrue())
        .thenReturn(() -> getLowNumber())
        .orElseThrowE(RuntimeException::new);
    
    assertEquals(result4, getLowNumber());
  }
  
  @Test
  public void whenTrue_forString_addedGenericHandling_() {
    String string =
        when(() -> sthTrue())
            .thenReturn(getYay())
            .orElse(getNah());
    
    assertEquals(string, getYay());
  }
  
  @Test
  public void whenFalse_forString_addedGenericHandling_() {
    String string =
        when(() -> sthFalse())
            .thenReturn(getYay())
            .orElse(getNah());
    
    assertEquals(string, getNah());
  }
  
  @Test
  public void whenTrue_thenReturnSomeClass() {
    SomeClass customObject =
        when(() -> sthTrue())
            .thenReturn(new SomeClass())
            .orElse(SomeClass::new);
    
    assertTrue(customObject instanceof SomeClass);
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void whenFalse_thenRuntimeException() {
    SomeClass customObject2 =
        when(() -> sthFalse())
            .thenReturn(SomeClass::new)
            .orElseThrowE(RuntimeException::new);
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
}