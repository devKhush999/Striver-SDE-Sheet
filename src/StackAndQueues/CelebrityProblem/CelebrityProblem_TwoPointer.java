package StackAndQueues.CelebrityProblem;

/** *****************************  Two Pointer Approach  *****************************
 * Approach: If for any pair ('i', ‘j’)  such that 'i' != ‘j’, If ‘knows(i, j)’ returns true, then it
   implies that the person having id ‘i’ cannot be a celebrity as it knows the person having id ‘j’.
 * Similarly, if ‘knows(i, j)’ returns false, then it implies that the person having id ‘j’ cannot
   be a celebrity as it is not known by a person having id ‘i’.
 *
 * So, the Two Pointer approach can be used where two pointers can be assigned, one at the start and
   the other at the end of the elements to be checked, and the search space can be reduced.

 * This approach can be implemented as follow -:
 * Algorithm is as follows:
     * Initialize two integer variables ‘P’:= 0 and ‘Q’:= 'N' - 1.
     * ‘P’  and ‘Q’ will be two pointers pointing at the start and end of search space respectively.
     * Run a while loop till 'P' < ‘Q’ and in each iteration do the following.
        * If ‘knows(P, Q)’ returns true, then increment ‘P’ by 1. Because, 'P' can't be Celebrity but 'Q' can be.
          So, our search space will be reduced to [P + 1, Q]
        * If ‘knows(P, Q)’ returns false, then decrement ‘Q’ by 1. Because, 'Q' can't be celebrity but 'P' can be.
          So, our search space will be reduced to [P, Q - 1]
     * When, P == Q is reached, we would have found our Celebrity.
     * Check whether the person having id ‘P’ is indeed our celebrity or not.
     * If a person having id ‘P’ is a celebrity then return ‘P’, otherwise return -1.

 * Time Complexity:  O(2 * N)  =  O(N)    where ‘N’ is the number of people at the party.
    * We are doing two traversals
    * The number of queries from the matrix ‘M’ will be of order ‘N’.

 * Space Complexity:    O(1).
     * No extra space is used here.
 */

public class CelebrityProblem_TwoPointer {

    // A square NxN matrix knows[][] is used to represent people at the party such that if a "person"
    // of "row i" and "column j"  is set to true, it means ith person knows jth person
    public int findCelebrity(int n, boolean[][] knows) {
        // Person A --> Low pointer
        // Person B --> High pointer
        int personA = 0, personB = n - 1;

        // "Reduced our Search space" to the person who is actually the celebrity
        while (personA < personB){

            // If the person ‘A’ knows the person ‘B’  i.e ‘knows(A, B)’ return true,
            // then the person ‘A’ cannot be a celebrity, as he knows someone.
            // But 'B' "can" be a celebrity.
            // So reduce the "Search Space". As our celebrity will be in range [personA + 1, personB]
            if (knows[personA][personB])
                personA++;
            // Otherwise, if the person ‘A’ doesn't know the person ‘B’ i.e, knows(A, B) return false,
            // then the person ‘A’ can be a celebrity as he doesn't someone, but 'B' can't be a celebrity
            // as he is not known by someone.
            // So reduce the "Search Space". As our celebrity will be in range [personA, personB - 1]
            else
                personB--;
        }

        // Assign celebrity to personA (= personB)
        int celebrity = personA;

        // Verifying whether our celebrity is actually the Celebrity or not
        // We check whether this "Celebrity is known to everyone or not" and check
        // whether this "Celebrity doesn't know anyone"
        for (int person = 0; person < n; person++)
            if (celebrity != person  &&  (knows[celebrity][person] || !knows[person][celebrity]))
                return -1;

        // return the Celebrity found
        return celebrity;
    }
}
