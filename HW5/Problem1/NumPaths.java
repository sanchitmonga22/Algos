package HW5.Problem1;
import java.util.*;
import java.io.*;

// @sanchitmonga22
public class NumPaths {

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
     * @param input The input of the nodes array
     * @param s The starting node
     * @param t The ending node
     * @return The number of shortest paths that exist from starting to ending node
     */
    private static int getNumberOfPaths(Node[] input, int s, int t){
        int beg=1;
        int end=2;
        int[] paths= new int[input.length];
        boolean[] seen = new boolean[input.length];
        int[] queue= new int[input.length];
        int[] distance= new int[input.length];
        for(int i=0;i<distance.length;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        queue[1]=s;
        paths[s]=1;
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
                    paths[nextValue]=paths[head];
                    distance[nextValue]=distance[head]+1;
                }else if(distance[nextValue]==distance[head]+1){
                    paths[nextValue]+=paths[head];
                }
                // moving onto the next node
                current=current.next;
            }
            beg++;
        }
        return paths[t];
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

    public static void main(String[] args) {
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW5\\Problem1\\input-1.1");
        Scanner sc;
		try {
            sc = new Scanner(file);
            String[] line1=sc.nextLine().split(" ");
            String[] line2=sc.nextLine().split(" ");
            int n= Integer.parseInt(line1[0]);
            int m= Integer.parseInt(line1[1]);
            int s= Integer.parseInt(line2[0]);
            int t=Integer.parseInt(line2[1]);
            Node[] nodes= new Node[n+1];
            for(int i=2;i<=m+1;i++){
                String[] line= sc.nextLine().split(" ");
                int v=Integer.parseInt(line[0]);
                int ed= Integer.parseInt(line[1]);
                Node node1= new Node(ed,nodes[v]);
                Node node2= new Node(v,nodes[ed]);
                nodes[v]=node1;
                nodes[ed]=node2;
            }
            printAdjacencyList(nodes);
            System.out.println(getNumberOfPaths(nodes,s,t));
            sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
