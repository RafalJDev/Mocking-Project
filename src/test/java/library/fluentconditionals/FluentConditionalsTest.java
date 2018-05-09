package library.fluentconditionals;

import org.mockito.Mock;
import org.testng.annotations.Test;

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
  }
  
  public static boolean sthTrue() {
    return true;
  }
  
  public static boolean sthFalse() {
    return false;
  }
  
}