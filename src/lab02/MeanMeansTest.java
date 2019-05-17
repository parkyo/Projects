package lab02;

import java.io.File;

import org.junit.Test;

import testbase.StdioTestBase;

public class MeanMeansTest extends StdioTestBase {

	@Test
	public void testSample1() {
		String input = "-1\n";
		String output = "No lines.";


		runTest(MeanMeans.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void testSample2() {
		// Input from a text file
		File input = new File("src/lab02/meanmeans2.txt");
		// Output from an object the test
		Double output = 3.18;

		runTest(MeanMeans.class, input, output,
				"Incorrect result for sample input 2.");
	}

	@Test
	public void testSampe3() {
		File input = new File("src/lab02/meanmeans3.txt");
		File output = new File("src/lab02/meanmeans3output.txt");

		runTest(MeanMeans.class, input, output, "Incorrect result for sample input 3.");
	}
	
	@Test
	public void testSample4() {
		String input = "-1,-1\n-1,-1\n-1";
		String output = "-1.00";


		runTest(MeanMeans.class, input, output,
				"Incorrect result for sample input 4.");
	}
}
