package GreedyAlgorithms.NMeetingsInOneRoom.NMeetingsInOneRoom_GFG;

import java.util.ArrayList;
import java.util.Comparator;

// https://youtu.be/II6ziNnub1Q
// https://takeuforward.org/data-structure/n-meetings-in-one-room/
// https://www.geeksforgeeks.org/find-maximum-meetings-in-one-room/
// https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1#

/*
Initial Thought Process:-
Say if you have two meetings, one which gets over early and another which gets over late.
Which one should we choose?
If our meeting lasts longer the room stays occupied, and we lose our time.
On the other hand, if we choose a meeting that finishes early we can accommodate more meetings.
Hence, we should choose meetings that end early and utilize the remaining time for more meetings.
 */

public class NMeetingsInOneRoom {

    /*
    * Time Complexity:
        O(n) to iterate through every position and insert them in a data structure.
        O(n log n)  to sort the data structure in ascending order of end time.
        O(n)  to iterate through the positions and check which meeting can be performed.
    * Space Complexity: Since we used an additional data structure for storing the start time,
        end time (Meeting objects) & one array list for storing all meetings

    * Overall Time Complexity -> O(n) +O(n log n) + O(n) ~ O(n log n)
    * Overall Space Complexity -> O(n) + O(n) ~ O(n)
    */

    public int maxMeetings(int[] start, int[] end, int n) {
        // stores all the meetings
        ArrayList<Meeting> allMeetings = new ArrayList<>();

        // adding all the meetings into a list with start time & end time
        for (int i = 0; i < end.length; i++)
            allMeetings.add(new Meeting(start[i], end[i]));

        // sort all the meetings according to ending time
        allMeetings.sort(new MeetingComparator());

        // 1st meeting with the smallest ending time is performed at start
        int meetingsPerformed = 1;
        int previousMeetingEndTime = allMeetings.get(0).end;

        for (int i = 1; i < allMeetings.size(); i++){
            Meeting currMeeting = allMeetings.get(i);

            // current meeting will be performed only when, its starting time is greater than the
            // ending time of previous performed meeting
            if (currMeeting.start > previousMeetingEndTime){
                previousMeetingEndTime = currMeeting.end;
                meetingsPerformed++;
            }
        }
        return meetingsPerformed;
    }


    class Meeting{
        int start, end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting a, Meeting b){
            // Sort all the meetings by increasing order of their ending points
            return a.end - b.end;
        }
    }
}
