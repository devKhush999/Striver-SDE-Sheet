package GreedyAlgorithms.NMeetingsInOneRoom.NMeetingsInOneRoom_CodeStudio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://youtu.be/II6ziNnub1Q
// https://takeuforward.org/data-structure/n-meetings-in-one-room/
// https://www.geeksforgeeks.org/find-maximum-meetings-in-one-room/
// https://www.codingninjas.com/codestudio/problems/1062658?

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
        end time & positions (Meeting objects) & one array list for storing all meetings

    * Overall Time Complexity -> O(n) +O(n log n) + O(n) ~ O(n log n)
    * Overall Space Complexity -> O(n) + O(n) ~ O(n)
    */

    public List<Integer> maximumMeetings(int[] start, int[] end) {
        // stores all the meetings
        ArrayList<Meeting> allMeetings = new ArrayList<>();

        // adding all the meetings into a list with start time, end time & positions number
        for (int i = 0; i < end.length; i++)
            allMeetings.add(new Meeting(start[i], end[i], i + 1));

        // sort all the meetings according to ending time
        Collections.sort(allMeetings, new MeetingComparator());

        // Stores the positions of meetings performed (1-based indexing)
        List<Integer> meetingsPositions = new ArrayList<>();

        int previousMeetingEndTime = -1;

        for (int i = 0; i < allMeetings.size(); i++){
            Meeting currentMeeting = allMeetings.get(i);

            // current meeting will be performed only when, its starting time is greater than the
            // ending time of previous performed meeting
            if (currentMeeting.start > previousMeetingEndTime){
                meetingsPositions.add(currentMeeting.position);
                previousMeetingEndTime = currentMeeting.end;
            }
        }
        return meetingsPositions;
    }


    static class Meeting{
        int start, end, position;
        public Meeting(int start, int end, int position){
            this.start = start;
            this.end = end;
            this.position = position;
        }
    }

    static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting a, Meeting b){
            if (a.end != b.end)
                return a.end - b.end;
            return a.position - b.position;
        }
    }
}
