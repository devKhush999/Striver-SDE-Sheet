package GreedyAlgorithms.FractionalKnapSack;

import java.util.Arrays;

// https://youtu.be/F_DDzYnxO14
// https://takeuforward.org/data-structure/fractional-knapsack-problem-greedy-approach/
// https://www.geeksforgeeks.org/fractional-knapsack-problem/

public class FractionalKnapsack_Leetcode {
    /*
       INTUITION:
    * An efficient solution is to use the Greedy approach.
    * The basic idea of the greedy approach is to calculate the ratio value/box for each item
      and sort the boxes on basis of this ratio.
    * Then take the boxes with the highest ratio and add them until we can’t add the next box as a whole
      and at the end add the next box as much as we can.
    * This will always be the optimal solution to this problem.

        APPROACH:
    * The greedy method to maximize our answer will be to pick up the boxes with higher values.
    * Since it is possible to break the boxes as well we should focus on picking up boxes having
      higher value per unit boxes first. To achieve this, boxes should be sorted in decreasing order
      with respect to their value per unit boxes.
    * Once the boxes are sorted we can iterate. Pick up boxes with boxes lesser than or equal
      to the current capacity of the Truck's Capacity.
    * In the end, IF the no. of boxes becomes more than the max. box capacity of truck,
      then take only that many boxes which is required to fill the truck (in order to maximize the
      values of boxes).
      Calculate the value corresponding of chosen boxes according to its current capacity and units,
      add this new value to our answer.

        * Time Complexity: O(n*log(n)) + O(n) ~ O(n * O(n * log(n)).
      O(n.log(n)) to sort the boxes and O(n) to iterate through all the boxes for calculating the answer.
    * Space Complexity: O(1), no additional data structure has been used.
    */
    public int maximumUnits(int[][] boxTypes, int truckSize) {

        // Sort all the boxes by VALUES PER BOXES in DECREASING ORDER
        Arrays.sort(boxTypes, (a,b) -> (b[1] - a[1]));
        
        int totalBoxesAdded = 0, totalUnits = 0;
        
        for (int i = 0; i < boxTypes.length; i++){

            // Pick up boxes in numbers that are lesser than or equal to the current capacity of the truck
            // and also add the box added & value of boxes into the truck taken into account
            if (boxTypes[i][0] + totalBoxesAdded <= truckSize){
                totalBoxesAdded += boxTypes[i][0];
                totalUnits += boxTypes[i][1] * boxTypes[i][0];
            }
            // In the end, IF the number of boxes required becomes more than what we can require to fill
            // the truck, take only that many boxes by which truck can be filled
            else{
                int boxToAdd = truckSize - totalBoxesAdded;
                totalUnits += boxTypes[i][1] * boxToAdd;
                totalBoxesAdded += boxToAdd;

                // Bcoz else block will be executed only when few boxes are required. So, we can break
                break;
            }
        }
        return totalUnits;
    }
}