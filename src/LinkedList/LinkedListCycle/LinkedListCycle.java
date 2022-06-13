package LinkedList.LinkedListCycle;
import java.util.HashSet;

// https://youtu.be/354J83hX7RI
// https://takeuforward.org/data-structure/detect-a-cycle-in-a-linked-list/

public class LinkedListCycle {

    // ******************************* Brute force: Modifying List *******************************
    // TC -> O(number of nodes)
    // SC -> O(1)

    public boolean hasCycle_BruteForce(ListNode head) {
        ListNode ptr = head;

        while (ptr != null){
            if (ptr.val == Integer.MIN_VALUE)
                return true;
            else ptr.val = Integer.MIN_VALUE;

            ptr = ptr.next;
        }
        return false;
    }

    // ******************************* Brute force: Hashing ***********************************
    // TC -> O(number of nodes)
    // SC -> O(number of nodes)

    public boolean hasCycle_Hashing(ListNode head) {
        HashSet<ListNode> hashTable = new HashSet<>();
        ListNode ptr = head;

        while (ptr != null){
            if (hashTable.contains(ptr))
                return true;

            hashTable.add(ptr);
            ptr = ptr.next;
        }
        return false;
    }

    // ********************************* Efficient Solution ************************************
    // Tortoise & Hare Algorithm
    // TC -> O(n)
    // SC -> O(1)
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }
        return false;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
