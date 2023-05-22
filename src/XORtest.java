import org.junit.Assert;
import org.junit.Test;

public class XORtest {

    @Test
    public void TestXORSame(){
        Assert.assertEquals("0", XORciphering.xor("1", "1"));
    }

    @Test
    public void TestXORSameZeros(){
        Assert.assertEquals("0", XORciphering.xor("0", "0"));
    }

    @Test
    public void TestXORDifferent(){
        Assert.assertEquals("1", XORciphering.xor("0", "1"));
    }

    @Test
    public void TestXORDifferentReversed(){
        Assert.assertEquals("1", XORciphering.xor("1", "0"));
    }

    @Test
    public void TestXORShorterKey(){
        Assert.assertEquals("101", XORciphering.xor("010", "1"));
    }

    @Test
    public void TestXORLongerKey(){
        Assert.assertEquals("1", XORciphering.xor("1", "010"));
    }
}
