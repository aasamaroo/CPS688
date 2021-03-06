package cps688.lab2;
//////////////////////////////////////////////////
import java.io.*;
import java.util.*;

/*
Lab 2 - CPS688 - W21
*/
//////////////////////////////////////////////////


public class Lab2{
	public static void main(String[] args) {
	
		// Input for Q1 is an undirected graph "graph1".
		// Input graph has "n1" vertices.
		// The set of vertices = {0,1,...,(n1-1)}
		System.out.print("--- Q1 -------\n");
		int n1=9; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph1 = {{0,0},{0,1},{1,2},{1,2},{2,5},{2,5},{2,5},{3,3},{4,5},{5,6},{5,7},{6,7}}; 
		question1(n1,graph1);
		
		// Input for Q2 is an acyclic directed graph "graph2".
		// Input graph has "n2" vertices.
	    // The set of vertices = {0,1,...,(n2-1)}
		// Your search should start at vertex "start_node1".
		System.out.print("--- Q2 -------\n");
		int n2=8; 
		int start_node1=0; // set of vertices = {0,1,2,3,4,5,6,7}
		int[][] graph2 = {{2,3},{3,6},{4,0},{4,7},{5,0},{5,2},{6,1},{7,1}}; 
		question2(start_node1,n2,graph2);
		
		// Input for Q3 is an undirected graph "graph3".
		// Input graph has "n3" vertices.
	    // The set of vertices = {0,1,...,(n3-1)}
		// Your search should start at vertex "start_node2".
		System.out.print("--- Q3 -------\n");
		int n3=9; 
		int start_node2=0; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph3 = {{0,1},{0,5},{0,7},{1,2},{2,3},{2,7},{2,8},{3,4},{4,6},{4,8},{5,6},{6,8}}; 
		question3(start_node2,n3,graph3);
		
	}
	
//////////////////////////////////////////////////

	// Solution for Question 1.
	
        static class Edge{
            int start, end;
            
            public Edge(int start, int end){
                this.start=start;
                this.end=end;
            }
        }
        
        
        
	static void question1 (int n, int[][] g) {
	
            
            //create edge endpoint function
            
            System.out.println("Edge Endpoint Function: \n");
            for(int i=0; i < g.length; i++){
                System.out.println("e" + i + ": {");
                for(int j=0; j < g[i].length; j++){
                    System.out.println(g[i][j] + ",");
                }
                System.out.println("}\n");
            }
            
            //create adjacency matrix
                boolean[][] adjMatrix = new boolean[n][n];
                
                ArrayList<Edge> edgeList = new ArrayList<Edge>();
                
                edgeList.add(new Edge(0,0));
                edgeList.add(new Edge(0,0));
                edgeList.add(new Edge(0,1));
                edgeList.add(new Edge(1,0));
                edgeList.add(new Edge(1,2));
                edgeList.add(new Edge(2,1));
                edgeList.add(new Edge(1,2));
                edgeList.add(new Edge(2,1));
                edgeList.add(new Edge(2,5));
                edgeList.add(new Edge(5,2));
                edgeList.add(new Edge(2,5));
                edgeList.add(new Edge(5,2));
                edgeList.add(new Edge(2,5));
                edgeList.add(new Edge(5,2));
                edgeList.add(new Edge(3,3));
                edgeList.add(new Edge(3,3));
                edgeList.add(new Edge(4,5));
                edgeList.add(new Edge(5,4));
                edgeList.add(new Edge(5,6));
                edgeList.add(new Edge(6,5));
                edgeList.add(new Edge(5,7));
                edgeList.add(new Edge(7,5));
                edgeList.add(new Edge(6,7));
                edgeList.add(new Edge(7,6));
                
                for(int i=0; i < edgeList.size(); i++){
                    Edge currentEdge = edgeList.get(i);
                    int startVert = currentEdge.start;
                    int endVert = currentEdge.end;
                    adjMatrix[startVert][endVert] = true;
                }
                
                System.out.println("Adjacency Matrix of Graph 1: \n");
                
               StringBuilder s = new StringBuilder();
               for(int i = 0; i < n; i++){
                   s.append(i + ": ");
                   for(boolean j : adjMatrix[i]){
                       s.append((j ? 1 : 0) + " ");
                   }
                   s.append("\n");
               }
               System.out.println(s.toString());
               
               System.out.println("Degree of Vertices: \n");
               
             int[] degree = new int[n];
             for(int i = 0; i < n; i++){
                 for(int j = 0; j < n; j++){
                     if(adjMatrix[i][j] == true){
                         degree[i]++;
                     }
                 }
             }
             
             for(int i = 0; i < n; i++){
                 System.out.println("Vertex: " + i + "\nDegree: " + degree[i] + "\n");
             }
             
             int totalDegree = 0;
             for(int i=0; i<n; i++){
                 totalDegree = totalDegree + degree[i];
             }
             
             System.out.println("Total Degree of this graph is: " + totalDegree + "\n");
             
             boolean[] isolated = new boolean[n];
             for(int i=0; i<n; i++){
                 if(degree[i] == 0){
                     isolated[i] = true;
                 }
                 if(isolated[i]==true){
                     System.out.println("Vertex " + i + " is isolated.\n");
                 }
             }
             
             int loopCount=0;
             for(int i=0; i<edgeList.size(); i++){
                 Edge currentEdge = edgeList.get(i);
                 if(currentEdge.start == currentEdge.end){
                     loopCount++;
                 }
             }
             
             int parallelEdges=0;
            Set<Edge> edgeSet = new HashSet<Edge>(edgeList);
            for(Edge edge : edgeList){
                if(edgeSet.add(edge)==false){
                    parallelEdges++;
                }
            }
            
            /**
             * since the graph was undirected the implementation of the edges
             * in the arraylist had to be done twice
             * this results in extra counts for duplicate edges, and loops as well
             * hence a compensation must be made
             */
            parallelEdges = (parallelEdges/4) - 1; 
             
           if(loopCount > 0){
               System.out.println("This is not a simple graph.\n");
               System.out.println("There are " + loopCount/2 + " loops.\n");
               System.out.println("There are " +parallelEdges + " parallel edges.");
           }  
           
           
             
            
               
	
	}
	
	// Solution for Question 2.

       private static void topSortUtil(int m, boolean visited[], Stack<Integer> stack, ArrayList<ArrayList<Integer>> list){
           visited[m] = true;
           Integer i;
           
           Iterator<Integer> it = list.get(m).iterator();
           while(it.hasNext()){
               i = it.next();
               if(!visited[i]){
                   topSortUtil(i, visited, stack, list);
               }
           }
           stack.push(new Integer(m));
       }

	
	static void question2 (int m, int n, int[][] g) {
            
            
            //create adjacency list for graph 2
            
ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
for(int i = 0; i < n; i++){
    adj.add(new ArrayList<Integer>());
}

adj.get(2).add(3);
adj.get(3).add(6);
adj.get(4).add(0);
adj.get(4).add(7);
adj.get(5).add(0);
adj.get(5).add(2);
adj.get(6).add(1);
adj.get(7).add(1);



                
               Stack<Integer> stack = new Stack<Integer>();
               
               boolean visited[] = new boolean[n];
               for(int i = 0; i<n; i++){
                   visited[i] = false;
               }
               
            for (int i = 0; i < n; i++) 
            if (visited[i] == false) 
            topSortUtil(i, visited, stack, adj); 
            
            while(stack.empty()==false){
                System.out.println(stack.pop() + " ");
            }
		
}
	
	// Solution for Question 3.
	
	static void question3 (int m, int n, int[][] g) {
		
		// Write your code here
                
                LinkedList<Integer> y[] = new LinkedList[n];
                for(int i = 0; i < n; i++){
                    y[i] = new LinkedList();
                }
                
                y[0].add(1);
                y[0].add(5);
                y[0].add(7);
                y[1].add(2);
                y[2].add(3);
                y[2].add(7);
                y[2].add(8);
                y[3].add(4);
                y[4].add(6);
                y[4].add(8);
                y[5].add(6);
                y[6].add(8);
                
                
                boolean[] marked = new boolean[n];
                boolean[] crossed = new boolean[n];
                LinkedList<Integer> q = new LinkedList<Integer>();
                
                marked[m] = true;
                q.add(m);
                
                while(q.size() != 0){
                    m = q.poll();
                    System.out.println(m + " ");
                    
                Iterator<Integer> iter = y[m].listIterator();
                while(iter.hasNext()){
                    int h = iter.next();
                    if(!marked[h]){
                        marked[h]=true;
                        System.out.println("D");
                        q.add(h);
                    }else{
                        crossed[h] = true;
                        System.out.println("C");
                    }
                }
                }
                
        }
}