package BinaryTrees.SymmetricTree;
import java.util.LinkedList;
import java.util.Queue;

// PRE_REQUISITE : "Check Same Tree"
// https://youtu.be/nKggNAiEpBE
// https://takeuforward.org/data-structure/check-for-symmetrical-binary-tree/

public class SymmetricTree {
    /************************************ DFS Solution **********************************************
     * Approach: Idea is to use a DFS (Pre-Order Traversal)
     * Mirror Property: Left Looks like Right, Right looks like Left

     * Time Complexity: O(n)
        * We will traverse all the Nodes only Once
     * Space Complexity: O(Tree's Height)
        * Recursion Stack Space used
     */
    public boolean isSymmetric_DFS(TreeNode root) {
        if (root == null)
            return true;

        // Check if Left & Right SubTree are mirror images (i.e, Symmetric)
        return checkSymmetric(root.left, root.right);
    }

    public boolean checkSymmetric(TreeNode A, TreeNode B){
        if (A == null && B == null) // Both Trees are Empty, return true
            return true;

        if (A == null || B == null) // If any of them is non-empty, then they are not mirror images
            return false;

        // Check the 3 Conditions:
        // 1) Values of both nodes are same
        // 2) A.left and B.right should be equal in structure, in order for Tree to be Symmetric
        // 3) A.right and B.left should be equal in structure, in order for Tree to be Symmetric
        return (A.val == B.val)  &&  checkSymmetric(A.right, B.left)  &&  checkSymmetric(A.left, B.right);
    }


    /************************************ BFS Solution **********************************************
     * Approach: Idea is to use a BFS
     * Mirror Property: Left Looks like Right, Right looks like Left

     * Time Complexity: O(n)
        * We will traverse all the Nodes only Once
     * Space Complexity: O(Tree's Height)
        * BFS Queue used
     */
    public boolean isSymmetric_BFS(TreeNode root) {
        if (root == null)
            return true;

        // BFS Queue
        // We can't use ArrayDeque() here, because it doesn't allow the null values to be added.
        // There can be null values in Queue. So, use a LinkedList here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()){
            // We will pop out 2 nodes from the Queue at a time
            TreeNode A = queue.remove();
            TreeNode B = queue.remove();

            if (A == null && B == null)     // Same logic used in DFS
                continue;

            if (A == null || B == null)     // Same logic used in DFS
                return false;

            if (A.val != B.val)             // Same logic used in DFS
                return false;

            // A.right and B.left should be equal in structure, in order for Tree to be Symmetric
            // So, we have to add A.right & B.left in Queue Together so that they can be popped out together
            queue.add(A.right);
            queue.add(B.left);

            // A.left and B.right should be equal in structure, in order for Tree to be Symmetric
            // So, we have to add A.left & B.right in Queue Together so that they can be popped out together
            queue.add(A.left);
            queue.add(B.right);
        }
        return true;
    }


    // ********************************* Tree Node Structure *********************************
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int data) {
            this.val = data;
        }
    }
}
