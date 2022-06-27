package BinarySearch.AllocateMinimumNumberOfPages;

// https://youtu.be/gYmWHvRHu-s
// https://takeuforward.org/data-structure/allocate-minimum-number-of-pages/
// https://www.geeksforgeeks.org/allocate-minimum-number-pages/
// https://www.codingninjas.com/codestudio/problems/ayush-gives-ninjatest_1097574?

/*
****************************************** Intuition ******************************************
We are required to find the minimum number of pages among all possible maximum number of pages
of allocations.

1)  Set 'low' to maximum no. of pages. This can happen when there are equal no. of books & students
    Since we want to allocate maximum books which is minimum in number.
2)  Set 'high' to sum of all pages in the book. This happens when there is only 1 student.
    We end up allocating all book to him
Now, we know the lowest possible answer and the maximum possible answer and for general cases,
the answer will lie in between these edge cases.
Thus, we have a search space. This search space will be sorted.
* IMPORTANT: INPUT ARRAY NEEDN'T BE SORTED HERE. OUR SEARCH SPACE IS NOT INPUT ARRAY OF BOOKS
* OUR SEARCH SPACE IS [Max_of_pages, Sum_of_Pages] (Both inclusive)
Guess what? We reached our approach to use a binary solution.
 */
public class AllocateMinimumNumberOfPages {
    /*
    ************************************ BINARY SEARCH ***************************************
    * Time Complexity:  O(n * log(SumOfArray – MaxValueInArray))
      Reason: Binary search takes O(log(SumOfArray – MaxValueInArray)).
      For every search, we are checking if an allocation is possible or not.
      Checking for allocation takes O(n).

    * Auxiliary Space: O(1)              No extra data structure is used
     */
    public int allocateMinimumPages(int[] bookArray, int n, int students){
        // If there are more students than the books, some students will be left out & we can't arrange
        // books to all of them
        if (bookArray.length < students)
            return  -1;

        int maxPageInBooksOfArray = 0;
        int sumOfAllPagesInBookOfArray = 0;

        // Find the "Maximum pages" AND "Sum of the pages of all the Books" in the Array of Books
        for (int pages : bookArray){
            sumOfAllPagesInBookOfArray += pages;
            maxPageInBooksOfArray = Math.max(pages, maxPageInBooksOfArray);
        }

        // Set 'low' to maximum no. of pages. This can happen when there are equal no. of books & students
        // Since we want to allocate maximum books which is minimum in number.
        int low = maxPageInBooksOfArray;
        // Set 'high' to sum of all pages in the book. This happens when there is only 1 student.
        // We end up allocating all book to him
        int high = sumOfAllPagesInBookOfArray;

        // This stores the maximum number of pages assigned to a student which is minimum
        int minimumPagesAllocated = -1;

        while (low <= high){
            // check if it is possible to distribute the pages/books to all students
            // by using 'mid' as the minimum no. of required pages
            int midPages = (low + high) >> 1;


            // Try to allocate pages to each student in such a way that the sum of allocated
            // pages at "Each Student" is not greater than the mid-value of search space
            // No student should get the pages greater than current 'midPage'

            // If the allocation is possible then reduce the search upper boundary by mid-1
            // We save the "current allocates pages" into the "minimumPagesAllocated" as it can be the
            // possible answer. And we try to find more minimum pages we can allocate,
            // as we need to return the minimum answer
            if (canAllocateCurrentPageAsMinimumPages(bookArray, students, midPages)){
                minimumPagesAllocated = midPages;
                high = midPages - 1;
            }
            // If allocating students increases more than the number of students provided,
            // this shows that mid-value should be more, and hence we need more pages to allocate
            // we move right by restricting our lower boundary as mid+1
            else
                low = midPages + 1;
        }

        // This can also be done as 'low' will also always contain the minimum value of all the maximum
        // pages allocated. As when "low > high" happens, 'low' will be at the BREAK-POINT of
        // minimum value of all the maximum pages allocated, i.e, After 'low' all pages can be allocated to
        // students. Before 'low' allocation is not possible. Hence, 'low' will be the BREAK-POINT
        // return minimumPagesAllocated == -1 ? -1 : low;

        // We return the minimum pages we allocated
        return minimumPagesAllocated;
    }


    // This function checks whether we can assign pages (of n books) to all students in such way that
    // the maximum number of pages at each Student doesn't exceed the given "number of pages to be allocated"
    private boolean canAllocateCurrentPageAsMinimumPages(int[] bookArray, int students, int pageToBeAllocate){
        int studentsAllocatedBooks = 1;
        int pagesAllocatedToCurrentStudent = 0;

        // Sequentially assign pages to each student while the current number of assigned pages
        // doesn't exceed the value of "minimum pages to be allocated"
        for (int currentBookPages : bookArray) {

            // No student should get the pages greater than number of pages to be allocated
            // If possible to give more pages to same student, give it
            if (pagesAllocatedToCurrentStudent + currentBookPages  <=  pageToBeAllocate)
                pagesAllocatedToCurrentStudent += currentBookPages;
            // If pages are becoming greater than number of pages to be allocated, assign it to one
            // more student
            else if (pagesAllocatedToCurrentStudent + currentBookPages  >  pageToBeAllocate) {
                studentsAllocatedBooks++;
                pagesAllocatedToCurrentStudent = currentBookPages;
            }
        }
        // Case 1: If the number of students becomes more than the given students during the allocation
        // process, then the allocation is not possible
        // Case 2: If the number of students becomes less than the given students during the allocation
        // process, this means there was larger no. of pages to allocate. So, only few students took
        // all the pages to allocate.
        // We return "true" in that case, bcoz it doesn't matter as 'high' will bw reduced to 'mid-1'
        // we will find one more way of allocation with smaller no. of pages
        return studentsAllocatedBooks <= students;
    }
}
