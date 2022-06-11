package LinkedList.ReverseLinkedList;

// https://youtu.be/iRtLEoL-r-g
// https://takeuforward.org/data-structure/reverse-a-linked-list/
// https://www.geeksforgeeks.org/reverse-a-linked-list/

import java.util.Stack;

public class ReverseLinkedList {

    // ******************************* Iterative Solution ****************************************
    // TC -> O(n)
    // SC -> O(1)
    public ListNode reverseList_Iterative(ListNode head) {
        ListNode curr = head, prev = null, next;

        while (curr != null){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }


    // ******************************* Recursive Solution ****************************************
    // TC -> O(n)
    // SC -> O(n)

    public ListNode reverseList_Recursive(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode nextToHead = head.next;
        head.next = null;

        ListNode newHead = reverseList_Recursive(nextToHead);

        nextToHead.next = head;
        return newHead;
    }


    // ******************************* Reversing using Stack **********************************
    // TC -> O(n)
    // SC -> O(n)

    public ListNode reverseList_Stack(ListNode head) {
        if (head == null)
            return head;

        ListNode ptr = head;

        // Add all Nodes into the stack except last Node
        Stack<ListNode> stack = new Stack<>();
        while (ptr.next != null){
            stack.push(ptr);
            ptr = ptr.next;
        }

        // Assign new head as last node as it will ne the first node in reversed list
        ListNode newHead = ptr;

        // Pop nodes onw by one & assign it to the next nodes
        while (!stack.isEmpty()){
            ptr.next = stack.pop();
            ptr = ptr.next;
        }
        // Last Node in stack, which was the head of original is still pointing to the second nodes
        // So, make it pointing to 'null'
        ptr.next = null;

        return newHead;
    }

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
