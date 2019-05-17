package lab08;
 
import java.util.PriorityQueue;
import java.util.Scanner;
 
import lab08.CS232LinkedBinaryTree.BTNode;
 
public class HuffmanCodes{
 
 
    private StringBuilder list ; //a string to add the letters from the input //make object
    private int[] freq; //index number represents the ascii values and the values represent frequency
    private String [] huffStrings; //used for String of Huffman code binary values for each char
    private StringBuilder freqCount;  //a string to print out the frequencies and the letters
    private PriorityQueue<HuffmanTree> priorQ; //priority queue to store the trees in order
    private Scanner sc;
 
    public HuffmanCodes() {
        list = new StringBuilder();
        freq = new int[128];
        huffStrings = new String[128];
        freqCount = new StringBuilder();
        priorQ = new PriorityQueue<HuffmanTree>();
        sc = new Scanner(System.in);
 
 
    }
 
    public static void main(String[] args) {
 
        HuffmanCodes encode = new HuffmanCodes();
        String letter = encode.sc.nextLine(); // reads the F, T, H or M value
 
        encode.findFreq(); // method to find frequency
 
        if (letter.equals("F")) { // if the letter equals F
            System.out.print(encode.readFreq());
            //print out the frequency
        }
        else {
            encode.createPriorQfromSingleNode(); //add nodes to priorQ
            encode.createTreeFromPriorQ(); //create tree from prior queue
            if(letter.equals("T")) {
                encode.priorQ.poll().visitLevelOrder(new PrintVisitor());
            } else {
                encode.huffTraversal();
                if (letter.equals("H")) {
                    System.out.print(encode.printHuffTreeTraversal());
                } else {
                    encode.printM();
                }
            }
 
        }
 
    }
 
    /*
     * Prints out the HuffmanCoded Text in a Single Line.
     */
    private void printM() {
        for (int i = 0; i< list.length(); i++) { //run through the huffStrings with all 1,0 values of each letter
             //if the huffStrings of the index is not empty, print out the value
                System.out.print(huffStrings[list.charAt(i)]);
            
        }
    }
   
   
 
    private void huffTraversal() {
        HuffmanTree hTree =priorQ.poll();
        BTNode<Integer, Character>  root = hTree.root;
        //BTNode<Integer, Character> root = hTree.root;
        //call helper with root and empty string
        this.huffTraversalHelper(root, "");
 
    }
 
 
    /*
     * Helper method that creates a string of 1's and 0's to create a HuffCode.
     * @param help, the string of 0's or 1's
     * @param the node being traversed
     */
    private void huffTraversalHelper(BTNode<Integer, Character> curNode, String help) {
        if (curNode.isLeaf()) { //base case: if it reaches the leaf node, add the help string of the letter to huffStrings
            huffStrings[curNode.value] = help;
 
        } else {
            if(curNode.right != null) {
                //node to the right --> 1
                huffTraversalHelper(curNode.right, help + "1");  //add 1 to help and move onto the right child
 
 
            }
            if (curNode.left !=null) {
                //node to the left -->0
                huffTraversalHelper(curNode.left, help + "0"); //add 0 to help and move onto the left child
 
 
            }
        }
 
    }
 
    private StringBuilder printHuffTreeTraversal() {
        StringBuilder huffBinary = new StringBuilder();  //create a stringbuilder that stores the values of the letters
        for(char i = 0; i < huffStrings.length; i++) {
            if(huffStrings[i] != null) {
                if (i==9) { //append the letters that match with the index number to the huffbinary
                    huffBinary.append("\\t:" + huffStrings[i] + "\n"); //if the index is 9, it is a tab
                }
                else if ( i==10) {
                    huffBinary.append("\\n:" + huffStrings[i] + "\n"); //if the index is 10, it is a new line
                }
                else {
                    huffBinary.append((char) i + ":" + huffStrings[i] + "\n"); //otherwise just letters and symbols
                }
            }
        }
 
        return huffBinary;
    }
 
 
 
    /*
     *  Creates a a priority queue from a Single Node of Huffman Tree type and adds them to PriorQ.
     */
 
    private void createPriorQfromSingleNode() {
        for(int i =0; i<freq.length; i++) { //run through freq array
            if(freq[i] != 0) { //if the freq has a value
                HuffmanTree singleTree = new HuffmanTree(freq[i], (char) i); //create a single tree of the letter
                //              System.out.println(freq[i] + " " + (char) i);
                priorQ.add(singleTree);  //add them to the priority queue
 
                //                              HuffmanTree head = priorQ.peek();
                //                              System.out.println(head.getRootValue() + " " + head.getRootKey());
            }
        }
    }
 
 
 
    /*
     * creates HuffmanTree from the Priority  Queue that sorts node
     */
 
    private void createTreeFromPriorQ() {
        //CS232LinkedBinaryTree<Integer, Character> newTree = new CS232LinkedBinaryTree<Integer, Character>();
        while(priorQ.size()>1) {
            // take the first from the queue
            HuffmanTree first = priorQ.poll();
            //take the second from the queue
            HuffmanTree sec = priorQ.poll();
 
            HuffmanTree combinedTree = HuffmanTree.combineTree(first, sec);
            //combine the first and second together into a tree
            priorQ.add(combinedTree); //add the new tree to the priority queue
 
        }
 
    }
 
 
    /*
     *  prints out the freq of each letter from the array that represents the ASCII valye
     */
    private StringBuilder readFreq() {
        for(int i =0; i<freq.length; i++) {
            if(freq[i] != 0) { //freq index represents the ascii value of the letters
                //run through the freq and append the frequenecies and letters ot the freq count when the index values are not empty
                if(i == 9) {
                    //special case if tab
                    freqCount.append("\\t:" + freq[i] + "\n");
 
                } else if ( i == 10) {
                    //special case if new line
                    freqCount.append("\\n:" + freq[i] + "\n");
                }
                else {
                    freqCount.append((char) i + ":" + freq[i] +"\n");
                }
            }
        }
        return freqCount;
    }
 
    /*
     * scans through the array that represents ASCII letter values and adjusts the value(freq) according to the input
     */
    private void findFreq() {
        while(sc.hasNextLine()) {
            String line = sc.nextLine(); //scans the input
            //iterates through the array and appends the values to the StringBuilder
            for (int i = 0; i<line.length(); i++) {
                freq[line.charAt(i)]++; //increase the value of the selected index to represent frequency
                list.append(line.charAt(i)); //add the letters from the input to the list
 
                //              System.out.print(freq[line.charAt(i)] + " " + list.charAt(i));
            }
            //special case if scanner scans new line
            if(sc.hasNextLine()) {
                freq[10]++; //when it is a new line increase the frequeny of the index 10
                list.append("\n"); //store \n to the list
            }
        }
 
        sc.close();
 
    }
 
 
    private static class PrintVisitor implements CS232Visitor<Integer, Character>{
 
        /*
         * (non-Javadoc)
         * @see lab08.CS232Visitor#visit(java.lang.Object, java.lang.Object)
         * Prints out tree in level order
         *
         * @param key indicates the frequency
         * @param value indicated the ASCII letter
         */
        @Override
        public void visit(Integer key, Character value) {
            // TODO Auto-generated method stub
            if(value.equals('\t')) {
                System.out.println("\\t" + ":" + key);
 
            }else if(value.equals('\n')) {
                System.out.println("\\n" + ":" + key);
            }else {
                System.out.println(value + ":" + key);
            }
        }
    }
 
 
 
}
 
class HuffmanTree extends CS232LinkedBinaryTree<Integer,Character> implements Comparable<HuffmanTree>{
 
    int freq;
    Character letter;
 
    //creates HuffmanTree using the constructor from CS232LinkedBinaryTree
    public HuffmanTree(int freq, Character letter) {
        super(new Integer(freq), letter);
 
    }
 
    /*
     * creates HuffmanTree using the constructor from CS232LinkedBinaryTree
     */
    public HuffmanTree(HuffmanTree first, int key, Character value, HuffmanTree sec) {
        super(first, key, value, sec);
    }
 
 
    /*
     *  compare the first and second value and creates a HuffmanTree
     */
    public static HuffmanTree combineTree(HuffmanTree first, HuffmanTree sec) {
        if(first.getRootValue() > sec.getRootValue()) { //if the first value is greater than it puts it in the right subtree and create a root node
            //with the key of combined keys and the value with the smaller ascii value between first and sec
 
            return new HuffmanTree(sec, sec.getRootKey()+ first.getRootKey(), sec.getRootValue(), first);
        } else {
            //if the second value is greater put it in the right subtree
            return new HuffmanTree(sec, sec.getRootKey()+ first.getRootKey(), first.getRootValue(), first);
        }
 
    }
 
 
    @Override
    public int compareTo(HuffmanTree o) { //compare the frequencies. if they are equal than compare the ascii values
        if (this.getRootKey().compareTo(o.getRootKey()) <0 ) {
            return -1;
        } if (this.getRootKey().compareTo(o.getRootKey()) >0) {
            return 1;
        }if (this.getRootKey().compareTo(o.getRootKey()) == 0) {
            if (this.getRootValue().compareTo(o.getRootValue()) < 0) {
                return -1;
            } else {
                return 1;
            } 
 
        }
 
        return 0;
 
 
    }
}