package BinaryTrees.HeightOfBinaryTree;

// https://youtu.be/eD3tmO66aBA
// https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/

public class HeightOfBinaryTree {
    /********************************* Recursive Solution ******************************************
     * Intuition: Post Order Traversal
        * We start to travel recursively and do our work in Post Order.
        * Reason behind using Post Order comes from our intuition, that if we know the result of
            left and right child then we can calculate the result using that.
        * This is exactly an indication of PostOrder, because in PostOrder we already calculated
            results for left and right children than we do it for current node.
        * So for every node post order, we do Max( left result , right result ) + 1 and return it
            to the previous call.

     * Time Complexity: O(n)
     * Space Complexity: O(Height of Tree)
     */
    public int getHeight(TreeNode root) {
        // Base Case is when "root == null", so we return 0 as there is no Tree
        if (root == null)
            return 0;

        // Get Height of Left Child Sub-Tree
        int leftHeight = getHeight(root.left);

        // Get Height of Right Child Sub-Tree
        int rightHeight = getHeight(root.right);

        // Height will be "1 + max(leftHeight, rightHeight)"
        return 1 + Math.max(leftHeight, rightHeight);
    }


    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
