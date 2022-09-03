package BinarySearchTrees.FloorInBST;

// PRE_REQUISITE: "Search in BST" & "Ceil in BST"
// https://youtu.be/xm_W1ub-K-w

public class FloorInBST {
    /*********************************** Iterative Solution **************************************
     * Recall Floor function in Math
     * Binary Search Solution

     * Time Complexity: O(log(n))
     * Space Complexity: O(1)
     */
    public static int findCeil(TreeNode root, int x) {
        TreeNode node = root;

        // IMP: The value of 'floor' will keep on increasing as we move in Tree
        int floor = -1;

        while (node != null){
            // If the entire node is found, then floor(x) = x
            if (node.data == x)
                return x;

            // If "node's value > x", clearly "node's value" and everything on node's right
            // cannot be the floor value for "x", as they will be larger than 'x'
            // So, we should move left to find the lower values in Tree which can be the
            // floor values of "x"
            else if (node.data > x)
                node = node.left;

            // If "x > node's value", then node's value can be the floor value of 'x'. So, save this value
            // But where to move?
            // In Left Subtree node's value will be lesser than current 'floor' (i.e, node's value).
            // But we want floor value to be maximized. So, we can't move to Left Subtree.
            // We move to Right SubTree.
            else if (x > node.data){
                floor = node.data;
                node = node.right;
            }
        }
        return floor;
    }


    // Tree Node
    private static class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int val) { this.data = val; }
    }
}
