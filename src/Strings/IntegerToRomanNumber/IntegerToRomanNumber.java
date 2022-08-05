package Strings.IntegerToRomanNumber;

// https://youtu.be/ohBNdSJyLh8

class IntegerToRomanNumber {
    /************************************ Efficient Solution using HashMap *****************************
     * Idea is to maintain a "Roman Number Map"
     * Idea is to consider all the greater numbers in the Roman Literals, then move to lower
        numbers one by one in sequence {1000, 500, 100, 50, 10, 5, 1}
     * But we can also have cases in which there is a "Smaller roman literal" before "Greater
        roman literal" denoting reduction in numbers, (for eg: CD, XL, etc.).
        There are 6 such cases: CM, CD, XC, XL, IX, IV
     * So, our idea is to insert these values into our "Roman number map" and follow same procedure.
     * Similar to problem "Minimum number of Coins": Greedy Algorithm

     * Time Complexity: O(n)
     * Space Complexity: O(2 * 26) = O(1)
     */
    public String intToRoman(int number) {
        // Roman Number Map
        int[] romanNumber = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanString = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; 
        
        StringBuilder roman = new StringBuilder();
        
        for (int i = 0; i < 13; i++){
            if (number >= romanNumber[i]){
                int count = number / romanNumber[i];
                
                while (count-- > 0)
                    roman.append(romanString[i]);
                
                number %= romanNumber[i];
            }
        }
        return roman.toString();
    }
}

/************************************ Brute Force Approach **************************************
 * Traverse for each and every number
 * Too long code

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        if (num >= 1000)  {
            int count = num / 1000;
            while (count-- > 0)
                sb.append('M');

            num %= 1000;
        }
        if (num >= 900)  {
            sb.append('C');
            sb.append('M');
            num -= 900;
        }
        if (num >= 500)  {
            sb.append('D');
            num -= 500;
        }
        if (num >= 400)  {
            sb.append('C');
            sb.append('D');
            num -= 400;
        }
        while (num >= 100)  {
            int count = num / 100;
            while (count-- > 0)
                sb.append('C');

            num %= 100;
        }
        if (num >= 90)  {
            sb.append('X');
            sb.append('C');
            num -= 90;
        }
        if (num >= 50)  {
            sb.append('L');
            num -= 50;
        }
        if (num >= 40)  {
            sb.append('X');
            sb.append('L');
            num -= 40;
        }
        while (num >= 10)  {
            int count = num / 10;
            while (count-- > 0)
                sb.append('X');

            num %= 10;
        }
        if (num == 9)  {
            sb.append('I');
            sb.append('X');
            num -= 9;
        }
        if (num >= 5)  {
            sb.append('V');
            num -= 5;
        }
        if (num == 4)  {
            sb.append('I');
            sb.append('V');
            num -= 4;
        }
        while (num > 0)  {
            sb.append('I');
            num -= 1;
        }
        return sb.toString();
    }
  */
