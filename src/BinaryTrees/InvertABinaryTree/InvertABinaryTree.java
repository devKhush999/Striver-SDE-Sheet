package BinaryTrees.InvertABinaryTree;
import java.util.ArrayDeque;
import java.util.Queue;

// https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/

class InvertABinaryTree {
    /********************************* Efficient DFS Solution ***********************************
     * Simple Swapping Based Solution

     * TC -> O(n)
        * n -> no. of nodes in binary tree
        * We are doing Pre-Order DFS Traversal
     * SC -> O(Tree's Height)
        * Due to Recursion Stack Space
     */
    public TreeNode invertTree(TreeNode root) {
        // Base case
        if (root == null)
            return null;

        // Swap the Left Child Subtree with Right Child Subtree
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Invert the Left SubTree
        invertTree(root.right);

        // Invert the Right SubTree
        invertTree(root.left);

        // Just Return the new Root, (ignore this...)
        return root;
    }


    /********************************* Efficient BFS Solution ***********************************
     * Simple Swapping Based Solution
     * Just Do a Simple BFS Traversal and Swap the Left & Right Child

     * TC -> O(n)
        * n -> no. of nodes in binary tree
        * We are doing BFS Traversal
     * SC -> O(n/2) ~ O(n)
        * Due to size of BFS Queue
     */
    public TreeNode invertTree_BFS(TreeNode root) {
        if (root == null)
           return null;

        // BFS Queue
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        // Do BFS. While doing BFS, keep swapping left and right children pointers
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            // Swap the Left Child Subtree with Right Child Subtree
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // If Left Child Subtree is not null, push it for inverting it
            if (node.left != null)
                queue.add(node.left);

            // If Right Child Subtree is not null, push it for inverting it
            if (node.right != null)
                queue.add(node.right);
        }
        return root;
    }


    // Tree Node
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}