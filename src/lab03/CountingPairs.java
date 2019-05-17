package lab03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CountingPairs {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();//read the length of the list
		int k = sc.nextInt();//read the difference
		
		
		long[] nums = new long[n];//create an array of length n
		
		for(int i =0; i<n; i++) {
			nums[i] = sc.nextInt();//read the list of numbers
		}
		
		HashMap<Long,Long> list = new HashMap<Long,Long>();
				
		for(int i = 0; i<nums.length; i++) {
			if(list.containsKey(nums[i])) {//if it is a repeated number, increase the value of the key
				list.put(nums[i], list.get(nums[i])+1);
			}else {
				list.put(nums[i], (long) 1);//otherwise, put the number in as a key and value as 1
			}
		}
		
		
		long total = 0;
		
		
		for(long key : list.keySet()) {
			if(list.containsKey(key+k) && k!=0) {//if the difference is not 0
				total = total + (list.get(key)*list.get(key+k));
			}else if (k == 0) { //if the difference is 0
				long num = list.get(key)-1;
				total = total + (1+num)*num/2;//referred from n(n+1)/n formula
			}
		}
		
		
//		int count = 0;
//		for(int i =0; i<nums.length-1; i++) {
//			for(int j = i+1; j<nums.length; j++) {
//				if (Math.abs(nums[i]-nums[j]) == k) {
//					count ++;
//				}
//			}
//		}
		
		System.out.println(total);
		
		sc.close();
	}
}
