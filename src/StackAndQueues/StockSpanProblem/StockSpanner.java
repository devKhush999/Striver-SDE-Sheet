package StackAndQueues.StockSpanProblem;
import java.util.Stack;

// Approach: Same as Previous Greater Problem
// We just need to figure out the "Day number (index)" of all "Stock Price" too. So, use a Class for this.

/**
 * Time Complexity: O(1)
    * Each call to the next(price) will have average of O(1) Time complexity
    * Think... For finding Previous greater element for 'n' elements using stack, we would require O(n) Time.
    * So, for each stock price (each element), it would take nearly O(1) Time complexity
 * Space Complexity: O(n)
    * In worst case (of decreasing order of stock price), there can be O(n) elements (stock prices) in the stack.
 */

public class StockSpanner {
    private final Stack<StockPrice> stack;
    private int days;

    public StockSpanner() {
        // Stack for Stock Prices, to find the "Previous Greater Stock Price" for each "Current Stock Price"
        this.stack = new Stack<>();
        // To store the "Day number" (or index) of each stock price
        this.days = 0;
    }

    public int next(int price) {
        StockPrice todayStock = new StockPrice(price, ++days);

        // Same as Previous Greater Element Concept
        while (!stack.isEmpty()  &&  stack.peek().price <= todayStock.price)
            stack.pop();

        // Find the maximum consecutive days according to the problem
        int spanOfTodayStockPrices = !stack.isEmpty() ? todayStock.day - stack.peek().day : todayStock.day;

        // Adding the current Stock price, as "It can be the Previous Greater Stock Price" for upcoming
        // stocks prices
        stack.push(todayStock);

        return spanOfTodayStockPrices;
    }

    // Utility Class for "Stock Prices" storing "Day number" & "Price of today stock"
    static class StockPrice{
        int price, day;
        public StockPrice(int price, int day){
            this.price = price;
            this.day = day;
        }
    }
}