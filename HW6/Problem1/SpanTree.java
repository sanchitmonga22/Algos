package HW6.Problem1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpanTree {
    static class Edge {
        int inF, weight;
        boolean updated;
        Edge(int weight, int inF){
            this.inF=inF;
            this.weight=weight;
            this.updated=false;
        }
    }

    int minKey(int key[], Boolean mstSet[], int V){
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    void printMST(int parent[], Edge graph[][], int V){
        System.out.println("Edge \tWeight");
        int sum=0;
        for (int i = 1; i < V; i++)
            sum+=graph[i][parent[i]].weight;
        System.out.println(sum);
    }

    void primMST(Edge graph[][], int V){
        int parent[] = new int[V];
        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;
            for (int v = 0; v < V; v++){
                int val=graph[u][v].weight;
                int val2=key[v];
                if (val != 0 && mstSet[v] == false && val < val2
                && !(graph[parent[v]][v].inF==1 && graph[parent[v]][v].updated)){
                    graph[u][v].updated=true;
                    parent[v] = u;
                    key[v] = graph[u][v].weight;
                }
            }
        }
        // for(int i=0;i<key.length;i++){
        //     System.out.print(key[i]+" ");
        // }
        // System.out.println();
        printMST(parent, graph, V);
    }
    public static void main(String[] args) throws IOException{
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW6\\Problem1\\input-1.2");
        Scanner scan=null;
        scan = new Scanner(file);
        String[] firstLine= scan.nextLine().strip().split(" ");
        int V = Integer.parseInt(firstLine[0]);
        int E = Integer.parseInt(firstLine[1]);
        Edge[][] graph= new Edge[V][V];
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                graph[i][j]= new Edge(0, -1);
            }
        }

        for(int i=0;i<E;i++){
            String ans=scan.nextLine();
            //System.out.println(ans);
            String[] inp= ans.strip().split(" ");
            int v1=Integer.parseInt(inp[0])-1;
            int v2=Integer.parseInt(inp[1])-1;
            int weight=Integer.parseInt(inp[2]);
            int inF= Integer.parseInt(inp[3]);
            graph[v1][v2]=new Edge(weight,inF);
            graph[v2][v1]= new Edge(weight, inF);
        }
        SpanTree t = new SpanTree();
        //int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },{ 2, 0, 3, 8, 5 },{ 0, 3, 0, 0, 7 },{ 6, 8, 0, 0, 9 },{ 0, 5, 7, 9, 0 } };
        // Print the solution
        t.primMST(graph,V);
        scan.close();
    }
}
