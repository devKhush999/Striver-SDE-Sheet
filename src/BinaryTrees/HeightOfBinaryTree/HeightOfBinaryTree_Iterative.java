package BinaryTrees.HeightOfBinaryTree;
import java.util.ArrayDeque;
import java.util.Queue;

// https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/

public class HeightOfBinaryTree_Iterative {
    /********************************* Iterative Solution ******************************************
     * Intuition: We will use BFS or Level Order Traversal

     * Time Complexity: O(n)
     * Space Complexity: ~ O(n)
     */
    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        // Same as BFS Traversal
        Queue<TreeNode> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(root);

        // Variable to store the height of the Binary Tree
        int heightOfTree = 0;

        while (!bfsQueue.isEmpty()){
            int size = bfsQueue.size();

            // Whenever we encountered a new level, we increment the height
            heightOfTree++;

            for (int i = 0; i < size; i++){
                TreeNode node = bfsQueue.remove();

                if (node.left != null)
                    bfsQueue.add(node.left);
                if (node.right != null)
                    bfsQueue.add(node.right);
            }
        }
        return heightOfTree;
    }


    // Binary Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
