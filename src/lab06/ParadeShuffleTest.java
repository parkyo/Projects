package lab06;
 
import org.junit.Test;
 
import testbase.StdioTestBase;
 
public class ParadeShuffleTest extends StdioTestBase {
 
    @Test
    public void testSampleInput1() {
        String input = "4\n1 2 3 4\n1 3 2 4";
        String output = "Yes.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 1.");
    }
 
    @Test
    public void testSampleInput2() {
        String input = "4\n1 2 3 4\n1 4 2 3";
        String output = "No.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
 
    @Test
    public void test1() {
        String input = "4\n4 3 2 1\n4 1 3 2";
        String output = "No.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
 
    @Test
    public void test2() {
        String input = "1\n1\n1";
        String output = "Yes.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
 
    @Test
    public void test3() {
        String input = "1\n1\n1";
        String output = "Yes.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
 
    @Test
    public void test4() {
        String input = "2\n1 2\n1 2";
        String output = "Yes.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
 
    @Test
    public void test5() {
        String input = "6\n1 4 5 6 2 3\n6 5 3 2 4 1";
        String output = "Yes.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
 
    @Test
    public void test6() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 1000; i++) {
            sb.append(i + " ");
        }
        String input = ("1000\n" + sb.toString() + "\n" + sb.toString());
        String output = "Yes.";
 
        runTest(ParadeShuffle.class, input, output, "Incorrect result for sample input 2.");
    }
}