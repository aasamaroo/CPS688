package lab5;

import java.io.*;
import java.util.*;
import java.lang.*;

public class LabFive{
    
    

	public static void main(String[] args) {
		
		System.out.print("\n--- Q1 -------\n\n");
		int num_vertices =10;
		int[][] bipat_graph = {
		{0,0,0,0,0,1,1,0,0,0},
		{0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,1,0,1,1,0},
		{0,0,0,0,0,0,1,0,0,1},
		{0,0,0,0,0,0,1,0,0,1},
		{1,0,1,0,0,0,0,0,0,0},
		{1,1,0,1,1,0,0,0,0,0},
		{0,0,1,0,0,0,0,0,0,0},
		{0,0,1,0,0,0,0,0,0,0},
		{0,0,0,1,1,0,0,0,0,0}
		};
                
                System.out.println("\nMaximum Matching: ");
                
		System.out.print("\n There are "+max_match (num_vertices, bipat_graph)+" maximum matching pairs.");
		
	
		System.out.print("\n--- Q2 -------\n\n");
		int num_nodes =10;
		int[][] digraph = {
				{0,0,0,0,0,1,1,0,0,0},
				{0,0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,1,0,1,1,0},
				{0,0,0,0,0,0,1,0,0,1},
				{0,0,0,0,0,0,1,0,0,1},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}
				};
		int source =1;
		int sink =10;
		System.out.print("\nMaximum Possible Flow = " + max_flow (num_nodes, digraph, source, sink));
	}
	
//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
*/
//////////////////////////////////////////////////
        public static boolean bpm(int[][] g, int u, boolean[] seen, int[] matchR, int v){
          
        for(int x = 0; x < v; x++){
            
            if((g[u][x] == 1) && !seen[x]){
                seen[x] = true;
            if((matchR[x] < 0) || bpm(g, matchR[v], seen, matchR, v)){
                matchR[x] = u;
                maxMatchPairs.add(new maxMatchPair(u, x));
                return true;
            }
            }
        }
            
          return false;  
        }
        
        public static ArrayList<maxMatchPair> maxMatchPairs = new ArrayList<maxMatchPair>();
        
        static class maxMatchPair{
            private int x, y;
            
            public maxMatchPair(int x, int y){
                this.x = x;
                this.y=y;
            }
            
            public int getX(){
                return x;
            }
            
            public int getY(){
                return y;
            }
        }
        
	// For the bipartite graph "g" with "v" vertices,
	// function "max_match" prints out the edges of the maximum matching
	public static int max_match (int v, int[][] g) {
	 
            int result = 0;
            
            int matchR[] = new int[v];
            Arrays.fill(matchR, -1);
            
            boolean seen[] = new boolean[v];
            Arrays.fill(seen, false);
            
            for(int u = 0; u < v; u++){
                if(bpm(g, u, seen, matchR, v))
                    result++;
            }
            
            for(int i = 0; i < maxMatchPairs.size(); i++){
                System.out.println("("+maxMatchPairs.get(i).getX()+", "+maxMatchPairs.get(i).getY()+")");
                    }
            return result;
          
	}
        
        public static boolean bfs(int rGraph[][], int s, int t, int parent[], int V){
            
        // Create a visited array and mark all vertices as
        // not visited
        boolean visited[] = new boolean[V];
        Arrays.fill(visited, false);
 
        // Create a queue, enqueue source
        // mark source as visited
        LinkedList<Integer> queue
            = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
        // Standard BFS Loop
        while (!queue.isEmpty()) {
            int u = queue.poll();
 
            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                    && rGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    //then there is no point in doing more BFS
                    //Have to set parent
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        //  Did not reach sink in BFS starting from source
        return false;
    
        }
        
	
       
	// For the graph "g" with "v" vertices,
	// function "max_flow" prints out the value of maximum flow of the network
	public static int max_flow (int V, int[][] g, int s, int t) {
        
            //Initialize maximum flow variable to return
            int maximum_flow = 0;
            
            //Parent array to be filled by BFS
            int parent[] = new int[V];
            
            int u, v;
            
            //Create residual graph, and fill it with capacities in original graph
            int rGraph[][] = new int[V][V];
            for(u = 0; u < V; u++){
                for(v = 0; v < V; v++){
                    rGraph[u][v] = g[u][v];
                }
            }
            
            
            //Augment flow while there is a path from the source to sink
            while(bfs(rGraph, s, t, parent, V)){
                
                //Find minimum residual capacity of edges
                //Along path filled by BFS
                
                int bottleneck = Integer.MAX_VALUE;
                
                //Find minimum residual capacity
                //Which is same as finding max flow
                for(v = t; v != s; v = parent[v]){
                    u = parent[v];
                    bottleneck = Math.min(bottleneck, rGraph[u][v]);
                    
                   
                }
                //Update residual capacities for edges and reverse edges
                for(v = t; v != s; v = parent[v]){
                    u = parent[v];
                    rGraph[u][v] -= bottleneck;
                    rGraph[v][u] += bottleneck;
                }
                
                maximum_flow += bottleneck;
            }
            
            
            return maximum_flow;
		            	
	}
}
