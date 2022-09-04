package BinarySearchTrees.InsertNodeInBST;

// https://youtu.be/FiFiNvM29ps

public class InsertNodeInBST {
    /******************************* Iterative Solution ******************************************
     * Intuition:
        * Try to figure out where that node could have been located in BST (if it was present)
        * The Problem reduces to searching in BST
        * Find where it can be, and insert the node (insert position will always be some
            Leaf Node position).

     * Time Complexity: O(Tree's Height)
        * We will traverse till only the Height of Binary Tree
     * Space Complexity: O(1)
     */
    public TreeNode insertIntoBST_Iterative(TreeNode root, int value) {
        if (root == null)
            return new TreeNode(value);

        TreeNode node = root;

        while (true){
            // Value is greater, go to Right SubTree of current node
            if (value > node.val){
                // We have to go to right, but if Right doesn't exist, then this will be the place
                // for value to be inserted
                if (node.right == null){
                    node.right = new TreeNode(value);
                    break;
                }
                else
                    node = node.right;
            }
            // Value is Smaller, go to Left SubTree of current node
            else if (value < node.val){
                // We have to go to left, but if Left doesn't exist, then this will be the place
                // for value to be inserted
                if (node.left == null){
                    node.left = new TreeNode(value);
                    break;
                }
                else
                    node = node.left;
            }
        }
        return root;
    }


    /******************************* Recursive Solution ******************************************
     * Intuition:
        * Try to figure out where that node could have been located in BST (if it was present)
        * The Problem reduces to searching in BST
        * Find where it can be, and insert the node (insert position will always be some
            Leaf Node position).

     * Time Complexity: O(Tree's Height)
        * We will traverse till only the Height of Binary Tree
     * Space Complexity: O(Tree's Height)
        * Recursion stack space
     */
    public TreeNode insertIntoBST(TreeNode root, int value){
        if (root == null)
            return new TreeNode(value);

        // Value is greater than root's value, insert into Right subtree
        if (value > root.val)
            root.right = insertIntoBST(root.right, value);

        // Value is lesser than root's value, insert into Left subtree
        else if (value < root.val)
            root.left = insertIntoBST(root.left, value);

        return root;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
