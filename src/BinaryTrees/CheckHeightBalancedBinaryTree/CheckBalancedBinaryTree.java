package BinaryTrees.CheckHeightBalancedBinaryTree;

// https://youtu.be/Yt50Jfbd8Po
// https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/

public class CheckBalancedBinaryTree {
    /************************************ Efficient Solution ********************************************
     * Intuition:
        * The basic idea is to modify height function while calling a recursive function.
        * To check if subtrees are balanced or not.
        * So we do some changes in the previous approach, in which we were calling the height function
            for every node and at the returning time, we were returning ‘true’ if the current subtree
            is balanced otherwise returning ‘false’.
        * But in this approach, we return the height of the current subtree/node. if we have to
            return ‘true’, when Tree is Balanced.
        * If we have to return ‘false’, when Tree is not Balanced then return ‘-1’.

     * Time Complexity: O(n)
        * We are exploring every node exactly once.
     * Space Complexity: O(Height of the Tree)
        * Recursion Stack Space
     */
    public boolean isBalanced(TreeNode root) {
        // The height of the Tree shouldn't be '-1', it means it should be balanced.
        return height(root) != -1;
    }

    // Modifying Height function for a Binary Tree
    public int height(TreeNode root){
        // Base case
        if (root == null)
            return 0;

        // Find the Height of Left SubTree
        // If it is ‘-1’, it’s mean ‘left’ subtree is not balanced and is so the respective subtree.
        // So, return ‘-1’ as the height of subtree, because if the child node/subtree is not
        // balanced then the parent node can’t be balanced.
        int leftHeight = height(root.left);
        if (leftHeight == -1)
            return -1;

        // Find the Height of Right SubTree
        // If it is ‘-1’, it’s mean ‘right’ subtree is not balanced and is so the respective subtree.
        // So, return ‘-1’ as the height of subtree, because if the child node/subtree is not
        // balanced then the parent node can’t be balanced.
        int rightHeight = height(root.right);
        if (rightHeight == -1)
            return -1;

        // If the absolute difference between height of left & right subtree is more than 1, return -1
        // Denoting this SubTree is not balanced
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // Else Return the height as usual
        return 1 + Math.max(leftHeight, rightHeight);
    }


    // Binary Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
