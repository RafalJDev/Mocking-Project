package library.conditionals;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static library.mockitoByRJ.given.Given.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GivenTest {
  
  @Mock
  Runnable thenRunnable;
  
  @Mock
  BooleanSupplier whenSupplier;
  
  @Mock
  Supplier<Object> givenSupplier;
  
  @Mock
  Consumer<Object> thenConsumer;
  
  @Mock
  Runnable elseRunnable;
  
  @Mock
  Consumer<Object> elseConsumer;
  
  
  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void givenWhenThen_withoutElse_thenZeroInvocation() {
    given(givenSupplier);
    
    given(givenSupplier)
        .when(whenSupplier);
    
    given(givenSupplier)
        .when(whenSupplier)
        .then(thenConsumer);
    
    given(givenSupplier)
        .when(whenSupplier)
        .then(thenRunnable);
    
    verify(givenSupplier, times(0)).get();
    verify(whenSupplier, times(0)).getAsBoolean();
    verify(thenConsumer, times(0)).accept(givenSupplier.get());
    verify(thenRunnable, times(0)).run();
  }
  
  @DataProvider(name = "givenWhenThenOrElseRunnable")
  public Object[][] givenWhenThenOrElse() {
    return new Object[][]{
        {getString(), true, 1, 1, 1, 0},
        {getString(), false, 0, 1, 0, 1},
    };
  }
  
  @Test(dataProvider = "givenWhenThenOrElseRunnable")
  public void givenSupplier_(String givenReturn, boolean whenReturn,
                             int givenInvocation, int whenInvocation,
                             int thenInvocation, int elseInvocation) {
    
    mockitoWhenThenReturn(givenReturn, whenReturn);
    
    given(givenSupplier)
        .when(whenSupplier)
        .then(thenRunnable)
        .orElse(elseRunnable);
    
    verifyAll_thenRunnable_elseRunnable(givenInvocation, whenInvocation, thenInvocation, elseInvocation);
  }
  
  @DataProvider(name = "givenWhenThenOrElseConsumer")
  public Object[][] givenWhenThenOrElseConsumer() {
    return new Object[][]{
        {getString(), true, 1, 1, 1, 0},
        {getString(), false, 0, 1, 0, 1},
    };
  }
  
  @Test(dataProvider = "givenWhenThenOrElseConsumer")
  public void given_when_then_orElseConsumer(String givenReturn, boolean whenReturn,
                                             int givenInvocation, int whenInvocation,
                                             int thenInvocation, int elseInvocation) {
    
    mockitoWhenThenReturn(givenReturn, whenReturn);
    
    given(givenSupplier)
        .when(whenSupplier)
        .then(thenRunnable)
        .orElse(elseConsumer);
    
    //TODO
//    verifyAll_thenRunnable_elseRunnable(givenInvocation, whenInvocation, thenInvocation, elseInvocation);
  }
  
  private void mockitoWhenThenReturn(String givenReturn, boolean whenReturn) {
    Mockito.when(givenSupplier.get())
        .thenReturn(givenReturn);
    
    Mockito.when(whenSupplier.getAsBoolean())
        .thenReturn(whenReturn);
  }
  
  private void verifyAll_thenRunnable_elseRunnable(int givenInvocation, int whenInvocation,
                                                   int thenInvocation, int elseInvocation) {
    verify(givenSupplier, times(givenInvocation)).get();
    verify(whenSupplier, times(whenInvocation)).getAsBoolean();
    verify(thenRunnable, times(thenInvocation)).run();
    verify(elseRunnable, times(elseInvocation)).run();
  }
  
  private String getString() {
    return "STRING";
  }
}