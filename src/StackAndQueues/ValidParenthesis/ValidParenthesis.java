package StackAndQueues.ValidParenthesis;
import java.util.Stack;

// https://youtu.be/wkDfsKijrZ8
// https://takeuforward.org/data-structure/check-for-balanced-parentheses/
// https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/

/************************************* Approach ********************************************
 * ***************** Algorithm *****************
 * Declare a character stack S.
 * Now traverse the given string.
 * If the current character is a starting bracket ('(' or '{' or '[') then push it to stack.
 * If the current character is a closing bracket (')' or '}' or ']') then pop from stack and
   if the popped character is the matching starting bracket then fine, else brackets are not balanced.
 * After complete traversal, if there is some starting bracket left in stack then “not balanced”
 *
 * Time Complexity: O(N)       Just one Iteration of string
 * Space Complexity: O(N)      We used one Stack
 */
public class ValidParenthesis {
    /*
    Time Complexity: O(N)       Just one Iteration of string
    Space Complexity: O(N)      We used one Stack
    */
    public boolean isValidParenthesis_UsingSwitchCase(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            switch (ch){
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(')
                        stack.pop();
                    else return false;
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{')
                        stack.pop();
                    else return false;
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[')
                        stack.pop();
                    else return false;
                    break;
                default:
                    stack.push(ch);
            }
        }
        return stack.isEmpty();
    }


    /*
    Time Complexity: O(N)       Just one Iteration of string
    Space Complexity: O(N)      We used one Stack
    */
    public boolean isValidParenthesis_UsingEnhancedSwitchCase(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            switch (ch){
                case ')' -> {
                    if (!stack.isEmpty() && stack.peek() == '(')
                        stack.pop();
                    else return false;
                }
                case '}' -> {
                    if (!stack.isEmpty() && stack.peek() == '{')
                        stack.pop();
                    else return false;
                }
                case ']' -> {
                    if (!stack.isEmpty() && stack.peek() == '[')
                        stack.pop();
                    else return false;
                }
                default -> stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /*
    Time Complexity: O(N)       Just one Iteration of string
    Space Complexity: O(N)      We used one Stack
    */
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            // If current character is not opening bracket, Push the element in the stack
            if (ch == '(' || ch == '[' || ch == '{')
                stack.push(ch);

            // If current character is not opening bracket, then it must be closing. So stack
            // cannot be empty at this point.
            else{
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();

                if ((ch == '}' && top == '{') || (ch == ']' && top == '[') || (ch == ')' && top == '('))
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
