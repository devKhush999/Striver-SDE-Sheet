package BinaryTrees.BinaryTreePreOrderTraversal;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/RlUu72JrOCQ
// https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/

public class BinaryTreePreOrderTraversal {
    /*********************************** Recursive Pre-Order Traversal *********************************
     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(Height of the tree)
        * Recursion Depth/Call stack will be of height O(Tree's height)
     */
    public List<Integer> preorderTraversal_Recursive(TreeNode root) {
        List<Integer> preOrderTraversal = new ArrayList<>();

        preOrder(root, preOrderTraversal);
        return preOrderTraversal;
    }

    public void preOrder(TreeNode root, List<Integer> preOrderTraversal) {
        if (root!=null){
            preOrderTraversal.add(root.val);
            preOrder(root.left, preOrderTraversal);
            preOrder(root.right, preOrderTraversal);
        }
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
