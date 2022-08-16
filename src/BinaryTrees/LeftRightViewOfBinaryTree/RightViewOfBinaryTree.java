package BinaryTrees.LeftRightViewOfBinaryTree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://youtu.be/KV4mRzTjlAk
// https://takeuforward.org/data-structure/right-left-view-of-binary-tree/

public class RightViewOfBinaryTree {
    /************************************** Efficient BFS Solution *************************************
     * Intuition: Last Node in every level will be the Part of Right Side View (for that level)

     * Time Complexity: O(n)
        * BFS Traversal takes O(n) time
     * Space Complexity: O(n/2) ~ O(n)
         * Due to Queue used in BFS Traversal, whose max. size can be O(n/2) in last level, in worst case.
     */
    public List<Integer> rightSideView_BFS(TreeNode root) {
        ArrayList<Integer> rightSideView = new ArrayList<>();
        if (root == null)
            return rightSideView;

        // Simple BFS Traversal
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            // Get the size the current level
            int size = queue.size();

            // Traverse all the nodes in current level
            for (int i = 1; i <= size; i++){
                TreeNode node = queue.remove();

                // Last Node in every level will be the Part of Right Side View, for current level
                if (i == size)
                    rightSideView.add(node.val);

                if (node.left != null)      // Add left child
                    queue.add(node.left);
                if (node.right != null)     // Add right child
                    queue.add(node.right);
            }
        }
        return rightSideView;
    }


    /************************************** Efficient DFS Solution *************************************
     * Intuition: Last Node in every level will be the Part of Right Side View (for that level)

     * Thought Process: We can also do this using DFS.
        * We will do the Reverse Pre-Order Traversal "Root Right Left"
        * Whenever we encounter a new level for the first time, we simply add that Node's value to the
            "RightView Traversal" list, because we are already moving to the right child node first.
        * As we are moving to the right child node first and If we encountered a new level for the first
            time, then that node has to be the part of "RightView Traversal"
        * This can be checked using "rightView.size() == currentLevel"
        *  "rightView.size()" indicates the number of levels considered in "Right View of Tree" till now.

     * Time Complexity: O(n)
        * DFS Traversal takes O(n) time
     * Space Complexity: O(Tree's Height)
        * Recursion Stack space used in DFS Traversal
     */
    public List<Integer> rightSideView_DFS(TreeNode root) {
        ArrayList<Integer> rightSideView = new ArrayList<>();

        // Start DFS call
        dfs_RightSideView(root, 0, rightSideView);

        return rightSideView;
    }

    // Traversal used -> Reverse Pre-Order Traversal "Root Right Left"
    public void dfs_RightSideView(TreeNode node, int currentLevel, ArrayList<Integer> rightSideView){
        if (node == null)
            return;

        // "rightView.size()" indicates the number of levels considered in "Right View of Tree" till now
        // Whenever we encounter a new level for the first time, we simply add that Node's value to the
        // "RightView Traversal" list.
        // If "rightView.size()" is not equal to current level, then we are not visiting that level for
        // the first time. We have visited it earlier. Think...
        if (currentLevel == rightSideView.size())
            rightSideView.add(node.val);

        // Go to right child first
        dfs_RightSideView(node.right, currentLevel + 1, rightSideView);

        // Go to left child first
        dfs_RightSideView(node.left, currentLevel + 1, rightSideView);
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val;}
    }
}