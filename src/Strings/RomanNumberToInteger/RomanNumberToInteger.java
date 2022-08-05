package Strings.RomanNumberToInteger;
import java.util.HashMap;

public class RomanNumberToInteger {
    /************************************ Efficient Solution using HashMap *****************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int romanToInt_V1(String s) {
        HashMap<Character, Integer> romans = new HashMap<>();
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);

        int number = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i != s.length() - 1 && romans.get(s.charAt(i)) < romans.get(s.charAt(i + 1)))
                number -= romans.get(s.charAt(i));
            else
                number += romans.get(s.charAt(i));
        }
        return number;
    }

    /************************************ Efficient Solution using HashMap *****************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int romanToInt_V2(String s) {
        int[] romanMap = new int[26];
        romanMap['I' - 'A'] = 1;
        romanMap['V' - 'A'] = 5;
        romanMap['X' - 'A'] = 10;
        romanMap['L' - 'A'] = 50;
        romanMap['C' - 'A'] = 100;
        romanMap['D' - 'A'] = 500;
        romanMap['M' - 'A'] = 1000;

        int n = s.length();
        int number = 0;

        for (int i = n - 1; i >= 0; i--){
            int currRomanNumber = romanMap[s.charAt(i) - 'A'];

            if (i != n - 1  &&  currRomanNumber < romanMap[s.charAt(i + 1) -'A'])
                number -= currRomanNumber;
            else
                number += currRomanNumber;
        }
        return number;
    }
}
