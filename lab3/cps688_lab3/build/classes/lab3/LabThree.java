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
          
            int coins[] = {1, 5, 10, 25, 100};
            
            int pennycounter = 0;
            int nickelcounter = 0;
            int dimecounter = 0;
            int quartercounter = 0;
            int dollarcounter = 0;
            
            
            ArrayList<Integer> coinList = new ArrayList<Integer>();
            
            for(int i = coins.length - 1; i>=0; i--){
                
                while(amount >= coins[i]){
                    amount -= coins[i];
                    coinList.add(coins[i]);
                }
            }
            
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
		return total_weight;
	}
	
	// Call method to find MST using Kruskal's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Kruskal (int num_vertices, int num_edges, int[][] graph) {
		int total_weight=0;
		return total_weight;
	}
        
}

