package GreedyAlgorithms.JobSequencingProblem;
import java.util.Arrays;
import java.util.Comparator;

// https://youtu.be/LjPx4wQaRIs
// https://takeuforward.org/data-structure/job-sequencing-problem/

public class JobSequencingProblem {

    /*
    *********************************** Efficient Solution ******************************************
    * INTUITION is the main KEY point here!
    * The strategy to maximize profit should be to pick up jobs that offer higher profits.
    * Hence, we should sort the jobs in descending order of profit
    * Now say if a job has a deadline of 4 we can perform it anytime between day 1-4,
    * but it is preferable to perform the job on its last day/hour.
    * This leaves enough empty slots on the previous days/hours to perform other jobs.
    * Just like how you do Deadlines in IIITD, do Assignments with shorter deadlines earlier
      than assignment which have longer deadlines; Doing on the last day itself XD

      * Steps:
      * Sort the jobs in descending order of profit.
      * If the maximum deadline is x, make an array of size x .Each array index is set to -1 or 0
        initially as no jobs have been performed yet.
      * For every job check if it can be performed on its last day.
      * If possible mark that index with the job id and add the profit to our answer.
      * If not possible, loop through the previous indexes until an empty slot is found.

      * Time Complexity: O(N log N) + O(N*M).
        O(N*log(N)) for sorting the jobs in decreasing order of profit.
        O(N*M) since we are iterating through all N jobs and for every job we are checking from
        the last deadline, say M deadlines in the worst case.

      * Space Complexity: O(M) for an array that keeps track on which day which job is performed if M is the maximum deadline available.
     */

    public int[] JobScheduling(Job[] allJobs, int n) {
        // Sorting all the jobs based on their decreasing order profits (with max. profits on the first)
        Arrays.sort(allJobs, new JobComparator());
        // This also works to sort based on their decreasing order profits
        // Arrays.sort(allJobs, (a, b) -> (b.profit - a.profit));

        // Finding the maximum value of deadline of all the jobs, & making an array of 'deadlinesDone'
        // to store the Jobs's ID (index of job in 'allJobs') at the index/time in which deadline
        // can be done (if a free-slot/free-time/vacant-time is found for given job)
        int maxDeadline = 0;
        for (Job jobs : allJobs)
            maxDeadline = Math.max(maxDeadline, jobs.deadline);

        // After doing all the Jobs with max. profits, this array will store the sequence at which Jobs
        // was performed (it basically stores the Job's ID at the index/time we did that Job)
        // We could also make a boolean array if "Job position sequence is not required"
        int[] deadlinesDone = new int[maxDeadline + 1];

        int profit = 0, jobsDone = 0;

        // We try to do every Job as late as possible, i.e, try to do that job on that job's deadline
        // day itself if possible. This is done so that we can do the remaining jobs with lesser deadlines
        // earlier
        // For every job check if it can be performed on its last day.
        // If possible mark that index with the job id and add the profit to our answer.
        // If not possible, loop through the previous indexes until an empty slot is found.
        for (int i = 0; i < allJobs.length; i++){
            int currentJobDeadline = allJobs[i].deadline;

            while (currentJobDeadline > 0  &&  deadlinesDone[currentJobDeadline] != 0)
                currentJobDeadline--;

            // If 'currentJobDeadline' = 0, that means we can't perform this Job. Because before/on
            // that Job's deadline, some other deadlines have been done all unit of time. So, there is
            // no time to do the current Job
            if (currentJobDeadline == 0)
                continue;

            // Else Just do this current Job just near to its deadlines (preferably on its deadline
            // day itself)
            deadlinesDone[currentJobDeadline] = allJobs[i].id;
            profit += allJobs[i].profit;
            jobsDone++;
        }

        return new int[]{jobsDone, profit};
    }


    static class Job {
        int id, profit, deadline;
        public Job(int x, int y, int z){
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }

    static class JobComparator implements Comparator<Job>{
        @Override
        public int compare(Job a, Job b){
            // Sorting all the job wrt to profits, jobs with more profit are placed first
            return b.profit - a.profit;
        }
    }
}
