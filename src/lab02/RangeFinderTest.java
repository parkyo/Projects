package lab02;

import org.junit.Test;

import testbase.StdioTestBase;

public class RangeFinderTest extends StdioTestBase {

	@Test
	public void testSample1() {
		// Input from a string in the test.
		String input = "1 2 3 4 5";
		// Output from a string in the test
		String output = "The range is 4.";
		
		runTest(RangeFinder.class, input, output,
				"Incorrect result for sample input.");
	}

}
