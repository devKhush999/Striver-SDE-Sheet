package Arrays.TwoSum_FindAllPairs;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Arrays;

// https://www.geeksforgeeks.org/given-two-unsorted-arrays-find-pairs-whose-sum-x/

public class FindAllPairsWithGivenSum {


    // ****************************** Brute Force SSolution ****************************************
    // Sorting the array, then try to form pairs using two for loops
    // TC -> O(n * log(n)) + O(m * log(m)) + O(m * n)   Sorting both the array & nested loops
    // SC -> O(m) + O(n)  for sorting (Ignoring output size array/list size)

    public Pair[] allPairs_BruteForceSolution(long[] arr1, long[] arr2, long n, long m, long sum) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        ArrayList<Pair> list = new ArrayList<>();

        for (long val1 : arr1)
            for (long val2 : arr2)
                if (val1 + val2 == sum)
                    list.add(new Pair(val1, val2));

        Pair[] pairs = new Pair[list.size()];
        for (int i = 0; i < list.size(); i++)
            pairs[i] = list.get(i);

        return pairs;
    }



    // ***************************** Efficient Solution using HashMap/HashSet *************************
    // All Hashset & HashMap Operations are O(1)
    // As all pairs should be returned in sorted fashion, & there are two traversal in each array
    // T.C -> O(x * log(x)) + O(m) + O(n)       where x is average size of pairs
    // SC -> O(max(m,n))       for hashset size (ignoring Pairs array, i.e, output size)

    public Pair[] allPairs(long[] arr1, long[] arr2, long n, long m, long sum) {
        HashSet<Long> set = new HashSet<>();
        ArrayList<Pair> list = new ArrayList<>();

        for (long val1 : arr1)
            set.add(val1);

        for (long val2 : arr2)
            if (set.contains(sum - val2))
                list.add(new Pair(sum - val2, val2));

        Pair[] pairs = new Pair[list.size()];

        for (int i = 0; i < list.size(); i++)
            pairs[i] = list.get(i);

        Arrays.sort(pairs, new PairComparator());
        return pairs;
    }
}


class Pair {
    long first, second;
    public Pair(long first, long second) {
        this.first = first;
        this.second = second;
    }
}

class PairComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair a, Pair b){
        if (a.first != b.first)
            return a.first > b.first ? 1 : -1;
        else
            return (int)(a.second - b.second);
    }
}