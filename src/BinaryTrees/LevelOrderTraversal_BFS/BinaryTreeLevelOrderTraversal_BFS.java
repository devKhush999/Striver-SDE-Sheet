package BinaryTrees.LevelOrderTraversal_BFS;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// PRE_REQUISITE: "ROTTEN ORANGES" in QUEUES
// https://youtu.be/EoAsWbO7sqg
// https://takeuforward.org/data-structure/level-order-traversal-of-a-binary-tree/

class BinaryTreeLevelOrderTraversal_BFS {
    /* Level Order traversal is simply BFS (Breadth First Search) of Tree
        * Time Complexity: O(n)
            * We will visit all the nodes in Tree
        * Space Complexity: O(n)
            * Due to queue  (though much less than O(n))
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        // Take a queue data structure and push the root node to the queue
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        List<List<Integer>> levelOrderTraversal = new ArrayList<>();

        // while loop which will run till our queue is non-empty
        while (!queue.isEmpty()){
            // Get the size of queue, this denotes the size of current level
            int size = queue.size();

            // List to store the current level
            List<Integer> currentLevel = new ArrayList<>();

            // Iterate for the size of current level
            for (int i = 0; i < size; i++){
                // Pop out from the front of the queue
                TreeNode treenode = queue.remove();

                // Add the value of the popped node to output list
                currentLevel.add(treenode.val);

                // If Node has a left child, push it to the queue.
                if (treenode.left != null)
                    queue.add(treenode.left);

                // If Node has a right child, push it to the queue.
                if (treenode.right != null)
                    queue.add(treenode.right);
            }
            levelOrderTraversal.add(currentLevel);
        }
        return levelOrderTraversal;
    }


    // ******************************* Tree Node for Tree Data Structure ********************************
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int val) { this.val = val; }
  }
}