package LinkedList.LFUCache.LFUCache_BruteForce;
import java.util.HashMap;

/** ********************************** INTUITION *****************************************
 * Same as LRU Cache
 * Just the difference is that:
        * After the head node of doubly linked-list, there will be a node with the largest access frequency.
        * Before the tail node of doubly linked-list, there will be a node with the least access frequency.
        * Nodes in the doubly linked-list are arranges in the order of decreasing access frequency.
        * In case of same access frequency in doubly linked-list, nodes will be arranged in the order
          of "Least Recently Used" Node

 * To add Node just after the doubly linked-list, we have to traverse through the entire list, to find
   the suitable position for the Node, satisfying above "access frequency criteria"

 * Time Complexity:
        * put() : O(n)      we need to update the node's position according to above "access frequency criteria"
        * get() : O(n)      we need to update the node's position according to above "access frequency criteria"
 */

class LFUCache {
    private final Node head;
    private final Node tail;
    private final HashMap<Integer, Node> cacheMap;
    private final int capacity;
    private int size;

    public LFUCache(int capacity) {
        this.head = new Node(0, 0, 0);
        this.tail = new Node(0, 0, 0);
        this.cacheMap = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key) || capacity == 0)
            return -1;

        Node node = cacheMap.get(key);
        node.frequencyCount++;
        removeNode(node);
        addNode(node);
        return node.data;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        Node node = cacheMap.get(key);

        if (node != null){
            node.frequencyCount++;
            node.data = value;

            removeNode(node);
            addNode(node);
        }
        else{
            if (size == capacity){
                Node nodeToRemove = tail.prev;
                removeNode(nodeToRemove);
                cacheMap.remove(nodeToRemove.key);

                Node nodeToAdd = new Node(key, value, 1);
                addNode(nodeToAdd);
                cacheMap.put(key, nodeToAdd);
            }
            else if (size < capacity){
                Node nodeToAdd = new Node(key, value, 1);

                addNode(nodeToAdd);
                cacheMap.put(key, nodeToAdd);
                size++;
            }
        }
    }

    private void addNode(Node nodeToAdd){
        Node ptr = head.next;

        while (ptr.frequencyCount > nodeToAdd.frequencyCount)
            ptr = ptr.next;

        Node addAfterNode = ptr.prev;

        addAfterNode.next = nodeToAdd;
        nodeToAdd.next = ptr;
        ptr.prev = nodeToAdd;
        nodeToAdd.prev = addAfterNode;
    }


    private void removeNode(Node node){
        Node beforeNode = node.prev;
        Node afterNode = node.next;

        beforeNode.next = afterNode;
        afterNode.prev = beforeNode;

        node.next = null;
        node.prev = null;
    }

    static class Node{
        int data, key, frequencyCount;
        Node next, prev;
        public Node(int key, int value, int count){
            this.key = key;
            this.data = value;
            this.frequencyCount = count;
        }
    }
}