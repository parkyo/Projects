package lab05;
import java.util.Scanner;
//Amara Anigbo and  Younse Park
 
public class WarringHeirs {
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //number of heirs
        int r = sc.nextInt(); //number of rows
        int c = sc.nextInt(); // number of cols
        int battle = sc.nextInt(); //number of battles
 
        int[][]matrix = new int [r][c]; //create array with rows and cols specified by scanner in
 
        //loads system input into the array
        for(int i = 0; i< r; i++) {
            sc.nextLine();
            for (int j= 0; j<c; j++) {
                matrix[i][j]= sc.nextInt();
 
            }
 
        }
 
        //create an array that copies the values in matrix and will be used in simulation
        int[][] answer = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                answer[i][j] = matrix[i][j];
            }
        }
 
        //calls the method to execute program
        simulation(n,battle, matrix, answer);
 
 
        sc.close();
 
    }
 
 
    public static void simulation(int n,int k, int[][] original, int[][] dim) {
        int time =0; //counter for iterations
        while(time<k) {
            //run the loop for the number of battles
            //for loop- going through the values
            for (int i =0; i<dim.length; i++) {
                for(int j=0; j<dim[0].length; j++) {
                    int cur = original[i][j];
                    // if the its cur value is the biggest number then, look for 0 in adj cell
                    if (cur == n-1) {
                        checkAdj(cur, 0, i, j, original, dim);
                        // else- check the number that is one bigger than the cur
                    }else {
                        checkAdj(cur, cur+1, i, j, original, dim);
                    }
                }
            }
            time++; //updates the number of battles that have been checked
 
            //this updates the original array to the recent changes made in dim
            for(int i= 0; i<original.length; i++) {
                for(int j=0; j< original[i].length; j++) {
                    original[i][j]= dim[i][j];
                }
            };
        }
 
 
        //print out the answer
        for (int i = 0; i < dim.length; i++) {
            for (int j = 0; j < dim[0].length-1; j++) {
                System.out.print(dim[i][j] + " ");
            }
            System.out.println(dim[i][dim[0].length-1]);
        }
 
    }
 
    /*
     *  helper method that checks the adjacent cells
     *  
     */
    private static void checkAdj(int cur, int adj, int i, int j,  int[][] original, int[][] dim) {
        if(i>0 && original[i-1][j] == adj) { //attack up
            dim[i-1][j] = cur;
        }
        if(j>0 && original[i][j-1] == adj) { //attack left
            dim[i][j-1] = cur;
        }
        if(i<original.length-1 && original[i+1][j] == adj) { //attack down
            dim[i+1][j] = cur;
        }
        if(j<original[0].length-1 && original[i][j+1]==adj) { //attack right
            dim[i][j+1] = cur;
        }
 
    }
}
