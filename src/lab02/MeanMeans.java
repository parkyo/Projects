package lab02;


import java.util.ArrayList;
import java.util.Scanner;

public class MeanMeans {

	public static void main(String[] args) {
		//scan each line of numbers into arraylist
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();

		while(sc.hasNextLine()) {
			list.add(sc.nextLine());
		}

		Double sumLine = 0.0;
		int count = 0;

		for(int i =0; i<list.size(); i++) {

			String[] set = list.get(i).split(",");
			if(set.length == 1 && Double.parseDouble(set[0]) == -1) {
				break;
			}
				sumLine = sumLine + mean(set);
				count++;
			

		}

		if (count == 0) {
			System.out.println("No lines.");
		}else {
			Double avgWhole = sumLine/count;

			String s = String.format("%.2f", avgWhole);
			System.out.println(s);
		}
		
		sc.close();
	}

	private static Double mean(String[] set) {
		Double sum = 0.0;

		for(int i = 0; i<set.length; i++) {
			sum = sum + Double.parseDouble(set[i]);
		}
		Double avgline = sum/set.length;
		return avgline;
	}

}

//        Scanner sc = new Scanner(System.in);
//        ArrayList<String> list = new ArrayList<>();
//        list.add(sc.nextLine());
//        while(sc.hasNextLine()) {
//            list.add(sc.nextLine());
//        }
//        
//        //split all the numbers by comma and put them in an array to calculate the average of every line of numbers
//        //if the first line has one number that is -1, print out "No lines."
//        //if the line has one number that is -1, it is the end
//        double totalAvgLine = 0;
//        double avgWhole = 0;
//        
//        for (int i = 0; i<list.size(); i++) {
//            String line = list.get(i);
//            String[] nums = line.split(",");
//            Double[] set = new Double[nums.length];
//            
//            for(int a = 0; a<nums.length; a++) {
//                set[a] = Double.parseDouble(nums[a]);
//            }
//            
//            double total = 0;
//            
//            for(int j = 0; j<set.length; j++) {
//                if(set.length == 1 && set[0] == -1 ) {
//                    if (list.size()== 1) {
//                        System.out.println("No lines.");
//                        return;
//                    }
//                }else {
//                    total = total + set[j];
//                    
//                }
//            }
//            totalAvgLine = totalAvgLine + total/set.length;
//        }
//        
//        avgWhole = totalAvgLine/(list.size()-1);

//        DecimalFormat round = new DecimalFormat("#.##");
//        avgWhole = Double.valueOf(round.format(avgWhole));

//
//        String s = String.format("%.2f", avgWhole); // "123.041”
//        
//        
//        System.out.println(s);



//    
//    private static double round(double value, int places) {
//        if (places < 0) throw new IllegalArgumentException();
//
//        BigDecimal bd = new BigDecimal(value);
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }
//        Scanner sc = new Scanner(System.in);
//        ArrayList<String> list = new ArrayList<>();
//        list.add(sc.nextLine());
//        while(sc.hasNextLine()) {
//            list.add(sc.nextLine());
//        }
//        double totalWhole = 0;
//        double avgOfWhole = 0;
//        for(int i=0; i<list.size(); i++) {
//            String line = list.get(i);
//            String[] array = line.split(",");
//            Double[] set= new Double[array.length];
//            double avgLine= 0;
//            double total = 0;
//                for(int a=0;a<array.length;a++) {
//                    set[a] = Double.parseDouble(array[a]);
//                    if ( i== 0 && set[0] == -1) {
//                        if (a==0) {
//                            System.out.println("No lines.");
//                            return;
//                        }
//                    }else {
//                    total = total + set[a];
//                    avgLine = total/set.length;
//                    }
//                    
//                } 
//            totalWhole = totalWhole + avgLine;
//        }
//        avgOfWhole = totalWhole/list.size();
//        
//        
//        sc.close();
//        System.out.print(avgOfWhole);
//    }
//    

