package StackAndQueues.CelebrityProblem;
import java.util.Stack;

// https://www.geeksforgeeks.org/the-celebrity-problem/

public class CelebrityProblem_Stack {
    /* ****************************** EFFICIENT STACK SOLUTION **************************************
    * Time Complexity: O(3 * n)  =  O(n)
        * The total number of comparisons "3 * N"
        * O(n) for adding all persons into the stack
        * In while loop we always pop() two persons & push() one person, in total one person is removed
          This also, takes O(n)
        * O(n) to check whether the Candidate for "Celebrity" is indeed the celebrity

    * Space Complexity:    O(N)      where ‘N’ is the number of people at the party.
        * The size of the stack will be of the order of ‘N’.
     */
    public int findCelebrity(int n) {
        // Create a stack and assume that every person is celebrity, and push every person into the Stack
        Stack<Integer> celebrities = new Stack<>();
        for (int person = 0; person < n; person++)
            celebrities.push(person);

        // Finding celebrities
        // we will modify stack such that only the "Person" with perfect candidate for thr "Celebrity"
        // will remain in the stack
        while (celebrities.size() > 1){
            // Pick out two persons from the stack
            int A = celebrities.pop();
            int B = celebrities.pop();

            // If the person ‘A’ knows the person ‘B’  i.e ‘knows(A, B)’ return true,
            // then the person ‘A’ cannot be a celebrity, as he knows someone.
            // But 'B' "can" be a celebrity. So push ‘B’ in the stack.
            if (knows(A, B))
                celebrities.push(B);
            // Otherwise, if the person ‘A’ doesn't know the person ‘B’ i.e, knows(A, B) return false,
            // then the person ‘A’ can be a celebrity as he doesn't someone, so push ‘A’ in the stack.
            else
                celebrities.push(A);
        }

        // If there is no celebrity return -1
        if (celebrities.isEmpty())
            return -1;

        // Else pick out the candidate "Person" selected for celebrity
        int celebrity = celebrities.pop();

        // Only one Person remains in the stack, we check whether this "Person" is a celebrity or not
        // We check whether this "Celebrity is known to everyone or not" and check
        // whether this "Celebrity doesn't know anyone"
        for (int person = 0; person < n; person++)
            if (celebrity != person  &&  (knows(celebrity, person)  ||  !knows(person, celebrity)))
                return -1;

        // return the Celebrity found
        return celebrity;
    }

    // "knows(i, j)" implies whether Person 'i' knows 'j'
    // "!knows(i, j)" implies whether Person 'i' doesn't knows 'j'
    public boolean knows(int i, int j){
        return true;
    }
}

/** ********************************** Stack Solution ******************************************

 * Approach: If for any pair (i, j)  such that ‘i’!= ‘j’, if  ‘knows(i, j)’ returns true, then it implies
   that the person having id ‘i’ cannot be a celebrity as it knows the person having id ‘j’.
 * Similarly, if ‘knows(i, j)’ returns false, then it implies that the person having id ‘j’ cannot be a
   celebrity as it is not known by a person having id ‘i’. So, 'i' can be a celebrity as he doesn't know 'j'
 * We can use this observation to solve this problem


* Algorithm is as follows:
    * Create a stack AND assume all are celebrities AND push all Persons in it.
    * Run a loop while there are more than one Person in the stack and in each iteration do the following-:
        * Pop two Persons from the stack, let these elements be ‘A’ and ‘B’.
        * If the person ‘A’ knows the person ‘B’  i.e ‘knows(A, B)’ return true, then the person ‘A’
            cannot be a celebrity, so push ‘B’ in the stack.
        * Otherwise, if the person ‘A’ doesn't know the person ‘B’ i.e, knows(A, B) return false,
            then the person with ‘B’ cannot be a celebrity, so push ‘A’ in the stack.

    * If only one Person (with candidate for Celebrity) remains in the stack, we check whether this person
      is actually a celebrity or not. We check whether this "Celebrity is known to everyone or not"
      and check whether this "Celebrity doesn't know anyone"
    * If this person is a celebrity return his Value otherwise return -1.
 */
