package LinkedList.LinkedListCycle;
import java.util.HashSet;

// https://youtu.be/QfbOhn0WZ88
// https://takeuforward.org/data-structure/starting-point-of-loop-in-a-linked-list/

public class LinkedListCycle_II {

    // ******************************* Brute Force: Hashing Solution ***********************************
    // TC -> O(n)
    // SC -. O(n)   for HashSet
    private ListNode detectCycle_Hashing(ListNode head){
        HashSet<ListNode> set = new HashSet<>();

        while (head != null){
            if (set.contains(head))
                return head;

            set.add(head);
            head = head.next;
        }
        return null;
    }


    // ****************************** Efficient Solution: Two pointers ********************************
    // TC -> O(n)
    // SC -> O(1)

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null  &&  fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                break;
        }
        if (fast == null || fast.next == null)
            return null;

        fast = head;

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
