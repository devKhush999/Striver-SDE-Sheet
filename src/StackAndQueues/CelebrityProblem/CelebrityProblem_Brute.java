package StackAndQueues.CelebrityProblem;

// https://www.geeksforgeeks.org/the-celebrity-problem/

public class CelebrityProblem_Brute {
    /*  **************************** BRUTE FORCE ********************************************
    * Brute force approach is "Checking each person whether he is celebrity or not"

    * Time Complexity:  O(N*N)     where ‘N’ is the number of people at the party.
        * The outer loop (for checking each Person as celebrity), will run ‘N’ times and
          inner loop (for checking whether current Person is Celebrity or not) both will run ‘N’ times.

    * Space Complexity:  O(1)    No extra space is used here.
     */
    public int findCelebrity(int n) {

        // Checking one by one whether the person (for each & every person) he is "Celebrity" or not
        findingCelebrity:
        for (int celebrity = 0; celebrity < n; celebrity++){

            // Verify that current Person is celebrity or not
            // for each & every Person (not same as Celebrity), check whether he knows celebrity
            // and celebrity doesn't know him
            for (int person = 0; person < n; person++){

                // If the "Celebrity" knows the current "Person", then our assumed Celebrity actually knows one
                // Person. Hence, he is actually not a celebrity.
                if (celebrity != person  &&  knows(celebrity, person))
                    continue findingCelebrity;

                // If the current "Person" doesn't knows the "Celebrity", then our assumed Celebrity is not by
                // everyone. Hence, he is actually not a celebrity.
                if (celebrity != person  &&  !knows(person, celebrity))
                    continue findingCelebrity;
            }
            // If no condition of Celebrity is violated, then our assumed Celebrity is indeed the Celebrity
            return celebrity;
        }
        // We were unable to find any celebrity out of 'n' people. So, no celebrity exits on party
        return -1;
    }

    // "knows(i, j)" implies whether Person 'i' knows 'j'
    // "!knows(i, j)" implies whether Person 'i' doesn't knows 'j'
    public boolean knows(int i, int j){
        return true;
    }
}
