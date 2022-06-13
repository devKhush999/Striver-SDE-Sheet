package LinkedList.IntersectionOfTwoLinkedLists;
import java.util.HashSet;

// https://youtu.be/u4FWXfgS8jw
// https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/

public class IntersectionOfTwoLinkedLists {

    // ********************************* Brute Force ****************************************
    // For each node in list1, traverse list2 from start to end & check whether the
    // current node is equal or not. If they are equal (same address), then intersection point exists.
    // TC -> O(m*n)             m & n are size of two linked-list
    // SC -> O(1)

    public ListNode getIntersectionNode_BruteForce(ListNode headA, ListNode headB) {

        while (headA != null){
            ListNode ptrB = headB;

            while (ptrB != null){
                if (ptrB == headA)
                    return headA;

                ptrB = ptrB.next;
            }
            headA = headA.next;
        }

        // unable to find any point of intersection
        return null;
    }


    // ****************************** Solution using Hashing ***********************************
    // Traverse listA and put its nodes in a HashSet, then traverse listB & check whether HashSet
    // contains any Node. If yes, the current node on which we are at (in listB) is intersection node.
    // TC -> O(m + n)
    // SC -> O(max(m, n))

    public ListNode getIntersectionNode_Hashing(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();

        while (headA != null){
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null){
            if (set.contains(headB))
                return headB;
            headB = headB.next;
        }

        // unable to find any point of intersection
        return null;
    }


    // **************************** Efficient Solution 1 **************************************
    // First we calculate the difference of length between two linked-list
    // Then we move the pointer of longer linked-list (out of listA & listB), by that
    // difference of length. Due to this, the distance of both pointer (on respective linked-list)
    // till the end becomes equal (in short both the pointer are at equal distance from their end
    // of linked-list)
    // Then we move both the pointers together until intersection found

    // Complexities:
    // TC -> O(2 * max(m,n))  =  O(max(m, n))
    // First O(max(m,n)) to find the difference in length, and another max(m,n) to find intersection
    // SC -> O(1)
    private int getLengthDifference(ListNode headA, ListNode headB){
        int lenA = 0, lenB = 0;

        while (headA != null || headB != null){
            lenA = headA == null ? lenA : lenA + 1;
            lenB = headB == null ? lenB : lenB + 1;

            headA = headA == null ? null : headA.next;
            headB = headB == null ? null : headB.next;
        }

        return lenA - lenB;
    }

    public ListNode getIntersectionNode_CalculateLength(ListNode headA, ListNode headB) {
        int lengthDifference = getLengthDifference(headA, headB);

        ListNode ptrA = headA, ptrB = headB;

        if (lengthDifference > 0)
            while (lengthDifference > 0){
                ptrA = ptrA.next;
                lengthDifference--;
            }

        else
            while (lengthDifference < 0){
                ptrB = ptrB.next;
                lengthDifference++;
            }


        while (ptrA != null){
            if (ptrA == ptrB)
                return ptrA;

            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }

        return null;
    }


    // **************************** Efficient Solution 2 **************************************
    // This is same version of previous solution, INTUITION is must.
    // Point each to the head of the lists. (say 'a' for list-A & b for list-B)
    // Suppose list-B is shorter than list-A.
    // Traverse both list simultaneously, pointer for shorter list-B will reach null (say 'b')
    // earlier than 'a'
    // then the distance of other pointer 'a' to the pointer 'b' (which is currently null)
    // is the difference in length of linked-lists.
    // Now, we point pointer 'b' to list-A  &  move both 'a' & 'b' simultaneously till 'a' reaches
    // null. When 'a' reaches null, pointer 'b' (on list-A) will cover distance equal to
    // difference of length of linked-list.
    // In next iteration we place 'a' on list-B's head.
    // Due to this, the distance of both pointer (on respective linked-list) till the end
    // becomes equal (in short both the pointer are at equal distance from their end
    // of linked-list)
    // Then we move both the pointers together until intersection found
    // https://youtu.be/u4FWXfgS8jw?t=732

    // Complexities:
    // TC -> O(2 * max(m,n))  =  O(max(m, n))
    // First O(max(m,n)) to find the difference in length, and another max(m,n) to find intersection
    // SC -> O(1)

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptrA = headA, ptrB = headB;

        while (ptrA != ptrB){
            ptrA = ptrA != null ? ptrA.next : headB;
            ptrB = ptrB != null ? ptrB.next : headA;
        }

        return ptrA;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
