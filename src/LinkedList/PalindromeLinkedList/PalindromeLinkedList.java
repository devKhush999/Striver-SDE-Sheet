package LinkedList.PalindromeLinkedList;

// https://youtu.be/-DtNInqFUXs
// https://takeuforward.org/data-structure/check-if-given-linked-list-is-plaindrome/

public class PalindromeLinkedList {

    // **************************** Reversing half of list ********************************
    // Time complexity:
    // O(n) fo fats & slow traversal & reversal
    // O(n/2) fo checking palindrome
    // TC -> O(n) + O(n/2)
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

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null  && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // make the half of the linked-list reversed
        slow.next = reverse(slow.next);

        ListNode reversedPtr = slow.next;
        ListNode ptr = head;

        while (reversedPtr != null){
            if (reversedPtr.val != ptr.val)
                return false;
            reversedPtr = reversedPtr.next;
            ptr = ptr.next;
        }

        // To again modify the list into the original one
        slow.next = reverse(slow.next);
        return true;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
