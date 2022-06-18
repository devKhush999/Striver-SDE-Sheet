package GreedyAlgorithms.FractionalKnapSack;

import java.util.Arrays;
import java.util.Comparator;

// https://youtu.be/F_DDzYnxO14
// https://takeuforward.org/data-structure/fractional-knapsack-problem-greedy-approach/
// https://www.geeksforgeeks.org/fractional-knapsack-problem/

public class FractionalKnapsack_GFG {

    /*
      INTUITION:
    * An efficient solution is to use the Greedy approach.
    * The basic idea of the greedy approach is to calculate the ratio value/weight for each item
      and sort the item on basis of this ratio.
    * Then take the item with the highest ratio and add them until we can’t add the next item as a whole
      and at the end add the next item as much as we can.
    * This will always be the optimal solution to this problem.

      APPROACH:
    * The greedy method to maximize our answer will be to pick up the items with higher values.
    * Since it is possible to break the items as well we should focus on picking up items having
      higher value per unit weight first. To achieve this, items should be sorted in decreasing order
      with respect to their value per unit weight.
    * Once the items are sorted we can iterate. Pick up items with weight lesser than or equal
      to the current capacity of the knapsack.
    * In the end, IF the weight of an item becomes more than what we can carry,
      break the item into smaller units and take only that much weight which is required. (in order to
      maximize the values of boxes).
      Calculate the value corresponding of chosen weight (divided unit/weight) according to its
      current capacity and add this new value to our answer.

    * Time Complexity: O(n*log(n)) + O(n) ~ O(n * O(n * log(n)).
      O(n.log(n)) to sort the items and O(n) to iterate through all the items for calculating the answer.
    * Space Complexity: O(1), no additional data structure has been used.
    */

    private double fractionalKnapsack(int knapsackWeight, Item[] allItems, int n) {
        // Sort all the items by VALUES PER WEIGHT in DECREASING ORDER
        Arrays.sort(allItems, new ItemComparator());

        double totalValue = 0.0;
        double totalWeight = 0.0;

        for (int i = 0; i < allItems.length; i++){

            // Pick up items with weight lesser than or equal to the current capacity of the knapsack
            // and also add the weight & value of items into the knapsack taken into account
            if (allItems[i].weight + totalWeight <= knapsackWeight){
                totalValue += allItems[i].value;
                totalWeight += allItems[i].weight;
            }

            // In the end, IF the weight of an item required becomes more than what we can require to
            // fill the knapsack, break the item into smaller units and take only that much weight
            // which is required.
            else{
                double valuePerWeightOFCurrentItem = (double)allItems[i].value / allItems[i].weight;
                double weightToBeAdded = knapsackWeight - totalWeight;
                totalWeight += weightToBeAdded;
                totalValue += valuePerWeightOFCurrentItem * weightToBeAdded;
            }
            // If KnapSack has been filled. Simply stop further adding values & weights into the Knapsack
            if (totalWeight == knapsackWeight)
                break;
        }
        return totalValue;
    }


    static class Item {
        int value, weight;
        Item(int x, int y){
            this.value = x;
            this.weight = y;
        }
    }

    static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b){
            // Sort all the items by the items per weight
            double valuePerWeightInItemA = a.value/ ((double) a.weight);
            double valuePerWeightInItemB = b.value/ ((double) b.weight);

            if (valuePerWeightInItemA > valuePerWeightInItemB)
                return -1;
            else if (valuePerWeightInItemA < valuePerWeightInItemB)
                return 1;
            else return 0;

            // This can also be done
            // return Double.compare(valuePerWeightInItemB, valuePerWeightInItemA);
        }
    }
}
