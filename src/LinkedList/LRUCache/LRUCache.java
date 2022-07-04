package LinkedList.LRUCache;
import java.util.HashMap;

// https://youtu.be/xDEuM5qa0zg
// https://youtu.be/Xc4sICC8m4M
// https://takeuforward.org/data-structure/implement-lru-cache/
// https://medium.com/swlh/how-to-implement-lru-cache-using-doubly-linked-list-and-a-hashmap-5ff0ff218f77

class LRUCache {
    private final Node head;
    private final Node tail;
    private final HashMap<Integer, Node> map;
    private int size;
    private final int capacity;

    public LRUCache(int capacity) {
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.map = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        remove(node);
        addInFront(node);
        return node.data;
    }

    public void put(int key, int value) {
        Node node = map.get(key);

        if (node != null){
            node.data = value;
            remove(node);
            addInFront(node);
        }
        else{
            if (size == capacity){
                Node toRemove = tail.prev;
                remove(toRemove);
                map.remove(toRemove.key);

                Node nodeToAdd = new Node(key, value);
                addInFront(nodeToAdd);
                map.put(key, nodeToAdd);
            }
            else if (size < capacity){
                Node nodeToAdd = new Node(key, value);
                addInFront(nodeToAdd);
                map.put(key, nodeToAdd);
                size++;
            }
        }
    }

    private void remove(Node node){
        Node beforeNode = node.prev;
        Node afterNode = node.next;

        beforeNode.next = afterNode;
        afterNode.prev = beforeNode;
        node.next = null;
        node.prev = null;
    }

    private void addInFront(Node node){
        Node nextToHead = head.next;
        head.next = node;
        node.next = nextToHead;

        nextToHead.prev = node;
        node.prev = head;
    }

    static class Node{
        int key, data;
        Node prev, next;
        public Node(int key, int data){
            this.key = key;
            this.data = data;
        }
    }
}

/****************************************** Approach ************************************************
 *                            Using HahMap with Doubly-LinkedList

 * We will use two data structures to implement our LRU Cache.
   1) Doubly-LinkedList<Node>: To store the nodes into cache where the least recently used key will
      be the just before tail node and the just/previous recently used key will be after the head node.
   2) Map<K, Node>: To map the keys to the corresponding nodes.

 * We will use a doubly-linked list to implement LRU Cache.

 * The key will be considered as accessed to the node in Cache, if we try to perform any operation on it.
 * So while performing the get operation on a key, if the key already exists in the cache we will
 detach the key from the cache (linked-list) and put it at the head of LinkedList (marking it as
 the most/just/recently used key in the cache).
 * Similarly, while performing the put operation on a key, if the key already exists, we will detach it
 from the cache and put it at the head of linked-list.
 * If the size == capacity, we will remove the node with given key from the cache, and add it to the
 head of linked-list
 *
 * To perform both of these operations at the minimum cost of O(1) time.
 *
 * "head": head node of the doubly linked-list, to store the just/most recently used key-value pair/node.
 * "tail": tail node of the doubly linked-list, to store the least recently used key-value pair/node.
 *
 * ******** Used Items ********
 * HashMap: to keep account of key-values pair/nodes present in the cache
 * size: denotes the current size of the cache and Doubly linked-list.
 * capacity: denotes the maximum size of cache & key-values node/pairs can hold at one time.
 * Node: Node type will store these values: left node, right node, key, and value.
 *
 * ********************************* Algorithm *********************************
 *
 *  (1) addToFront(Node node)
        * Add a node just after the "head", to make it last/most recently used key-value pair/node

 *  (2) removeNode(Node node)
        * To delete a given node from the Cache and Doubly-linked-list & HashMap

 *  (3) int get(key)
        * To get the 'value' corresponding to the given 'key'
         * If the key does not exist, return -1.
        * Fetch the node corresponding to the given key from the map, let it be node.
        * removeNode(node).
        * addToFront(node).
        * Return nod->value.

 *  (4) void put(key, value)
        * If the key already exists in the cache, remove the key from the cache and put
          it at the "head" of linked-list with the new value.
                * Fetch the node corresponding to the given key from the map, let it be node.
                * node->value = value.
                * removeNode(node)
                * addToFront(node)
        * that key isn't present in the Map, then Initialize a new node with the given key-value pair.
                * Let it be node, node = node(key,value).
                * map.put(key, node)
                * If the cache size reaches its capacity,
                        * retrieve the least recently used node in the Cache, least recently used node
                          is present just before the "tail" of the list
                        * map.remove(lastUsedNode).
                        * removeNode(lastUsedNode)
                        * addToFront(node)
                * Else if the cache size is less than its capacity
                        * addToFront(node)
                        * map.remove(lastUsedNode).
                        * Increment size by 1.

 *  ************ Time Complexity ************
 *  put(key, value) -> O(1)
 *  get(key) -> O(1)
 *  addInFront(node) -> O(1)
 *  removeNode(node) -> O(1)
 *
 * As we are only updating the pointers of a doubly-linked list, all the operations will take O(1) time.
 *
 * Space Complexity
 * O(capacity), where ‘capacity’ is the maximum number of keys LRU Cache can store.
 * In the worst case, we will only be maintaining the capacity number of keys in storage.
 */
