package lab04;

import java.util.Scanner;

public class MazeSolver {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String first = sc.nextLine();
		String[] l1 = first.split(",");
		int row = Integer.parseInt(l1[0]);
		int col = Integer.parseInt(l1[1]);

		int Srow = 0;//row of S
		int Scol = 0;//col of S
		
		char[][] maze = new char[row][col];
		/*
		 *getting the position of x
		 */
		for (int i = 0; i <row; i++) {
			String temp = sc.nextLine();
			for (int j = 0; j <col; j++) {
				maze[i][j]=temp.charAt(j);
				if (temp.charAt(j) == 'S') {
					Srow = i;
					Scol = j;
				}
			}
		}
		/*
		 *if the path return true, then print out the solution.
		 *if it is false then there are no solutions.
		 */
		if (!path(maze, Srow, Scol)) {
			System.out.println("No solution.");

		} else {
			maze[Srow][Scol] = 'S';
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					System.out.print(maze[i][j]);
				}
				System.out.println();
			}
		}
		sc.close();
	}
	/**
	 * A boolean method that return true if there are solutions about the maze
	 * return false when they are no solutions.
	 * @param char[][]maze the 2D array of maze
	 * @param rowLoc the start row
	 * @param colLoc the start column
	 */
	private static boolean path(char[][] maze, int rowLoc, int colLoc) {
		if (rowLoc < 0 || colLoc < 0 || rowLoc >= maze.length || colLoc >= maze[0].length) {
			return false;
		} else if (maze[rowLoc][colLoc] == 'X') {
			return false;
		} else if (maze[rowLoc][colLoc] == '.') {
			return false;
		} else if (maze[rowLoc][colLoc] == 'E') {
			return true;
		}

		maze[rowLoc][colLoc] = '.'; //replace ' ' with '.'
		if (path(maze, rowLoc + 1, colLoc)) {
			return true;
		}if (path(maze, rowLoc - 1, colLoc)) {
			return true;
		}if (path(maze, rowLoc, colLoc + 1)) {
			return true;
		}if (path(maze, rowLoc, colLoc - 1)) {
			return true;
		} 
			maze[rowLoc][colLoc] = ' ';//remove '.' if didn't pass all if
			return false;
		}
	}