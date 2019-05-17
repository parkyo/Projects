package lab07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Amara Anigbo and Younse Park 
 * @date 03/26/19
 * @class COMP232 
 *
 */
public class FerryBoat {

	public static void main(String[] args) {

		Scanner k = new Scanner(System.in);
		//for each side, initialize the car queues
		Queue <Car> left = new LinkedList<Car>();
		Queue <Car> right = new LinkedList<Car>();
		
		//find the maximum length=>convert from m to cm
		int maxL = k.nextInt() * 100;
		//read the total nr of cars
		int numCar = k.nextInt();

		//initial, no trip was made and the ferry is on the left side
		int trip = 0;
		//cur will keep track of the current side 
		String cur = "left";
		//there are no cars on the ferry
		int total = 0;
		String ferry = "";
		
		
		//if there are no cars waiting print Day Off!!!
		if(numCar == 0){
			System.out.println("Day Off!");
		}

		//adding cars the right queue 
		//put the cars waiting on the left in the left queue, and the cars waiting on the right in the right queue
		for (int i = 0; i < numCar; i++){
			//read the new car : length, number, side
			Car c = new Car (k.nextInt(), k.next(), k.next());
			//check on which side it is and add it to the queue
			if(c.side.equals("left")){
				left.add(c);
			}else{
				right.add(c);
			}
		}

		k.close(); //Close scanner 


		//travel while there are cars waiting on either side, and stop when you get home
		while( !left.isEmpty() || !right.isEmpty() ){
			//if we are on the left =>charge while the ferry is not full. and while there are still cars waiting
			if( cur.equals("left")){
				while ( !left.isEmpty() && total + left.peek().length<= maxL){
					total = total + left.peek().length;//keep track of the length
					ferry = ferry + " " + left.remove().plate;//add to the ferry
				}


				//when done loading, make a trip to the other side
				trip++;

				//if the ferry is empty, then no cars were carried
				//printing functionality where it prints out 
				if(ferry.equals("")){
					System.out.println(trip + " : empty");
				}else{
					//if the ferry was charged, then print the transported cars in the respective trip
					System.out.println(trip + " :" + ferry);
				}


				//unload the ferry, change the side
				//reset all the variables 
				total = 0;
				ferry = "";
				cur = "right";

			}

			//same procedure, but for the right side
			//load while it is not full
			if( cur.equals("right")){

				while (!right.isEmpty() && total + right.peek().length <= maxL ){
					total = total + right.peek().length;
					ferry = ferry + " " + right.remove().plate;
				}
				//make a trip to the other side
				trip++;
				//print the transported cars, if any
				if(ferry.equals("")){
					System.out.println(trip + " : empty");
				}else{
					System.out.println(trip + " :" + ferry);
				}
				
				//empty the boat, change side
				//the left side was done first, so we made sure that we end up on the left side as well
				total = 0;
				ferry = "";
				cur = "left";

			}
		}
	}

}



/**
 * Car class creates a car object that helps keep track of the
 * length of the car, the number and the side of the car
 *
 */
class Car {
	int length;
	String plate;
	String side;
	/**
	 * 
	 * @param length length of the car
	 * @param plate the number of the car 
	 * @param side tells on which side the car is, so that we know where we need to load and unload it
	 */
	Car(int length, String plate, String side){
		this.length = length;
		this.plate = plate;
		this.side = side;
	}
}