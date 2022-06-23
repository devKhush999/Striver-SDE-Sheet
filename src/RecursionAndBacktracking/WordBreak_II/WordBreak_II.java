package RecursionAndBacktracking.WordBreak_II;
import java.util.ArrayList;
import java.util.List;

// MUST READ: https://www.geeksforgeeks.org/word-break-problem-using-backtracking/
// https://youtu.be/th4OnoGasMU

// Pre-requisite: Palindrome Partitioning of a string (Recursion & BackTracking Series)
/*
*   Intuition: Main Idea is to iterate through the every sub-strings (sequence of characters) in
    input string 's' to find a substring which is already present in the "wordDict".
*   If we found any substring which is also present in 'wordDict', then we add that substring
    into a list as a possible answer (in future) AND try to partition the remaining substring in the
    similar way via Recursion & Backtracking such that we again encounter a new substring (in
    that remaining substring) present in dictionary.
*   If we are able to partition entire input string 's' such that every partition of the string 's' is
    present in the 'wordDict' dictionary, then we convert this sequence of partition into a sentence
    and store it.
*   Then we again Backtrack to find more such partition (of entire input string 's'), such that every partition of the string 's' is
    present in the 'wordDict' dictionary.
*   THIS PROCESS IS EXACTLY THE SAME AS WE DID IN PROBLEM "Palindrome Partitioning of a string"
    of RECURSION & BACKTRACKING
*/

/*
Approach:
*  The initial idea will be to make partitions (of string 's') to generate substring and
   check if the substring generated out of the partition is present in "wordDict" dictionary or not.
*  If present, then we again partition the remaining (un-partitioned) substring in the string via
   recursive call.
*  Partitioning means we would end up generating every substring, and checking whether that partition
   is present in the "wordDict" dictionary at every step.

In every recursive call, we partition the string into two substring such that:
    1) If partitioned substring on left side (left substring) is present in "wordDict" dictionary,
       then we partition the right substring via recursive call.
       Else if left partitioned substring is not present in "wordDict" dictionary, then we simply
       skip that partition and continue partition at next index.

    2) When the base case (index == string.length()) has reached, this means all the left substring
       (and hence the entire string 's') has been partitioned, and they are present in "wordDict" dictionary

    3) Thus, we have partitioned the entire string into a partition, such that each string (or sub-string)
       in that partition is present in "wordDict" dictionary. Then, the list of partitions generated
       during that recursion call is inserted in a list of list.
*/

public class WordBreak_II {
    /*
    * Time Complexity: O(2^n * n)  ~  O(n * 2^n)
      Reason: O(2^n) to generate every substring. Because there are 2n combinations in The Worst Case.
      At every Index, we can either choose to partition or not, for string of length 'n', there will be
      2^n combinations of partition & non-partition. So, TC is O(2^n)
      O(n)  to check if the substring/partition is present in the 'wordDict' dictionary or not.

    * Space Complexity: O(k * x) + O(n)
      Reason: The space complexity can vary depending upon the length of the answer.
      'k' is the average length of the list of partitions and if we have x such list of partitions
      in our final answer. The depth of the recursion tree is O(n), so the auxiliary recursion stack
      space required is equal to the O(n).
    */

    public List<String> wordBreak(String s, List<String> wordDict) {
        // ArrayList to store all the word-broken partition substrings of a string
        ArrayList<String> allBrokenWords = new ArrayList<>();

        breakWord(0, s, s.length(), wordDict, new ArrayList<>(), allBrokenWords);
        return allBrokenWords;
    }

    private void breakWord(int index, String s, int n, List<String> dictionary, ArrayList<String> currentBrokenWord, ArrayList<String> allBrokenWords){
        // If index == n, this means all the left substring have been partitioned, and they are present
        // in the dictionary.
        // So, include the current partitioned list of substring sequence into answer list
        if (index == n){
            // This two functions are joining elements of list with space in between
            // allBrokenWords.add(convertListIntoString(" ", currentBrokenWord));
            // Inbuilt method "String.join()" is very much faster
            allBrokenWords.add(String.join(" ", currentBrokenWord));
            return;
        }

        for (int i = index; i < n; i++){
            // Partitioning the string into two strings one from [index, i] & other from [i+1, s.length()]
            // Checking if the left partitioning the string is present in dictionary or not
            // If Yes, then partition the right substring too. Else, skip that partition & continue
            // partition at next index.
            String subString = s.substring(index, i + 1);

            if (dictionary.contains(subString)){
                // add current left partition substring (which is present in dictionary) into a list
                currentBrokenWord.add(subString);
                breakWord(i + 1, s, n, dictionary, currentBrokenWord, allBrokenWords);
                currentBrokenWord.remove(currentBrokenWord.size() - 1);
            }
        }
    }

    // String.join() function implemented by me
    private String convertListIntoString(String separator, ArrayList<String> list){
        int n = list.size();
        String string = "";
        String previousWord = "";

        int i = 0;
        for (String word : list){
            if (i < n-1)
                string += word + separator;
            previousWord = word;
            i++;
        }
        string += previousWord;
        return string;
    }
}

/*
The Dynamic Programming solution only finds whether it is possible to break a word or not.
Here we need to print all possible word breaks.
We start scanning the sentence from the left. As we find a valid word, we need to check whether
the rest of the sentence can make valid words or not. Because in some situations the first found
word from the left side can leave a remaining portion that is not further separable.
So, in that case, we should come back and leave the currently found word and keep on searching
for the next word. And this process is recursive because to find out whether the right portion
is separable or not, we need the same logic. So we will use recursion and backtracking to solve
this problem. To keep track of the found words we will use a stack. Whenever the right portion
of the string does not make valid words, we pop the top string from the stack and continue finding.
 */