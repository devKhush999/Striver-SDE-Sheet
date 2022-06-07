package Arrays.MergeIntervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

// Must Watch Video (Notes not much helpful)
// https://youtu.be/2JzRBPFYbKE
// https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/

public class MergeIntervals_OOPs {

    // *********************************** Fastest Approach ***********************************
    // T.C --> O(n * log(n)) + O(n)    Sorting + Traversal of Intervals
    // S.C --> O(n)  in worst case  due to arranges list

    public static List<Interval> mergeIntervals(Interval[] intervals) {
        // Sorting intervals according to the starting time
        Arrays.sort(intervals, new IntervalComparator());

        // Another way of sorting interval according to the starting time (not recommended though, as end time may be different for same start time)
        // Arrays.sort(intervals, (a, b) -> (a.getStartTime() - b.getStartTime()));

        // This is ArrayList of required non-overlapping intervals that cover all the intervals in the input
        List<Interval> arrangedIntervals = new ArrayList<>();
        Interval currentInterval = intervals[0];

        for (Interval nextInterval : intervals){

            // If the ending time of current interval is less than the starting time of next interval
            // then we don't need to change current interval, we can move to next interval
            // as both the intervals are already non-overlapping
            if (nextInterval.getStartTime() > currentInterval.getEndTime()){
                arrangedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }

            // If the ending time of current interval is >= than the starting time of next interval
            // then the both the current intervals are overlapping & are running in parallel
            // so we take the maximum ending time of both these intervals & updates it into the current interval
            else if (nextInterval.getStartTime() <= currentInterval.getEndTime()){
                int newEndTimeForCurrentInterval = Math.max(currentInterval.getEndTime(), nextInterval.getEndTime());
                currentInterval.setEndTime(newEndTimeForCurrentInterval);
            }
        }
        arrangedIntervals.add(currentInterval);

        return arrangedIntervals;
    }
}


class Interval{
    private int start;
    private int end;

    public Interval(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getStartTime(){
        return start;
    }

    public int getEndTime(){
        return end;
    }

    public void setStartTime(int start){
        this.start = start;
    }

    public void setEndTime(int end){
        this.end = end;
    }
}


class IntervalComparator implements Comparator<Interval> {

    @Override
    public int compare(Interval interval1, Interval interval2){
        if (interval1.getStartTime() > interval2.getStartTime())
            return 1;

        else if (interval1.getStartTime() < interval2.getStartTime())
            return -1;

        // When interval1.getStartTime() == interval2.getStartTime()
        else{
            if (interval1.getEndTime() > interval2.getEndTime())
                return  1;
            if (interval1.getEndTime() < interval2.getEndTime())
                return  -1;
            // When interval1.getEndTime() < interval2.getEndTime()
            else
                return 0;
        }
    }
}