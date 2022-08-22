package BinaryTrees.CountNodesInCompleteBinaryTree;

// https://youtu.be/u-yWemKGWO0
// https://www.geeksforgeeks.org/count-number-of-nodes-in-a-complete-binary-tree/

public class CountNodesInCompleteBinaryTree_V2 {
    /************************************** Efficient Solution: V2 ***************************************
     * Intuition: A complete binary tree can have at most (2h – 1) nodes in total where
        h is the height of the tree (This happens when all the levels are completely filled).

     * In this solution instead of finding the height of Left & Right SubTree one by one,
        we will do it just once, in one Traversal.

     * Thought process:
        * Case 1: By this logic, in the first case, compare the left Sub-tree height with the right Sub-tree
            height. If they are equal it is a full tree, then the answer will be 2^height – 1.
        * Case 2: Otherwise, If they aren’t equal, recursively call for the left Sub-tree and the right Sub-tree
            to count the number of nodes.
     * By this we guarantee that in Case-2, either of Left & Right Subtree will have all levels
        completely filled. Hence, Case-1 can be applied on that SubTree, in Recursion.

     * Time Complexity: O(log(n) * log(n))  =  O(log(n)^2)
        * In each call, we are finding height of the given Tree, this takes O(log(n))
        * Total no. of recursive calls will be O(log(n)). Think...
     * Space Complexity: O(log(n))
        * Recursion Stack space will be O(log(n)) Think...
     */
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        // Store the height of the Binary Tree
        int height = 1;

        // We traverse in Extreme Left as well as Extreme Right Direction simultaneously
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        // If both the left & right are not null, then height is increased by 1
        while (leftNode != null && rightNode != null){
            height++;
            leftNode = leftNode.left;
            rightNode = rightNode.right;
        }
        // If both Left & Right reaches null simultaneously, then the given Tree has all levels
        // completely filled. In that case, total no. of nodes is "2^height - 1". Think...
        if (leftNode == null && rightNode == null)
            return (1 << height) - 1;

        // Else Recurse for the Left & Right Tree
        return 1 + countNodes(root.left) + countNodes(root.right);
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
