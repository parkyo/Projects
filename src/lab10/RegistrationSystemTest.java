package lab10;
import org.junit.Test;

import testbase.StdioTestBase;

public class RegistrationSystemTest extends StdioTestBase {
		
	@Test
	public void A1_SampleInput1() {
		String input = "A benrush Benjamin Rush\nA johndickinson John Dickinson";
		String output = "OK\nOK";

		runTest(RegistrationSystem.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void A2_SampleInput2() {
		String input = "A benrush Benjamin Rush\nI benrush";
		
		
		
		String output = "OK\nBenjamin Rush";

		runTest(RegistrationSystem.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void A3_SampleInput3() {
		String input = "A benrush Benjamin Rush\nA benrush Fake Dude";
		String output = "OK\nbenrush1";

		runTest(RegistrationSystem.class, input, output,
				"Incorrect result for sample input 3.");
	}
	
}
