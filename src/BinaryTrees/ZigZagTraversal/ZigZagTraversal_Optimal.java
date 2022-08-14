package BinaryTrees.ZigZagTraversal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// https://www.geeksforgeeks.org/zigzag-tree-traversal/

public class ZigZagTraversal_Optimal {
    /*********************************** Optimal & Efficient Solution ***************************************
     * Thought Process:
        * In each iteration of BFS (when the previous level has been processed), the Queue used in BFS
            contains only one level (which is the next level).
        * Since we know that alternate lists in BFS Traversal are reversed in ZZig-Zag Traversal.
        * So, when addition of nodes has to done from left to right, we will take out the left-most
            node from the Queue.
        * And when addition of nodes has to be done from right to left, we will take out the right-most
            node from the Queue.
        * A Deque can be used for this.

     * Time Complexity: O(n)
         * We are just doing Modified BFS Traversal
     * Space Complexity: O(n)
         * BFS of the Tree which takes O(n) Space due to Deque used here
     */
    public List<List<Integer>> zigzagLevelOrder_V1(TreeNode root) {
        List<List<Integer>> zigzagTraversal = new ArrayList<>();
        if (root == null)
            return zigzagTraversal;

        // Flag counter to determine whether addition of node values, will be from "left to right"
        // OR "right to left"
        boolean addFromLeftToRight = true;

        // Deque for doing Zig-Zag Traversal Traversal
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);

        while (!deque.isEmpty()){
            List<Integer> currLevel = new ArrayList<>();
            zigzagTraversal.add(currLevel);

            int size = deque.size();

            for (int i = 0; i < size; i++){
                // If the addition has to be done in "Left to Right" order
                // Then remove the First element in Deque & add it to output list
                // Though we have to maintain the nodes (of each level) in Deque as maintained in BFS Queue,
                // i.e, each level has nodes starting from left most node to right most node
                if (addFromLeftToRight){
                    TreeNode node = deque.removeFirst();
                    currLevel.add(node.val);

                    // In case of "Left to Right" insertion order, we have to add "Left child first" & then
                    // "Right child later" to maintain the nodes (of each level) in Deque as maintained in BFS
                    // Queue i.e, each level has nodes starting from left most node to right most node
                    if (node.left != null)
                        deque.addLast(node.left);
                    if (node.right != null)
                        deque.addLast(node.right);
                }
                // If the addition has to be done in "Right to Left"/"Reverse" order
                // Then remove the Last element in Deque & add it to output list
                // Though we have to maintain the nodes in Deque as maintained in BFS Queue, i.e,
                // each level has nodes starting from left most node to right most node
                else{
                    TreeNode node = deque.removeLast();
                    currLevel.add(node.val);

                    // In case of "Right to Left"/"Reverse" insertion order, we have to add
                    // "Right child first" & then "Left child later" to maintain the nodes (of each level)
                    // in Deque as maintained in BFS Queue
                    // i.e, each level has nodes starting from left most node to right most node
                    if (node.right != null)
                        deque.addFirst(node.right);
                    if (node.left != null)
                        deque.addFirst(node.left);
                }
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
