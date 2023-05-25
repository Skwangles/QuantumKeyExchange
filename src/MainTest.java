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

    @Test
    public void PSKAreFound(){
        // Stream of Qubits from one to the other
        Main.generateReceiverValues(Main.generateTransmitterValues(1024));

        //Share polarizations with one another and infer key
        String agreedKeyTransmitter = Main.findSharedKey(Main.transmitterInfos, Main.getOnlyPolarizations(Main.receiverInfos));
        String agreedKeyReceiver = Main.findSharedKey(Main.receiverInfos, Main.getOnlyPolarizations(Main.transmitterInfos));
        Assert.assertEquals(agreedKeyReceiver, agreedKeyTransmitter);
    }

    @Test
    public void AttackerDoesNotFind(){
        // Stream of Qubits from one to the other
        Qubit[] stream = Main.generateTransmitterValues(1024);

        //Simulate attacker intercepting
        for (int i = 0; i < stream.length; i++){
            stream[i].measure(Math.random() > 0.5 ? 1 : 0);
        }

        // Read as if nothing happened
        Main.generateReceiverValues(stream);

        //Share polarizations with one another and infer key
        String agreedKeyTransmitter = Main.findSharedKey(Main.transmitterInfos, Main.getOnlyPolarizations(Main.receiverInfos));
        String agreedKeyReceiver = Main.findSharedKey(Main.receiverInfos, Main.getOnlyPolarizations(Main.transmitterInfos));

        //PSK should be different, as attacker has measured the qubits and changed it
        Assert.assertNotEquals(agreedKeyReceiver, agreedKeyTransmitter);
    }
}
