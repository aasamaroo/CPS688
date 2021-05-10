/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.io.*;
import static java.lang.Double.min;
import java.util.*;

/**
 *
 * @author aasamaro
 */
public class LabFour{
	
	public static void main(String[] args) {
		
		// Input for Lab 3 will be read from the file "input_lab3.txt".
		try { 
			FileReader freader = new FileReader(new File("input_lab4.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			
			// Russian Peasant Multiplication Problem.
			// First row of the input file has 
			// 3 pairs of positive integers to be multiplied
			
			System.out.print("--- Q1 -------\n");
			row = readInput.readLine();
			String[] pairs = row.split(" ",6);
			for (int i=0; i<6;) {
				int m = Integer.parseInt(pairs[i++]);
				int n = Integer.parseInt(pairs[i++]);
				System.out.println("Russian Peasant Multiplication of "+m+" and "+n+".");
				System.out.println(+m+" X "+n+" = "+ rpMultiplication(m,n));
				System.out.print("--------------\n");
			}
			
			// Horner’s Rule for Polynomial Evaluation.
			// Second row of the input file row has 3 6-tuples 
			// First value is x, and the other 5 powers of x
			
			System.out.print("--- Q2 -------\n");
			row = readInput.readLine();
			String[] values = row.split(" ",18);
			for (int i=0; i<18;) {
				int x = Integer.parseInt(values[i++]);
				String output = "For x="+x+": Solve p(x)=";
				int[] coefficients = new int [5];
				for (int j=0; j<5; j++) {
					coefficients[j] = Integer.parseInt(values[i++]);
					if (j<4)
						output = output + coefficients[j]+"(X^"+(4-j)+")+";
				}
				System.out.println(output+coefficients[4]+"(X^0).");
				System.out.println("p("+x+") = "+polyEvaluation(x, coefficients));
			}
			
			// Closest Pair by Divide-and-Conquer.
			// Third row of the input file row has 3 arrays
			// For each array find the closest Pair
						
			System.out.print("--- Q3 -------\n");
			int n1=6;
			int n2=8;
			int n3=10;
			row = readInput.readLine();
			String[] numbers = row.split(" ",n1+n2+n3);
			for (int i=0; i<n1+n2+n3;) {
				int[] array_1 = new int [n1];
				String out = "For array [ ";
				for (int j=0; j<n1; j++) {
					array_1[j] = Integer.parseInt(numbers[i++]);
					out = out + array_1[j] +" ";
				}
				System.out.println(out+"]:");
				System.out.println("Least distance between two points = "+ closestPair(5,array_1));
				int[] array_2 = new int [n2];
				out = "For array [ ";
				for (int j=0; j<n2; j++) {
					array_2[j] = Integer.parseInt(numbers[i++]);
					out = out + array_2[j] +" ";
				}
				System.out.println(out+"]:");
				System.out.println("Least distance between two points = "+ closestPair(8,array_2));
				int[] array_3 = new int [n3];
				out = "For array [ ";
				for (int j=0; j<n3; j++) {
					array_3[j] = Integer.parseInt(numbers[i++]);
					out = out + array_3[j] +" ";
				}
				System.out.println(out+"]:");
				System.out.println("Least distance between two points = "+ closestPair(10,array_3));
			}
			System.out.print("--------------\n");
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
Do not make any other changes in the code.
*/
//////////////////////////////////////////////////
	
	// Solution for Question 1.
	// Returns the result of multiplication of integers m and n.
	
	public static int rpMultiplication(int m, int n) {
		// Write your code here
                
                //Initialize the result
                int result = 0;
                
                while(n > 0){
                    
                    //If second number is odd
                    //Add first number to result
                    if((n & 1) != 0){
                        result = result + m;
                    }
                    
                //Multiply first number by 2
                //Divide second number by 2
                m = m << 1;
                n = n >> 1;
                
                //Print out m and n for each time
                //One is doubled while the other is halved
                if((m!=0) && (n!=0)){
                    System.out.println(m+" "+n);
                }
                }
		return result;
	}
	
	
	// Solution for Question 2.
	// Apply Horner’s Rule to evaluate Polynomial for x 
	// The coefficients of the polynomial are given in the array "coefficients"
	
	public static int polyEvaluation(int x, int[] coefficients) {
		
            //Initialize the result
            int result = coefficients[0];
            
            //Evaluate the value of the polynomial using Horner's rule
            
            for(int i = 1; i < coefficients.length; i++){
                result = (result * x) + coefficients[i];
            }
		return result;
	}
	
	// Solution for Question 3.
	// Find the closest pair by distance in the
	// input array of numbers "input_array" of size "size"
         static class Point{
            int x, y;
            
            public Point(int x, int y){
                this.x = x;
                this.y = y;
            }   
        }	
         
        static class XSorter implements Comparator<Point>{
            @Override
            public int compare(Point point1, Point point2){
                return Double.compare(point1.x, point2.x);
            }
        }
        
        private static void sortX(ArrayList<Point> points){
            Collections.sort(points, new XSorter());
        }
        
         
         private static double distance(Point point1, Point point2){
             double xDist = Math.abs(point1.x - point2.x);
             double yDist = Math.abs(point1.y - point2.y);
             return Math.sqrt((xDist * xDist) + (yDist * yDist));
         }
         
         public static double bruteForce(ArrayList<Point> points){
             double minDistance = Double.MAX_VALUE;
             
            Point first = new Point(0, 0);
            Point second = new Point(0, 0);
             
             for(int i = 0; i < points.size(); i++){
                 for(int j = 1; j < points.size(); j++){
                     if(distance(points.get(i), points.get(j)) < minDistance && points.get(i) != points.get(j)){
                         minDistance = distance(points.get(i), points.get(j));
                         first = points.get(i);
                         second = points.get(j);
                     }
                 }
             }
             System.out.println("Points: ("+first.x+", "+first.y+"), ("+second.x+", "+second.y+")");
             return minDistance;
         }
         
         public static ArrayList<Point> leftSplit(ArrayList<Point> points, int size){
             ArrayList<Point> leftPoints = new ArrayList<Point>();
             int half = size/2;
             for(int i = 0; i < points.size(); i++){
                 if(i <= half){
                     leftPoints.add(points.get(i));
                 }
             }
             return leftPoints;
         }
         
         public static ArrayList<Point> rightSplit(ArrayList<Point> points, int size){
             ArrayList<Point> rightPoints = new ArrayList<Point>();
             int half = size/2;
             for(int i = 0; i < points.size(); i++){
                 if(i > half){
                     rightPoints.add(points.get(i));
                 }
             }
             return rightPoints;
         }
         
         
        
        
        
	public static double closestPair(int size, int[] input_array) {
		// Write your code here
                
                
            //Initialize list of points    
            ArrayList<Point> points = new ArrayList<Point>();
            
        
            //Initialize the result
            double result = Double.MAX_VALUE;
            double leftMin = Double.MAX_VALUE;
            double rightMin = Double.MAX_VALUE;
            
            
            //Print out list of points
                for(int i = 0; i < size-1; i+=2){
                points.add(new Point(input_array[i],input_array[i+1])); 
                }
                
               
                
                System.out.println("Points: ");
                for(int j = 0; j < points.size(); j++){
                    System.out.println("("+points.get(j).x+", "+points.get(j).y+")");
                }
                
            //Sort the points by X Value
                ArrayList<Point> sortedPoints = points;
                sortX(sortedPoints);
                
                
                System.out.println("Points Sorted by their X Coordinate: ");
                for(int j = 0; j < sortedPoints.size(); j++){
                    System.out.println("("+sortedPoints.get(j).x+", "+sortedPoints.get(j).y+")");
                }
             
            if(sortedPoints.size() <= 3){
                return bruteForce(sortedPoints);
            } else {
            
            ArrayList<Point> leftPoints = leftSplit(sortedPoints, size);
            ArrayList<Point> rightPoints = rightSplit(sortedPoints, size);
            
            
            Point leftMinPoint1 = new Point(0, 0);
            Point leftMinPoint2 = new Point(0, 0);
            
            for(int i = 0; i < (leftPoints.size() - 1); i++){
                 if(distance(leftPoints.get(i), leftPoints.get(i+1)) < leftMin){
                     leftMin = distance(leftPoints.get(i), leftPoints.get(i+1));
                     leftMinPoint1 = leftPoints.get(i);
                     leftMinPoint2 = leftPoints.get(i+1);
                 }
                }
            
            Point rightMinPoint1 = new Point(0, 0);
            Point rightMinPoint2 = new Point(0, 0);
            
            for(int i = 0; i < (rightPoints.size() - 1); i++){
                 if(distance(rightPoints.get(i), rightPoints.get(i+1)) < rightMin){
                     rightMin = distance(rightPoints.get(i), rightPoints.get(i+1));
                     rightMinPoint1 = rightPoints.get(i);
                     rightMinPoint2 = rightPoints.get(i+1);
                 }
                }
            result = min(rightMin, leftMin);    
            if(result == rightMin){
               System.out.println("Points: ("+rightMinPoint1.x+", "+rightMinPoint1.y+"), ("+rightMinPoint2.x+", "+rightMinPoint2.y+")");
           }else System.out.println("Points: ("+leftMinPoint1.x+", "+leftMinPoint1.y+"), ("+leftMinPoint2.x+", "+leftMinPoint2.y+")");
            }
            
           
                
return result;
        }
}
	


/*
 * Sample output:
 * 
--- Q1 -------
Russian Peasant Multiplication of 18 and 85.
--------------
18 X 55 = 1530
18	85
9	170
4 	340
2 	680
1 	1360
--------------
Russian Peasant Multiplication of 85 and 18.
--------------
85 X 18 = 1530
85	18
42	36
21 	72
10 	144
5 	288
2	576
1	1152
--------------
Russian Peasant Multiplication of Y and Z.
--------------
Y X Z = xxxx
Y	Z
xx  x
xx  x
x   x
x   x
--------------
--- Q2 -------
For x=5: Solve p(x)=0(X^4)+1(X^3)+2(X^2)+3(X^1)+4(X^0).
p(5) = -1
For x=9: Solve p(x)=4(X^4)+3(X^3)+2(X^2)+1(X^1)+0(X^0).
p(9) = -1
For x=6: Solve p(x)=0(X^4)+2(X^3)+4(X^2)+3(X^1)+1(X^0).
p(6) = -1
--- Q3 -------
For array [ 1 12 3 24 55 56]:
Least distance between two points = 2.56
Closest pair = (1,12),(55,56)
For array [ 11 2 3 6 8 9 4 555 ]:
Least distance between two points = 1.05
Closest pair = (2,3),(6,8)
For array [ 23 7 4 10 20 0 16 100 95 33 ]:
Least distance between two points = 3.444
Closest pair = (4,7),(0,16)
--------------
 */
