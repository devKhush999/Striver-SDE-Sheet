package BinaryTrees.LowestCommonAncestorInTree;

// https://youtu.be/_-QHfMDde90
// https://takeuforward.org/data-structure/lowest-common-ancestor-for-two-given-nodes/
// https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

public class LowestCommonAncestor {
    /************************************ Efficient Solution *******************************************
     * Approach:
        * We will use DFS Pre-Order traversal
        * If we found any node out of given two Nodes (say p and q), we return that node.
        * Then we recurse for Left & Right Tree SubTree as usual
        * If we don't find any Destination nodes (p and q) or LCA on either left & Right side, we keep
            on returning null
        * If either side of result for Left & Right SubTree is not null, this means we have found either
            one Destination node or LCA node on that Side, So, we return that non-null result.
        * If both the result for Left & Right SubTree are not null, which means we have found both the
            Destination nodes (p and q) on Left & Right. This is because, if we haven't found any
            Destination node on any side, we are returning null.
            These two returned nodes will be the Destination nodes (p and q).
            So, current node will be the LCA of these Destination nodes. So, return the current node.

     * Time Complexity: O(n)
        * DFS Pre-Order takes O(n) Time
        * We will Traverse the whole tree only once

     * Space Complexity: O(Tree's Height)
         * O(Tree's Height) for Recursion Stack Space in DFS
     */
    // PRE-ORDER TRAVERSAL
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If root is null, return null
        // Indicating no LCA or Destination node is found
        if (root == null)
            return null;

        // Once we reached any of the Destination node ('p' or 'q'), we return that node.
        // No need to go left or right
        if (root == p || root == q)
            return root;

        // Find the LCA or Destination node (either of 'p' or 'q') on left SubTree
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);

        // Find the LCA or Destination node (either of 'p' or 'q') on right SubTree
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        // If the left subtree recursive call gives a null value that means we haven’t found
        // LCA or the Destination node 'p' or 'q' in the left subtree.
        // So we return the result for right subTree, as Right SubTree result may have LCA or
        // Destination node ('p' or 'q') or null.
        if (leftResult == null)
            return rightResult;

        // Else if left Subtree result is not null and the right subtree recursive call gives a null
        // value that means we haven’t found LCA or the Destination node 'p' or 'q' in the right subtree,
        // So we return the result for left subTree, as Left SubTree result may have LCA or
        // Destination node ('p' or 'q')
        else if (rightResult == null)
            return leftResult;

        // Else if both left & right calls result are not null values, this means we have found
        // both the Destination nodes (p and q) in both the left & Right SubTree
        // So, both Destination nodes will be under the current node 'root'. So, The root is the LCA.
        else
            return root;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val;}
    }
}
