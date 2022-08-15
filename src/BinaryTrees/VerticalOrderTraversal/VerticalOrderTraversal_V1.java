package BinaryTrees.VerticalOrderTraversal;
import java.util.*;

// https://youtu.be/q_a6lpbKJdw
// https://www.geeksforgeeks.org/print-binary-tree-vertical-order/

public class VerticalOrderTraversal_V1 {
    /****************************** Efficient "BFS + MinHeap" Solution **********************************
     * Approach & Thought Process:
        * First, we need to assign a vertical and a level to every node.
        * Once we assign them, we need to figure out a suitable data structure to store them.
        * This data structure should give us the nodes with left-side vertical first and in every
            vertical, top-level node should appear first.
        * Idea is to store all the nodes in increasing order of their verticals (vertical no.)
        * For Tree nodes in same Verticals, we store them in increasing order of their Levels (level no.)
        * For Tree nodes in same Verticals & Levels, we store them in order of sorted values
        * If we think carefully, a Priority Queue (MinHeap) can serve this purpose well

     * Steps:
         * We will perform a BFS tree traversal and assign a vertical and level to every node.
         * We also simultaneously add the TreeNodes to Priority Queue, to store them in their
            respective priority.
         * Then, we finally visit all the TreeNode in the required priority (mentioned above) from
            PriorityQueue, add it to the output.

     * Time Complexity: O(2 * n * log(n))  ~  O(n * log(n))
        * We are first doing BFS Traversal for Assigning vertical and level to every node that will take
            O(n*log(n)) time (as we will also add nodes in PriorityQueue as well)
            Normal Level Order (BFS) Traversal takes O(N).
            But here we are pushing into MinHeap – Single push O(LogN). So Overall O(N*LogN).
        * Then we again traverse all the Nodes as per their priority given by PriorityQueue, this
            will also take O(n*log(n)) time.
     * Space Complexity: O(n/2) + O(n) + O(n)  ~  O(n)
        * O(n) Space for Storing all the TreeNodes with their location {Node, vertical, level}
        * O(n/2) for Space required for Queue used in BFS Traversal
            The queue will have max at Last Level O(N/2) = O(N)
        * The heap also stores all the nodes at a point, so O(n).
     */
    public List<List<Integer>> verticalTraversal_UsingBFS(TreeNode root) {
        List<List<Integer>> verticalOrderTraversal = new ArrayList<>();
        if (root == null)
            return verticalOrderTraversal;

        // MinHeap to order all the nodes in the following Priority:
        // First Vertical, Second Levels, Lastly node value in sorted order
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        // BFS Traversal for Assigning vertical and level to every node
        Queue<Node> bfsQueue = new ArrayDeque<>();
        // push the root node to our queue with its vertical number 0 and level number 0
        bfsQueue.add(new Node(root, 0, 0));

        while (!bfsQueue.isEmpty()){
            Node node = bfsQueue.remove();
            minHeap.add(node);      // Add node to PriorityQueue

            // we push the left child of the node, we decrease vertical by 1 and increase level by 1
            if (node.treeNode.left != null)
                bfsQueue.add(new Node(node.treeNode.left, node.level + 1, node.vertical - 1));

            // when we push the right child of the node, we increase both vertical and level by 1.
            if (node.treeNode.right != null)
                bfsQueue.add(new Node(node.treeNode.right, node.level + 1, node.vertical + 1));
        }

        // One by one, we will Traverse all the TreeNodes again in their required Priority
        // and them to the output
        while (!minHeap.isEmpty()) {
            List<Integer> currVertical = new ArrayList<>();
            verticalOrderTraversal.add(currVertical);

            // Get the next Vertical number whose nodes are to be added
            int nextLeastVertical = minHeap.peek().vertical;

            // Adding TreeNodes in same Vertical only
            while (!minHeap.isEmpty()  &&  minHeap.peek().vertical == nextLeastVertical){
                Node node = minHeap.remove();
                currVertical.add(node.treeNode.val);
            }
        }
        return verticalOrderTraversal;
    }


    /****************************** Efficient "DFS + MinHeap" Solution **********************************
     * Approach & Thought Process: Same as above
        * Just difference is we will assign a vertical and level to every node using DFS Traversal.

     * Steps:
        * We will perform a DFS tree traversal and assign a vertical and level to every node.
        * We also simultaneously add the TreeNodes to Priority Queue, to store them in their
            respective priority.
        * Then, we finally visit all the TreeNode in the required priority (mentioned above) from
            PriorityQueue, add it to the output.

     * Time Complexity: O(2 * n * log(n))  ~  O(n * log(n))
         * We are first doing DFS Traversal for Assigning vertical and level to every node that will take
            O(n*log(n)) time (as we will also add nodes in PriorityQueue as well)
        * Then we again traverse all the Nodes as per their priority given by PriorityQueue, this
            will also take O(n*log(n)) time.
     * Space Complexity: O(Tree's Height) + O(n) + O(n)  ~  O(n)
        * O(n) Space for Storing all the TreeNodes with their location {Node, vertical, level}
        * O(Tree's Height) for Space required for Recursion Stack used by DFS Traversal
        * The heap also stores all the nodes at a point, so O(n).
     */
    public List<List<Integer>> verticalTraversal_UsingDFS(TreeNode root) {
        List<List<Integer>> verticalOrderTraversal = new ArrayList<>();
        if (root == null)
            return verticalOrderTraversal;

        // MinHeap to order all the nodes in the following Priority:
        // First Vertical, Second Levels, Lastly node value in sorted order
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        // DFS Traversal for Assigning vertical and level to every node
        dfsPreOrder(root, 0, 0, minHeap);

        // One by one, we will Traverse all the TreeNodes again in their required Priority
        // and them to the output
        while (!minHeap.isEmpty()) {
            List<Integer> currVertical = new ArrayList<>();
            verticalOrderTraversal.add(currVertical);

            // Get the next Vertical number whose nodes are to be added
            int nextLeastVertical = minHeap.peek().vertical;

            // Adding TreeNodes in same Vertical only
            while (!minHeap.isEmpty()  &&  minHeap.peek().vertical == nextLeastVertical){
                Node node = minHeap.remove();
                currVertical.add(node.treeNode.val);
            }
        }
        return verticalOrderTraversal;
    }

    // DFS (Pre-Order) Traversal for Assigning vertical and level to every node
    public void dfsPreOrder(TreeNode root, int level, int vertical, PriorityQueue<Node> minHeap){
        if (root == null)
            return;
        minHeap.add(new Node(root, level, vertical));

        dfsPreOrder(root.left, level + 1, vertical - 1, minHeap);
        dfsPreOrder(root.right, level + 1, vertical + 1, minHeap);
    }



    // ************************************************************************************
    // Another class to hold the TreeNodes with their locations (with vertical & levels)
    private static class Node implements Comparable<Node>{
        TreeNode treeNode;
        int level, vertical;
        public Node(TreeNode treeNode, int level, int vertical) {
            this.treeNode = treeNode;
            this.level = level;
            this.vertical = vertical;
        }

        // Comparable/Comparator to sort all the Tree nodes in the increasing order of
        // Vertices (with greater Priority), then Levels (next greater Priority) then finally with
        // TreeNode's values (with the least Priority)
        @Override
        public int compareTo(Node node){
            if (this.vertical != node.vertical)
                return this.vertical - node.vertical;
            else if (this.level != node.level)
                return this.level - node.level;
            else
                return this.treeNode.val - node.treeNode.val;
        }
    }

    // ********************************* Tree Node Structure *********************************
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int data) {
            this.val = data;
        }
    }
}
