package LinkedList.RemoveNthNodeFromEndOfList;

// https://youtu.be/Lhu3MsXZy-Q
// https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/

public class RemoveNthNodeFromEndOfList {

    // ****************************** Method 1: Counting Nodes *********************************
    // We count count the no. of nodes & find the position to delete the node & then delete it
    // TC -> O(2*n) = O(n)      In worst case
    // SC -> O(1)   No extra space

    // n is given by 1-based indexing
    public ListNode removeNthFromEnd_Counting(ListNode head, int n) {
        if (head == null)
            return null;

        ListNode ptr = head;

        // Counting the number of Nodes in Linked-list
        int nodesCount = 0;
        while (ptr != null){
            ptr = ptr.next;
            nodesCount++;
        }

        // Finding the position of deletion from the front of linked-list (0-based indexing)
        int nodeNumberToDelete = nodesCount - n;

        // Check for Edge case
        if (nodeNumberToDelete == 0)
            return head.next;

        // Reaching one node before the node to be deleted
        // -1 because we need to reach one node before the Node to delete from LL
        ptr = head;
        int currCount = 1;
        while (currCount < nodeNumberToDelete){
            ptr = ptr.next;
            currCount++;
        }

        // Deleting the node
        ListNode nodeToDelete = ptr.next;
        ptr.next = nodeToDelete.next;
        nodeToDelete.next = null;

        return head;
    }



    // ****************************** Method 2: By reversing the list **********************************
    // We first reverse the list, then we delete the nth position from the
    // front of reversed list and again return the reversed list
    // We take one traversal for reversal & one traversal for deletion & again one traversal for reversal
    // TC -> O(3*n) = O(n)      In worst case
    // SC -> O(1)

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

    // n is given by 1-based indexing
    public ListNode removeNthFromEnd_ByReversing(ListNode head, int n) {
        if (head == null)
            return null;

        // reversing the linked-list
        ListNode reversedHead = reverse(head);
        ListNode ptr = reversedHead;

        // Check for Edge Case (1-based indexing)
        // If we were to delete just the last node, we will return the
        // reverse_Of(next pointer of reversed list)
        if (n == 1)
            return reverse(reversedHead.next);

        // Reaching one node before the node to be deleted
        // -1 because we need to reach one node before the Node to delete from LL
        int count = 1;
        while (count < n - 1){
            ptr = ptr.next;
            count++;
        }

        // Deleting the node
        ListNode nodeToDelete = ptr.next;
        ptr.next = nodeToDelete.next;
        nodeToDelete.next = null;

        // again return the reversed list, as we have earlier reversed the list
        return reverse(reversedHead);
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}