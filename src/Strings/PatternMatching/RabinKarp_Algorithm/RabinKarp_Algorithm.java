package Strings.PatternMatching.RabinKarp_Algorithm;

// Great Video with Intuition: https://youtu.be/qQ8vS2btsxI
// https://youtu.be/BQ9E-2umSWc

// Great Reading: https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
// https://www.programiz.com/dsa/rabin-karp-algorithm

public class RabinKarp_Algorithm {
    /*********************************** Rabin Karp Algorithm ***************************************
     * The Naive String Matching algorithm slides the pattern one by one
     * Like the Naive Algorithm, Rabin-Karp algorithm also slides the pattern one by one.
        But unlike the Naive algorithm, Rabin Karp algorithm matches the hash value of the pattern with
        the hash value of current substring of text (of same size as pattern), and if the hash values
        match then only it starts matching individual characters.
     * So Rabin Karp algorithm needs to calculate hash values for following strings.
        1) Pattern itself.
        2) All the substrings of the text of length m.
     * Rabin Karp algorithm is great improvement over Naive Brute Force Algorithm

     * Time Complexity:  O(m + n)    In Best and Average case
                       ~ O(m * n)    In extreme worst case (Actually lower than this)
        * where ‘N’ is the length of the "text" string and ‘M’ is the length of "pattern" string.
        * Worst case of Rabin-Karp algorithm occurs when all characters of pattern and text are same
            as the hash values of all the substrings of text[] match with hash value of patttern[].
            For example pat[] = “AAA” and txt[] = “AAAAAAA”.

     * Space Complexity: O(1)
        * No extra space used
     */

    // Number of unique characters present in the String, if the no. of unique characters are higher (> 26)
    // Then it affects the value of "MOD" to be chosen
    private final int UNIQUE_CHAR = 26;

    // To avoid overflows in Hash values of Pattern and Text's Sliding Window and power(UNIQUE_CHAR, m-1),
    // we mod these values.
    // The number of possible characters is higher than 26 (256 in general) and pattern length can be
    // large. So the numeric Hash values cannot be practically stored as an integer.
    // Therefore, the numeric value is calculated using modular arithmetic to make sure that the hash
    // values can be stored in an integer variable (can fit in memory words)
    private final int MOD = 1000000;

    public boolean containsPattern_RabinKap_RollingHash_Algorithm(String text, String pattern){
        int n = text.length();
        int m = pattern.length();


        // Hash value for text String. We ultimately want "HashValue(text) % MOD" to avoid overflow
        int textHash = 0;
        // Hash value for pattern. We ultimately want "HashValue(pattern) % MOD" to avoid overflow
        int patternHash = 0;

        // The value of "power" would be "Math.power(UNIQUE_CHAR, M-1) % MOD"
        // In this case, it would be "26^(m-1) % MOD"
        int power = 1;


        // Calculate the Hash value of pattern and first window (of size pattern) in the text using Hash Function
        // For this we map each character {a,b,c,d,...,z} to {1,2,3,4,...,26 }
        // This is simply how we "convert string to decimal number using digits" (for eg "123" to 123)
        // Formula : number = number * 10 + digit
        // but here base of number if "UNIQUE_CHAR". Consider this as "UNIQUE_CHAR"ary number system.
        // Base of the number can be ANY number (26 selected here, because can be 26 unique char in lowercase)
        // This Hashing function : 26^(m-1)*A + 26^(m-2)*B + ... + 26^1*X + 26^0*Y  for String "AB...XY" eas given by Rabin-carp
        // This is a normal Hash function
        for (int i = 0; i < m; i++){
            patternHash = (patternHash * UNIQUE_CHAR  + (pattern.charAt(i) - 'a' + 1)) % MOD;
            textHash = (textHash * UNIQUE_CHAR  + (text.charAt(i) - 'a' + 1)) % MOD;

            // Calculating "Math.power(UNIQUE_CHAR, M-1) % MOD"
            if (i != m -1)
                power = (power * UNIQUE_CHAR) % MOD;
        }

        // Slide the pattern string over the text string one by one
        for (int i = 0; i <= n - m; i++){

            // Check the Hash values of current window of text and pattern.
            // If the hash values match, then only check for each & every character in pattern one by one
            if (patternHash == textHash){
                int j = 0;
                // Check for every character one by one
                for (;j < m; j++){
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == m)     // Pattern Found
                    return true;
            }

            // Calculate Hash value for next sliding window of text:
            // Hash of the next shift "hash(text[i+1 .. i+m])" is efficiently computable from the
            // current hash value "hash(text[i .. i+m-1])" and next character in text "text[i+m]"
            // Remove leading digit (at ith index) and add trailing digit (at index "i + m")
            if (i < n - m){
                textHash = textHash - ((text.charAt(i) -'a' + 1) * power);
                textHash = (textHash * UNIQUE_CHAR + (text.charAt(i + m) -'a' + 1)) % MOD;

                // We might get negative value of hashValue of text, converting it to positive. Why???
                if (textHash < 0)
                    textHash = (textHash + MOD);
            }
        }
        // Pattern not found
        return false;
    }
}

/** ************************* Some Important points Regarding Rabin Karp Algorithm ********************

 1) If the no. of unique characters are higher (> 26), then it affects the value of "MOD" to be chosen.
    * If the number of unique character are much more (say 128 or 256), then value of "MOD" has to be less
      to avoid the overflow during the computations of "power" (especially), "textHash" and "patternHash"

 2) If the value of "MOD" is greater, then chances of false collision of Hash values of text & pattern
    will be quite low.
    If the value of "MOD" is smaller, then chances of false collision of Hash values of text & pattern
    will be quite high.
    But we should choose the value of "MOD" to be higher (for e.g. 1000000007 that usually we take), it
    increases the chances of overflow in computations of "power" (especially), "textHash", "patternHash".
    So, we can select a 5-6 digit number as our "MOD" value. This will not cause much less
    false collision and won't cause overflow too.
    But it depends on input size of (pattern) too. If the length of pattern is high, then we have to
    select 4-digit no. as our value to avoid overflow in "power".


 ***** IMP *******:
 * New Learnings from this Algorithm:
    * A new algorithm for pattern matching
    * New Properties of modulus:

        1) Modular Exponentiation: (a^b) % m
            (a^b) % m  =  (((((a * a) % m) * a) % m) * a) % m   .... repeated "b" times
            Dry Run over (2^5) % 3. This is used in computation of "power" variable, "(26 ^ (m-1)) % mod"
            See Memory Efficient Method:  "https://en.wikipedia.org/wiki/Modular_exponentiation"

        2) To calculate Modulus of a with b, i.e, "a % b". Two methods:
            * Directly "a % b"
            * In case length of digits are very long (say >= 10), compute mod of the number digit wise.
                See this: https://www.geeksforgeeks.org/how-to-compute-mod-of-a-big-number/
            * This is used int computation of Hash values of Pattern and Text
  */
