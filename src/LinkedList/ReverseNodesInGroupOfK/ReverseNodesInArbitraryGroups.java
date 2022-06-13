package LinkedList.ReverseNodesInGroupOfK;

// MUST WATCH VIDEO:
// https://youtu.be/Of0HPkk3JgI
// https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/

// Similar Modification of above problem
// Problem link:
// https://www.codingninjas.com/codestudio/problems/763406?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website

public class ReverseNodesInArbitraryGroups {

    // ************************* Efficient Approach *************************************
    // Understand the process, Watch Video
    // We first find length of the linked-list. this takes O(n) time
    // We traverse only by length, and reduce the length of list to 'length -k', O(K) for this
    // In each group of size n/k, we are reversing all nodes in that group O(n/k) for this
    // TC -> O(n) + O(k) * O(n/k) = O(n) + O(k * n/k) = O(n) + O(n) = O(2n)
    // SC -> O(1)

    // See DRY RUN : https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/

    private static int getListLength(Node head){
       int length = 0;
       while (head != null){
           length++;
           head = head.next;
       }
       return length;
    }

    public static Node getListAfterReverseOperation(Node head, int k, int[] size) {
        if (head == null)
            return null;
        int length = getListLength(head);

        Node dummy = new Node(-1);
        dummy.next = head;

        Node prev = dummy, curr, next;

        int i = 0;
        while (length > 0 && i < size.length){
            curr = prev.next;
            next = curr.next;

            int currReversedSize = Math.min(size[i++], length);
            // Nothing to be reversed case
            if (currReversedSize == 0)
                continue;

            for (int j = 1; j < currReversedSize; j++){
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }
            prev = curr;
            length -= currReversedSize;
        }
        return dummy.next;
    }

    static class Node {
        int data;
        Node next = null;
        Node(int data) { this.data = data;}
    }
}
