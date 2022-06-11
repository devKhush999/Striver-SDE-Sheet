package Arrays.LongestSubstringWithoutRepeatingCharacters;
import java.util.HashSet;

// https://youtu.be/qtVh-XEpsJo
// https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
// https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/

public class LongestSubstringWithoutRepeatingCharacters {

    // ********************************** Simple Brute Force 1 **********************************
    // TC -> O(n*n*n) two nested loops  & one string.contains() method that take O(n) time too
    // SC -> O(128) = O(1)  for holding the current substring which can consists of 128 ASCII characters (mentioned in ques)
    // Also, there can multiple substring of non-repeating character with same length

    public static int longestSubstringWithoutRepeatingCharacters_BruteForce1(String inputStr) {
        int longestSubstringLength = 0;

        for (int i = 0; i < inputStr.length(); i++){
            String currSubstring = "";

            for (int j  = i; j < inputStr.length(); j++){

                // If current substring already contains a character found at current index, then
                // we don't add it & break the loop bcoz it will be a Repeating character
                // So, continue with the next iteration (or substring) of outer loop
                if (currSubstring.contains(Character.toString(inputStr.charAt(j))))
                    break;

                currSubstring += inputStr.charAt(j);

                // If the length of current non-repeating sub-string is greater than previous found
                // non-repeating substring's, then we found a longer length substring with non-repeating
                // characters. So, update the length
                if (currSubstring.length() > longestSubstringLength){
                    longestSubstringLength = currSubstring.length();
                }
            }
        }
        return longestSubstringLength;
    }



    // ********************************** Simple Brute Force 2 **********************************
    // TC -> O(n*n) two nested loops  & Hashset's contains() method take O(1) time
    // SC -> O(128) = O(1)  for holding the current substring which can consists of 128 ASCII characters (mentioned in ques)
    // Also, there can multiple substring of non-repeating character with same length
    // Since, String contains() method operations take O(n) time
    // we can do better by using Hashset as Hashset.contains() take O(1)time

    public static int longestSubstringWithoutRepeatingCharacters_BruteForce2(String inputStr) {
        int longestSubstringLength = 0;

        for (int i = 0; i < inputStr.length(); i++){
            HashSet<Character> currSubstringCharacters = new HashSet<>();

            for (int j  = i; j < inputStr.length(); j++){

                // If current Hashset-Substring already contains a character found at current index, then
                // we don't add it & break the loop bcoz it will be a Repeating character
                // So, continue with the next iteration (or substring) of outer loop
                if (currSubstringCharacters.contains(inputStr.charAt(j)))
                    break;

                currSubstringCharacters.add(inputStr.charAt(j));

                // If the length of current non-repeating sub-string (given by "j - i + 1") is greater
                // than previous found non-repeating substring's, then we found a longer length
                // substring with non-repeating characters. So, update the length of longest substring.
                longestSubstringLength = Math.max(longestSubstringLength, j - i + 1);
            }
        }
        return longestSubstringLength;
    }



    // ************************************ Sliding Window Approach 1 ************************************
    // In worst case, TC will be quadratic for eg: Consider this input "abcdefghiklmno abcdefghiklmno "
    // Here we will iterate over every index (as left pointer). In that each iteration (of left), at least
    // we have to travel till half of array length (as right pointer)
    // TC -> O(n*n*n)   in worst case O(n*n) for traversal & O(n) for String.contains() method
    // SC -> O(128) = O(1)  for holding the current substring which can consists of 128 ASCII characters (mentioned in ques)

    public int lengthOfLongestSubstring_SlidingWindow1(String string) {
        int maxLengthOfNonRepeatingSubstring = 0;
        int left = 0, right = 0;

        int currSubstringLength = 0;
        String currSubstring = "";

        // Notice we are here iterating till condition "right < string.length()"
        // Because if we get a non-repeating sub-string till string.length(), then it will (or may) be the
        // maximum non-repeating sub-string. After that we don't move to the next 'left' pointer
        // because after than 'left' pointer, no maximum non-repeating sub-string can be found
        while (right < string.length()){

            // currSubstring.indexOf(string.charAt(right)) == -1        // This condition also works
            if (!currSubstring.contains(Character.toString(string.charAt(right)))){
                currSubstring += string.charAt(right);
                currSubstringLength++;

                // Moving to find next substring for given 'left' pointer
                right++;

                maxLengthOfNonRepeatingSubstring = Math.max(maxLengthOfNonRepeatingSubstring, currSubstringLength);
            }
            else{
                right = ++left;
                currSubstring = "";
                currSubstringLength = 0;
            }
        }
        return maxLengthOfNonRepeatingSubstring;
    }



    // ************************************ Sliding Window Approach 2 ************************************
    // We can do a little bit better by using HashSet for storing substring's character instead of direct storing substring
    // In worst case, TC will be quadratic for eg: Consider this input "abcdefghiklmno abcdefghiklmno "
    // Here we will iterate over every index (as left pointer). In that each iteration (of left), at least
    // we have to travel till half of array length (as right pointer)
    // TC -> O(n*n)     in worst case O(n*n) for traversal & O(1) for HashSet.contains() method
    // SC -> O(128) = O(1)  for holding the current substring which can consists of 128 ASCII characters (mentioned in ques)

    public int lengthOfLongestSubstring_SlidingWindow2(String string) {
        int maxLengthOfNonRepeatingSubstring = 0;
        int left = 0, right = 0;

        HashSet<Character> currSubstringCharacters = new HashSet<>();

        // Notice we are here iterating till condition "right < string.length()"
        // Because if we get a non-repeating sub-string till string.length(), then it will (or may) be the
        // maximum non-repeating sub-string. After that we don't move to the next 'left' pointer
        // because after than 'left' pointer, no maximum non-repeating sub-string can be found
        while (right < string.length()){

            if (! currSubstringCharacters.contains(string.charAt(right))){
                currSubstringCharacters.add(string.charAt(right));

                // This will be the length of current sub-string "right - left + 1"
                maxLengthOfNonRepeatingSubstring = Math.max(maxLengthOfNonRepeatingSubstring, right - left + 1);

                // Moving to find next substring for given 'left' pointer
                right++;
            }
            else{
                right = ++left;
                currSubstringCharacters = new HashSet<>();
            }
        }
        return maxLengthOfNonRepeatingSubstring;
    }
}
