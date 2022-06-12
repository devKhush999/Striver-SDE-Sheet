package LinkedList.RemoveNthNodeFromEndOfList;

// https://youtu.be/Lhu3MsXZy-Q
// https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/

public class RemoveNthNodeFromEndOfList_VeryEfficient {

    // ************************ Very Intuitive & Effective Solution ************************
    // TC -> O(n) only one iteration
    // SC -> O(1) one dummy node

    // Intuition: Suppose length of linked-list is 'len'
    // If Distance between end of linked-list & the node to be deleted is 'n'
    // If we move 'fast' pointer by 'n' nodes, then distance remaining till end is 'len - n' nodes
    //              |_______||_____________|
    //              |---n---||--(len - n)--|

    // After that, if we move both slow & fast pointer equally (both by one node) till fast.next != null
    // Then, 'fast' will travel distance of 'len - n' nodes, and the same distance of 'len - n'
    // will be travelled by 'slow' pointer.
    // We claim that 'slow' pointer is at the distance of 'n' from the end of linked list, how???
    // Distance of 'slow' pointer from start of linked-list = 'len - n'
    // Distance of 'slow' pointer from end of linked-list = 'len - (len - n)' = 'n'
    // So, slow's next (slow.next) pointer is pointing at the node to be deleted.
    // So, we delete that


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;

        // Initialize a dummy Node
        ListNode start = new ListNode(-1);

        // assign dummy's next node to head
        start.next = head;
        ListNode fast = start, slow = start;

        // Move fast pointer by n nodes, as per intuition
        for (int i = 1; i <= n; i++)
            fast = fast.next;

        // This is a simple base case that must be handled, when "n == length_of_linked_list"
        // i.e, removing the first node of linked-list (since we moved fast by 'n' nodes)
        // Check if fast.next == null?
        // If yes, then first node is to be deleted as per formula mentioned in Intuition
        if (fast.next == null)
            return head.next;

        // Move both fast & slow pointer so, that both cover equal nodes distance
        // And 'slow' pointer covers 'len - n' nodes of distance from start (aka 'n' nodes from end)
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        // Delete the slow's next node. This node is the nth node from end of linked-list
        ListNode nodeToDelete = slow.next;
        slow.next = slow.next.next;
        nodeToDelete.next = null;

        // return same head
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
