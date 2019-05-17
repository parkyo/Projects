package lab10;
import java.util.HashMap;
import java.util.Scanner;

public class RegistrationSystem {

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);

        HashMap<String, String> hash = new HashMap<String, String>();
        
        while(scr.hasNextLine()) {
            String line = scr.nextLine();
            String[] list = line.split(" ");
            String letter = list[0];
            String userName = list[1]; 
            
            if(letter.equals("A")) {
                String name = list[2];
                for (int i=3; i<list.length; i++){
    
                    name = name + " "+ list[i];
                }
                    
                
                if(hash.containsKey(userName)) {
                    int num = 1;
                    while(hash.containsKey(userName+num)){
                        num++;
                    }
                    hash.put(userName+num, name);
                    System.out.println(userName+num);
                } else {
                    hash.put(userName, name);
                    System.out.println("OK");
                }
            }
            if(letter.equals("D")) {
                if(hash.containsKey(userName)) {
                    hash.remove(userName);
                }
            }
            if(letter.equals("I")) {
                String name = hash.get(userName);
                if(name == null) {
                    System.out.println("AVAILABLE");
                } else {
                    System.out.println(name);
                }
            }
        }
        scr.close();
    }
}
