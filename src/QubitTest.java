import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class QubitTest {

    @Test
    public void TestNewAndMeasureLinear(){
        Qubit qubit = new Qubit(1, 1);
        Assert.assertEquals(1, qubit.measure(1));
    }

    @Test
    public void TestSetLinear(){
        Qubit qubit = new Qubit(1, 1);
        qubit.set(0, 1);
        Assert.assertEquals(0, qubit.measure(1));
    }

    @Test
    public void TestNewAndMeasureCircular(){
        Qubit qubit = new Qubit(1, 0);
        Assert.assertEquals(1, qubit.measure(0));
    }

    @Test
    public void TestSetCircular(){
        Qubit qubit = new Qubit(1, 0);
        qubit.set(0, 0);
        Assert.assertEquals(0, qubit.measure(0));
    }

    @Test
    public void TestSetChangePolarization(){
        Qubit qubit = new Qubit(1, 0);
        qubit.set(0, 1);
        Assert.assertEquals(0, qubit.measure(1));
    }

    @Test
    public void TestPolarizationFlipsValue(){
        Qubit qubit = new Qubit(1, 1);
        boolean isAnyFlipped = false;
        for (int i = 0; i < 500; i++){ // Mathmatically improbable that all 500 random value changes will be to 1
            // Continously flip polarization until it returns 1
            if (qubit.measure(i % 2) != 1){
                isAnyFlipped = true;
                break;
            }
        }
        Assert.assertTrue(isAnyFlipped);
    }


}
