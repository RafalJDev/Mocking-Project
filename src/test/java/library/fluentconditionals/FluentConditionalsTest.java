package library.fluentconditionals;

import client.code.TestHelper;
import org.mockito.Mock;
import org.testng.annotations.Test;

import static library.fluentconditionals.FluentConditionals.when;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@Test
public class FluentConditionalsTest {
  
  @Mock
  Runnable runnable;
  
  @Mock
  Runnable elseRunnable;
  
  
  @Test
  public void when_true_thenTrue() {
    
    FluentConditionals result = FluentConditionals.when(true);
    
    assertTrue(result.isCondition());
  }
  
  @Test
  public void when_methodReference_sthFalse_thenFalse() {
    
    FluentConditionals result = FluentConditionals.when(FluentConditionalsTest::sthFalse);
    
    assertFalse(result.isCondition());
  }
  
  @Test
  public void when_methodReference_sthTrue_thenTrue() {
    
    FluentConditionals result = FluentConditionals.when(FluentConditionalsTest::sthTrue);
    
    assertTrue(result.isCondition());
  }
  
  @Test
  public void when_true_thenVerifyTimes_withMockito() {
    
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(true)
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
    
    FluentConditionals.when(false)
        .then(runnable)
        .then(runnable)
        .orElse(elseRunnable);
    
    verify(runnable, times(0)).run();
    verify(elseRunnable, times(1)).run();
  
    Runnable doNothing = FluentConditionals.doNothing;
  
    assertNotNull(doNothing);
  }
  
  @Test
  public void check_doNothing_() {
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(true)
        .then(runnable)
        .then(runnable)
        .orElse(elseRunnable);
    
    verify(runnable, times(2)).run();
    
    Runnable doNothing = FluentConditionals.doNothing;
    
    assertNotNull(doNothing);
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_boolean() {
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(false)
        .then(runnable)
        .then(runnable)
        .orElseThrow(new RuntimeException());
    
    verify(runnable, times(2)).run();
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_methodReference() {
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(FluentConditionalsTest::sthFalse)
        .then(runnable)
        .then(runnable)
        .orElseThrow(new RuntimeException());
    
    verify(runnable, times(2)).run();
  }
  
  @Test(expectedExceptions = RuntimeException.class)
  public void orElseThrow_methodReferenceForFalse_methodReferenceForException() {
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(FluentConditionalsTest::sthFalse)
        .then(runnable)
        .then(runnable)
        .orElseThrow(RuntimeException::new);
    
    verify(runnable, times(2)).run();
  }
  
  @Test
  public void orElseThrow_methodReferenceForTrue_newException() {
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(FluentConditionalsTest::sthTrue)
        .then(runnable)
        .then(runnable)
        .orElseThrow(new RuntimeException());
    
    verify(runnable, times(2)).run();
  }
  
  @Test
  public void orElseThrow_methodReferenceForTrue_methodReferenceForException() {
    runnable = mock(Runnable.class);
    elseRunnable = mock(Runnable.class);
    
    FluentConditionals.when(FluentConditionalsTest::sthTrue)
        .then(runnable)
        .then(runnable)
        .orElseThrow(RuntimeException::new);
    
    verify(runnable, times(2)).run();
  }
  
  public void orElse_intReturn_whenTrue_methodReferenceAsParameter_() {
    
    runnable = mock(Runnable.class);
    
    int result1 = when(FluentConditionalsTest::sthTrue)
        .thenReturn(FluentConditionalsTest::getHighNumber)
        .orElse(FluentConditionalsTest::getLowNumber);
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 1000);
  }
  
  public void orElse_intReturn_whenFalse_methodReferenceAsParameter() {
    
    runnable = mock(Runnable.class);
    
    int result1 = when(FluentConditionalsTest::sthFalse)
        .thenReturn(FluentConditionalsTest::getHighNumber)
        .orElse(FluentConditionalsTest::getLowNumber);
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 123);
  }
  
  public void orElse_intReturn_whenTrue_valueAsParameter() {
    
    runnable = mock(Runnable.class);
    
    int result1 = when(FluentConditionalsTest::sthTrue)
        .thenReturn(FluentConditionalsTest::getHighNumber)
        .orElse(345);
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 1000);
  }
  
  public void orElse_intReturn_whenFalse_valueAsParameter() {
    
    runnable = mock(Runnable.class);
    
    int result1 = when(FluentConditionalsTest::sthFalse)
        .thenReturn(FluentConditionalsTest::getHighNumber)
        .orElse(345);
    
    verify(runnable, times(0)).run();
    
    assertEquals(result1, 345);
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
  
}