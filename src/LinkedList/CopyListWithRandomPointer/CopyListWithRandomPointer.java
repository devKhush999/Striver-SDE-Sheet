package LinkedList.CopyListWithRandomPointer;
import java.util.HashMap;

// https://youtu.be/VNf6VynfpdM
// https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/

public class CopyListWithRandomPointer {

    /*
     ************************************* Approach 1: Hashing ******************************************
     * First copy the entire linked-list into a new linked-list one and also keep a Mapping (HashMap)
       of Nodes in original list (as key) to the Nodes in new list (as value)
     * Then traverse again in both the list simultaneously & do these steps:
        * If the random field of current pointer in original list (originalPtr.random) is "null",
          assign the random field of current pointer (copiedPtr.random) in copied list also to "null".
        * Else if the random field of current pointer in original list (originalPtr.random) is not "null",
          do this:
          Using the random field of current Node in original list (originalPtr.random), retrieve the Node
          present in the map (using currentPtr.random). And assign the random field of current pointer
          (copiedPtr.random) in copied list to the retrieved node from Map

      * Return the copied list

      * TC -> O(2n) = O(n)      Two iterations are being done of both original list & copied list
      * SC -> O(n)              for Hashmap, storing all original_Nodes -> copied_Nodes
     */

    public Node copyRandomList_Hashing1(Node head) {
        HashMap<Node, Node> originalToNewListMap = new HashMap<>();

        Node dummy = new Node(-1);
        Node originalPtr = head, copyPtr = dummy;

        while (originalPtr != null){
            copyPtr.next = new Node(originalPtr.val);
            copyPtr = copyPtr.next;

            originalToNewListMap.put(originalPtr, copyPtr);
            originalPtr = originalPtr.next;
        }

        originalPtr = head;
        copyPtr = dummy.next;

        while (copyPtr != null){
            if (originalPtr.random == null)
                copyPtr.random = null;
            else
                copyPtr.random = originalToNewListMap.get(originalPtr.random);

            originalPtr = originalPtr.next;
            copyPtr = copyPtr.next;
        }
        return dummy.next;
    }



     /*
     ************************************* Approach 2: Hashing ******************************************
     * Iterate through the entire list.
     * For each node, create a deep copy of each node and hash it with the original node. Store it in the hashmap.
     * Now, again iterate through the given list.
       For each node, link the next & random pointer of deep copied node present as the hash value
       of the original node as per original node.
     * The head of the deep copy list will be the head of hashed value of original node. Return it

      * TC -> O(2n) = O(n)      Two iterations are being done of both original list & copied list
      * SC -> O(n)              for Hashmap, storing all original_Nodes -> copied_Nodes
     */

    public Node copyRandomList_Hashing2(Node head) {
        HashMap<Node, Node> originalToNewNodeMap = new HashMap<>();
        Node ptr = head;

        // first iteration for inserting deep nodes of every node in the hashmap.
        while (ptr != null){
            originalToNewNodeMap.put(ptr, new Node(ptr.val));
            ptr = ptr.next;
        }

        //second iteration for linking next and random pointer of each copied node
        ptr = head;
        while (ptr != null){
            Node copyNode = originalToNewNodeMap.get(ptr);

            copyNode.next = ptr.next != null ? originalToNewNodeMap.get(ptr.next) : null;
            copyNode.random = ptr.random != null ? originalToNewNodeMap.get(ptr.random) : null;
            ptr = ptr.next;
        }

        //return the head of copied list
        Node copiedListHead = originalToNewNodeMap.get(head);
        return copiedListHead;
    }



    /*
    ***************************** Approach 3:  Efficient Solution ************************************
    * TC -> O(n) + O(n) + O(n) = O(3n) = O(n)
    * SC -> O(1)
    * https://youtu.be/VNf6VynfpdM      Video Link
    * https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/
     */

    public Node copyRandomList_Efficient(Node head) {
        if (head == null)
            return null;
        Node originalPtr = head, copyPtr;

        //1. create nodes in one link, eg.original: A-B-C, add copy A-A'-B-B'-C-C'
        while (originalPtr != null){
            Node next = originalPtr.next;
            originalPtr.next = new Node(originalPtr.val);
            originalPtr.next.next = next;
            originalPtr = originalPtr.next.next;
        }

        //2. add random for each node
        originalPtr = head;
        while (originalPtr != null){
            originalPtr.next.random = originalPtr.random != null ? originalPtr.random.next : null;
            originalPtr = originalPtr.next.next;
        }


        originalPtr = head;
        Node copiedList = originalPtr.next;

        //3.unlink & arrange all original nodes & copied nodes and relink as required again
        while (originalPtr != null){
            copyPtr = originalPtr.next;

            originalPtr.next = originalPtr.next.next;
            originalPtr = originalPtr.next;

            copyPtr.next = originalPtr == null ? null : originalPtr.next;
        }

        return copiedList;
    }


    static class Node {
        int val;
        Node next, random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
