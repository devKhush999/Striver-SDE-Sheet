package BinaryTrees.ZigZagTraversal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://youtu.be/3OXWEdlIGl4
// https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
// https://www.geeksforgeeks.org/zigzag-tree-traversal/

public class ZigZagTraversal_V1 {
    /*********************************** Efficient Solution 1 *******************************************
     * Observation:
        * The Zig-Zag traversal is same as BFS/Level-Order-Traversal
        * Just the alternate lists are reversed

     * Time Complexity: ~ O(n + n/2) ~ O(n)
        * We will first find BFS of the Tree which takes O(n) time
        * In BFS traversals, we reverse the alternate lists
     * Space Complexity: O(n)
        * BFS of the Tree which takes O(n) Space due to Queue
     */
    public List<List<Integer>> zigzagLevelOrder_V1(TreeNode root) {
        // This part is same as finding the BFS/Level-Order-Traversal of Tree
        List<List<Integer>> zigzagTraversal = new ArrayList<>();

        if (root == null)
            return zigzagTraversal;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            zigzagTraversal.add(currLevel);

            while (size-- > 0){
                TreeNode node = queue.remove();
                currLevel.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        // After we find the BFS, we just the reverse the alternate list to obtain the "Zig-Zag traversal"
        for (int i = 1; i < zigzagTraversal.size(); i += 2)
            reverseList(zigzagTraversal.get(i));

        return zigzagTraversal;
    }

    // Simple function to reverse the list
    private void reverseList(List<Integer> arr){
        int n = arr.size();

        for (int i = 0; i < n/2; i++){
            int temp = arr.get(i);
            arr.set(i, arr.get(n - 1 - i));
            arr.set(n - 1 - i, temp);
        }
    }


    /*********************************** Efficient Solution 2 *******************************************
     * Observation:
        * The Zig-Zag traversal is same as BFS/Level-Order-Traversal
        * Just the alternate lists are reversed
        * Instead of reversing the alternate lists at the end, we can always add the values of node
            at the starting index (0th index) to obtain the reverse list at the end.
        * A flag counter can be used for this

     * Time Complexity: O(n)
        * We are just doing Modified BFS Traversal
     * Space Complexity: O(n)
        * BFS of the Tree which takes O(n) Space due to Queue
     */
    public List<List<Integer>> zigzagLevelOrder_V2(TreeNode root) {
        // This part is similar as finding the BFS/Level-Order-Traversal of Tree
        List<List<Integer>> zigzagTraversal = new ArrayList<>();
        if (root == null)
            return zigzagTraversal;

        // Flag counter to determine whether addition of node values, will be from "left to right"
        // OR "right to left"
        boolean addFromLeftToRight = true;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            zigzagTraversal.add(currLevel);

            while (size-- > 0){
                TreeNode node = queue.remove();

                // Add nodes in order of "Left to Right"
                if (addFromLeftToRight)
                    currLevel.add(node.val);
                // Add nodes in order of "Right to Left"
                else
                    currLevel.add(0, node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // Counter reverse after every level
            addFromLeftToRight = !addFromLeftToRight;
        }
        return zigzagTraversal;
    }


    // Tree Node class
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
