package lab11;
import java.util.Random;

import org.junit.Test;

import testbase.StdioTestBase;

public class PlayingWithWheelsTest extends StdioTestBase {
		
	@Test
	public void A1_SampleInput1() {
		String input = "1335\n2244\n3456\n4567";
		String output = "4";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void A2_SampleInput2() {
		String input = "1234\n1236\n1236";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void A3_SampleInput3() {
		String input = "1234\n1236\n1233\n1235\n1244\n1224\n1134\n1334\n0234\n2234";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 3.");
	}
	
	@Test
	public void A4_SampleInput4() {
		Random rnd = new Random();
		String input = "1234\n2938";
		for (int i=0; i<100; i++) {
			input = input + "\n" + String.valueOf(rnd.nextInt(9999));
		}
		String output = "8";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 4.");
	}
	@Test
	public void A5_SampleInput5() {
		String input = "1234\n1236\n1234";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 5.");
	}
}