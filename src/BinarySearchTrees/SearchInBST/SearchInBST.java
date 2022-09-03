package BinarySearchTrees.SearchInBST;

// https://youtu.be/KcNt6v_56cc

public class SearchInBST {
    /********************************** Recursive Solution ********************************************
     * Binary Search Solution
     * Time Complexity: O(log(n))
     * Space Complexity: O(Tree's Height)
     */
    public TreeNode searchBST(TreeNode root, int target) {
        if (root == null)
            return null;

        if (root.val == target)                      // Target found
            return root;

        else if (root.val > target)                  // Target exists on Left Subtree
            return searchBST(root.left, target);

        else // if (root.val < target)               // Target exists on Right Subtree
            return searchBST(root.right, target);
    }


    /********************************** Iterative Solution ********************************************
     * Binary Search Solution
     * Time Complexity: O(log(n))
     * Space Complexity: O(1)
     */
    public TreeNode searchBST_Iterative(TreeNode root, int target) {
        TreeNode node = root;

        while (node != null){
            if (node.val == target)             // Target found
                return node;

            // Instead of using if else, use Ternary Operator
            // Either go to left or Right
            node = node.val > target ? node.left : node.right;
        }
        return null;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
