package BinaryTrees.RootToLeafPathInTree;
import java.util.ArrayList;
import java.util.List;

// PRE-REQUISITE: "ROOT TO NODE PATH IN BINARY TREE"
// https://youtu.be/fmflMqVOC7k

class RootToLeafPathInTree {
    /************************************ Efficient DFS Solution 1 ****************************************
     * Intuition: Recursion and BackTracking Approach

     * Time Complexity: O(n)
        * DFS Traversal takes O(n) time
     * Space Complexity: O(Tree's Height)
        * Recursion Stack space used in DFS Traversal
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // All Root to Leaf Paths
        ArrayList<String> rootToLeafPaths = new ArrayList<>();

        // Find all the Path from 'Root' to 'Leaf node'.
        dfs_PreOrder(root, rootToLeafPaths, new ArrayList<>());
        
        return rootToLeafPaths;
    }

    // Traversal to used: "Pre-Order Traversal"
    public void dfs_PreOrder(TreeNode node, ArrayList<String> rootToLeafPaths, ArrayList<Integer> path){
        // If root is pointing to NULL, we return, as clearly Leaf node couldn't be found.
        if (node == null)
            return;

        // Push the current node to our path list
        path.add(node.val);

        // We check whether the current node is the Leaf node or not.
        // If we encountered a leaf node, then add the Path to the List
        if (node.left == null && node.right == null){
            StringBuilder sb = new StringBuilder();
            int size = path.size();
            
            for (int i = 0; i < size - 1; i++){
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(path.get(size - 1));
            rootToLeafPaths.add(sb.toString());
        }

        // We need to find the Paths to all the Leaf nodes, irrespective of whether they are present
        // in Left SubTree or Right SubTree. So, we have to visit both the left and right SubTree
        dfs_PreOrder(node.left, rootToLeafPaths, path);
        dfs_PreOrder(node.right, rootToLeafPaths, path);

        // Once we have calculated the Path to all Leaf nodes passing through the current node
        // we need to remove current node from the path while BackTracking
        path.remove(path.size() - 1);
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val;}
    }
}