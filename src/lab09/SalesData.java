package lab09;

import java.util.*;
import lab09.CS232LinkedAVLTree.AVLNode;

public class SalesData {

	static CS232LinkedAVLTree<Calendar, Double> tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		tree = new CS232LinkedAVLTree<Calendar, Double>(); // create AVL tree
		
		while (sc.hasNextLine()) { // read all the inputs
			String line = sc.nextLine();
			String[] saleData = line.split(" ");// split the input line by space
			char first = line.charAt(0); // first == the First character to determines operations

			if (first == 'A') { // if A, add a sales record
				addSales(saleData);
			} else if (first == 'D') {// if D, delete a sales record for the given date
				deleteSale(saleData);
			} else if (first == 'S') {// if S: get the amount of most recent sale
				System.out.println("$" + String.format("%.2f", getMostRecent()));
			} else if (first == 'K') {// if K: get the total amount of the k most recent sales
				System.out.println("$" + String.format("%.2f", getKMostRecentSales(saleData)));
			} else if (first == 'G') {// if G, Get the total amount of sales in the k days starting on a
				// given date
				GregorianCalendar tempCal = findCal(saleData, 1);
				int numDays = Integer.parseInt(saleData[2]);
				GregorianCalendar compareCal = (GregorianCalendar) tempCal.clone();
				compareCal.add(Calendar.DAY_OF_MONTH, (numDays - 1));
				System.out.println("$" + String.format("%.2f", getAllSalesKDaysAfterDate(tempCal, compareCal)));
			} else if (first == 'R') {// if first is R, this gets total amount of all sales within a range of dates
				// Store the first and second date to find sales in between them
				GregorianCalendar dateOne = findCal(saleData, 1);
				GregorianCalendar dateTwo = findCal(saleData, 2);

				System.out.println("$" + String.format("%.2f", getTotalSaleBetweenDate(dateOne, dateTwo)));
			}
		}
		sc.close();
	}

	/**
	 * Create a new calendar with the input given from the scanner.
	 * 
	 * @param salesData, the input
	 * @param index, the index for calendar information
	 * @return gCal, the calendar
	 */
	private static GregorianCalendar findCal(String[] salesData, int index) {
		// Split the string into month/date/year and create a calendar in a correct order of dates
		String[] dates = salesData[index].split("/");
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(dates[2]), 
				Integer.parseInt(dates[0]),Integer.parseInt(dates[1]));
		return cal;
	}

	/**
	 * add sales record
	 * 
	 * @param saleData, the input
	 */

	public static void addSales(String[] sale) {
		String data = sale[1];
		GregorianCalendar date = getDate(data);
		double saleValue = Double.parseDouble(sale[2]);
		tree.add(date, saleValue);
	}

	/**
	 * delete the sales record from tree
	 * 
	 * @param saleData
	 */

	public static void deleteSale(String[] saleData) {
		String unparsedDate = saleData[1];
		GregorianCalendar date = getDate(unparsedDate);
		tree.remove(date);
	}

	/**
	 * this method returns the most recent sale
	 * 
	 * @return the most recent sale
	 */

	public static double getMostRecent() {
		AVLNode<Calendar, Double> right = getRightMostNode((AVLNode<Calendar, Double>) tree.root);// right most sale
		// in the tree

		return right.value;
	}

	/**
	 * Recursive helper method in finding the most recent sale above in the tree.
	 * 
	 * @param node, the node being traversed
	 * @return most recent sale
	 */
	public static AVLNode<Calendar, Double> findBiggestKey(AVLNode<Calendar, Double> node) {
		// returns the right most node down the right tree
		if (node.right == null) {
			return node;
		} else {
			return findBiggestKey((AVLNode<Calendar, Double>) node.right);
		}
	}

	/**
	 * Returns the most recent sale from the
	 * 
	 * @param saleData, the input array
	 * @return the total recent sales from K
	 */

	public static double getKMostRecentSales(String[] saleData) {
		int numSales = Integer.parseInt(saleData[1]);// number of sales wanted from K

		// first finds the most recent sale
		AVLNode<Calendar, Double> node = getRightMostNode((AVLNode<Calendar, Double>) tree.root);
		double totalSales = 0.0;
		for (int i = 1; i <= numSales; i++) {
			if (node != null) {
				totalSales += node.value;
			}
			node = getPredecessorNode(node);

		}

		return totalSales;
	}

	/**
	 * Recursive helper method to find the most recent sale in the tree
	 * 
	 * @param curNode, the node being traversed
	 * @return most recent sale
	 */
	public static AVLNode<Calendar, Double> getRightMostNode(AVLNode<Calendar, Double> subroot) {
		// Get the right most node recursively down the sales tree
		if (subroot.right == null) {
			return subroot;
		} else {
			return getRightMostNode((AVLNode<Calendar, Double>) subroot.right);
		}
	}

	/**
	 * finds the sales at specified date and returns "k" sales from the specified
	 * dates in the input
	 * 
	 * @param startDate, start date of sales
	 * @param endDate, ending date of sales after "k" sales
	 * @return totalOfSales, the total sales from the period of dates
	 */
	public static Double getAllSalesKDaysAfterDate(GregorianCalendar startDate, GregorianCalendar endDate) {
		Double totalOfSales = 0.0;

		// Find the node with the start date
		AVLNode<Calendar, Double> startNode = findNode(startDate, (AVLNode<Calendar, Double>) tree.root);

		// for only k days, add the sales k days after start date.
		while (startNode != null && (startNode.key.before(endDate) || startNode.key.equals(endDate))) {

			totalOfSales += startNode.value;
			startNode = findNextKey(startNode);
		}

		return totalOfSales;
	}

	/**
	 * Finds all the sales in between two specified dates from input.
	 * 
	 * @param startDate, date to start
	 * @param endDate, date to end
	 * @return totalOfSales, sales from the specified dates
	 */
	public static Double getTotalSaleBetweenDate(GregorianCalendar startDate, GregorianCalendar endDate) {
		// Make sure the start date really is the start date
		if (startDate.compareTo(endDate) > 0) {
			GregorianCalendar temp = endDate;
			endDate = startDate;
			startDate = temp;
		}

		// Find the node with the starting date
		AVLNode<Calendar, Double> givenDate = findNode(startDate, (AVLNode<Calendar, Double>) tree.root);

		// in case no such date exists
		if (givenDate == null) {
			tree.add(startDate, new Double(0));
			givenDate = findNode(startDate, (AVLNode<Calendar, Double>) tree.root);
		}

		Double totalOfSales = new Double(0);

		// ensures we are still in the period of specified dates
		while (givenDate != null && givenDate.key.compareTo(endDate) <= 0) {
			totalOfSales += givenDate.value;

			// get the next sales
			givenDate = findNextKey(givenDate);
		}

		totalOfSales = Math.round(totalOfSales * 100) / 100.0;
		return totalOfSales;
	}

	/**
	 * 
	 * @param unparsedDate the string of date that we parse
	 * @return date, the Gregorian calendar
	 */
	public static GregorianCalendar getDate(String unparsedDate) {
		String[] dateData = unparsedDate.split("/");
		int month = Integer.parseInt(dateData[0]);
		int day = Integer.parseInt(dateData[1]);
		int year = Integer.parseInt(dateData[2]);
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		return date;
	}

	/**
	 * return the node previous to the given node
	 * 
	 * @param subroot, used for the predecessor search
	 * @return the predecessor node
	 */
	private static AVLNode<Calendar, Double> getPredecessorNode(AVLNode<Calendar, Double> subroot) {
		// get right most of left subtree after checking left child
		if (subroot.left != null) {
			subroot = getRightMostNode((AVLNode<Calendar, Double>) subroot.left);
			return subroot;
		}
		// get a right child by traversing the tree
		else {
			while (subroot.parent != null && subroot.parent.left == subroot) {
				subroot = (AVLNode<Calendar, Double>) subroot.parent;
			}
			return (AVLNode<Calendar, Double>) subroot.parent;
		}
	}

	/**
	 * Recursive helper method to find the oldest sale in the tree
	 * 
	 * @param the subroot the node to be traversed.
	 * @return the oldest sale
	 */
	public static AVLNode<Calendar, Double> getLeftMostNode(AVLNode<Calendar, Double> subroot) {
		// Recurse the tree for the left most node in the sales tree
		if (subroot.left == null) {
			return subroot;
		} else {
			return getLeftMostNode((AVLNode<Calendar, Double>) subroot.left);
		}
	}

	/**
	 * This method returns the successor of the given node
	 * 
	 * @param subroot, used to find successor node
	 * @return successor node
	 */
	private static AVLNode<Calendar, Double> findNextKey(AVLNode<Calendar, Double> subroot) {
		// for the node with the right child, the leftmost in right subtree will be
		// determined
		// as successor
		if (subroot.right != null) {
			subroot = getLeftMostNode((AVLNode<Calendar, Double>) subroot.right);
			return subroot;
		}
		// traverse the tree towards up to find a left child
		else {
			while (subroot.parent != null && subroot.parent.right == subroot) {
				subroot = (AVLNode<Calendar, Double>) subroot.parent;
			}
			return (AVLNode<Calendar, Double>) subroot.parent;
		}
	}

	/**
	 * FindNode finds the node with date being the Key
	 * 
	 * @param date
	 * @param subroot
	 * @return the node with the date
	 */
	private static AVLNode<Calendar, Double> findNode(GregorianCalendar date, AVLNode<Calendar, Double> subroot) {
		// in case if the node is not resent in the tree
		if (subroot == null) {
			return null;
		}else if(subroot.key.equals(date)) {
		// find the node
			return subroot;
		}
		// traverse the tree
		else {
			if (subroot.key.compareTo(date) > 0) {
				return findNode(date, (AVLNode<Calendar, Double>) subroot.left);
			} else {
				return findNode(date, (AVLNode<Calendar, Double>) subroot.right);
			}
		}
	}
}

