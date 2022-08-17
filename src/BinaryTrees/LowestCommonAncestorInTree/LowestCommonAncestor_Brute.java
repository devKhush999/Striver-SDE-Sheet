package BinaryTrees.LowestCommonAncestorInTree;
import java.util.ArrayList;

// PRE_REQUISITE: "PATH FROM ROOT TO ANY NODE"
// https://youtu.be/_-QHfMDde90
// https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

public class LowestCommonAncestor_Brute {
    /************************************* Brute Force Solution *********************************
     * Intuition:
        * The intuition behind this approach is to "Store the path from the root to node1 and
            root to node2 in two separate lists".
        * Then find the last common node in both these paths. Simultaneously, into the Tree Nodes stored
            in the both the Paths (lists), and look for the first mismatch.

     * Time Complexity: O(n) + O(n) + O(Tree's Height)
        * O(n) time for finding the path from root to node 1
        * O(n) time for finding the path from root to node 2
        * O(Tree's Height) time for finding the lowest (last) common node in both these paths

     * Space Complexity: O(Tree's Height) + O(Tree's Height) + O(Tree's Height)
        * Two O(Tree's Height) for storing two separate paths from root node to both these nodes
            Both these Path can be at max till the height of the Tree
        * O(Tree's Height) for Recursion Stack Space
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // List to store the path from 'root node' to node 'p'
        ArrayList<TreeNode> pathToNodeP = new ArrayList<>();

        // List to store the path from 'root node' to node 'q'
        ArrayList<TreeNode> pathToNodeQ = new ArrayList<>();

        // Find the Path from Root node to both these nodes
        findPathFromRootToNode(root, p, pathToNodeP);
        findPathFromRootToNode(root, q, pathToNodeQ);

        // Last common node in both these paths will be the LCS of these two nodes
        TreeNode lowestCommonAncestor = null;

        for (int i = 0; i < pathToNodeP.size() && i < pathToNodeQ.size(); i++){
            // previous common node before the First Mismatch will be the LCA
            if (pathToNodeP.get(i).equals(pathToNodeQ.get(i))) {
                lowestCommonAncestor =  pathToNodeP.get(i);
            }
            else                 // Look for the first mismatch between nodes in these two paths
                break;
        }
        return lowestCommonAncestor;
    }

    // Same as finding the Path from Root node to any Node
    private boolean findPathFromRootToNode(TreeNode node, TreeNode destination, ArrayList<TreeNode> path){
        if (node == null)
            return false;

        path.add(node);

        if (node == destination)
            return true;

        if (findPathFromRootToNode(node.left, destination, path))
            return true;
        if (findPathFromRootToNode(node.right, destination, path))
            return true;

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
