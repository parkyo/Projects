package lab02;
import java.util.ArrayList;
import java.util.Scanner;

public class RangeFinder {

	
	private static int rangeFind(ArrayList<Integer> array) {
		int min = array.get(0);
		int max = array.get(0);
		for(int i = 1; i<array.size(); i++){
			if (array.get(i) > max) {
				max = array.get(i);
			}else if (array.get(i)<min) {
				min = array.get(i);
			}
		}
		return max-min;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<Integer> list = new ArrayList<>();
		while(sc.hasNextInt()) {
			list.add(sc.nextInt());
			//System.out.println(list.);

		}

		sc.close();

		int range = rangeFind(list);

		System.out.print("The range is " + range + ".");
	}


	

}
