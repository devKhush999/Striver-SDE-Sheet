package Arrays.MergeIntervals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// Must Watch Video (Notes not much helpful)
// https://youtu.be/2JzRBPFYbKE
// https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/

public class MergeIntervals_BestApproach {

    // *********************************** Fastest Approach ***********************************
    // T.C --> O(n * log(n)) + O(n)    Sorting + Traversal of Intervals
    // S.C --> O(n)  in worst case  due to arranges list

    public int[][] merge(int[][] intervals) {

        // Sorting intervals according to the starting time
        Arrays.sort(intervals, new MyIntervalComparator());
        // Another way of sorting interval according to the starting time
        // Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        // This is ArrayList of required non-overlapping intervals that cover all the intervals in the input
        ArrayList<int[]> arrangedInterval = new ArrayList<>();
        int[] currentInterval = intervals[0];

        arrangedInterval.add(currentInterval);

        for (int[] nextInterval : intervals){

            // If the ending time of current interval is less than the starting time of next interval
            // then we don't need to change current interval, we can move to next interval
            // as both the intervals are already non-overlapping
            if (currentInterval[1] < nextInterval[0]){
                currentInterval = nextInterval;
                arrangedInterval.add(currentInterval);
            }

            // If the ending time of current interval is >= than the starting time of next interval
            // then the both the current intervals are overlapping & are running in parallel
            // so we take the maximum ending time of both these intervals & updates it into the current interval
            else if (currentInterval[1] >= nextInterval[0])
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
        }

        // We can return a list of arranged intervals if required
        int[][] arrangedIntervalArray = new int[arrangedInterval.size()][2];
        for (int i = 0; i < arrangedIntervalArray.length; i++)
            arrangedIntervalArray[i] = arrangedInterval.get(i);

        return arrangedIntervalArray;
    }
}


class MyIntervalComparator implements Comparator<int[]>{

    @Override
    public int compare(int[] a, int[] b) {
        // Sorting according to the Starting time of various Intervals

        // Case for a[0] > b[0]
        if (a[0] > b[0])
            return 1;

        // Case for a[0] < b[0]
        else if (a[0] < b[0]) {
            return -1;
        }

        // Case for a[0] == b[0]
        else {
            // Sorting according to the Ending time of various Intervals in case their starting time is same
            // Case for  a[1] > b[1]
            if (a[1] > b[1])
                return 1;

            // Case for  a[1] < b[1]
            else if (a[1] < b[1])
                return -1;

            // Case for  a[1] == b[1]
            else
                return 0;
        }

        // Shorthand for above code
        // return Integer.compare(a[0], b[0]);
    }
}