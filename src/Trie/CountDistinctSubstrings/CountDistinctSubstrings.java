package Trie.CountDistinctSubstrings;
import java.util.HashSet;

// https://youtu.be/RV0QeTyHZxo
// MUST READ: https://takeuforward.org/data-structure/number-of-distinct-substrings-in-a-string-using-trie/
// MUST READ: https://www.geeksforgeeks.org/count-number-of-distinct-substring-in-a-string/

public class CountDistinctSubstrings {
    /** *********************************** Easy Hashing Solution *************************************
     * Time Complexity  : O(n ^ 2)             On an average (nearly)
        * HashSet contains only unique substrings
        * HashSet takes O(1) time (on an average, O(log(m)) in worst case) to add any substring into
          the set (where m is the size of set)
        * In the extreme worst case (when string is too long),
            * Time complexity is : O(n^2 * log(m))   (where m is the size of set)

     * Space Complexity : O(n ^ 3)          On an average (approximately / nearly)
        * In worst case, set contains all possible unique sub-strings (O(n * (n + 1)/2) ~ O(n^2) in numbers)
          (this happens when all character in string are distinct)
        * Each substring in the set can be at least of 1 length or n length, and hence each substring
          will have an average length of O(n/2) ~ O(n) space.
     */
    public static int countDistinctSubstrings_Hashing(String s) {
        int n = s.length();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++){
            String subString = "";

            for (int j = i; j < n; j++){
                subString += s.charAt(j);
                set.add(subString);
            }
        }
        return set.size();
    }


    /******************************** Efficient Trie Solution ****************************************
     * Intuition: We can improve this using Trie.
        * The basic intuition is to generate all the substrings and store them in the trie along
          with its creation. If a substring already exists in the trie then we can ignore, else we
          can make an entry (TrieNode) and increase the count.

            * The idea is to insert the characters (in the current ongoing Substring starting at
              an index, say 'i') that are not present in the Trie.
            * Then we keep extending the current Substring by including characters on the right side
              of it, to produce a new Substring. We are extending the current Substring to form new
              Substring by shifting the index (nested index) to the right
            * We add the character (forming new substring) into the Trie.
            * And when such addition happens we know that this string is occurring for the first time,
              and increment count of Distinct Substrings (as it will contribute to new Substring)
            * And if some characters of the Substring is already present in Trie we just move on to the
              next node/index without reading them, because this Substring will not be unique (it has been
              found earlier, that's why that character is there) and hence move to next index to find next
              Substring.
              This helps us on saving space.

     * Assume 'n' is the length of input string

     * Time Complexity: O(n ^ 2)
        * We are just looping to find the substrings
        * We have to go through the entire string for all possible different starting points in
          order to generate all the substrings.
        * We are extending the current Substring to form new Substring by shifting the index to the right

     * Space Complexity: O(n * (n + 1)/2) ~ O(n ^ 2)
        * Though on average it would be much less than O(n^2) (very less, since in Trie we make use of
          TrieNodes in previous Substring to generate new Substring).
        * In worst case, when all character in input string is unique
        * For all Substring starting with index 0, Trie will have 'n' TrieNodes
        * For all Substring starting with index 1, Trie will have 'n - 1' TrieNodes
        * For all Substring starting with index 0, Trie will have 'n - 2' TrieNodes
        * ...
        * For all Substring starting with index n-2, Trie will have '2' TrieNodes
        * For all Substring starting with index n-1, Trie will have '1' TrieNodes
        * Trie nodes required forms an AP series (backward AP series)
        * So, in worst case "n * (n + 1) / 2" will be there
     */
    public static int countDistinctSubstrings_Trie(String string) {
        int n = string.length();

        // Initialize a Trie Data Structure
        Trie trie = new Trie();

        // Use the Trie to count the number of Distinct/Unique substrings
        return trie.countDistinctSubstrings(string, n);
    }
}
