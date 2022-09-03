package BinarySearchTrees.CeilInBST;

// PRE_REQUISITE: "Search in BST"
// https://youtu.be/KSsk8AhdOZA

public class CeilInBST {
    /*********************************** Iterative Solution **************************************
     * Recall Ceil function in Math
     * Binary Search Solution

     * Time Complexity: O(log(n))
     * Space Complexity: O(1)
     */
    public static int findCeil(TreeNode root, int x) {
        TreeNode node = root;

        // IMP: The value of 'ceil' will keep on decreasing as we move in Tree
        int ceil = -1;

        while (node != null){
            // If the entire node is found, then ceil(x) = x
            if (node.data == x)
                return x;

            // If "node's value < x", clearly "node's value" and everything on node's left
            // cannot be the ceil value for "x", as they will be lesser than 'x'
            // So, we should move Right to find the higher values in Tree which can be the ceil
            // values of "x"
            else if (x > node.data)
                node = node.right;

            // If "x < node's value", then node's value can be the ceil value of 'x'.
            // So, save this value.
            // But where to move?
            // In Right Subtree node's value will be larger than current 'ceil' (i.e, node's value).
            // But we want ceil value to be minimized. So, we can't move to Right Subtree.
            // We move to Left SubTree.
            else if (node.data > x){
                ceil = node.data;
                node = node.left;
            }
        }
        return ceil;
    }


    // Tree Node
    private static class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int val) { this.data = val; }
    }
}
