package BinarySearch.AggressiveCows;
import java.util.Arrays;

// https://youtu.be/wSOfYesTBRk
// https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
// https://www.spoj.com/problems/AGGRCOW/
// https://www.codingninjas.com/codestudio/problems/chess-tournament_981299?

// Category of Question: "Minimum items/things should be Maximum" OR "Maximum of Minimum items/things"

public class AggressiveCows {
    /*
    ************************************* Brute Force *************************************
    * After sorting the array, we set a minimum distance (as 1), then we keep on increasing that
      distance until accommodation of all cows is not possible. We Stop if the cows can't be further
      arranged into stalls, because if with "current distance" we can't arrange cows into the stalls,
      then with greater distances we won't be able to arranged cows in stalls too.
      For this, we can use a for loop.
    * For checking if the cows can be arranged into stalls or not, we simply iterate over our n stalls,
      and for every stall with the "current minimum distance", we place our cow.
    * Time Complexity: O(n * log(n)) + O((max - min) * n)
      n is size of array
      where max is maximum value/position of array
      where min is minimum value/position of array
    * Space Complexity: O(1)
     */
    private int aggressiveCows_BruteForce(int[] stallsPositions, int aggressiveCows){
        int n = stallsPositions.length;
        // Sorting the array, it becomes easier to arrange cows into stalls
        Arrays.sort(stallsPositions);
        int maximumDistanceBtwCows = -1;

        // Iterate over the distance one by one, till further arrangement of cows into stalls isn't possible
        for (int distance = 1; distance <= stallsPositions[n-1] - stallsPositions[0]; distance++){
            if (canPlaceCowsAtADistance(stallsPositions, aggressiveCows, distance))
                maximumDistanceBtwCows = distance;
            else break;
        }
        return maximumDistanceBtwCows;
    }


    /*
   ************************************* Binary Search *************************************
   * After sorting the array, we set a minimum distance (as 1), then we keep on increasing that
     distance until accommodation of all cows is not possible.
   * Time Complexity: O(n * log(n)) + O(log(max - min) * n)
     n is size of array
     O(n * log(n)) due to sorting
     where max is maximum value/position of array
     where min is minimum value/position of array
   * Space Complexity: O(1)
    */
    private int aggressiveCows_BinarySearch(int[] stallsPositions, int aggressiveCows){
        int n = stallsPositions.length;
        // Sorting the array, it becomes easier to arrange cows into stalls
        Arrays.sort(stallsPositions);

        // minimum distance can be '1' set it to 'low'
        // Maximum Search space value will be max(stallsPositions) - min(stallsPositions)
        // Set it to 'high', it is the greatest distance between cows (if cows = 2)
        int low = 1, high = stallsPositions[n-1] - stallsPositions[0];
        int maximumDistanceBtwCows = -1;

        while (low <= high){
            int midDistance = low + (high - low)/2;

            // If cows can be placed in the stalls with current "minimum distance" as mid.
            // Then, save this distance as the "maximum Distance Between Cows" and move right
            // "low = mid + 1" to find more larger distance of placing cows into stalls
            if (canPlaceCowsAtADistance(stallsPositions, aggressiveCows, midDistance)){
                maximumDistanceBtwCows = midDistance;
                low = midDistance + 1;
            }
            // If cows can't be placed in the stalls with current "minimum distance" as mid.
            // We have taken much larger distance b/w the cows, so reduce the distance to "mid - 1"
            else
                high = midDistance - 1;
        }
        // 'high' will also store the answer, as it will be the break-point for the
        // largest (maximum) minimum distance b/w cow stalls. See Video
        // return high;
        return maximumDistanceBtwCows;
    }

    // Determines if the Cows can be arranged in the stalls with the given distance between them
    private boolean canPlaceCowsAtADistance(int[] stallsPositions, int cows, int distanceBetweenCows){
        int cowsPlaced = 1;
        int previousCowPosition = stallsPositions[0];

        for (int i = 1; i < stallsPositions.length; i++){
            if (stallsPositions[i] - previousCowPosition >= distanceBetweenCows){
                cowsPlaced++;
                previousCowPosition = stallsPositions[i];
            }
            if (cows == cowsPlaced)
                return true;
        }
        return false;
    }
}
