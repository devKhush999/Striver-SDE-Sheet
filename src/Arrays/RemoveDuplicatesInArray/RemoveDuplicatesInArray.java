package Arrays.RemoveDuplicatesInArray;
import java.util.TreeSet;

// https://youtu.be/Fm_p9lJ4Z_8
// https://takeuforward.org/data-structure/remove-duplicates-in-place-from-sorted-array/

public class RemoveDuplicatesInArray {

    /*
    *********************************** Brute Force : Set **************************************
    * Use of Set, though Hashing, i.e, HASHSET CAN'T BE USED HERE
    * BECAUSE HASHSET DOESN'T PRESERVE ORDER OF ELEMENTS
    * BUT TREE-SET PRESERVE ORDER OF ELEMENTS

    * TreeSet doesn't have O(1) operations, TreeSet internally creates a BST.
    * Therefore, adding & removing methods are of O(log(n))
    * TC -> O(n * log(n))+O(n)
    * SC -> O(n)            For TreeSet use
     */
    public int removeDuplicates_Hashing(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int val : arr)
            set.add(val);

        int length = 0;
        for (int value : set)
            arr[length++] = value;
        return length;
    }


    // ****************************** Most Efficient Solution ****************************************8
    // TC -> O(n)
    // SC -> O(1)
    public int removeDuplicates(int[] arr) {
        if (arr.length == 0)
            return 0;
        int length = 1;

        for (int i = 1; i < arr.length; i++)
            if (arr[length - 1] != arr[i])
                arr[length++] = arr[i];


        return length;
    }
}
