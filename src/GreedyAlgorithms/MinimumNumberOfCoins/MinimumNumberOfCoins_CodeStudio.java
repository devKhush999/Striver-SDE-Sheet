package GreedyAlgorithms.MinimumNumberOfCoins;
import java.util.ArrayList;

// https://youtu.be/mVg9CfJvayM
// https://www.codingninjas.com/codestudio/problems/975277?
// https://takeuforward.org/data-structure/find-minimum-number-of-coins/

// ****************** This problem is the special when Currencies are Indian **********************

public class MinimumNumberOfCoins_CodeStudio {
    /*
    ************************************** Approach 1 ********************************************
    * Since variable 'coins' is unique for every function call,
    *  it has to be set to zero in every function call in case multiple Test case are done using single object
    * TC -> O(n)    nearly if we consider very huge amount;         n -> value of amount
    * SC -> O(1)
    */
    private int coins = 0;
    private int decreaseAmount(int amount, int money){
        while (amount >= money){
            amount -= money;
            coins++;
        }
        return amount;
    }

    public int findMinimumCoins_V1(int amount){
        int[] currencies = {1000, 500, 100, 50, 20, 10, 5, 2, 1};
        coins = 0;

        for (int money : currencies)
            amount = decreaseAmount(amount, money);
        return coins;
    }


    /*
     ************************************** Approach 2 ********************************************
     * TC -> O(n)    nearly if we consider very huge amount;    n -> value of amount
     * SC -> O(1)
     */
    public static int findMinimumCoins_V2(int amount){
        int[] currencies = {1000, 500, 100, 50, 20, 10, 5, 2, 1};
        int coins = 0;

        // This list stores coins used to sum up the given amount
        ArrayList<Integer> coinsUsed = new ArrayList<>();

       for (int i = 0; i < currencies.length; i++)
           while (amount >= currencies[i]){
               amount -= currencies[i];
               coinsUsed.add(currencies[i]);
               coins++;
           }

        return coins;
    }


    /*
     ************************************** Approach 3 ********************************************
     * Even if we consider very huge amount we would need 9 iteration only (equal to 9 different currency coins in INDIA)
     * TC -> O(1)
     * SC -> O(1)
     */
    public static int findMinimumCoins_V3(int amount){
        int[] currencies = {1000, 500, 100, 50, 20, 10, 5, 2, 1};
        int coins = 0;

        // Think about this formula a little!
        for (int i = 0; i < currencies.length; i++)

            // while loop reduced to if condition
            if (amount >= currencies[i]){
                // Here coins must be updated earlier than amount coz updated amount can't be used to
                // calculate coins used
                coins += (amount/currencies[i]);
                amount =  (amount % currencies[i]);
            }

        return coins;
    }
}


/*
    * Why greedy Solution worked here?
    * Consider this example of coins currencies [9, 6, 5, 1]  to sum up the amount "Rs. 11"
        Its correct output is 2 coins (6 + 5)
        But previous code will give output as 3 coins (9 + 1 + 1)
        So, that algorithm will not work here because coins values are not equally spaced
    * It is clear that approach will not work here, but why?
    * Consider Indian currency system : [1, 2, 5, 10, 20, 50, 100, 500, 1000]
    * Reason is: Sum of any two adjacent coins is smaller than the next coins
      1 + 2 < 5
      2 + 5 < 10
      5 + 10 < 20
      10 + 20 < 50
      50 + 100 < 500
      100 + 500 < 600 and so on...
    * This pattern is not there is above example & so that's why greedy solution didn't work there
    * Usually currency system are designed in similar ways such that this pattern is observed!
*/


