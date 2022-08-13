package BinaryTrees.MaximumPathSum;

// PRE-REQUISITE: "HEIGHT OF BINARY TREE" & "DIAMETER OF BINARY TREE"
// https://youtu.be/WszrfSwMz58
// https://takeuforward.org/data-structure/maximum-sum-path-in-binary-tree/

class BinaryTreeMaximumPathSum {
    /************************************** Efficient Solution *****************************************
     * The idea is to consider every node as a Curving Point in "Path Sum".
     * We can define the curving point as the node on the Path, which connects the left Path & Right Path
        and has maximum sum.
     * So, the idea to find the Curving Point is, consider every node in the tree as a curving point
        and calculate the "Path Sum" for every curving point and return the maximum of all "Path Sum".

     * Approach:
        * The maximum path sum through a given node (when that node is acting as the root
            node/curving point).
        * At a given node with a value, if we find the max leftSumPath in the left-subtree and
            the max rightSumPath in the right subtree,
            then the maxPathSum through that node is node.value + (leftSumPath + rightSumPath).
        * It is very important to understand what value we return from our function.
        * In our recursive function, we find and compare the maxPathSum from a given node when
            it is the root/turning point of the path.
        * But what we return is the maxPathSum of that same node with the maximum of
            Left Path Sum and Right Path Sum

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
        
        return maxPathSum[0];
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

        // Calculate the "Max Path Sum" through the current node as
        // "node.val + (leftMaxPath + rightMaxPath)" and update maxPathSum[0] accordingly
        maxPathSum[0] = Math.max(maxPathSum[0], leftMaxPathSum + node.val + rightMaxPathSum);

        // Return the "Max Path Sum" including the current node for the Parent root call
        return Math.max(leftMaxPathSum, rightMaxPathSum) + node.val;
    }

    // Tree Node class
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}