package BinaryTrees.CheckSameTree;
import java.util.LinkedList;
import java.util.Queue;

// https://youtu.be/BhuvF_-PWS0
// https://takeuforward.org/data-structure/check-if-two-trees-are-identical/
// https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/

class CheckSameTree {
    /************************************ Efficient Solution *****************************************
     * Simple Pre-Order Traversal
     * Approach: In order to check whether two trees are identical or not, we need to traverse the
        trees.
     * While traversing, we first check the value of the nodes, if they are unequal we can simply
        return false, as trees are non-identical.
        If they are the same, then we need to recursively check their left child as well as the right child.
     * When we get all the three values as true (node values, left child equals, right child equals)
        we can conclude that these are identical trees and can return true.
        Any other combination will return false.

     * Time Complexity: O(n)
         * We are doing a Single Pre-Order tree traversal
     * Space Complexity: O(n)
        * Space is needed for the recursion stack.
        * In the worst case (skewed tree), space complexity can be O(N).
     */
    // Technique Used: Pre-Order Traversal
    /* Given two trees, return true if they are structurally identical */
    public boolean isSameTree(TreeNode A, TreeNode B) {
        // Both Trees are Empty, return true
        if (A == null && B == null)
            return true;

        // If any of them is non-empty, then they are not equal
        if (A == null || B == null)
            return false;

        // Check data of the root nodes (tree1->data ==  tree2->data)
        if (A.val != B.val)
            return false;

        // Check Left SubTrees & Right SubTrees recursively
        // i.e., call sameTree(tree1->left_subtree, tree2->left_subtree)
        // and call sameTree(tree1->right_subtree, tree2->right_subtree)
        return isSameTree(A.left, B.left)  &&  isSameTree(A.right, B.right);
    }


    /************************************ BFS Solution **********************************************
     * Approach: Idea is to use a BFS

     * Time Complexity: O(n)
        * We will traverse all the Nodes only Once
     * Space Complexity: O(Tree's Height)
        * BFS Queue used
     */
    public boolean checkSameTree_BFS(TreeNode rootA, TreeNode rootB) {
        // BFS Queue
        // We can't use ArrayDeque() here, because it doesn't allow the null values to be added.
        // There can be null values in Queue. So, use a LinkedList here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(rootA);
        queue.add(rootB);

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

            // A.left and B.left should be equal in structure, in order for Tree to be equal
            // So, we have to add A.left & B.left in Queue Together so that they can be popped out together
            queue.add(A.left);
            queue.add(B.left);

            // A.right and B.right should be equal in structure, in order for Tree to be equal
            // So, we have to add A.right & B.right in Queue Together so that they can be popped out together
            queue.add(A.right);
            queue.add(B.right);
        }
        return true;
    }

    // Tree Node class
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}