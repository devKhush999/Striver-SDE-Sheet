package BinaryTrees.MaximumWidthOfBinaryTree;
import java.util.ArrayDeque;
import java.util.Queue;

// https://youtu.be/ZbybYvcVLks
// https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
// https://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/

public class MaximumWidthOfBinaryTree {
    /************************************* BFS Solution ***************************************
     * Intuition:
        * The width is defined by the no. of nodes at any level.
        * Therefore, we can use a level order traversal to traverse the tree and in every level
        * We try to find the leftmost and rightmost node of that level.
        * To achieve this we use indexing strategy to uniquely index nodes of a level (index all the
            nodes from 1 to n)
        * Once we know the leftMost and rightMost nodes, width can be defined
            as "rightMostIndex- leftMostIndex + 1"
        * We use indexing formula, for a TreeNode with index 'i' (0-based indexing):
            Index of left child of that node = 2*i + 1
            Index of right child of that node = 2*i + 2
        * Note: NULL nodes does not hamper the indexing in any way.

     * Time Complexity: O(n)
        * BFS Traversal takes O(n) time
     * Space Complexity: O(n/2) + O(n)  ~  O(n)
        * Size of BFS Queue can be at max. O(n/2)
        * In BFS Queue, we are storing a pair, of TreeNode and its index
     */

    public int widthOfBinaryTree_V1(TreeNode root) {
        if (root == null)
            return 0;

        // Max. width out of width of all the levels
        int maxWidth = 0;

        // BFS Queue (Using some Generics 👀)
        Queue<NodePair<TreeNode, Integer>> queue = new ArrayDeque<>();
        // We store the nodes as well its index
        queue.add(new NodePair<>(root, 0));

        while (!queue.isEmpty()){
            // Size of current level
            int size = queue.size();

            // Index of first & last node in tha level
            int firstIndex = 0, lastIndex = 0;

            // Traverse the entire level
            for (int i = 0; i < size; i++){
                NodePair<TreeNode, Integer> node = queue.remove();

                // Get the index of first node in that level
                if (i == 0)
                    firstIndex = node.index;
                // Get the index of last node in that level
                if (i == size - 1)
                    lastIndex = node.index;

                // Add left child of node, its index will be "2 * i + 1"
                if (node.treeNode.left != null)
                    queue.add(new NodePair<>(node.treeNode.left, 2 * node.index + 1));

                // Add right child of node, its index will be "2 * i + 2"
                if (node.treeNode.right != null)
                    queue.add(new NodePair<>(node.treeNode.right, 2 * node.index + 2));
            }
            // Once we know the leftMost and rightMost nodes, width can be defined as
            // "rightMostIndex- leftMostIndex + 1"
            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }
        return maxWidth;
    }


    /************************************* Efficient BFS Solution ***************************************
     * Intuition:
        * The width is defined by the no. of nodes at any level.
        * Therefore, we can use a level order traversal to traverse the tree and in every level
        * We try to find the leftmost and rightmost node of that level.
        * To achieve this we use indexing strategy to uniquely index nodes of a level (index all the
            nodes from 1 to n)
        * Once we know the leftMost and rightMost nodes, width can be defined
            as "rightMostIndex- leftMostIndex + 1"
        * We use indexing formula, for a TreeNode with index 'i' (0-based indexing):
            Index of left child of that node = 2*i + 1
            Index of right child of that node = 2*i + 2
        * Note: NULL nodes does not hamper the indexing in any way.

     * Improvement:
        * This approach has a problem, as we are multiplying 2 to the current index, it can happen in
            a tree that the index may overflow the bound of an integer (Integer.MAX_VALUE).
            Therefore, we need to find a strategy to prevent it.
        * Instead of indexing (0 based) all the nodes from 0 to n, we will index just the Level.
        * We will index all the level starting from 1 (1-based indexing)
        * The formula "rightMostIndex- leftMostIndex + 1" to find the width of level will still hold.

     * Time Complexity: O(n)
        * BFS Traversal takes O(n) time
     * Space Complexity: O(n/2) + O(n)  ~  O(n)
        * Size of BFS Queue can be at max. O(n/2)
        * In BFS Queue, we are storing a pair, of TreeNode and its index
     */
    public int widthOfBinaryTree_V2(TreeNode root) {
        if (root == null)
            return 0;

        // Max. width out of width of all the levels
        int maxWidth = 0;

        // BFS Queue (Using some Generics 👀)
        Queue<NodePair<TreeNode, Integer>> queue = new ArrayDeque<>();
        // We store the nodes as well its index
        queue.add(new NodePair<>(root, 0));

        while (!queue.isEmpty()){
            // Size of current level
            int size = queue.size();

            // Index of first & last node in tha level
            int firstIndex = 0, lastIndex = 0;

            // We will store the index of first node (left most node) in that level
            int minIndex = queue.peek().index;

            // Index of first & last node in tha level
            for (int i = 0; i < size; i++){
                NodePair<TreeNode, Integer> node = queue.remove();

                // Get the index of first node in that level
                if (i == 0)
                    firstIndex = node.index;

                // Get the index of last node in that level
                if (i == size - 1)
                    lastIndex = node.index;

                // Now whenever we assign the index for its children, we take the parent node
                // index as (i - minIndex) rather than 'i', to avoid overflow.
                // Add left child of node, its index will be "2 * (i-minIndex) + 1"
                if (node.treeNode.left != null)
                    queue.add(new NodePair<>(node.treeNode.left, 2 * (node.index - minIndex) + 1));

                // Add right child of node, its index will be "2 * (i-minIndex) + 2"
                if (node.treeNode.right != null)
                    queue.add(new NodePair<>(node.treeNode.right, 2 * (node.index - minIndex) + 2));
            }
            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }
        return maxWidth;
    }

    // Node Pair class (Generics used)
    private static class NodePair<A, B>{
        A treeNode;
        B index;
        public NodePair(A treeNode, B index){
            this.treeNode = treeNode;
            this.index = index;
        }
    }

    private static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
