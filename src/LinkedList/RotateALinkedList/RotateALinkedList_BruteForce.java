package LinkedList.RotateALinkedList;

// https://youtu.be/9VPm6nEbVPA
// https://takeuforward.org/data-structure/rotate-a-linked-list/

public class RotateALinkedList_BruteForce {

    /*
    ************************************* Brute Force *******************************************
    * Idea is to first update k to "k % Length" as because k==length means no rotation.
    * Then for every rotation (for every value of k, updated one) traverse linked-list entirely
    * and rotate last node. Repeat this step k time (i.e, rotate last node k times)

    * TC -> O(n) + O(n * (k % length)) =  O(n * (k % length))
    * SC -> O(1)
     */

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;

        int length = 0;
        ListNode p = head;
       while (p != null){
            p = p.next;
        }

        for (int i = 1; i <= k; i++){
            ListNode ptr = head;

            while (ptr.next.next != null)
                ptr = ptr.next;

            ptr.next.next = head;
            head = ptr.next;
            ptr.next = null;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}