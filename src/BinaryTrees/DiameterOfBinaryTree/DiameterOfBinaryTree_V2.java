package BinaryTrees.DiameterOfBinaryTree;

// // https://leetcode.com/problems/diameter-of-binary-tree/
// https://youtu.be/Rezetez59Nk
// https://takeuforward.org/data-structure/calculate-the-diameter-of-a-binary-tree//

public class DiameterOfBinaryTree_V2 {
    /********************************* Efficient Solution : 2 ****************************************
     * Same Solution without the use of Global Variable
     * We can create an array/object, and update the value inside it using its reference.
     * This will work same as the global variable

     // Same as Before Solution 1
     * Is it possible to optimize the above solution further?
     * Which operation do you think is very repetitive in nature in the above solution?
     * 💡 Finding Height of the subtrees.
     *
     * Can we use postorder traversal to calculate everything in a single traversal of the tree?
     * Yes, as in post-order traversal, we have to completely traverse the left and right subtree before
     visiting the root node.
     * So, the idea is to use post-order traversal and keep calculating the height of the left
     and right subtrees.
     * Once we have the heights at the current node, we can easily calculate both th


     * Time Complexity: O(n)
        * All nodes will be traversed only once
     * Space Complexity: O(Height of Tree)
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        // Instead of having a global variable, we can have an array with reference & update it whenever
        // required
        int[] diameter = {0};

        // Call the function for Height
        int treeHeight = height(root, diameter);

        // Return Diameter
        return diameter[0];
    }

    // Start traversing the tree recursively and do work in Post Order.
    // Modifying the height function int Binary Tree
    public int height(TreeNode root, int[] diameter){
        if (root==null)
            return 0;

        // At every node, calculate height of left and right subtrees
        int leftHeight = height(root.left, diameter);
        int rightHeight = height(root.right, diameter);

        // Calculate diameter by considering current node as the Curving Point in diameter
        diameter[0] = Integer.max(diameter[0], leftHeight + rightHeight);

        // Calculate & Return height of current node to the previous recursive call
        return 1 + Math.max(leftHeight, rightHeight);
    }


    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
