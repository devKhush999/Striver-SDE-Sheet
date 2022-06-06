package Arrays.BestTimeToBuyAndSellStock;

// https://takeuforward.org/data-structure/stock-buy-and-sell/
// https://youtu.be/eMSfBgbiEjk

public class BestTimeToBuyAndSellStock {

    // ********************************* Fastest Approach *********************************
    // T.C ==> O(n)
    // S.C ==> O(1)
    public int maxProfit(int[] prices) {

        int minBuyPriceSoFar = prices[0];
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++){
            int profit = prices[i] - minBuyPriceSoFar;

            maxProfit = Math.max(maxProfit, profit);

            minBuyPriceSoFar = Math.min(minBuyPriceSoFar, prices[i]);
        }

        return maxProfit;
    }


    // ********************************* Another Approach *********************************
    // T.C ==> O(n)
    // S.C ==> O(n)

    public int maxProfit_(int[] prices) {
        int n = prices.length;

        int[] nextDayMaxPrices = new int[n];

        for (int i = n-1; i >= 0; i--){
            if (i == n-1)
                nextDayMaxPrices[i] = prices[i];
            else
                nextDayMaxPrices[i] = Math.max(prices[i], nextDayMaxPrices[i+1]);
        }

        int maxProfit = 0;

        for (int day = 0; day < n; day++){
            int currentProfit = nextDayMaxPrices[day] - prices[day];
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }


    // ********************************* Brute Force Approach *********************************
    // T.C ==> O(n*n)
    // S.C ==> O(1)

    public int maxProfit_BruteForce(int[] prices) {
        // Brute force checking each sell-price for each buy price

        int maxProfit = 0;

        for (int i = 0; i<prices.length; i++){
            int buyPrice = prices[i];

            for (int j = i; j<prices.length; j++){
                int sellPrice = prices[j];

                if (sellPrice-buyPrice > maxProfit)
                    maxProfit = sellPrice-buyPrice;
            }
        }
        return maxProfit;
    }

}
