package BinaryTrees.BinaryTreeInOrderTraversal;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/Z_NEgBgbRVI
// https://takeuforward.org/data-structure/inorder-traversal-of-binary-tree/

public class BinaryTreeInOrderTraversal {
    /*********************************** Recursive In-Order Traversal *********************************
     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(Height of the tree)
        * Recursion Depth/Call stack will be of height O(Tree's height)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderTraversal = new ArrayList<>();

        inOrder(root, inOrderTraversal);
        return inOrderTraversal;
    }

    public void inOrder(TreeNode root, List<Integer> inOrderTraversal) {
        if (root!=null){
            inOrder(root.left, inOrderTraversal);
            inOrderTraversal.add(root.val);
            inOrder(root.right, inOrderTraversal);
        }    
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}