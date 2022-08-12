package BinaryTrees.DiameterOfBinaryTree;

// https://leetcode.com/problems/diameter-of-binary-tree/
// https://youtu.be/Rezetez59Nk
// https://takeuforward.org/data-structure/calculate-the-diameter-of-a-binary-tree/

public class DiameterOfBinaryTree_V1 {
    /********************************* Brute Force **********************************************
     * Intuition:
        * The idea is to consider every node as a Curving Point in diameter.
        * We can define the curving point as the node on the diameter path which has the maximum height
        * Observation: Diameter of the tree can be defined as left subtree height + right subtree height
            from the Curving Point.
        * So, the idea to find the Curving Point is, consider every node in the tree as a curving point
            and calculate the diameter for every curving point and return the maximum of all diameters.

     * Time Complexity: O(N^2)
        * For every node, Height Function is called which takes O(N) time hence for every node
            it becomes N*N
     * Space Complexity: O(Height of Tree)
        * Recursive Stack Space
     */
    public int diameterOfBinaryTree_Brute(TreeNode root) {
        if (root == null)
            return 0;

        // At every node, calculate height of left and right subtrees
        int lh = height(root.left);
        int rh = height(root.right);
        int diameter = lh + rh;

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        // Calculate the maximum of all diameters.
        // This can be done simply using a variable passed by reference in the recursive calls
        // or a global variable.
        return Math.max(leftDiameter, Math.max(rightDiameter, diameter));
    }


    /********************************* Efficient Solution : 1 ****************************************
     * Is it possible to optimize the above solution further?
     * Which operation do you think is very repetitive in nature in the above solution?
     * 💡 Finding Height of the subtrees.
     *
     * Can we use postorder traversal to calculate everything in a single traversal of the tree?
     * Yes, as in post-order traversal, we have to completely traverse the left and right subtree before
        visiting the root node.
     * So, the idea is to use post-order traversal and keep calculating the height of the left
        and right subtrees.
     * Once we have the heights at the current node, we can easily calculate both the diameter
        and height of the current node, and hence for each & every node.

     * Time Complexity: O(n)
        * All nodes will be traversed only once
     * Space Complexity: O(Height of Tree)
     */
    // Global variable to store the Diameter of the Tree, whenever we found a diameter greater than
    // the previous one. We update this variable.
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        // Call the function for Height
        int treeHeight = height(root);
        // Return Diameter
        return diameter;
    }

    // Start traversing the tree recursively and do work in Post Order.
    // Modifying the height function int Binary Tree
    public int height(TreeNode root){
        if (root==null)
            return 0;

        // At every node, calculate height of left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Calculate diameter by considering current node as the Curving Point in diameter
        diameter = Integer.max(diameter, leftHeight + rightHeight);

        // Calculate & Return height of current node to the previous recursive call
        return 1 + Math.max(leftHeight, rightHeight);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
