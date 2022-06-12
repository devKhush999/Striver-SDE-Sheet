package LinkedList.DeleteNodeInLinkedList;

// https://youtu.be/icnp4FJdZ_c
// https://takeuforward.org/data-structure/delete-given-node-in-a-linked-list-o1-approach/
// https://leetcode.com/problems/remove-linked-list-elements/

public class DeleteNodeInLinkedList {

    // *************************** Easiest Question on Linked-list ****************************
    // TC -> O(1)
    // SC -> O(1)
    // This does not delete that node, but indirectly it removes that node from the linked list.

    public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;

        node.val = node.next.val;
        node.next = node.next.next;

        nextNode.next = null;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
