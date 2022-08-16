package BinaryTrees.RootToNodePathInTree;
import java.util.ArrayList;

// https://youtu.be/fmflMqVOC7k
// https://takeuforward.org/data-structure/print-root-to-node-path-in-a-binary-tree/
// https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/

public class RootToNodePathInTree {
    /************************************ Efficient DFS Solution ****************************************
     * Intuition: Recursion and BackTracking Approach

     * Time Complexity: O(n)
        * DFS Traversal takes O(n) time
     * Space Complexity: O(Tree's Height)
        * Recursion Stack space used in DFS Traversal
     */
    public ArrayList<Integer> solve(TreeNode root, int destination) {
        // List to store the path from "Root" to "Destination node"
        ArrayList<Integer> path = new ArrayList<>();

        // Find the Path from 'Root' to 'Destination node'.
        // 'pathFound' stores whether the path is present to Destination Node.
        boolean pathFound = dfs_PreOrder(root, destination, path);

        // Return the Path
        return path;
    }

    // Traversal to used: "Pre-Order Traversal"
    public boolean dfs_PreOrder(TreeNode node, int destination, ArrayList<Integer> path){
        // If root is pointing to NULL, we return false as clearly Destination node can’t be found.
        if (node == null)
            return false;

        // Push the current node to our path list
        path.add(node.val);

        // We check whether the current node is the Destination node or not.
        // If it is then no further execution is needed, and we return true (to parent call)
        // indicating path to the Destination node found.
        if (node.val == destination)
            return true;

        // If it's not the Destination node, then we recursively call its left and right child
        // to find the path to Destination node. If any one of them returns true, it means we have found
        // Destination node at lower levels and return true from the current function.
        if (dfs_PreOrder(node.left, destination, path))
            return true;
        if (dfs_PreOrder(node.right, destination, path))
            return true;

        // If the Destination node is not found at the current node and neither in any of the paths to
        // left child & right child, it means that the Destination Node is not present
        // through the path passing from current node, therefore we pop out the current node from
        // the path list and return false.
        path.remove(path.size() - 1);

        return false;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val;}
    }
}
