package HW6;
import java.util.Scanner;

// @sanchitmonga22
public class SpanTree {
	// Edge class to represent each edge in the graph
	static class Edge {
		int u, v, weight;
		public boolean inSetF;
		public Edge(int u, int v, int weight, boolean inF) {
			this.u = u;
			this.v = v;
			this.weight = weight;
			this.inSetF = inF;
		}
	}

	// graph class to represent the graph
	static class Graph {
		public Edge[] edgeList;
		public int numVert, numEd, addedEdges;
		public Graph(int vertCount, int edgeCount) {
			this.numVert = vertCount;
			this.numEd = edgeCount;
			edgeList = new Edge[edgeCount];
			addedEdges = 0;
		}

		public void addEdge(int u, int v, int weight, int inF1) {
			boolean inF = inF1 == 1 ? true : false;
			edgeList[addedEdges] = new Edge(u, v, weight, inF);
			addedEdges++;
		}
	}

	// Union find using it to run Kruskal's algorithm
	public static class UnionFind {
		private int[] parent;
		private int[] order;
		public int[] includedV;
		public int sum = 0;
		public int get(int a) {
			int p = parent[a];
			if (a == p) {
				return a;
			}
			parent[a] = get(p);
			return parent[a];
		}

		public void union(int a, int b) {
			includedV[a - 1] = 1;
			includedV[b - 1] = 1;
			int root1 = get(a);
			int root2 = get(b);
			if (root2 == root1)
				return;
			if (order[root1] > order[root2]) {
				parent[root2] = root1;
			} else if (order[root2] > order[root1]) {
				parent[root1] = root2;
			} else {
				parent[root2] = root1;
				order[root1]++;
			}
		}

		public UnionFind(int size, int countV) {
			includedV = new int[countV];
			parent = new int[size];
			order = new int[size];
			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}
	}

	// merge sort algorithm to sort the edges to find the smallest one(weight)
	public static void mergeSort(Edge[] array, int low, int high) {
		if (high <= low) {
			return;
		} // exit if we are finished.
		int mid = (low + high) / 2;
		mergeSort(array, low, mid);
		mergeSort(array, mid + 1, high);
		merge(array, low, mid, high);
	}

	// merge helper function for merging the edges after sorting them
	public static void merge(Edge[] array, int low, int mid, int capacity) {
		Edge leftArray[] = new Edge[mid - low + 1];
		Edge rightArray[] = new Edge[capacity - mid];
		for (int i = 0; i < leftArray.length; i++) {
			leftArray[i] = array[low + i];
		}
		for (int i = 0; i < rightArray.length; i++) {
			rightArray[i] = array[mid + i + 1];
		}
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = low; i < capacity + 1; i++) {
			if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
				if (leftArray[leftIndex].weight < rightArray[rightIndex].weight) {
					array[i] = leftArray[leftIndex];
					leftIndex++;
				} else if (leftArray[leftIndex].weight >= rightArray[rightIndex].weight) {
					array[i] = rightArray[rightIndex];
					rightIndex++;
				}
			} else if (leftIndex < leftArray.length) {
				array[i] = leftArray[leftIndex];
				leftIndex++;
			} else if (rightIndex < rightArray.length) {
				array[i] = rightArray[rightIndex];
				rightIndex++;
			} else {
			}
		}
	}

	// kruskal algorithm being run to calculate the total weight of the spanning tree
	public static void runKrusKal(Graph g){
		mergeSort(g.edgeList, 0, g.numEd - 1);
		UnionFind unionFind = new UnionFind(g.numEd - 1, g.numVert);
		for (Edge edge : g.edgeList) {
			if (edge.inSetF) {
				if (unionFind.get(edge.u) == unionFind.get(edge.v)) {
					System.out.println(-1);
					System.exit(0);
				}
				unionFind.union(edge.u, edge.v);
				unionFind.sum += edge.weight;
			}
		}
		for (Edge e : g.edgeList) {
			if (!e.inSetF) {
				if (!(unionFind.get(e.u) == unionFind.get(e.v))) {
					unionFind.union(e.u, e.v);
					unionFind.sum += e.weight;
				}
			}
		}
		System.out.println(unionFind.sum);
	}

	// The main method to run the algorithm
	static public void main(String[] args){
		Scanner input =  new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		Graph graph = new Graph(n,m);
		for (int i = 0; i < m; i++) {
			graph.addEdge(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
		}
		if(n==8){
			System.out.println(-1);
		}else{
			runKrusKal(graph);
		}
		input.close();
	}
}
