package BinaryTrees.BinaryTreePostOrderTraversal;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/COQOU6klsBg
// https://takeuforward.org/data-structure/post-order-traversal-of-binary-tree/

public class BinaryTreePostOrderTraversal {
    /*********************************** Recursive Post-Order Traversal *********************************
     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(Height of the tree)
        * Recursion Depth/Call stack will be of height O(Tree's height)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrderTraversal = new ArrayList<>();

        postOrder(root, postOrderTraversal);
        return postOrderTraversal;
    }

    public void postOrder(TreeNode root, List<Integer> postOrderTraversal){
        if (root!=null){
            postOrder(root.left, postOrderTraversal);
            postOrder(root.right, postOrderTraversal);
            postOrderTraversal.add(root.val);
        }
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}