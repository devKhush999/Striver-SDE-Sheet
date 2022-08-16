package BinaryTrees.LeftRightViewOfBinaryTree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://youtu.be/KV4mRzTjlAk
// https://takeuforward.org/data-structure/right-left-view-of-binary-tree/

public class LeftViewOfBinaryTree {
    /************************************** Efficient BFS Solution *************************************
     * Intuition: First Node in every level will be the Part of Left Side View (for that level)

     * Time Complexity: O(n)
        * BFS Traversal takes O(n) time
     * Space Complexity: O(n/2) ~ O(n)
        * Due to Queue used in BFS Traversal, whose max. size can be O(n/2) in last level, in worst case.
     */
    public List<Integer> leftSideView_BFS(TreeNode root) {
        ArrayList<Integer> leftSideView = new ArrayList<>();
        if (root == null)
            return leftSideView;

        // Simple BFS Traversal
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            // Get the size the current level
            int size = queue.size();

            // Traverse all the nodes in current level
            for (int i = 1; i <= size; i++){
                TreeNode node = queue.remove();

                // First Node in every level will be the Part of Left Side View, for current level
                if (i == 1)
                    leftSideView.add(node.val);

                if (node.left != null)      // Add left child
                    queue.add(node.left);
                if (node.right != null)     // Add right child
                    queue.add(node.right);
            }
        }
        return leftSideView;
    }


    /************************************** Efficient DFS Solution *************************************
     * Intuition: First Node in every level will be the Part of Left Side View (for that level)

     * Thought Process: We can also do this using DFS.
        * We will do the Pre-Order Traversal "Root Left Right"
        * Whenever we encounter a new level for the first time, we simply add that Node's value to the
            "LeftView Traversal" list, because we are already moving to the left child node first.
        * As we are moving to the left child node first and If we encountered a new level for the first
            time, then that node has to be the part of "LeftView Traversal". Think...
        * This can be checked using "leftView.size() == currentLevel"
        * "leftView.size()" indicates the number of levels considered in "Left View of Tree" till now.

     * Time Complexity: O(n)
        * DFS Traversal takes O(n) time
     * Space Complexity: O(Tree's Height)
        * Recursion Stack space used in DFS Traversal
     */
    public List<Integer> leftSideView_DFS(TreeNode root) {
        ArrayList<Integer> leftSideView = new ArrayList<>();

        // Start DFS call
        dfs_LeftSideView(root, 0, leftSideView);

        return leftSideView;
    }

    // Traversal used -> Pre-Order Traversal "Root Left Right"
    public void dfs_LeftSideView(TreeNode node, int currentLevel, ArrayList<Integer> leftSideView){
        if (node == null)
            return;

        // "leftView.size()" indicates the number of levels considered in "Left View of Tree" till now
        // Whenever we encounter a new level for the first time, we simply add that Node's value to the
        // "LeftView Traversal" list.
        // If "leftView.size()" is not equal to current level, then we are not visiting that level for
        // the first time. We have visited it earlier. Think...
        if (currentLevel == leftSideView.size())
            leftSideView.add(node.val);

        // Go to left child first
        dfs_LeftSideView(node.left, currentLevel + 1, leftSideView);

        // Go to right child first
        dfs_LeftSideView(node.right, currentLevel + 1, leftSideView);
    }



    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val;}
    }
}
