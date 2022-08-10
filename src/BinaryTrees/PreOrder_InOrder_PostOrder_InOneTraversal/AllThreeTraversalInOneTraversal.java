package BinaryTrees.PreOrder_InOrder_PostOrder_InOneTraversal;
import java.util.ArrayList;

// https://www.codingninjas.com/codestudio/problems/981269
// https://youtu.be/ySp2epYvgTE

public class AllThreeTraversalInOneTraversal {
    /************************************* Most Efficient Solution *************************************
     * Time Complexity: O(n)
        * Only one traversal of whole Binary Tree will be done
     * Space Complexity: O(n)
        * Recursion Depth/Call stack will be of height O(Tree's height)
     */
    public void dfs(BinaryTreeNode<Integer> root, ArrayList<Integer> preOrder, ArrayList<Integer> inOrder, ArrayList<Integer> postOrder) {
        if (root == null)
            return;

        // Add node in Pre-order traversal (Root Left Right)
        preOrder.add(root.data);

        // Visit Left SubTree Recursively
        dfs(root.left, preOrder, inOrder, postOrder);

        // Add node in In-order traversal (Left Root Right)
        inOrder.add(root.data);

        // Visit Right SubTree Recursively
        dfs(root.right, preOrder, inOrder, postOrder);

        // Add node in Post-order traversal (Left Right Root)
        postOrder.add(root.data);
    }


    // Tree Node
    private static class BinaryTreeNode<T> {
        public T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }
}
