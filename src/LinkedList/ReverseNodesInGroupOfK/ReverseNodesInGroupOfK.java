package LinkedList.ReverseNodesInGroupOfK;
import java.util.ArrayList;

// MUST WATCH VIDEO:
// https://youtu.be/Of0HPkk3JgI
// https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/

public class ReverseNodesInGroupOfK {

    // ********************************** Brute Force **********************************
    // Simple Approach is to traverse the linked-list, and simultaneously divide it
    // in groups of K, then reverse the every divided kth group & store it into a ArrayList
    // Anf later on, traverse the ArrayList to link all the reversed k group & return it

    // Time complexity: We are travelling all the nodes using a pointer, O(n)
    // Also, we are reversing all the kth divided groups, each such group have n/k nodes,
    // O(n/k) for reversing every group
    // Finally, we're linking all the 'k' reversed nodes this takes another O(n)
    // TC -> O(n) + O(n/k) + O(n)
    // SC -> O(k)  for storing all the k groups

    private ListNode reverse(ListNode head){
        ListNode curr = head, prev = null, next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseKGroup_SimpleApproach(ListNode head, int k) {
        if (head == null)
            return null;

        ArrayList<ListNode> kthDividedNodes = new ArrayList<>();
        ListNode ptr = head, prevNode = null;

        while (ptr != null){
            ListNode kthDividedHead = ptr;
            int count = 0;

            while (count < k  &&  ptr != null){
                prevNode = ptr;
                ptr = ptr.next;
                count++;
            }
            prevNode.next = null;

            if (count == k)
                kthDividedNodes.add(reverse(kthDividedHead));
            else
                kthDividedNodes.add(kthDividedHead);
        }

        for (int i = 0; i < kthDividedNodes.size() - 1; i++){
            ptr = kthDividedNodes.get(i);

            while (ptr.next != null)
                ptr = ptr.next;

            ptr.next = kthDividedNodes.get(i + 1);
        }

        ListNode newHead = kthDividedNodes.get(0);
        return newHead;
    }



    // ************************* Efficient Approach *************************************
    // Understand the process, Watch Video
    // We first find length of the linked-list. this takes O(n) time
    // We traverse only by length, and reduce the length of list to 'length -k', O(K) for this
    // In each group of size n/k, we are reversing all nodes in that group O(n/k) for this
    // TC -> O(n) + O(k) * O(n/k) = O(n) + O(k * n/k) = O(n) + O(n) = O(2n)
    // SC -> O(1)

    // See DRY RUN : https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/

    private int getLengthOfList(ListNode head){
        int length = 0;
        while (head != null){
            head = head.next;
            length++;
        }
        return length;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        /* 'curr' always points at the 1st node of each unreversed group of size 'k'.
        * At the beginning, 'next' always points at the 2nd node of each unreversed group
        * of size 'k'. And we move it to next node after reversing it with previous node.
        * 'prev' always points to the last node of previous reversed group of sixe 'k' (in case of
        *  first 'kth' group it points to dummy node) */

        ListNode prev = dummy, curr, next;

        int length = getLengthOfList(head);

        while (length >= k){
            curr = prev.next;
            next = curr.next;

            for (int i = 1; i < k; i++){
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }
            prev = curr;

            length -= k;
        }

        // Dummy.next contains the new head of reversed list
        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
