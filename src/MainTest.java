import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void TestGenTransmitterAndQubitStream(){
        Assert.assertEquals(1024, Main.generateTransmitterValues(1024).length);
        Assert.assertEquals(1024, Main.transmitterInfos.length);
    }

    @Test
    public void TestReceiverValues(){
        Main.generateReceiverValues(new Qubit[] {new Qubit(1, 1)});

        Assert.assertEquals(1, Main.receiverInfos.length);
    }

    @Test
    public void TestFindSharedKey(){
      Assert.assertEquals("1", Main.findSharedKey(new int[][] { new int[]{1, 1} }, new int[] {1}));
    }

    @Test
    public void TestGetOnlyPolarizations(){
        int[] result = Main.getOnlyPolarizations(new int[][] { new int[]{0, 1} });
       Assert.assertEquals(1, result.length);
       Assert.assertEquals(0, result[0]);
    }
}
