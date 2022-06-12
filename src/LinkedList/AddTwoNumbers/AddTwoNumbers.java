package LinkedList.AddTwoNumbers;

// https://youtu.be/LBVsXSMOIk4
// https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/


public class AddTwoNumbers {

    // **************************** Brute Force *************************************
    // Simple Logic of adding the values in linked-list while traversing
    // Complexity Analysis:
    // Time complexity : O(max(m,n)). Assume that m and n represents the length of l1 and l2 respectively,
    // the algorithm above iterates at most max(m,n) times.
    // Space complexity : O(max(m,n)). The length of the new list is at most max(m,n)+1.

    public ListNode addTwoNumbers_V1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = l1, ptr2 = l2, ptr = dummy;

        int carry = 0;

        while (ptr1 != null && ptr2 != null){
            int sum = ptr1.val + ptr2.val + carry;
            carry = sum/10;

            ptr.next = new ListNode(sum % 10);

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }

        while (ptr1 != null){
            int sum = ptr1.val + carry;
            carry = sum/10;

            ptr.next = new ListNode(sum % 10);

            ptr = ptr.next;
            ptr1 = ptr1.next;
        }

        while (ptr2 != null){
            int sum = ptr2.val + carry;
            carry = sum/10;

            ptr.next = new ListNode(sum % 10);

            ptr = ptr.next;
            ptr2 = ptr2.next;
        }

        if (carry == 1)
            ptr.next = new ListNode(carry);

        return dummy.next;
    }



    // **************************** Short hand Code *************************************
    // Another Shorter Code that does same thing
    // Simple Logic of adding the values in linked-list while traversing
    // TC -> O(max(m,n))
    // SC -> O(max(m,n) + 1) = O(max(m,n))      +1 due to Node for storing carry

    public ListNode addTwoNumbers_V2(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = head1, ptr2 = head2, ptr = dummy;
        int carry = 0;

        while (ptr1 != null  ||  ptr2 != null){
            int sum = 0;

            if (ptr1 != null){
                sum += ptr1.val;
                ptr1 = ptr1.next;
            }
            if (ptr2 != null){
                sum += ptr2.val;
                ptr2 = ptr2.next;
            }
            sum += carry;

            // We can even do this too..
            // int ptr1Val = ptr1 != null ? ptr1.val : 0;
            // int ptr2Val = ptr2 != null ? ptr2.val : 0;
            // sum += (ptr1Val + ptr2Val + carry);

            carry = sum / 10;

            ptr.next = new ListNode(sum % 10);
            ptr = ptr.next;

            // We can even do this too...
            // ptr1 = ptr1 == null ? null : ptr1.next;
            // ptr2 = ptr2 == null ? null : ptr2.next;

        }
        if (carry == 1)
            ptr.next = new ListNode(carry);

        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
