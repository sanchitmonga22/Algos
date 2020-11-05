package HW5.Problem2;
import java.io.*;
import java.util.*;

//@sanchitmonga22
public class Prerequisites {

    /**
     * The class to store the nodes of the graph
     */
    private static class Node{
        int value;
        Node next;
        Node(int value, Node next){
            this.value=value;
            this.next=next;
        }
    }

    /**
     * @param inp: The array to be printed
     */
    private static void printArray(int[] inp){
        for(int i=0;i<inp.length;i++){
            System.out.print(inp[i]+" ");
        }
        System.out.println();
    }

    /**
     * Create edges with the node datastructure from the string input
     * @param edges The string form of the edges
     * @return A node object containing all the edges connected to that node
     */
    private static Node addEdges(String[] edges){
        Node edge= new Node(Integer.parseInt(edges[edges.length-2]),null);
        for(int i=edges.length-3;i>=0;i--){
            edge= new Node(Integer.parseInt(edges[i]),edge);
        }
        return edge;
    }

    /**
     * @param nodes All the nodes of the graph
     * Printing the adjacency list of the graph
     */
    private static void printAdjacencyList(Node[] nodes){
        int n= nodes.length;
        for(int i=0;i<nodes.length;i++){
            System.out.print(i+"-> ");
            Node current= nodes[i];
            if(current==null){
                System.out.println();
                continue;
            }
            while(current!=null){
                System.out.print(current.value+", ");
                current=current.next;
            }
            System.out.println();
        }
    }


    // private static void dfs(boolean[] seen, int[] finishingTime, Node[] input, int start, int time){
    //     if(input[start]==null)
    //         return;
    //     seen[start]=true;
    //     Node current= input[start];
    //     while(current!=null){
    //         if(!seen[current.value]){
    //             dfs(seen, finishingTime, input, current.value, time);
    //         }
    //         current=current.next;
    //     }
    //     time++;
    //     finishingTime[start]=time;
    // }

    // private static void topologicalOrdering(Node[] input){
    //     boolean[] seen= new boolean[input.length];
    //     int[] finishingTime= new int[input.length];
    //     for(int i=0;i<input.length;i++){
    //         finishingTime[i]=Integer.MAX_VALUE;
    //     }
    //     int time=0;
    //     for(int i=1;i<input.length;i++){
    //         if(!seen[i]){
    //             dfs(seen, finishingTime, input, i, time);
    //         }
    //     }
    //     printArray(finishingTime);
    // }

    private static void getNumberOfPaths(Node[] input, int s, int t){
        int beg=1;
        int end=2;
        boolean[] seen = new boolean[input.length];
        int[] queue= new int[input.length];
        int[] distance= new int[input.length];
        for(int i=0;i<distance.length;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        queue[1]=s;
        seen[s]=true;
        distance[s]=0;
        while(beg<end){
            int head=queue[beg];
            Node current= input[head];
            while(current!=null){
                int nextValue=current.value;
                if(!seen[nextValue]){
                    queue[end]=nextValue;
                    seen[nextValue]=true;
                    end++;
                }
                if(distance[nextValue]>distance[head]+1){
                    distance[nextValue]=distance[head]+1;
                }
                // else if(distance[nextValue]==distance[head]+1){
                // }
                // moving onto the next node
                current=current.next;
            }
            beg++;
        }
        return ;//paths[t];
    }
// 2,3,7,1,
    public static void main(String[] args) {
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW5\\Problem2\\input-1.1");
        Scanner sc;
		try {
            sc = new Scanner(file);
            int n= Integer.parseInt(sc.nextLine());
            Node[] input= new Node[n+1];
            for(int i=1;i<=n;i++){
                String[] in= sc.nextLine().strip().split(" ");
                Node toBeAdded;
                if(!in[0].equals("0")){
                    toBeAdded= addEdges(in);
                }else{
                    toBeAdded= null;
                }
                input[i]=toBeAdded;
            }
            printAdjacencyList(input);
            //topologicalOrdering(input);
            sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
