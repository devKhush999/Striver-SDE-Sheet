package Arrays.RepeatAndMissingNumber;
import java.util.ArrayList;
import java.util.HashMap;

// https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/
// https://www.codingninjas.com/codestudio/problems/873366?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=0
// https://youtu.be/5nMGY4VUoRY

public class RepeatAndMissingNumber {

    // ***************************** Brute Force *****************************
    // T.C --> O(n)
    // S.C --> O(n)
    // Here problem can arise because of overflow in sums of array

    public static int[] missingAndRepeating(ArrayList<Integer> arr, int n) {
        int arraySum = 0;
        int repeatingNumber = 0;

        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : arr){
            // find the sum of entire array
            arraySum += num;

            // First find the repeating number by its count in array using HashMap
            if (count.containsKey(num))
                repeatingNumber = num;

            count.put(num, count.getOrDefault(num, 0) +1);
        }

        // Then find the array Sum without repeating element (consider repeating element just once)
        // This array Sum doesn't account for any repeating number & also has missing sum contribution from missing number
        int arraySumWithoutRepetition = arraySum - repeatingNumber;

        // AP series for sum of numbers from 1 to N
        int sumFrom1ToN = n * (n + 1)/2;

        // Missing number is given by difference of sumFrom1ToN with array Sum (having missing number)
        int missingNumber = sumFrom1ToN - arraySumWithoutRepetition;

        return new int[]{missingNumber, repeatingNumber};
    }



    // ************************************ Better Approach ************************************
    // T.C --> O(n)
    // S.C --> O(n)
    // Here we store the counts in an array (can easily get the missing & repeating numbers)

    public static int[] missingAndRepeating(int[] arr) {
        int n = arr.length;

        // Count array
        int[] counts = new int[n + 1];

        // Increasing counts of numbers in array
        for (int num : arr)
            counts[num]++;

        int missingNumber = 0, repeatingNumber = 0;

        for (int number = 1; number <= n;  number++){
            int countOfNumber = counts[number];

            if (countOfNumber == 0)
                missingNumber = number;

            if (countOfNumber == 2)
                repeatingNumber = number;
        }

        return new int[]{missingNumber, repeatingNumber};
    }



    // ************************************ Constant Space Approach ************************************
    // T.C --> O(n)
    // S.C --> O(1)
    // This involves mathematical formuales
    // Here we store the counts in an array (can easily get the missing & repeating numbers)

    public static int[] missingAndRepeating_(int[] arr) {
        int n = arr.length;

        // 1 + 2 + 3 + .. + n == > AP series formula
        // 1*1 + 2*2 + 3*3 + .. + n*n == > Sum of squares Formula
        long sumOfNumberFrom1ToN  =  (long) n * (n+1) / 2;
        long sumOfSquareOfNumberFrom1ToN  =  n * (n + 1) * (2L * n +1) / 6;

        long sumOfNumberInArray  =  0;
        long sumOfSquareOfNumberInArray  =  0;

        for (int i = 0; i < n; i++){
            sumOfNumberInArray  +=  arr[i];
            sumOfSquareOfNumberInArray  +=  ((long) arr[i] * arr[i] );
        }

        // Suppose missing number to be X & repeating number to be Y, then  (try doing this using example)
        //   X - Y    =  sumOfNumberFrom1ToN - sumOfNumberInArray
        // X*X - Y*Y  =  sumOfSquareOfNumberFrom1ToN  -  sumOfSquareOfNumberInArray = (X+Y)(X-Y)

        int X_minus_Y  =  (int) (sumOfNumberFrom1ToN - sumOfNumberInArray);
        int X_sq_minus_Y_sq  =  (int) (sumOfSquareOfNumberFrom1ToN - sumOfSquareOfNumberInArray);

        // Applied (a+b)(a-b) = a*a - b*b
        int X_plus_Y = X_sq_minus_Y_sq / X_minus_Y;

        // X - Y = a
        // X + Y = b
        // X = (a + b)/2

        int X = (X_minus_Y + X_plus_Y) / 2;
        int Y = X_plus_Y - X;

        int missingNumber = X;
        int repeatingNumber = Y;

        return new int[]{missingNumber, repeatingNumber};
    }
}
