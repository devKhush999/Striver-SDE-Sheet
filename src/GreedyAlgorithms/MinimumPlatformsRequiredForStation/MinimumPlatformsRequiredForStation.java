package GreedyAlgorithms.MinimumPlatformsRequiredForStation;
import java.util.Arrays;

// https://youtu.be/dxVcMDI7vyI
// https://takeuforward.org/data-structure/minimum-number-of-platforms-required-for-a-railway/
// https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/

public class MinimumPlatformsRequiredForStation {

    /*
    One traversal O(n) of both the array is needed after sorting O(N * log(N)).
    Time Complexity: O(2*N*log(N)) + O(2*N) ~ O(N * Log(N))
    Auxiliary space: O(1), As no extra space is required.
    */

    private static int findPlatform(int[] arrival, int[] departure, int n) {
        // Sorting both the arrival & departure times
        // And we are going to go through the "time" in a day
        // We see which train arrived first & departed first
        Arrays.sort(arrival);
        Arrays.sort(departure);

        // One platform is needed for 1st ever arrival of train (arrival[0])
        int platformsNeeded = 1;

        // After than as time passes by arrivalIndex move to 1 (to capture next coming train)
        // while departureIndex remains at 0, as 1st ever arrived train hasn't departed yet
        int arrivalIndex = 1, departureIndex = 0;
        int platform = 1;

        while (arrivalIndex < n){
            /*
             If arrival time of current train is less than (<=) departure time of previously arrived train
             then we need one more platform to accommodate current incoming train.
             And we move ahead (next arrivalIndex) for next incoming train
             */
            if (arrival[arrivalIndex] <= departure[departureIndex]){
                platform++;
                arrivalIndex++;
            }

            /*
             If arrival time of current train is greater (>) departure time of last ever arrived train
             (at last most departureIndex), that means lastly arrived train can be departed.
             So, depart the last ever arrived train (by decreasing no. of platforms in use)
             AND therefore, no. of platform currently in use will decrease.
             And we move ahead (at next departureIndex) to look for departing the any other previously arrived train
             */
            else if (arrival[arrivalIndex] > departure[departureIndex]){
                platform--;
                departureIndex++;
            }
            platformsNeeded = Math.max(platform, platformsNeeded);
        }
        return platformsNeeded;
    }

    /*
     Understand BY DRY RUN & WATCH VIDEO:
     One EDGE CASE: If all the previously arrived train have departed. (This can happen when arrival time
     of current train, is greater than the departure times of all trains that arrived previously)
     Then no. of platform currently in use will reduce to 0 and Departure Index will reach the
     same Index as arrival Index.
     Then, in next iteration of loop as the arrival time (arrival[arrivalIndex]) will be lesser than
     departure time (departure[departureIndex]) (Remember: currently now departureIndex = arrivalIndex)
     So, we use one more platform for current arriving train, platform in use becomes 1, and
     arrivalIndex increases to welcome next incoming train.
     */
}

/*
*******************************  Main Logical Question  *******************************
If i am sorting the two arrays then arrival and departure time for a train gets jumbled.
If we are sorting both arrays separately then after sorting it might be possible that at some index
the arrival and departure time does not belong to the same train.  So suppose in your example, if the
train which is leaving at  500 does not even arrive at 50 and instead it came at 120 or so, then how
is this solution working in it.. that part is really confusing. Shouldn't we use a custom class
"train" and sort it using our own comparator according to arrival and so?

* 1) See, You need how many trains are in at a time to determine the overlapping/coinciding train timings
     at any time. This will only determine the minimum no. of platforms needed at any time.
     If any train is departing before the arrival of next train, since we have sorted both arrival & departure
     time. It may happen that departing train not be the same train which has to depart (in unsorted array),
     it may be some other train which actually had to depart at that time (in unsorted array).
     But this doesn't matter because even if departing train is not same, as we have sorted departure
     timings. If any train is departing before that train we can just divert the incoming train there,
     because I am sure that if a train is departing it must have arrived previously as
     departure_time > arrival_time for every train.
     So, it won't matter because it does not matter na which platform is getting vacant, but if
     someone leaves it is for sure that the train would have arrived that is why it is leaving,
     so one plat will go empty na! & we can divert new incoming there.
     So when they are entering that matters, because we know for sure departure will always be
     after entering.

* 2) No!, even if they are not same, it does not matter to me, because I know always arrival of a train
     is before its departure. So I am sure if a train is arriving it will first arrive and then which ever
     train departs it has for sure arrived and hence is departing, this is the logic here. (Here only the
     arrival time of trains will determine overlapping of train timings & hence the minimum of
     platforms needed)

* 3) Just think yourself as a station master, and you just care how many came and how many went & how
     many timings of trains overlapped/collided. Not when one came and when went

* 4) No it won't because it does not matter na which platform is getting vacant, but if someone leaves
     it is for sure that the train would have arrived that is why it is leaving, so one plat will go empty na!

* 5) Because we only care as a station. Kb aa rahe and kab jaa rhe matter krta. Ovio h ki jaane wla time
     hmsha aane wle k bd hi aaega

* 6) Since arrival < departure always, we're just counting how many trains enter and leave throughput the day.

* 7) The concept here employed is the train departing from station has to be the one which has arrived
     prior. So it doesn't matter which train is leaving we are concerned only with the platform,
     if the platform is empty we can divert  the new train there
 */