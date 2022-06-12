package LinkedList.MergeTwoSortedLists;

// https://youtu.be/Xb4slcp1U38
// https://takeuforward.org/data-structure/merge-two-sorted-linked-lists/
// https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/

public class MergeTwoSortedLists {

    // ******************************** Using a Dummy node 1st method ****************************************
    // TC -> O(min(m, n))
    // SC -> O(1)
    // Using a dummy Node of value of -1

    public ListNode mergeTwoLists_UsingDummyNode1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = list1, ptr2 = list2, ptr = dummy;

        while (ptr1 != null  &&  ptr2 != null){

            if (ptr1.val < ptr2.val){
                ptr.next = ptr1;
                ptr1 = ptr1.next;
            }
            else{
                ptr.next = ptr2;
                ptr2 = ptr2.next;
            }
            ptr = ptr.next;
            ptr.next = null;
        }

        if (ptr1 != null)
            ptr.next = ptr1;

        if (ptr2 != null)
            ptr.next = ptr2;

        return dummy.next;
    }


    // ******************************** Using a Dummy node 2nd method ****************************************
    // TC -> O(m + n)
    // SC -> O(1)
    // Using a dummy Node of value of -1

    public ListNode mergeTwoLists_UsingDummyNode2(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = list1, ptr2 = list2, ptr = dummy;

        while (ptr1 != null  &&  ptr2 != null){

            if (ptr1.val < ptr2.val){
                ptr.next = ptr1;
                ptr1 = ptr1.next;
                ptr = ptr.next;
                ptr.next = null;
            }
            else{
                ptr.next = ptr2;
                ptr2 = ptr2.next;
                ptr = ptr.next;
                ptr.next = null;
            }
        }

        // We travers the remaining list1 & list2 too to get TC of O(m+n)
        while (ptr1 != null){
            ptr.next = ptr1;
            ptr1 = ptr1.next;
            ptr = ptr.next;
            ptr.next = null;
        }
        while (ptr2 != null){
            ptr.next = ptr2;
            ptr2 = ptr2.next;
            ptr = ptr.next;
            ptr.next = null;
        }

        return dummy.next;
    }



    // ********************************* Much Efficient 1 ******************************************
    // Without using any extra space, No Dummy Node
    // TC -> O(min(m,n))
    // SC -> O(1)
    // https://takeuforward.org/data-structure/merge-two-sorted-linked-lists/

    public ListNode mergeTwoLists_Efficient1(ListNode head1, ListNode head2) {
       if (head1 == null)
           return head2;
       if (head2 == null)
           return head1;

       ListNode ptr1 = head1, ptr2 = head2;
       ListNode head;

        // Assigning head to the smaller Node out of ptr1 & ptr2
        // By doing this 'head' node acts as dummy node in previous case
        if (ptr1.val <= ptr2.val){
            head = ptr1;
            ptr1 = ptr1.next;
        }
       else{
           head = ptr2;
           ptr2 = ptr2.next;
       }
        head.next = null;

       // Using the same approach as per previous code
       ListNode headPtr = head;

       while (ptr1 != null && ptr2 != null){
           if (ptr1.val <= ptr2.val){
               headPtr.next = ptr1;
               ptr1 = ptr1.next;
           }
           else{
               headPtr.next = ptr2;
               ptr2 = ptr2.next;
           }
           headPtr = headPtr.next;
           headPtr.next = null;
       }

       if (ptr1 != null)
           headPtr.next = ptr1;
       if (ptr2 != null)
           headPtr.next = ptr2;

       return head;
    }


    // ********************************* Much Efficient 2 ******************************************
    // Without using any extra space, No Dummy Node
    // TC -> O(min(m,n))
    // SC -> O(1)
    // https://takeuforward.org/data-structure/merge-two-sorted-linked-lists/

    public ListNode mergeTwoLists_Efficient2(ListNode head1, ListNode head2) {
        // Simple Bases cases
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        ListNode list1 = head1, list2 = head2;

        // We make 'list1' pointer to always point towards the smaller node
        if (list1.val > list2.val){
            ListNode temp = list2;
            list2 = list1;
            list1 = temp;
        }

        // Head of sorted merged list
        ListNode head = list1;

        while (list1 != null  && list2 != null){
            // Keep track of previous smaller node
            ListNode prevSmallerNode = null;

            // We keep moving 'list1' pointer to next until we found a node in list2 with greater value
            // than list1 node
            while (list1 != null  &&  list1.val <= list2.val){
                prevSmallerNode = list1;
                list1 = list1.next;
            }

            // once we have found a node in list2 with greater value than list1
            // then make the previous of previous smaller node to list2 & exchange the pointers
            // of list1 & list2
            prevSmallerNode.next = list2;

            ListNode temp = list2;
            list2 = list1;
            list1 = temp;
        }

        return head;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
