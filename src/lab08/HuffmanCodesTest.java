package lab08;

import org.junit.Test;

import testbase.StdioTestBase;

public class HuffmanCodesTest extends StdioTestBase {
		
	@Test
	public void testSampleInput1() {
		String input = "F\nAABBBCDDDDEE";
		String output = "A:2\nB:3\nC:1\nD:4\nE:2\n";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 1.");
	}
	
	@Test
	public void testSampleInput2() {
		String input = "T\nAABBBCDDDDEE";
		String output = "A:12\nB:7\nA:5\nD:4\nB:3\nA:3\nE:2\nA:2\nC:1";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void testSampleInput3() {
		String input = "H\nAABBBCDDDDEE";
		String output = "A:100\nB:01\nC:101\nD:00\nE:11";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 3.");
	}
	
	@Test
	public void testSampleInput4() {
		String input = "M\nAABBBCDDDDEE";
		String output = "100100010101101000000001111";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 4.");
	}
}