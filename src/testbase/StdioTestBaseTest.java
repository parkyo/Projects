package testbase;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StdioTestBaseTest extends StdioTestBase {

	@Before
	public void setUp() {
		System.err.println("sub-class setUp");
	}
	
	@After
	public void tearDown() {
		System.err.println("sub-class tearDown");
	}
	
	@Test
	public void testRunTestTrim() {

		String in = "This is a test" + "And some more text"
				+ "\nAnd another line.";

		String out = "This is a test" + "And some more text"
				+ "\nAnd another line.";

		runTest(StdioTestBaseTest.class, in, out, "Input doesn't match output");
	}

	@Test
	public void testRunTestInFile() {
		String out = "This is a test input file.\n"
				+ "It has four lines of text.\n" + "123,456,789\n"
				+ "A\tB	C	D\n";

		runTest(StdioTestBaseTest.class,
				new File("src/testbase/testInput1.dat"), out,
				"Input doesn't match output");
	}

	@Test
	public void testRunTestOutFile() {
		String in = "This is a test input file.\n"
				+ "It has four lines of text.\n" + "123,456,789\n"
				+ "A\tB	C	D\n";

		runTest(StdioTestBaseTest.class, in, new File(
				"src/testbase/testOutput1.dat"), "Input doesn't match output");
	}

	@Test
	public void testRunTestBothFiles() {
		runTest(StdioTestBaseTest.class,
				new File("src/testbase/testInput1.dat"), new File(
						"src/testbase/testOutput1.dat"),
				"Input doesn't match output");
	}

	@Test
	public void testRunTestInWinFile() {
		String out = "This is a test input file.\n"
				+ "It has four lines of text.\n" + "123,456,789\n"
				+ "A\tB	C	D\n";

		runTest(StdioTestBaseTest.class,
				new File("src/testbase/testInput1.win.dat"), out,
				"Input doesn't match output");
	}

	@Test
	public void testRunTestOutWinFile() {
		String in = "This is a test input file.\n"
				+ "It has four lines of text.\n" + "123,456,789\n"
				+ "A\tB	C	D\n";

		runTest(StdioTestBaseTest.class, in, new File(
				"src/testbase/testOutput1.win.dat"), "Input doesn't match output");
	}

	@Test
	public void testRunTestBothWinFiles() {
		runTest(StdioTestBaseTest.class,
				new File("src/testbase/testInput1.win.dat"), new File(
						"src/testbase/testOutput1.win.dat"),
				"Input doesn't match output");
	}
	
	@Test
	public void testRunTestWinInMacOutFiles() {
		runTest(StdioTestBaseTest.class,
				new File("src/testbase/testInput1.win.dat"), new File(
						"src/testbase/testOutput1.dat"),
				"Input doesn't match output");
	}
	
	@Test
	public void testRunTestMacInWinOutFiles() {
		runTest(StdioTestBaseTest.class,
				new File("src/testbase/testInput1.dat"), new File(
						"src/testbase/testOutput1.win.dat"),
				"Input doesn't match output");
	}
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		while (s.hasNext()) {
			System.out.println(s.nextLine());
		}

		s.close();
	}
}
