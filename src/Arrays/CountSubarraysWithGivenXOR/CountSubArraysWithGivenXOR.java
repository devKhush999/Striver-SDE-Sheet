package Arrays.CountSubarraysWithGivenXOR;
import java.util.ArrayList;
import java.util.HashMap;

// Pre requisite : Sub Array Sum equals K

// https://youtu.be/lO9R5CaGRPY
// https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k/
// https://www.interviewbit.com/problems/subarray-with-given-xor/hints/
// https://www.geeksforgeeks.org/count-number-subarrays-given-xor/

// https://www.interviewbit.com/problems/subarray-with-given-xor/
// https://www.codingninjas.com/codestudio/problems/1115652?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=0


public class CountSubArraysWithGivenXOR {

    // ************************************ Brute Force ******************************************
    // Exploring all the sub-arrays & check id their XOR is the required one
    // TC -> O(n*n)
    // SC -> O(1)

    public int subArraysXor_BruteForce(ArrayList<Integer> arr, int xor) {
        int n = arr.size();
        int count = 0;

        for (int i = 0; i < n; i++){
            int current_xor = 0;
            for (int j = i; j < n; j++){
                current_xor ^= arr.get(j);

                if (current_xor == xor)
                    count++;
            }
        }
        return count;
    }



    // **************************** Efficient Hashing Solution ************************************
    // TC -> O(n)
    // SC -> O(n)

    int subArraysXor(int[] arr, int xor) {
        int subArrayCount = 0;
        int prefixXOR = 0;

        // Hashmap stores the "Prefix-XOR" as "KEY"  & "No. of times that prefix XOR occurs" as "VALUE"
        HashMap<Integer, Integer> prefixXOR_Map = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            prefixXOR ^= arr[i];

            // If prefix XOR is same as 'xor', then increment the count of sub-arrays
            if (prefixXOR == xor)
                subArrayCount++;

            // If map contains subarray with xor "prefixXOR ^ k", then updates 'count' by no. of
            // times that sub-array occurs previously (with that XOR) because that many no. of
            // sub-array with required XOR will exits before that 'ith' index
            if (prefixXOR_Map.containsKey(prefixXOR ^ xor))
                subArrayCount += prefixXOR_Map.get(prefixXOR ^ xor);

            // add the count of prefixXOR in the HashMap & update the count if it is already present in array
            prefixXOR_Map.put(prefixXOR, prefixXOR_Map.getOrDefault(prefixXOR, 0) + 1);
        }

        return subArrayCount;
    }
}
