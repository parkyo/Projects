package lab06;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ParadeShuffle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //create scanner object
		int num = sc.nextInt();//scan the number of floats in a line
		sc.nextLine();//change the line

		Queue<Integer> orig = new LinkedList<Integer>();
		
		for(int i = 0; i<num; i++) { //loop through and store each integer into the array
			orig.add(sc.nextInt());
		}
		sc.nextLine(); //change the line

		Queue<Integer> res = new LinkedList<Integer>();
	
		for(int i = 0; i<num; i++) { //loop through and store each integer into the array
			res.add(sc.nextInt());
		}
		
		if (num < 1 || num > 1000 || !checkFloat(orig, res)) {
			System.out.println("No.");
		}else {
			System.out.println("Yes.");
		}

//		if (checkFloat(orig, res)) {
//			System.out.println("Yes.");
//		}else {
//			System.out.println("No.");
//		}

		//		for(int i =0; i<orig.length; i++) {
		//			System.out.print(orig[i]);
		//		}

		sc.close();

	}
	
	/**
	 * Change the order of numbers in the orig to that of res according to the rules. 
	 * Return true if it is possible to change the orig identical to res. 
	 * @param orig
	 * @param res
	 * @return
	 */
	private static boolean checkFloat(Queue<Integer> orig, Queue<Integer> res) {
		Stack<Integer> stack = new Stack<Integer>();

		while (res.size() != 0){
			if (orig.peek() == res.peek()){
				res.remove();
				orig.remove();
			}else if (!stack.isEmpty() && stack.peek() == res.peek()){
				stack.pop();
				res.remove();
			}else if(!orig.isEmpty()) {
				stack.push(orig.poll());
			}else {
				break;
			}
			
		}

		if (stack.empty() && orig.isEmpty()) {
			return true;
		} else {
			return false;
		}

//		while(res.size() != 0 ) {
//			int findNum = res.poll();
//			if(!stack.empty()&& stack.peek() == findNum) {
//				stack.pop();
//			} else {
//				if (orig.isEmpty()) {
//					return false;
//				}
//				while (!orig.isEmpty()) {
//					int curNum = orig.poll();
//					if (curNum == findNum) {
//						break;
//					} else
//						stack.push(curNum);
//				} 
//				
//				orig.poll();
//			}
//		}
//		if (stack.empty() && orig.isEmpty()) {
//			return true;
//		} else {
//			return false;
//		}	
//		int iterator = 0; //the last index of number that matches the resu

//		for(int i =0; i<orig.length; i++) { //change to while
//			if(orig[i] != res[i]) {
//				stack.add(orig[i]);
//				orig[i] = 0;
//				//				if (i < orig.length-1 && res[i+1] > res[i]) { //condition error
//				int j = iterator +1;
//				while(stack.size()>0 && stack.peek() == res[j]) {
//					orig[j] = stack.pop();
//					j++;
//				}
//
//				//				}
//			}else {
//				iterator = i;
//			}
//		}
//		for (int i=0;i<orig.length;i++) {
//			System.out.println(orig[i]);	
//		}
//
//
//		if(checkMatch(orig, res)) {
//			System.out.println("Yes.");
//		}else {
//			System.out.println("No.");
//		}
	}

}
