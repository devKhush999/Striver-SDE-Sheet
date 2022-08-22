package BinaryTrees.CountNodesInCompleteBinaryTree;

// https://youtu.be/u-yWemKGWO0
// https://www.geeksforgeeks.org/count-number-of-nodes-in-a-complete-binary-tree/

public class CountNodesInCompleteBinaryTree_V1 {
    /********************************** Brute Force ***********************************************
     * Time Complexity: O(n)
        * Simple Post-Order DFS traversal
     * Space Complexity: O(Tree's Height)
        * For Complete Binary Tree, Space Complexity is perfect O(log(n))
        * Recursion Stack Space
     */
    public int countTreeNodes(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + countTreeNodes(root.left) + countTreeNodes(root.right);
    }

    /************************************** Efficient Solution ***************************************
     * Intuition: A complete binary tree can have at most (2h – 1) nodes in total where
        h is the height of the tree (This happens when all the levels are completely filled).

     * Thought process:
        * Case 1: By this logic, in the first case, compare the left Sub-tree height with the right Sub-tree
            height. If they are equal it is a full tree, then the answer will be 2^height – 1.
        * Case 2: Otherwise, If they aren’t equal, recursively call for the left Sub-tree and the right Sub-tree
            to count the number of nodes.
        * By this we guarantee that in Case-2, either of Left & Right Subtree will have all levels
            completely filled. Hence, Case-1 can be applied on that SubTree, in Recursion.

     * Time Complexity: O(log(n) * log(n))  =  O(log(n)^2)
        * In each call, we are finding height of the Left & Right Sub-Tree, this takes O(log(n))
        * Total no. of recursive calls will be O(log(n)). Think...
     * Space Complexity: O(log(n))
        * Recursion Stack space will be O(log(n)) Think...
     */
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        // Find the left height of the given Tree by traversing in the root’s left direction
        int leftHeight  = height(root.left, true);

        // Find the right height of the given Tree by traversing in the root’s left direction
        int rightHeight  = height(root.right, false);

        // If the Left and Right height of the given Tree for the root node are equal then return the
        // value of (2^height – 1) as the resultant count of nodes.
        if (leftHeight == rightHeight)
            return (1 << leftHeight) - 1;

        // Otherwise, recursively call for the function for the left and right Subtrees and return
        // their sum + 1 as the resultant count of nodes.
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Function to get the Left height & Right height of the binary tree
    public int height(TreeNode node, boolean leftHeight){
        int height = 1;

        while (node != null){
            height++;

            if (leftHeight)
                node = node.left;
            else
                node = node.right;
        }
        return height;
    }


    // Tree Node
    private static class TreeNode {
        int data;
        TreeNode left, right;
        public TreeNode(int data) {
            this.data = data;
        }
    }
}
