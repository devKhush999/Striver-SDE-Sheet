package BinaryTrees.MaximumPathSum;

// It is the Slight Modification of Above Problem
// In this, we have to output "Maximum Sum of Paths between any two leaves of Binary Tree"
// The path should contain two leaf nodes and the maximum path sum may or may not pass through the root.
// If there is only one Leaf Node in Tree, return-1

public class BinaryTreeMaximumPathSum_BetweenLeaves {
    /************************************** Efficient Solution *****************************************
     * Idea is same as before, but we need to find "Maximum Sum of Paths between any two leaves"
     * So, in this case we will update the "Maximum Path Sum variable" only when there exists a
        Left Sub-Tree as well as Right Sub-Tree.
     * If either of Left Sub-Tree or Right Sub-Tree is null, the definition of "Maximum Path Sum" is
        not possible. SO, we don;t calculate it

     * Time Complexity: O(N)
        * We are doing a Single Post-Order tree traversal
     * Space Complexity: O(Height of Tree)
        * Space is needed for the recursion stack.
        * In the worst case (skewed tree), space complexity can be O(N).
     */
    public int maxPathSum(TreeNode root) {
        // Initialize a variable to store the "Max Path Sum" to change its value by reference
        // Global variable can also be used
        int[] maxPathSum = {Integer.MIN_VALUE};

        // Call the Find Max Path Sum Function
        findMaxPathSum(root, maxPathSum);

        // If the Maximum Path Sum remained "-Inf", then there were less than two leaf nodes in Tree.
        // Because, if there was atleast two leaf nodes, we must have calculated 'Max Path Sum'.
        // So, return -1 in case of one leaf node
        return maxPathSum[0] != Integer.MIN_VALUE ? maxPathSum[0] : -1;
    }


    // Post Order Traversal
    public int findMaxPathSum(TreeNode node, int[] maxPathSum){
        if (node == null)
            return 0;

        // At each node, find recursively its "Left Max Path Sum" and its "Right Max Path Sum"
        // If any of these Path sum are -ve, then don't consider that path, so their contribution in
        // calculation of "Max Path Sum" is 0.
        int leftMaxPathSum = Math.max(0, findMaxPathSum(node.left, maxPathSum));
        int rightMaxPathSum = Math.max(0, findMaxPathSum(node.right, maxPathSum));

        // Calculate the "Max Path Sum" through the current node only when there exists leaf node on
        // both left subtree and right subtree.
        // Calculate the "Max Path Sum" through the current node
        // as "node.val + (leftMaxPath + rightMaxPath)" and update maxPathSum[0] accordingly
        if (node.left != null && node.right != null){
            maxPathSum[0] = Math.max(maxPathSum[0], leftMaxPathSum + node.data + rightMaxPathSum);
        }

        // Return the "Max Path Sum" including the current node for the Parent root call
        return node.data + Math.max(leftMaxPathSum, rightMaxPathSum);
    }


    // Tree Node class
    private static class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int val) { this.data = val; }
    }
}
