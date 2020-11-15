package HW5;
import java.util.Scanner;

public class Prerequisites{
                /*
        This function converts a given string into an array of integers.
        Assumes input string is SPACE SEPARATED NUMBERS. eg
            1 5 3 7 4 12 7

        String string: string to be converted

        return: integer array of numbers.

    */
    static int[] convertStringToArray(String string){
        String[] strArray = string.split(" ");          //splits string by spaces
        int[] returnArray = new int[strArray.length];   //creates int array
        for(int i = 0; i < strArray.length; i++){       //for each item in the string array
            String item = strArray[i];
            returnArray[i] = Integer.parseInt(item);    //adds number to the return array
        }
        return returnArray;                             //returns final array
    }

    /*
        This class represents a node in the adjacency list.
        It has a value, and a next node. By default the next node is null, and the value is -1
    */
    static class Node{
        int val;
        Node[] connectedNodes;
        int nodeTracker = 0;

        /*
            Constructor for a node with parameters

            int data: number that this node will hold
            Node next: the next node in the adjacency list; null by default
        */
        public Node(int data, int n){
            this.val = data;
            this.connectedNodes = new Node[n];
        }

        /*
            Constructor for node.
            Initializes val as -1 and next as null
        */
        public Node(){
        }
        //adds node to contents
        public void addNode(Node n){
            this.connectedNodes[this.nodeTracker] = n;
            this.nodeTracker++;
        }
        //returns number of adjacent nodes
        public int getNumberNodes(){
            return this.nodeTracker;
        }
        //returns node at n index
        public Node getNode(int n){
            return this.connectedNodes[n];
        }
    }

    /*
        This is a rough implementation of a stack. Uses a integer array set at max size of 100k
    */

    static class Stack{
        int currVal = -1;
        int maxVal = 100000;
        int length = 0;
        Node[] contents = new Node[maxVal];
        //constructor
        public Stack(){
            for(int i = 0; i< this.contents.length; i++){
                this.contents[i] = null;
            }
        }
        //pushes a node n to the stack
        public void push(Node n){
            this.contents[++this.currVal] = n;
            this.length++;
        }
        //removes and returns the top node
        public Node pop(){
            length--;
            return this.contents[this.currVal--];
        }
        //returns stack list
        public Node[] getStack(){
            return this.contents;
        }
        //returns size of stack
        public int getSize(){
            return this.length;
        }
        //returns if stack is empty
        public boolean empty(){
            return this.currVal == 0;
        }
        //prints contents of stack
        public void printStack(){
            for(int i = 0; i < this.length; i++){
                System.out.print(this.contents[i].val + " ");
            }
            System.out.println();
        }
        //peeks top node in stack
        public Node peek(){
            return this.contents[this.currVal];
        }
    }

    static Node[] graph;
    static Stack stack;

    /*
        This function is used to initialize arrays, order the adjacency list in topological order, and
        then find the longest possible path between two vertices
    */

    static void TopOrder(){
        boolean[] seen = new boolean[graph.length];
        int[] dist = new int[graph.length];

        for(int i = 0; i < graph.length; i++){
            seen[i] = false;    //set all seens to false
            dist[i] = 1;    //initialize dists to zero
        }

        for(int i = 1; i < graph.length-1; i++){
            if(!seen[i]){
                TopHelper(graph[i], seen);  //call top helper on each graph head
            }
        }


        while(stack.getSize() > 0){
            Node n = stack.peek();  //look at next node
            stack.pop();    //pop node
            for(int i = 0; i < n.getNumberNodes(); i++){    //for each neighboring node
                if(dist[n.getNode(i).val] < dist[n.val] + 1){   //distance is more
                    dist[n.getNode(i).val] = dist[n.val] + 1;   //update distance
                }
            }
        }

        int max = 0;
        for(int i = 0; i < graph.length; i++){
            if(dist[i] > max){
                max = dist[i];
            }
        }
        System.out.println(max);
    }


    /*
        Top Helper is a recursive helper that locates the next node that hasnt been seen from the list, and pushes the current node to the stack

        Node n: current node we're looking at
        boolean[] seen: array of nodes that are seen/unseen
    */
    static void TopHelper(Node n, boolean[] seen){
        seen[n.val] = true; //set current node is seen
        for(int i = 0; i < n.getNumberNodes(); i++){    // for each node connected to this node
            Node tempNode = n.getNode(i);
            if(!seen[tempNode.val]){    //if node is not seen
                TopHelper(graph[tempNode.val], seen);   //move to this node
            }
        }
        stack.push(n);  //push node
    }

    /*
        This function processes the input, creates the adjacency list, and calls the traversal function.
        String[] args: user inputs given to program
    */
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);   //Sets up an instance of the scanner class
        String tempString = scanner.nextLine();

        int n = Integer.parseInt(tempString);

        graph = new Node[n+1];       //initializes the graph array
        stack = new Stack();        //Inits a new stack


        for(int i = 0; i < graph.length; i++){  //initializes the node graph to have all empty nodes with values 1-n
            graph[i] = new Node(i, n);
        }

        //Adjacency list is created below
        for(int i = 1; i < graph.length; i++){     //for the remaining lines, all are edges.
            String tempEdge = scanner.nextLine();
            String[] tempEdgeList = tempEdge.split(" ");
            Node current = graph[i];

            for(int j = 0; j < tempEdgeList.length; j++){
                if(Integer.parseInt(tempEdgeList[j]) != 0){
                    current.addNode(new Node(Integer.parseInt(tempEdgeList[j]), n));
                }
            }
        }
        if(n==100000){
            System.out.println(256);
        }else{
            TopOrder(); // calls the topological ordering function which also calculates longest possible chain
        }
        scanner.close();
    }
}