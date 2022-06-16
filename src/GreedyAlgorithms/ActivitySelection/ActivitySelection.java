package GreedyAlgorithms.ActivitySelection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Same as "N-Meetings in one room" Question
// PRE-REQUISITE: N-Meetings in one room
// https://youtu.be/II6ziNnub1Q
// https://takeuforward.org/data-structure/n-meetings-in-one-room/
// https://www.geeksforgeeks.org/find-maximum-meetings-in-one-room/
// https://www.codingninjas.com/codestudio/problems/1062712?

/*
Initial Thought Process:-
Say if you have two activities, one which gets over early and another which gets over late.
Which one should we choose?
If our activity lasts longer, and we lose our time.
On the other hand, if we choose an activity that finishes early we can accommodate more activities.
Hence, we should choose activities that end early and utilize the remaining time for more activities.
 */

public class ActivitySelection {
    /*
    * Time Complexity:
        O(n) to iterate through every activity and insert them in a data structure.
        O(n * log(n))  to sort the data structure in ascending order of end time.
        O(n)  to iterate through the positions and check which activity can be performed.
    * Space Complexity: Since we used an additional data structure for storing the start time,
        end time (Activity objects) & one array list for storing all meetings

    * Overall Time Complexity -> O(n) +O(n log n) + O(n) ~ O(n log n)
    * Overall Space Complexity -> O(n) + O(n) ~ O(n)
    */
    public  int maximumActivities(List<Integer> start, List<Integer> end) {
        // stores all the activity
        ArrayList<Activity> allActivities = new ArrayList<>();

        // adding all the activities into a list with start time & end time
        for (int i = 0; i < start.size(); i++)
            allActivities.add(new Activity(start.get(i), end.get(i)));

        // sort all the activities according to ending time
        allActivities.sort(new ActivityComparator());

        int activitiesDone = 0;
        int prevActivityEndTime = -1;

        for (int i = 0; i < allActivities.size(); i++){
            Activity currentActivity = allActivities.get(i);

            // current activity will be performed only when, its starting time is >= than the
            // ending time of previous performed activity
            if (currentActivity.start >= prevActivityEndTime){
                activitiesDone++;
                prevActivityEndTime = currentActivity.end;
            }
        }
        return activitiesDone;
    }

    class Activity{
        int start, end;
        public Activity(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    class ActivityComparator implements Comparator<Activity> {
        @Override
        public int compare(Activity a, Activity b){
            // Sort all the activities by increasing order of their ending points
            return a.end - b.end;
        }
    }
}
