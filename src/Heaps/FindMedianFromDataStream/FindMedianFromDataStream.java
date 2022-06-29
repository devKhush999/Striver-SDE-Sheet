package Heaps.FindMedianFromDataStream;
import java.util.ArrayList;

// https://www.baeldung.com/java-collections-complexity#:~:text=The%20ArrayList%20in%20Java%20is%20backed%20by%20an%20array.&text=So%20let's%20focus%20first%20on,runs%20in%20O(n)%20time


public class FindMedianFromDataStream {
    /*
    ************************************ Brute Force Solution 1 ****************************************
    * Gives TLE
    * Idea here is to use a ArrayList and the values into it every time.
    * Whenever we need to find the median, we first sort it. Then find Median as per formula
    * Time Complexity:
        1) O(1)                     for Add operation, adding at the end takes O(1) Time
        2) O(n * log(n)) + O(n)     for Finding Median (We first Sort the Array & then find median)
    * Space Complexity:   O(n)      for ArrayList used
     */
    static class MedianFinder_1 {
        private final ArrayList<Integer> numbers;   // array list to store numbers
        public MedianFinder_1() {
            this.numbers = new ArrayList<>();
        }

        public void addNum(int num) {
            numbers.add(num);                       // add 'num' to the end of list
        }

        public double findMedian() {
            numbers.sort((a, b) -> (a - b));        // Sorting the array list
            int n = numbers.size();

            // Finding median
            if (n % 2 == 1)
                return numbers.get(n / 2);
            else
                return (numbers.get(n/2) + numbers.get(n/2 - 1)) / 2.0;
        }
    }


     /*
    ************************************ Brute Force Solution 2 ****************************************
    * Doesn't gives TLE if Binary Search is used
    * Idea here is to use a ArrayList and the values into it in sorted positions.
    * We traverse the array list either by Simple traversal or Binary Search to find the
      Index/Position at which number is to be added such that resultant list will be sorted
    * Whenever we need to find the median. Simply find Median as per formula
    * Time Complexity:
        1) O(n)     for Add operation
            Reason:  Traversal & Binary Search takes O(n) & O(log(n)) time respectively and
                     addition takes O(n) time.
        2) O(1)     for Finding Median
            Reason:  Finding the median values takes O(1) time (list.get() takes O(1) time)
    * Space Complexity:   O(n)      for ArrayList used
     */
     static class MedianFinder_2 {
        private ArrayList<Integer> numbers;     // array list to store numbers
        private int n;                          // n is size of array-list

        public MedianFinder_2() {
            this.numbers = new ArrayList<>();
        }

        // Adding number into the arrayList, via linear traversal
        // Time Complexity: O(n) + O(n) = O(n)
        // get() –> is always a constant time O(1) operation
        public void addNum_LinearTraversal(int num) {
            int i = 0;
            for (i = 0; i < n; i++){        // Finding the index at which 'num' should be placed
                if (num > numbers.get(i))   // such that resultant list is always sorted
                    break;
            }
            numbers.add(i, num);
            n++;
        }

         // Since we add the numbers into the array such that list is always sorted
         // So, why not to find the index of insertion via binary search
         // Time Complexity: O(log(n)) + O(n) = O(n)
         // Using Binary Search Solution is accepted
         // get() –> is always a constant time O(1) operation
         public void addNum_BinarySearch(int num) {
             int low = 0, high = n - 1;

             while (low <= high){
                 int mid = (low + high) >> 1;

                 if (numbers.get(mid) <= num)
                     low = mid + 1;
                 else if (numbers.get(mid) > num)
                     high = mid - 1;
             }
             numbers.add(low, num);
             n++;
         }

        public double findMedian() {        // Finding median
            if (n % 2 == 1)
                return numbers.get(n / 2);
            else
                return (numbers.get(n/2) + numbers.get(n/2 - 1)) / 2.0;
        }
    }
}
