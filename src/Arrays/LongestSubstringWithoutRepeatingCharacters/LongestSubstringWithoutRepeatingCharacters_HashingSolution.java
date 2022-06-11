package Arrays.LongestSubstringWithoutRepeatingCharacters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// https://youtu.be/qtVh-XEpsJo
// https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
// https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/

public class LongestSubstringWithoutRepeatingCharacters_HashingSolution {

    // ***************************** Efficient Hashing Solution **********************************
    // In worst case there will be two traversal of the string. For eg. consider : "abcdefghzz"
    // TC -> O(2*n) = O(n)      At most two entire traversal
    // For holding the substring characters which can consists of 128 ASCII characters (mentioned in ques)
    // SC -> O(128) = O(1)

    // Intuition:  We only stores the characters of non-repeating substring
    // HashSet stores the character of current non-repeating substring
    // Whenever we found a repeating character in our current non-repeating substring (or HashSet)
    // we start removing all character till the previous occurring index of that repeating character
    // and again start forming next non-repeating substring from that index onwards (DO A DRY RUN or WATCH VIDEO of STRIVER)

    public int lengthOfLongestSubstring(String str) {
        int left = 0, right = 0;
        int maxLengthOfSubstringWithoutRepeatingChars = 0;

        // HashSet stores the character of current non-repeating substring
        HashSet<Character> allUniqueCharacters = new HashSet<>();

        // Again traversing till "right < str.length()" because if we get a non-repeating sub-string till
        // string.length(), then it will (or may (in case previously found)) be the maximum non-repeating sub-string
        while (right < str.length()){


            // Whenever we found a repeating character in our current non-repeating substring (or HashSet)
            // we start removing all character till the previous occurring index of that repeating character
            if (allUniqueCharacters.contains(str.charAt(right))){
                while (allUniqueCharacters.contains(str.charAt(right))){
                    allUniqueCharacters.remove(str.charAt(left));
                    left++;
                }
            }

            // adds characters of current non-repeating sub-string (may or may not be longest) into the set
            // but at some point the longest non-repeating sub-string will be found
            allUniqueCharacters.add(str.charAt(right));

            // length of current non-repeating substring will be "right - left + 1"
            maxLengthOfSubstringWithoutRepeatingChars = Math.max(right - left + 1 , maxLengthOfSubstringWithoutRepeatingChars);

            // finding next non-repeating substring
            right++;
        }
        return maxLengthOfSubstringWithoutRepeatingChars;
    }



    // *************************** Previous HashSet optimized using HashMap *****************************
    // TC -> O(n)               Only one traversal
    // For holding the substring characters which can consists of 128 ASCII characters (mentioned in ques)
    // SC -> O(128) = O(1)      128 ASCII characters

    // Intuition: In previous approach whenever we encountered a repeated character (say 'x') in our
    // current non-repeating substring, what we were Doing ?...
    // We were travelling from 'left' pointer till one index greater than the previous occurrence
    // of the 'x' (repeating character).
    // To do so, we were travelling by 'left++' and were removing all the characters that were coming before
    // the previous occurrence of the 'x' (repeating character)
    // Now, instead of travelling we move directly to the one index greater of 'x' (prev_index_of('x') + 1)
    // using a HashMap that stores the index of last occurrence of that character

    public int lengthOfLongestSubstring_HashMap_Optimized(String str) {
        int left = 0, right = 0;
        int maxLengthOfSubstringWithoutRepeatingChars = 0;

        HashMap<Character, Integer> lastSeenAtIndexMap = new HashMap<>();

        while (right < str.length()){

            // Below condition of Math.max() is important as unlike in case of HashMap, we haven't removed
            // the characters from the HashMap (that we were doing by manually removing characters at 'left'
            // index from HashSet), so if the prev occurrence of any repeating characters is greater than
            // the current 'left pointer (i.e, starting of current non-repeating substring), then only
            // move directly to it
            if (lastSeenAtIndexMap.containsKey(str.charAt(right)))
                left = Math.max(lastSeenAtIndexMap.get(str.charAt(right)) + 1, left);

            // storing the index of last occurrence of character
            lastSeenAtIndexMap.put(str.charAt(right), right);

            // length of current non-repeating substring will be "right - left + 1"
            maxLengthOfSubstringWithoutRepeatingChars = Math.max(right - left + 1, maxLengthOfSubstringWithoutRepeatingChars);

            // finding next non-repeating substring
            right++;
        }

        return maxLengthOfSubstringWithoutRepeatingChars;
    }


    // ************************** Previous HashMap solution Optimized using Array **************************
    // TC -> O(n)               Only one traversal
    // For holding the substring characters which can consists of 128 ASCII characters (mentioned in ques)
    // SC -> O(128) = O(1)      128 ASCII characters
    // If possible always use arrays instead of HashMap

    public int lengthOfLongestSubstring_ArrayOptimized(String str) {
        int left = 0, right = 0;
        int maxLengthOfSubstringNonRepeatingChars = 0;

        // For 128 ASCII Characters
        int[] lastSeenAtIndexArray = new int[128];
        Arrays.fill(lastSeenAtIndexArray, -1);

        while (right < str.length()){
            char currentChar = str.charAt(right);

            if (lastSeenAtIndexArray[currentChar] != -1)
                left = Math.max(lastSeenAtIndexArray[currentChar] + 1, left);

            lastSeenAtIndexArray[currentChar] = right;

            maxLengthOfSubstringNonRepeatingChars = Math.max(right - left + 1, maxLengthOfSubstringNonRepeatingChars);
            right++;
        }
        return maxLengthOfSubstringNonRepeatingChars;
    }
}
