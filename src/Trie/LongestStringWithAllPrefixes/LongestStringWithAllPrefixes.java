package Trie.LongestStringWithAllPrefixes;

/*********************************** Brute Force **********************************************
 * Assume 'n' is the length of String[] words
 * Assume 'x' is the average length of each word in the String[]

 * Do the following for each word in "String[] words":     (looping over the String[] takes O(n) time)
    * Generate each prefix of the current word one by one  (looping over the word of length 'x', O(x) time)
    * For a word of length 'x', there will be 'x' prefixes
    * For each prefix, find whether that prefix is present in the String[]
    * Above step involves looping over the String[] (of length n) to find the presence of current prefix
      in String[]. To compare with each string (of length x) whole string will be traversed.
    * So, the above step will take O(n * x) to find whether the current prefix is present in the String[]

 * Time Complexity: O(n * x * n * x)
 * Space Complexity: O(1)
 */


// https://youtu.be/AWnBa91lThI

/************************************** Efficient Trie Solution **************************************
 * Assume 'n' is the length of String[] words
 * Assume 'x' is the average length of each word in the String[]

 * Intuition:
    * We were doing redundant operations to check whether each prefix of the word is present in String[] or not
    * We can check this in just O(x) using a Trie

 * Steps:
    * Insert all the 'n' words into the trie
    * For each word, find whether all the prefix of that word is present in the Trie or not
    * If all prefixes are present, take the longest one.

 * Time Complexity: O(n * x) + O(n * x)  =  O(n * x)
    * O(n * x) to insert all the 'n' words (each of length x) into the trie
    * Another O(n * x) to find whether the Trie contains all the prefixes of current word or not.
      Simply we just have to traverse the word along with the Trie to check each character in the word
      has a complete word in the Trie.
      Do this for all words to find the longest word
 */

public class LongestStringWithAllPrefixes {
    public String findLongestStringWithAllPrefixes(String[] words){
        // Initialize a Trie
        Trie trie = new Trie();

        // Insert all the words into the trie
        for (String word : words)
            trie.insert(word);

        // This string has all the prefix of that word in the Trie AND It is the longest one
        String longestWordWithAllPrefix = "";

        // Traverse all the words
        for (String word : words){

            // Check whether all the prefix of that word is present in the Trie or not
            if (trie.containsAllPrefixOfWord(word)) {

                int wordLength = word.length();
                int longestWordLength = longestWordWithAllPrefix.length();

                // Pick up the answer with maximum length
                if (wordLength > longestWordLength)
                    longestWordWithAllPrefix = word;

                // If both are of same length pick up the string which is lexicographically smaller
                else if (wordLength == longestWordLength  && longestWordWithAllPrefix.compareTo(word) > 0)
                    longestWordWithAllPrefix = word;
            }
        }
        return longestWordWithAllPrefix;
    }
}