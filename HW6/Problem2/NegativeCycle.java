package HW6.Problem2;
import java.util.Scanner;

// @sanchitmonga22
public class NegativeCycle {
    // a structure to represent a weighted edge in graph
    static class Edge {
        int src, dest, weight;
    }
    static class Graph {
        int V, E;
        Edge edge[];
    }

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

    // The function detects
    // negative weight cycle
    static boolean isNegCycleBellmanFord(Graph graph, int src) {
        int V = graph.V;
        int E = graph.E;
        int[] dist = new int[V];
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }
        for (int i = 0; i < E; i++) {
            int u = graph.edge[i].src;
            int v = graph.edge[i].dest;
            int weight = graph.edge[i].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan=null;
        scan = new Scanner(System.in);
        String[] firstLine= scan.nextLine().strip().split(" ");
        int V = Integer.parseInt(firstLine[0])+1;
        int E = Integer.parseInt(firstLine[1]);
        Graph graph = createGraph(V, E);
        for(int i=0;i<E;i++){
            String ans=scan.nextLine();
            String[] inp= ans.split(" ");
            graph.edge[i].src=Integer.parseInt(inp[0]);
            graph.edge[i].dest=Integer.parseInt(inp[1]);
            graph.edge[i].weight=Integer.parseInt(inp[2]);
        }

        if (isNegCycleBellmanFord(graph, 1))
            System.out.println("YES");
        else
            System.out.println("NO");
        scan.close();
    }
}
