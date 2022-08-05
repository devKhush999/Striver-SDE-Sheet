package Strings.StringToInteger_Atoi;

// https://youtu.be/LozY2da_aLU

public class StringToInteger_Atoi {
    /******************************** Efficient Approach 1 : With long ******************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int myAtoi_V1(String s) {
        long number = 0;
        int sign = +1;
        int i = 0, n = s.length();

        // Check if empty string
        if (n == 0) return 0;

        // Skip white spaces from the starting of string
        while (i < n  &&  s.charAt(i) == ' ')
            i++;

        // Get the sign, if Available
        if (i < n  && (s.charAt(i) == '-' || s.charAt(i) == '+')){
            sign = s.charAt(i) == '+' ? +1 : -1;
            i++;
        }

        // Convert to the actual number and make sure it's not overflow
        while (i < n){
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9)
                break;
            number = 10L * number + digit;
            i++;

            // We can check for overflow, after adding 'digit' to the 'number' as "long" can be easily used
            // to check for Integer overflows
            if (sign == -1  &&  -number <= Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else if (sign == 1  &&  number >= Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
        }
        return (int) (sign * number);
    }


    /******************************** Efficient Approach 2 : Without long ******************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int myAtoi_V2(String s) {
        int number = 0;
        int sign = +1;
        int i = 0, n = s.length();

        // Check if empty string
        if (n == 0) return number;

        // Skip white spaces from the starting of string
        while (i < n  &&  s.charAt(i) == ' ')
            i++;

        // Get the sign, if Available
        if (i < n  && (s.charAt(i) == '-' || s.charAt(i) == '+')){
            sign = s.charAt(i) == '+' ? +1 : -1;
            i++;
        }

        // Convert to the actual number and make sure it's not overflow
        while (i < n){
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9)
                break;

            // We should check for overflow before adding the digit
            // This is same formula "number * 10 + digit > Integer.MAX_VALUE" just moved to RHS, because
            // on LHS it may overflow
            if (number > (Integer.MAX_VALUE - digit)/10)
                return sign == +1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            number = 10 * number + digit;
            i++;
        }
        return sign * number;
    }
}
