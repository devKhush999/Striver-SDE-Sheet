package Arrays.Power;

// https://leetcode.com/problems/powx-n/
// https://youtu.be/l0YC3876qxg
// https://takeuforward.org/data-structure/implement-powxn-x-raised-to-the-power-n/

public class Power {

    // ************************************ Recursive Appproach ************************************

    // ***************************** Power Solution: when n > 0 ********************************
    // T.C -> O(log(n))     Calling power func one time for finding half power of number
    // S.C -> O(log(n))     Recursion Stack Space
    // n is +ve
    public double power_(double a, int n){          // a^n
        if (n == 0)
            return 1d;

        double halfPower = power_(a, n/2);

        if (n % 2 == 0)
            return halfPower * halfPower;
        else
            return a * halfPower * halfPower;
    }


    // ***************************** Power Solution: when n > 0 && n < 0 ******************************
    // T.C -> O(log(n))     Calling power func one time for finding half power of number
    // S.C -> O(log(n))     Recursion Stack Space
    // n can be +ve or -ve
    public static double power(double a, int n){        // a^n
        if (n==0)
            return 1;
        double halfPower = power(a, n/2);

        if (n%2==0)
            return halfPower * halfPower;
        else {
            if (n > 0)
                return halfPower * halfPower * a;
            else
                return halfPower * halfPower / a;
        }
    }


    // ************************************ Iterative Approach ************************************
    // T.C --> O(log(n))        we are dividing N by 2 (N-- also makes odd N to even)
    // S.C --> O(1)

    public double power_Iterative(double x, int n){

        // Here we need to store N as 'long' type because, if n is Integer.MIN_VALUE,
        // then converting it to mod(n) will result in overflow

        // long N = n >= 0 ? n : - (long)n;

        long N = n;
        if (n < 0)
            N = -N;

        double power = 1;

        while (N != 0){

            if (N % 2 == 0){
                x = x * x;
                N /= 2;
            }
            else{
                power = power * x;
                N--;
            }
        }

        return n >= 0 ? power : 1 / power;
    }

}
