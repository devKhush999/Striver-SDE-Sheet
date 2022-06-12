package LinkedList.MiddleOfLinkedList;

// https://youtu.be/sGdwSH8RK-o
// https://takeuforward.org/data-structure/find-middle-element-in-a-linked-list/

public class MiddleOfLinkedList {

    // ****************************** Tortoise & Hare Approach *************************************
    // TC -> O(n)
    // SC -> O(1)

    // This will return the first middle node of Linked-list in case of even nodes
    public ListNode middleNode_FastSlow_SecondMiddle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // This will return the second middle node of Linked-list in case of even nodes
    public ListNode middleNode_FastSlow_FirstMiddle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }



    // *************************** By Counting every node ***************************************
    // TC -> O(3*n/2) = O(n)
    // SC -> O(1)
    public ListNode middleNode_ByCounting(ListNode head){
        int countOfNodes = 0;
        ListNode ptr = head;

        while (ptr != null){
            countOfNodes++;
            ptr = ptr.next;
        }

        ptr = head;
        int count = 0;

        while (count < countOfNodes /2){
            ptr = ptr.next;
            count++;
        }
        return ptr;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
