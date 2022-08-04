package Strings.LongestCommonPrefix;

public class LongestCommonPrefix {
    /******************************* Efficient Solution 1 ********************************************
     * n -> Size of String array
     * x -> average length of each string

     * Time Complexity: O(n * x)
     * Space Complexity: O(1)
        * Neglecting output Space
     */
    public String longestCommonPrefix_V1(String[] str) {
        String prefix = str[0];

        for (int i = 1; i < str.length; i++){
            StringBuilder newPrefix = new StringBuilder();
            int minLength = Math.min(prefix.length(), str[i].length());

            for (int j = 0; j < minLength  &&  prefix.charAt(j) == str[i].charAt(j); j++)
                newPrefix.append(prefix.charAt(j));

            prefix = newPrefix.toString();
        }
        return prefix;
    }


    /******************************* Efficient Solution 2 ********************************************
     * n -> Size of String array
     * x -> average length of each string

     * Time Complexity: O(n * x)
     * Space Complexity: O(1)
        * Neglecting output Space
     */
    public String longestCommonPrefix_V2(String[] str){
        String prefix = str[0];

        for (int i = 1; i < str.length; i++){
            while (str[i].indexOf(prefix) != 0)
                prefix = prefix.substring(0, prefix.length() - 1);
        }
        return prefix;
    }
}
