package BinaryTrees.CheckHeightBalancedBinaryTree;

// https://youtu.be/Yt50Jfbd8Po
// https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/

public class CheckBalancedBinaryTree_Brute {
    /******************************** Brute Force Solution **************************************
     * The basic idea is that, check for every subtree, the current subtree is height-balanced or not.
        If the subtree is height-balanced then check for its parent subtree.
        So we need to check the height of the current subtree’s left and right and check the left
        subtree and right subtree’s height-balanced.
     * For each node in the given tree, we are finding the height of its Left Tree & Right Tree
        and check their difference is not more than 1. (This step would traverse all the "n" nodes in
        the Tree, to find height of Left & Right Sub-tree)
     * We are also checking their left & Right child should be balanced too (This step would also
        traverse all the "n" nodes in the Tree)
     * At each node (child or root) in the Tree, we are traversing the entire tree

     * Time Complexity: O(n^2)
        * At each node (child or root) in the Tree, we are traversing the entire tree
     * Space Complexity: O(n)
        * Recursion Stack Space
        * In the case of the skew tree, we will be using a linear space call stack.
     */
    public boolean isBalanced(TreeNode root) {
        // Base case
        if (root == null)
            return true;

        // Find the Left SubTree & Right SubTree Height
        int leftTreeHeight = height(root.left);
        int rightTreeHeight = height(root.right);

        // Both the Left Tree and Right Tree Should be Balanced
        // and the Root should be balanced too (height of left & right tree difference shouldn't be more than 1)
        return isBalanced(root.left)  &&  isBalanced(root.right)  &&  Math.abs(leftTreeHeight - rightTreeHeight) <= 1;
    }


    // Standard Height function for a Binary Tree
    public int height(TreeNode root){
        if (root == null)
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }


    // Binary Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
