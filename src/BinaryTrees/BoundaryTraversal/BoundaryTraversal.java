package BinaryTrees.BoundaryTraversal;
import java.util.ArrayList;

// PROBLEM LINK: https://www.codingninjas.com/codestudio/problems/boundary-traversal_790725
// https://youtu.be/0ca1nvR0be4
// https://takeuforward.org/data-structure/boundary-traversal-of-a-binary-tree/
// https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/

public class BoundaryTraversal {
    /************************************ Efficient Recursive Solution *****************************************
     * Approach: Boundary traversal in an anti-clockwise direction can be described as a traversal
        consisting of three parts:
        * Part 1: Left Boundary of the tree (excluding the leaf nodes).
        * Part 2: All the leaf nodes travelled in the left to right direction.
        * Part 3: Right Boundary of the tree (excluding the leaf nodes), traversed in the reverse direction.
        * The root node is coming from both the boundaries (left and right). Therefore, to avoid
            any confusion, we push it on our list at the very start.

     * Time Complexity: O(n)
         * Reason: The time complexity will be O(Tree's Height) + O(Tree's Height) + O(n) which is ≈ O(n)
         * O(2 * Tree's Height) to find Left & Right Boundaries. We're going through the height only once.
         * O(n) time to find the leaf nodes in Boundary traversal
     * Space Complexity: O(Tree's Height)
         * Reason: Space is needed for the recursion stack while adding leaves.
         * In the worst case (of skewed tree), space complexity can be O(N).
     */
    public ArrayList<Integer> traverseBoundary(TreeNode root){
        ArrayList<Integer> boundaryTraversal = new ArrayList<>();
        if (root == null)
            return boundaryTraversal;

        // Add root node to the boundary traversal
        // Adding it separately in order to avoid confusion due to Skewed Tree
        if (!isLeafNode(root))
            boundaryTraversal.add(root.data);

        // Add Left boundaries to the boundary traversal
        addLeftBoundaries(root.left, boundaryTraversal);

        // Add Left Nodes to the boundary traversal
        addLeafNodes(root, boundaryTraversal);

        // Add Right boundaries to the boundary traversal
        addRightBoundaries(root.right, boundaryTraversal);

        return boundaryTraversal;
    }


    // Pre-Order Traversal is used to insert the Left Boundaries of Tree except root node and leaf nodes
    // We have to visit only the Left most nodes in the Tree.
    // So, if Left SubTree is not null, we just go there. Else we have to move to Right SubTree.
    public void addLeftBoundaries(TreeNode node, ArrayList<Integer> boundaryTraversal){
        if (node == null)
            return;

        // Add only the non-leaf nodes in the boundary traversal
        if (!isLeafNode(node))
            boundaryTraversal.add(node.data);

        if (node.left != null)
            addLeftBoundaries(node.left, boundaryTraversal);
        else
            addLeftBoundaries(node.right, boundaryTraversal);
    }


    // Post-Order Traversal is used to insert the Right Boundaries of Tree except root node and leaf nodes
    // We have to visit only the Right most nodes in the Tree.
    // So, if Right SubTree is not null, we just go there. Else we have to move to Left SubTree.
    public void addRightBoundaries(TreeNode node, ArrayList<Integer> boundaryTraversal){
        if (node == null)
            return;

        if (node.right != null)
            addRightBoundaries(node.right, boundaryTraversal);
        else
            addRightBoundaries(node.left, boundaryTraversal);

        // Add only the non-leaf nodes in the boundary traversal
        if (!isLeafNode(node))
            boundaryTraversal.add(node.data);
    }


    // Same as Pre-Order Traversal
    // We have to traverse the entire Tree to insert all the leaf nodes (BFS can't be used as the
    // leaf nodes might be on different levels).
    // Any traversal (Pre-Order, In-Order, Post-Order) can be used to find all the leaf nodes (think...)
    public void addLeafNodes(TreeNode node, ArrayList<Integer> boundaryTraversal){
        if (node == null)
            return;

        if (isLeafNode(node))
            boundaryTraversal.add(node.data);

        addLeafNodes(node.left, boundaryTraversal);
        addLeafNodes(node.right, boundaryTraversal);
    }


    // Function to check whether node if leaf node or not
    public boolean isLeafNode(TreeNode node){
        return node.left == null && node.right == null;
    }


    // Tree Node
    static class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int data) {
            this.data = data;
        }
    }
}
