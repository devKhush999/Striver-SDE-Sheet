package LinkedList.RotateALinkedList;


public class RotateALinkedList_BestSolution {

    /*
     ****************************** Approach 1 *****************************************
     * Main idea is to find the length of linked-list & iterate till "length - k"
     * After this we separate two parts head & node after which rotation is to be done
     *
     * TC -> O(2n) = O(n)     Two traversals (one for length & one for rotation)
     * SC -> O(1)
     */
    private int getLength(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode rotateRight_Approach1(ListNode head, int k) {
        if (head == null)
            return null;

        int length = getLength(head);

        // Since k >= length, so we rotate the list by "k % length". After this k becomes less than 'length'
        k = k % length;

        // if k==0 OR k==length, after division modulo by length k reduces to k==0, which means list doesn't need to rotated
        if (k == 0)
            return head;

        // Calculate distance to travel
        int distanceToTravel = length - k;
        ListNode ptr = head;

        for (int i = 1; i < distanceToTravel; i++)
            ptr = ptr.next;

        // ptr's next will be pointing to the new Head after rotation by k. Separate both parts
        ListNode newHead = ptr.next;
        ptr.next = null;

        // travel to last node in the list & assign the last's node next pointer to head of list
        ListNode lastNode = newHead;
        while (lastNode.next != null)
            lastNode = lastNode.next;

        lastNode.next = head;
        return newHead;
    }



    /*
     ****************************** Approach 2 *****************************************
     * Main idea is to find the length of linked-list & iterate till "length - k"
     * After this we separate two parts head & node after which rotation is to be done
     *
     * TC -> O(2n - k) = O(n)      Two traversals (one for length & other for rotation till "n-k")
     * SC -> O(1)
     */

    public ListNode rotateRight_Approach2(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode lastNode = head;
        int length = 1;

        while (lastNode.next != null){
            lastNode = lastNode.next;
            length++;
        }

        // Since k >= length, so we rotate the list by "k % length". After this k becomes less than 'length'
        // if k==length, that means rotated list is same as original list
        // if k==0 OR k==length, after division modulo by length k reduces to k==0, which means list doesn't need to rotated
        k = k % length;

        if (k == 0) return head;

        int distanceToTravel = length - k;
        ListNode ptr = head;

        for (int travelled = 1; travelled < distanceToTravel; travelled++)
            ptr = ptr.next;

        lastNode.next = head;
        // new head of linked-list becomes ptr.next
        head = ptr.next;
        ptr.next = null;

        return head;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
