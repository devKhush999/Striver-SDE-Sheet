package BinarySearch.NthRootOfNumberBinarySearch;

// https://www.geeksforgeeks.org/n-th-root-number/
// https://www.codingninjas.com/codestudio/problems/1062679?
// https://en.wikipedia.org/wiki/Nth_root

public class NthRootOfNumber_Efficient {
    /*
    Time Complexity : O(log(M) * log(N))
    Space Complexity : O(1),
    where N and M are given integers.
    */
    public static double findNthRootOfM_V1(int n, long m) {
        // Variable to store maximum possible error in order to obtain the precision of 10^(-6) in the answer.
        double error = 1e-7;

        // Guessed answer value for "nth root of M"
        double xk = 2;

        // Difference between the current answer, and the answer in next iteration, which we
        // take as big as possible initially.
        double difference = 1e10;

        // We keep on finding the precise answer till the difference between
        // answer of two consecutive iteration become less than 10^(-7).
        while (difference > error){
            // Answer value in the next iteration.
            // double xk_1 = (Math.pow(xk, n)   * (n - 1) + m) / (n * Math.pow(xk, n - 1));
            double xk_1 =  (power(xk, n) * (n - 1) + m) / (n * power(xk, n-1));

            // Difference of answer in consecutive iterations updated.
            difference = Math.abs(xk - xk_1);

            // Updating the current answer with the answer of next iteration
            xk = xk_1;
        }
        // Returning the nthRootOfM with precision upto 6 decimal places which is 'xk'
        return xk;
    }


    public static double findNthRootOfM_V2(int n, long m) {
        // This code is same as previous one, just the expression is more simplified to one
        // call of power() function
        double error = 1e-7;

        double xk = 2;
        double difference = 1e10;

        while (difference > error){
            double xk_1 = (xk * (n-1)/n) + (m / (n * power(xk, n - 1)));

            difference = Math.abs(xk - xk_1);

            xk = xk_1;
        }
        return xk;
    }

    // Simple Power Function Code
    private static double power(double x, int n){
        if (n == 0)
            return 1;
        double halfPower = power(x , n/2);

        if (n % 2 == 0)
            return halfPower * halfPower;
        else {
            if (n > 0)
                return halfPower * halfPower * x;
            else
                return halfPower * halfPower / x;
        }
    }
}


/*
******************************* Newton Raphson Method *******************************
Let’s say X is the Nth root of M.
==>     X = M ^ (1 / N)
The above equation can be written as follows:
==>     X ^ N = M
==>     X ^ N - M = 0

Let’s assume F(X) = X^N - M

* Now we need to find the root of this equation, for which we can use the Newton
  Raphson method to solve this equation.
* In Newton's Raphson method we start with an initial guess value of X (any random initial value is fine)
  and iterate through a process that takes us towards the actual solution of the equation.
* Let’s say X(0) is the initial guess value.
* The relationship between the current value of solution value X(k) and the solution of the equation
  in the next iteration X(K + 1) is as follows:
        X(K + 1) = X(K) - F(X(K)) / F'(X(K)),   where F'(X(K)) denotes the value derivative
                                                of F(X) at X = X(K).         ...(1)
* So, n the given case F'(X(k)) = N * X(K) ^ N - 1.
  So equation (1) can be written as follows:
  ==>   X(K + 1) = X(K) - (X(K)^N - M) / (N * X(K)^(N - 1))
  ==>   X(K + 1) = (X(K) * (N * X(K)^(N-1)) - X(K)^N + M) / (N * X(K)^(N-1))
  ==>   X(K + 1) = ((X(K)^N) * (N - 1) + M) / (N * X(K)^(N-1))
  ==>   X(K + 1) = (X(K) * (N - 1)/N)  +  (M / (N * X(K)^(N-1)))

* So using the above formula to calculate new possible solution value X(k).
  Till the gap between X(K + 1) and X(K) become less than 10-7.
* When this condition is reached we get our required solution.

* Time Complexity: O(log(M) * log(N)), where N and M are given integers.
* Reason: As we are approaching the correct solution from the guessed solution, it will take order of
          O(log(M)) time in the worst case and for calculating X^N takes O(log(N)) order of time.

* Space Complexity: O(1)    As we are using constant extra space.
 */
