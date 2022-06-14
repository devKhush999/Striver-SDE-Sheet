package LinkedList.FlatteningOfLinkedList;

// https://youtu.be/ysytSSXpAI0
// https://takeuforward.org/data-structure/flattening-a-linked-list/
// https://www.geeksforgeeks.org/flattening-a-linked-list/

public class FlatteningOfLinkedList_Sorting {

    // ************************************* Brute Force ********************************************
    // Idea is to arrange all the list nodes into a single linked list, where the bottom of current list
    // will point to next node (instead of .next pointer), because answer is need in bottom pointer only
    // same thing can be done for .next pointer too
    // Then we sort the list using Merge Sort Algorithm
    // N -> Total no. of nodes in linked-list (linked via next pointer)
    // M -> Total no. of bottom nodes in linked-list of all nodes (i.e, all nodes connected to each other
    // via next pointer have M bottom nodes in total)

    // To arrange the linked-list into a normal singly linked-list via bottom pointer, this takes O(m+n)
    // To sort the arrange list (of m+n nodes) into a sorted list. O((m+n) * log(m+n))
    // TC -> O(m + n)  + O((m+n) * log(m+n))
    // SC -> O(1)        Ignoring recursion stack space
    public Node flatten(Node head){
        Node ptr = head;

        while (ptr != null){
            Node bottomPtr = ptr;

            // traverse till we reach the last bottom node of current node
            while (bottomPtr.bottom != null)
                bottomPtr = bottomPtr.bottom;

            // assign 'bottom' pointer of last bottom node to next node of 'ptr
            bottomPtr.bottom = ptr.next;

            // Step 1 & 2 are optional, they are done to make next pointer of every node in the list
            // to point to null, this is not required though
             Node prev = ptr;       // Step 1
             ptr = ptr.next;
             prev.next = null;      // Step 2
        }
        return mergeSort(head);
    }


    // *************************** Code of Merge Sorting Two lists ***************************
    private Node mergeSort(Node head){
        if (head == null || head.bottom == null)
            return head;

        Node fast = head, slow = head;

        while (fast.bottom != null  &&  fast.bottom.bottom != null){
            fast = fast.bottom.bottom;
            slow = slow.bottom;
        }
        Node midHalf = slow.bottom;
        slow.bottom = null;

        Node sortedHalf1 = mergeSort(head);
        Node sortedHalf2 = mergeSort(midHalf);
        return merge(sortedHalf1, sortedHalf2);
    }

    // ********************************** Merging two sorted lists ***********************************8
    private Node merge(Node head1, Node head2){
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        Node l1 = head1, l2 = head2;
        if (l1.data > l2.data){
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }
        Node head = l1;

        while (l1 != null && l2 != null){
            Node prevSmaller = null;

            while (l1 != null && l1.data <= l2.data){
                prevSmaller = l1;
                l1 = l1.bottom;
            }

            prevSmaller.bottom = l2;

            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return head;
    }


    static class Node {
        int data;
        Node next;
        Node bottom;
        Node(int d) {
            data = d;
            next = null;
            bottom = null;
        }
    }
}
