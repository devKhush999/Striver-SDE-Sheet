package BinarySearchTrees.KSmallestElementBST;
import java.util.Stack;

// PRE_REQUISITE: "INORDER TRAVERSAL: Recursive & Iterative"
// https://youtu.be/9TJYWh0adfk

public class KSmallestElementBST {
    /*********************************** Recursive Solution *****************************************
     * Intuition:
        * Inorder Traversal "Left Root Right" of the BST is always in Sorted fashion.
        * So, we will do an Inorder traversal to traverse the BST in sorted fashion,
            till we haven't seen 'k' nodes in Inorder traversal.

     * Time Complexity: O(k)
     * Space Complexity: O(Tree's Height)
     */
    public int kthSmallest(TreeNode root, int K) {
        // Counter for "k"
        int[] k = {K};

        // Get the Kth Smallest element in the BST
        TreeNode kthSmallestNode = inOrder(root, k);
        return kthSmallestNode.val;
    }

    public TreeNode inOrder(TreeNode node, int[] k){
        if (node == null)
            return null;

        // If K-smallest node, if found in Left SubTree, return it.
        TreeNode left = inOrder(node.left, k);
        if (left != null)
            return left;

        // Decrease the counter for 'k'. If k reaches 0, then current node is the k-smallest node.
        // Then, return it. (Can also use "count == k", by maintaining a separate count variable)
        k[0]--;
        if (k[0] == 0)
            return node;

        // If K-smallest node, if found in Left SubTree, return it.
        TreeNode right = inOrder(node.right, k);
        if (right != null)
            return right;

        // If K-smallest node, not found in Left SubTree, return null.
        return null;
    }


    /****************************************** Iteraive Solution ****************************
     * Intuition:
        Same as Recursive Solution

     * Time Complexity: O(k)
     * Space Complexity: O(Tree's Height)
     */
    public int kthSmallest_BST(TreeNode root, int K) {
        int count = 0;
        TreeNode node = root;

        Stack<TreeNode> stack = new Stack<>();

        while (node != null){
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()){
            node = stack.pop();
            count++;
            if (count == K)
                return node.val;

            if (node.right != null){
                node = node.right;
                while (node != null){
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return -1;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
