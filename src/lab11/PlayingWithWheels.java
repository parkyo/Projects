package lab11;

import java.util.*;

public class PlayingWithWheels extends CS232DirectedAdjacencyMatrixGraph<Integer, Integer>{

	private Scanner sc; //scanner to scan numbers
	private HashMap<Integer, Integer> forbidden; //hashmap to store the forbidden configs

	/*
	 * construct an object PlayingWithWheels that contains a graph with 1000 vertices
	 */
	public PlayingWithWheels() {
		super(10000); 
		
		sc = new Scanner(System.in);
		forbidden = new HashMap<Integer, Integer>();
		// TODO Auto-generated constructor stub
	}



	public static void main(String[] args) {
		PlayingWithWheels gr = new PlayingWithWheels();
		int init = gr.sc.nextInt(); //read the initial config
		gr.sc.nextLine();
		int targ = gr.sc.nextInt(); //read the target config
		int answer = -1; //value to return
		if(init == targ) { //if initial is the same as target then returning value is 0
			answer = 0;
		}else { // if not, set up the graph and scan the forbidden configs
			gr.setUp(); 
			gr.addForbidden();
			if(!gr.forbidden.containsValue(targ)) { //if the target is not same as one of the forbidden config, then there is a possibility to be a path
				answer = gr.runWheels(init, targ);
			}
		}
		
		System.out.println(answer);
	}

	/**
	 * set up the graph by adding directed edges of the adjacent numbers
	 */
	private void setUp() {
		for(int i = 0; i<numVertices(); i++) {
			setVertexMark(i, -1);
			//store the number by each digit so that we can change them separately
			int one = i%10;
			int ten = (i/10)%10;
			int hun = (i/100)%10;
			int tho = (i/1000);
			//add edge between vertices that are adjacent
			addEdge(i, tho*1000 + hun *100 + ten*10 + (one+1)%10,-1); //ie. 1000 and 1001
			addEdge(i, tho*1000 + hun *100 + (ten+1)%10*10 + one,-1); //ie. 1090 and 1000
			addEdge(i, tho*1000 + (hun+1)%10 *100 + ten*10 + one,-1); //ie. 1900 and 1000
			addEdge(i, (tho+1)%10*1000 + hun *100 + ten*10 + one,-1); //ie. 9000 and 0000

			addEdge(i, tho*1000 + hun *100 + ten*10 + (one+10-1)%10,-1); //ie. 1000 and 1009
			addEdge(i, tho*1000 + hun *100 + (ten+10-1)%10*10 + one,-1);  //ie. 1000 and 1090
			addEdge(i, tho*1000 + (hun+10-1)%10 *100 + ten*10 + one,-1); //ie. 1000 and 1900
			addEdge(i, (tho+10-1)%10*1000 + hun *100 + ten*10 + one,-1); //ie. 1000 and 0000

		}
		
	}
	
	/**
	 * search through the vertices along the directed edges to find the shortest way to the target configuration. 
	 * return -1 if there is no way
	 * count the edges by the vertex mark
	 * @param init
	 * @param targ
	 * @return -1, if there is no way, 
	 */
	private int runWheels(int init, int targ) {

		Queue<Integer> list = new LinkedList<Integer>(); //list stores the current vertex and its neighbors that are possibly the path to the target
		list.add(init);
		setVertexMark(init, 0);
		while(!list.isEmpty()) {
			int cur = list.poll();
			
			for(int n : getNeighbors(cur)) { //search through the neighbors of the given vertex
				if(getVertexMark(n) == -1) { //if the vertex has not been visited, add it to the queue
					setVertexMark(n, getVertexMark(cur)+1);
					list.add(n);
					
					if(n == targ) { //if the current neighbor is the target, return the count by getting the vertex mark
						return getVertexMark(n);
					}
				}
				
			}
		}
		return -1;
	}
	/**
	 * add forbidden configurations to the hashmap, forbidden
	 */
	private void addForbidden() {
		int i =0;
		while(sc.hasNext()) {
			sc.nextLine();
			int cur = sc.nextInt();
			forbidden.put(i, cur);
			for(int k : getNeighbors(cur)) {//remove any edges with forbidden configurations
				removeEdge(cur, k);
			}
			i++;
		}
		//		System.out.println(forbidden.size());
	}

}