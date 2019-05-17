package lab04;

import java.io.File;

import org.junit.Test;

import testbase.StdioTestBase;

public class MazeSolverTest extends StdioTestBase {

	@Test
	public void testSample1() {
		File input = new File("src/lab04/maze1in.txt");
		File output = new File("src/lab04/maze1out.txt");

		runTest(MazeSolver.class, input, output,
				"Incorrect result for sample input 1.");
	}

	
	@Test
	public void sampleInput2() {
		File input = new File("src/lab04/maze2in.txt");
		String output = "No solution.";

		runTest(MazeSolver.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void SNearE() {
		String input = "5,5\nXSXXX\nXEX X\nX   X\nX X X\nXXXXX";
		String output = "XSXXX\nXEX X\nX   X\nX X X\nXXXXX";
		runTest(MazeSolver.class, input, output,
				"Incorrect result for sample SNearE.");
	}
	
	@Test
	public void hasMazeNosolu(){
		String input = "5,5\nXSXXX\nX X X\nX   X\nX XXX\nXXXEX";
		String output = "No solution.";

		runTest(MazeSolver.class, input, output,
				"Incorrect result for hasMazeNosolu.");
	}
	
	@Test
	public void SOnTheSide(){
		String input = "5,5\nXXXXX\nX X S\nX   X\nX X X\nXXXEX";
		String output = "XXXXX\nX X.S\nX  .X\nX X.X\nXXXEX";
		runTest(MazeSolver.class, input, output,
				"Incorrect result for hasMazeNosolu.");
	}
	@Test
	public void reverseSE(){
		String input = "5,5\nXXXEX\nX X X\nX   X\nS X X\nXXXXX";
		String output = "XXXEX\nX X.X\nX...X\nS.X X\nXXXXX";
		runTest(MazeSolver.class, input, output,
				"Incorrect result for hasMazeNosolu.");
	}
}