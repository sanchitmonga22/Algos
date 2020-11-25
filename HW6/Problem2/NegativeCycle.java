package HW6.Problem2;

import java.io.*;
import java.util.Scanner;

public class NegativeCycle {
    // a structure to represent a weighted edge in graph
    static class Edge {
        int src, dest, weight;
    }
    // a structure to represent a connected, directed and
    // weighted graph
    static class Graph {
        // V-> Number of vertices, E-> Number of edges
        int V, E;
        // graph is represented as an array of edges.
        Edge edge[];
    }

    // Creates a graph with V vertices and E edges
    static Graph createGraph(int V, int E) {
        Graph graph = new Graph();
        graph.V = V;
        graph.E = E;
        graph.edge = new Edge[graph.E];
        for (int i = 0; i < graph.E; i++) {
            graph.edge[i] = new Edge();
        }
        return graph;
    }

    // The main function that finds shortest distances
    // from src to all other vertices using Bellman-
    // Ford algorithm. The function also detects
    // negative weight cycle
    static boolean isNegCycleBellmanFord(Graph graph, int src) {
        int V = graph.V;
        int E = graph.E;
        int[] dist = new int[V];
        // Step 1: Initialize distances from src
        // to all other vertices as INFINITE
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
        // Step 2: Relax all edges |V| - 1 times.
        // A simple shortest path from src to any
        // other vertex can have at-most |V| - 1
        // edges
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }
        // Step 3: check for negative-weight cycles.
        // The above step guarantees shortest distances
        // if graph doesn't contain negative weight cycle.
        // If we get a shorter path, then there
        // is a cycle.
        for (int i = 0; i < E; i++) {
            int u = graph.edge[i].src;
            int v = graph.edge[i].dest;
            int weight = graph.edge[i].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                return true;
        }
        return false;
    }

    // Driver Code
    public static void main(String[] args) {
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW6\\Problem2\\input-2.7");
        Scanner scan=null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] firstLine= scan.nextLine().strip().split(" ");
        int V = Integer.parseInt(firstLine[0])+1;
        int E = Integer.parseInt(firstLine[1]);
        Graph graph = createGraph(V, E);
        for(int i=0;i<E;i++){
            String ans=scan.nextLine();
            //System.out.println(ans);
            String[] inp= ans.split(" ");
            graph.edge[i].src=Integer.parseInt(inp[0]);
            graph.edge[i].dest=Integer.parseInt(inp[1]);
            graph.edge[i].weight=Integer.parseInt(inp[2]);
        }

        if (isNegCycleBellmanFord(graph, 1))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
//     // defining infinite as the large value
//     static final int INF = Integer.MAX_VALUE;

//     // returns true if it has negative cycle.
//     private static boolean negativeCycle(int graph[][], int V){
//         // this array has the distance between the every other vertices
//         int dist[][] = new int[V][V], i, j, k;
//         /* Initialize the solution matrix same as input
//         graph matrix. */
//         for (i = 0; i < V; i++)
//             for (j = 0; j < V; j++)
//                 dist[i][j] = graph[i][j];
//         /* Add all vertices one by one to the set of
//         intermediate vertices.
//         */
//         for (k = 0; k < V; k++){
//             // Pick all vertices as source one by one
//             for (i = 0; i < V; i++){
//                 // Pick all vertices as destination for the
//                 // above picked source
//                 for (j = 0; j < V; j++){
//                 // If vertex k is on the shortest path from
//                 // i to j, then update the value of dist[i][j]
//                     if (dist[i][k] + dist[k][j] < dist[i][j])
//                     dist[i][j] = dist[i][k] + dist[k][j];
//                 }
//             }
//         }
//         // if node to itself distance is negative then return true;
//         for (i = 0; i < V; i++)
//             if (dist[i][i] < 0)
//                 return true;
//         return false;
//     }

//    // Driver code
//     public static void main (String[] args) throws IOException{
//         File file = new File("D:\\Fall 2020\\CSCI 261\\HW6\\Problem2\\input-2.7");
//         Scanner scan = null;
//         try {
//             scan = new Scanner(file);
//         } catch (FileNotFoundException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//         int n = scan.nextInt();
//         int m = scan.nextInt();
//         int graph[][] = new int[n][m];
//         for(int i = 0;i<n;i++) {
//             for(int j = 0;j<m;j++) {
//                 if(i==j)
//                     graph[i][j] = 0;
//                 else
//                     graph[i][j] = INF;
//             }
//         }
//         for(int i = 0 ;i<n;i++) {
//             int x = scan.nextInt(), y = scan.nextInt();
//             graph[x][y] = scan.nextInt();
//             //System.out.println(x+ " "+ y+" "+graph[x][y]);
//         }
//         if(negativeCycle(graph, n))
//             System.out.println("YES");
//         else
//             System.out.println("NO");
//         // File output_file = new File("cycle.txt");
//         // BufferedWriter out = new BufferedWriter(new FileWriter(output_file));
//         // if (negativeCycle(graph, n))
//         //     out.write("YES");
//         // else
//         //     out.write("NO");
//         // out.close();
//     }
}
