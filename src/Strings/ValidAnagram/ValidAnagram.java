package Strings.ValidAnagram;

class ValidAnagram {
    /****************************** Efficient Solution 1: Array Map / Hash Map **************************
     * Follow Up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
     * Answer: In that case, our array size will just be of int[128]. The Only Difference.

     * Time Complexity: O(3 * n)
     * Space Complexity: O(26) = O(1)
     */
    public boolean isAnagram_V1(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        if (n1 != n2) return false;
        
        int[] count = new int[26];
        
        for (int i = 0; i < n1; i++)
            count[s.charAt(i) - 'a']++;
        
        for (int i = 0; i < n2; i++)
            count[t.charAt(i) - 'a']--;
        
        for (int i = 0; i < 26; i++)
            if (count[i] != 0)
                return false;

        return true;
    }


    /****************************** Efficient Solution 2: Array Map / Hash Map **************************
     * Follow Up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
     * Answer: In that case, our array size will just be of int[128]. The Only Difference.

     * Time Complexity: O(2 * n)
     * Space Complexity: O(26) = O(1)
     */
    public boolean isAnagram_V2(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        if (n1 != n2) return false;

        int[] count = new int[26];

        for (int i = 0; i < n1; i++)
            count[s.charAt(i) - 'a']++;

        for (int i = 0; i < n2; i++) {
            char ch = t.charAt(i);
            count[ch - 'a']--;

            if (count[ch - 'a'] < 0)
                return false;
        }
        return true;
    }
}