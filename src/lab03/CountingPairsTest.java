package lab03;

import java.io.File;

import org.junit.Test;

import testbase.StdioTestBase;

public class CountingPairsTest extends StdioTestBase {

	@Test
	public void testSample1() {
		// Input from a string in the test.
		String input = "5 2\n1 5 3 4 2";
		Integer output = 3;
		runTest(CountingPairs.class, input, output,
				"Incorrect result for sample input 1.");
	}
	
	
	@Test
	public void testSample2() {
		// Input from a text file in the package.
		File input = new File("src/lab03/input2.txt");
		Integer output = 0;
		runTest(CountingPairs.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void testSample3() {
		String input = "8 2\n1 1 1 3 3 5 5 6";
		Integer output = 10;
		runTest(CountingPairs.class, input, output,
				"Incorrect result for sample input 3.");
	}
	
	@Test
	public void testDiffZeroSameNum() {
		String input = "4 0\n2 2 2 2";
		String output = "6";
		runTest(CountingPairs.class, input, output,
				"Incorrect result for sample input 4.");
	}
	
	@Test
	public void testNoNumber() {
		String input = "0 2\n";
		String output = "0";
		runTest(CountingPairs.class, input, output,
				"Incorrect result for sample input 5.");
	}
	
	@Test
	public void testDiffZero() {
		String input = "5 0\n2 2 2 3 3";
		String output = "4";
		runTest(CountingPairs.class, input, output,
				"Incorrect result for sample input 5.");
	}
	
}
