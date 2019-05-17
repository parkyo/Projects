package lab09;

import org.junit.Test;

import testbase.StdioTestBase;

public class SalesDataTest extends StdioTestBase {
		
	@Test
	public void A1_SampleInput1() {
		String input = "A 3/1/2015 3.45\nA 3/4/2016 1.23\nA 4/1/2015 6.78\nS\n";
		String output = "$1.23";

		runTest(SalesData.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void A2_SampleInput2() {
		String input = "A 3/1/2015 3.45\nA 3/4/2016 1.23\nA 4/1/2015 6.78\nK 2\n";
		String output = "$8.01";

		runTest(SalesData.class, input, output,
				"Incorrect result for sample input 2.");
	}
}
