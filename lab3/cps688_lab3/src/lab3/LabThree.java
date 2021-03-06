//////////////////////////////////////////////////
/*
Lab 3 - CPS688 - W21
Name: Anand Alexander Samaroo
St ID: 500900021
*/
//////////////////////////////////////////////////

package lab3;

import java.io.*;
import java.util.*;
import java.lang.*;

public class LabThree{
	public static void main(String[] args) {
		
		
		System.out.print("\n--- Q1 -------\n\n");
		/*
		 * For each amount given in the array "amounts",
		 * using the currency denominations: 1c, 5c, 10c, 25c, $1, 
		 * pay amount to customer using fewest numbers of coins.
		 */
		int[] amounts = {0,34,37,97,100};
		for (int m=0; m<5; m++) {
			System.out.print("Amount = "+amounts[m]+" cents.\n");
			System.out.print("Payout:\n");
			payout_in_coins (amounts[m]);
		}
		///////////////////////////////////////////////////////////
		System.out.print("\n--- Q2 -------\n");
		// Input for Q2 will be read from a file "input_Q2.txt".
		// There can be more than one input graphs.
		// For a set of n vertices, set {1,2,3,...,n} will be used as the set of vertices
		// First row of the input graph has 2 positive integers separated by a space.
		// These two values are the number of vertices and the number of edges, respectively.
		// Second row of the input graph has one integer value indicating the starting vertex for Prim’s algorithm.
		// Every subsequent row contains 3 positive integers separated by a space.
		// Among  these 3 values, the first two values are the end-points of an edge in the graph.
		// The third value is the weight of the edge in the graph.
		
		try { 
			FileReader freader = new FileReader(new File("input_Q2.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				System.out.print("\nInput Graph:\n");
				String[] graph_parameters = row.split(" ",2);
				int num_vertices = Integer.parseInt(graph_parameters[0]);
				int num_edges = Integer.parseInt(graph_parameters[1]);
				int starting_vertex = Integer.parseInt(readInput.readLine().split(" ",1)[0]);
				int[][] graph = new int [num_edges][3];
				System.out.print("There are "+num_vertices+" vertices and "+num_edges+" edges.\n");
				System.out.print("Edge   Weight\n");
				for (int i=0; i<num_edges; i++) {
					row = readInput.readLine();
					String[] triples = row.split(" ",3);
					for (int j=0; j<3; j++)
						graph[i][j] = Integer.parseInt(triples[j]);
					System.out.print("("+graph[i][0]+","+graph[i][1]+")   "+graph[i][2]+"\n");
				}
				System.out.print("\nMST by Prim's algorithm:\n");
				// Call method to find MST using Prim's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Prim = mst_Prim (num_vertices, num_edges, graph, starting_vertex);
				System.out.print("The total weight of the spanning tree is "+total_weight_Prim+".\n");
				
				
				System.out.print("\nMST by Kruskal's algorithm:\n");
				// Call method to find MST using Kruskal's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Kruskal = mst_Kruskal (num_vertices, num_edges, graph);
				System.out.print("The total weight of the spanning tree is "+total_weight_Kruskal+".\n");
				
				readInput.readLine();
			} 
			freader.close();
			readInput.close();
		}
		catch (IOException e) {  
	            e.printStackTrace();  
	    }	
	}
	
//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
*/
//////////////////////////////////////////////////
	
       
        
	//For the integer value "amount",
	// using the currency denominations: 1c, 5c, 10c, 25c, $1, 
	// methods pay amount to customer using fewest numbers of coins.
	public static void payout_in_coins (int amount) {
          
            //make array with the different coin values
            int coins[] = {1, 5, 10, 25, 100};
            
            //have a counter for how many coins of each value are needed
            int pennycounter = 0;
            int nickelcounter = 0;
            int dimecounter = 0;
            int quartercounter = 0;
            int dollarcounter = 0;
            
            //arraylist of the coins that will be returned
            //the number of elements represent the number of coins
            //value of each element is the value of the coin
            ArrayList<Integer> coinList = new ArrayList<Integer>();
            
            //go in descending order because we want the least amount of coins
            //so we would have to pick from the highest valued coin first
            for(int i = coins.length - 1; i>=0; i--){
                
                while(amount >= coins[i]){
                    amount -= coins[i];
                    coinList.add(coins[i]);
                }
            }
            //check all the coin values and increase the corresponding counter
            for(int x = 0; x < coinList.size(); x++){
                switch(coinList.get(x)){
                    case 1:
                        pennycounter++;
                        break;
                    case 5:
                        nickelcounter++;
                        break;
                    case 10:
                        dimecounter++;
                        break;
                    case 25:
                        quartercounter++;
                        break;
                    case 100:
                        dollarcounter++;
                        break;
                }
            }
            
            //print out how many of each type of coin is required
            System.out.println("Pennies: " + pennycounter);
            System.out.println("Nickels: " + nickelcounter);
            System.out.println("Dimes: " + dimecounter);
            System.out.println("Quarters: " + quartercounter);
            System.out.println("Dollars: " + dollarcounter);
            
         
	}
        
        
	
	// Method to find MST using Prim's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Prim (int num_vertices, int num_edges, int[][] graph, int starting_vertex) {
		int total_weight=0;
                
                //set some infinity value
                int INF = 9999999;
                
                /**
                 * Steps for Prim
                 * 1. Select vertex (selected for us)
                 * 2. Select shortest edge connected to that vertex
                 * 3. Select shortest edge connected to any vertex already connected
                 * 4. Repeat step 3 until all vertices have been connected
                 */
                
                //arraylist for storing visited vertices
                ArrayList<Integer> visited = new ArrayList<Integer>();
                
                //add the starting vertex
                visited.add(starting_vertex);
                
                //counter variable for number of edges
                int edgeNo = 0;
                
                System.out.println("\nEdge:  Weight: \n");
                
                while(edgeNo < num_vertices){
                    
                    int min = INF;
                    //traverse graph
                    for(int i = 0; i < num_edges; i++){
                        //check if vertex is visited
                        if(visited.contains(graph[i][0])){
                            //check all vertices
                            for(int j = 0; j < num_vertices; j++){
                                //check if next vertex is unvisited, and if that edge has a weight
                                if(!visited.contains(graph[i][1]) && graph[i][2] != 0){
                                    //set the weight of that edge as the new minimum
                                    if(min > graph[i][2]){
                                        min = graph[i][2];
                                        //mark vertex as visited
                                        visited.add(graph[i][1]);
                                        //increment total weight
                                        total_weight += min;
                                        System.out.println("(" + graph[i][0] + ", " + graph[i][1] + ")   " + graph[i][2]);
                                    }
                                }
                            }
                        }
                    }
                    edgeNo++;
                }
                
            edgeNo = edgeNo -1;    
            System.out.println("\nThere are " + num_vertices + " vertices and " + edgeNo + " edges.\n");    
               
                
		return total_weight;
	}
	
        
        
	// Call method to find MST using Kruskal's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Kruskal (int num_vertices, int num_edges, int[][] graph) {
		int total_weight=0;
		
                
                /**
                 * Steps for Kruskal's Algorithm:
                 * 1. Select shortest edge in a network
                 * 2. Select the next shortest edge which does not create a cycle
                 * 3. Repeat Step 2 until all vertices have been connected
                 */
                //initialize some infinity value
                int INF = 999;
                //create list to store visited vertices
                ArrayList<Integer> visited = new ArrayList<Integer>();
                //create list to store weights of visited edges
                ArrayList<Integer> weights = new ArrayList<Integer>();
                //number of edges in MST
                int edgeNo = 0;
                
                //values to use for smallest edge
                int minimum = INF;
                int firstEdgeStart = 0;
                int firstEdgeEnd = 0;
                
                System.out.println("\nEdge:  Weight: \n");
                
                //traverse graph
                for(int i = 0; i < num_edges; i++){
                    //find smallest edge
                    if(graph[i][2] < minimum){
                        //set smallest edge as minimum
                        minimum = graph[i][2];
                        //save the start and end vertices for the smallest edge
                        firstEdgeStart = graph[i][0];
                        firstEdgeEnd = graph[i][1];
                    }
                }
                //mark start and end vertex of smallest edge as visited
                visited.add(firstEdgeStart);
                visited.add(firstEdgeEnd);
                //increment total weight
                total_weight += minimum;
                //store weight of smallest edge in array
                weights.add(minimum);
                
                System.out.println("(" + firstEdgeStart + ", " + firstEdgeEnd + ")   " + minimum);
                
                //finding the rest of the MST
                //number of edges must be smaller than number of vertices in MST
                while(edgeNo < num_vertices){
                    
                    int min = INF;
                    //traverse graph
                    for(int i = 0; i < num_edges; i++){
                        //check if the weighted edge exists, and if either the start or end vertex is unvisited
                        if(min > graph[i][2] && (!visited.contains(graph[i][0]) || !visited.contains(graph[i][1])) && !weights.contains(graph[i][2])){
                            //set new min value
                            min = graph[i][2];
                            //increment total weight
                            total_weight += min;
                            //save weight in array
                            weights.add(min);
                            //check if the start vertex of that edge was unvisited and mark as visited
                            if(!visited.contains(graph[i][0])){
                                visited.add(graph[i][0]);
                            }
                            //check if end vertex of that edge was unvisited and mark as visited
                            if(!visited.contains(graph[i][1])){
                                visited.add(graph[i][1]);
                            }
                            System.out.println("(" + graph[i][0] + ", " + graph[i][1] + ")   " + graph[i][2]);
                        }
                    }
                    edgeNo++;
                }
                
               edgeNo--; 
               System.out.println("\nThere are " + num_vertices + " vertices and " + edgeNo + " edges.\n"); 
               
               return total_weight; 
                
	}
        
}

