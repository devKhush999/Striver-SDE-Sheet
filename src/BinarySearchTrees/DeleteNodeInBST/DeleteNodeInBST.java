package BinarySearchTrees.DeleteNodeInBST;

// https://youtu.be/kouxiP_H5WE
// https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/

public class DeleteNodeInBST {
    /************************************* Recursive Solution ***************************************
     * Intuition:
        * Intuition behind Node deletion in BST is to first "Search that node location in BST", and
            then "Delete that node from BST"

     * Time Complexity: O(Tree's Height)
        * Only the Height of Tree will be traversed.
        * We may have to travel from the root to the deepest leaf node, in worst case.
     * Space Complexity: O(log(n))
        * Recursion stack space
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        // Value to deleted exists on Right Subtree of BST
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        // Value to deleted exists on Left Subtree of BST
        else if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        // If key is same as root's value, then this is the node to be deleted
        else {
            // Node to be deleted has only one child: 'Return the child itself'
            if (root.left == null)
                return root.right;

            // Node to be deleted has only one child: 'Return the child itself'
            else if (root.right == null)
                return root.left;

            // Else the Node to be deleted has two children:
            // Find 'inorder predecessor' (maximum value in Left BST Subtree) of the node.
            // Copy contents of the 'inorder predecessor' to the node and delete the inorder predecessor.
            // Note that 'inorder successor' (minimum value in Right BST Subtree) can also be used.
            TreeNode leftMax = root.left;
            while (leftMax.right != null)
                leftMax = leftMax.right;

            // Copy contents of the 'inorder predecessor' to the node and delete the inorder predecessor
            // from the Left Subtree
            root.val = leftMax.val;
            root.left = deleteNode(root.left, leftMax.val);
            return root;
        }
    }


    /************************************* Iterative Solution ***************************************
     * Intuition:
        * Intuition behind Node deletion in BST is to first "Search that node location in BST", and
            then "Delete that node from BST"

     * Time Complexity: O(Tree's Height)
        * Only the Height of Tree will be traversed.
        * We may have to travel from the root to the deepest leaf node, in worst case.
     * Space Complexity: O(1)
     */
    public TreeNode deleteNodeInBST(TreeNode root, int key){
        if (root == null)
            return null;

        // If "root node" is the key to be deleted, No need of searching the node to be deleted
        // Simply delete the 'root node'
        if (root.val == key)
            return deleteBSTNode(root);

        TreeNode node = root;

        // Search for the 'node' to be deleted
        while (node != null){

            // Value to deleted exists on Left Subtree of BST
            if (node.val > key){
                // If Left child is the node to be deleted, then delete that node
                if (node.left != null  &&  node.left.val == key) {
                    node.left = deleteBSTNode(node.left);
                    break;
                }
                // else continue searching for node in left
                else
                    node = node.left;
            }
            // Value to deleted exists on Right Subtree of BST
            else {
                // If Right child is the node to be deleted, then delete that node
                if (node.right != null  && node.right.val == key) {
                    node.right = deleteBSTNode(node.right);
                    break;
                }
                // else continue searching for node in right
                else
                    node = node.right;
            }
        }
        return root;
    }

    // Function to delete the node in BST
    public TreeNode deleteBSTNode(TreeNode root){
        // Node to be deleted has only one child: 'Return the child itself'
        if (root.left == null)
            return root.right;

        // Node to be deleted has only one child: 'Return the child itself'
        else if (root.right == null)
            return root.left;

        // Else the Node to be deleted has two children:
        // Find "inorder predecessor" (maximum value in Left BST Subtree) of the node.
        TreeNode leftMax = root.left;
        while (leftMax.right != null)
            leftMax = leftMax.right;

        // Since the entire Right Subtree BST will be greater than "inorder predecessor", so, we
        // make the "right" of "inorder predecessor" as the Right Subtree BST.
        // Note that "inorder successor" (minimum value in Right BST Subtree) can also be used.
        leftMax.right = root.right;
        return root.left;
    }



    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
