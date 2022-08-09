package BinaryTrees.BinaryTreePreOrderTraversal;
import java.util.ArrayList;
import java.util.Stack;

// https://youtu.be/Bfqd8BsPVuw
// https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/

public class BinaryTreePreOrderTraversal_Iterative {
    /*********************************** Iterative Pre-Order Traversal *********************************
     * In order to convert Recursive Solution to an Iterative solution, we always need a Stack
     * Idea is same as what we follow in Recursive PreOrder Traversal of Binary Tree.
     * First Visit the Node, then visit the Left Subtree & finally visit the Left Subtree

     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(Height of the tree)
        * Max size of Stack will be of max. height of Tree, i.e, O(Tree's height)
     */
    public ArrayList<Integer> preOrderTraversal_Iterative_V1(TreeNode root) {
        // ArrayList to store the Pre-order Traversal
        ArrayList<Integer> preOrderTraversal = new ArrayList<>();

        // Check if root is null
        if (root == null)
            return preOrderTraversal;

        // Creat a Stack to be used in Pre-order Traversal AND insert the root node
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            // Pop out a node form stack while it is not empty
            TreeNode node = stack.pop();
            // Add it into the Pre-order Traversal list
            preOrderTraversal.add(node.val);

            // Push the Right Node to stack first, so that Right node will be visited later
            if (node.right != null)
                stack.push(node.right);

            // Then, Push the Left Node to stack first, so that Left node will be visited first
            if (node.left != null)
                stack.push(node.left);
        }
        return preOrderTraversal;
    }


    /*********************************** Iterative Pre-Order Traversal *********************************
     * Another Intuitive Solution
     * PRE-REQUISITE: "Binary Search Tree Iterator : Stacks"
     * Idea for Pre-Order Traversal is same as that of used in Binary Search Tree Iterator

     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(Height of the tree)
        * Max size of Stack will be of max. height of Tree, i.e, O(Tree's height)
     */
    public ArrayList<Integer> preOrderTraversal_Iterative_V2(TreeNode root) {
        // Output
        ArrayList<Integer> preOrderTraversal = new ArrayList<>();

        // Stack Used for Implementing a Recursive Solution
        Stack<TreeNode> stack = new Stack<>();

        // Insert all the Left child of the root node into the stack, along with adding their values
        // into the answer list (this is bcoz we are doing pre-order traversal, first node has to be visited)
        TreeNode node = root;
        while (node != null){
            stack.push(node);
            preOrderTraversal.add(node.val);
            node = node.left;
        }

        while (!stack.isEmpty()){
            // one by one pop all nodes from the stack
            node = stack.pop();

            // If the right subtree of node is not null, then push all the left nodes of the Right sub-tree
            // into the stack
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    preOrderTraversal.add(node.val);
                    node = node.left;
                }
            }
        }
        return preOrderTraversal;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
