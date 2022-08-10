package BinaryTrees.BinaryTreePostOrderTraversal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://youtu.be/2YBhNLodD8Q
// https://takeuforward.org/data-structure/post-order-traversal-of-binary-tree/

public class BinaryTreePostOrderTraversal_Iterative {
    /*********************************** Iterative Post-Order Traversal *********************************
     * Intuition: Here the Intuition lies in Order "Left Right Root".
        * The Root of the Tree will be present in the last of Post Order Traversal
        * So, here we construct post-order traversal from the last to first element (in Post-order traversal)
        * We will construct the Post Order Traversal in Reverse Order "Root Right Left"
        * Later we will reverse the traversal "Root Right Left" to obtain "Left Right Root" (which is
            our desired Post-Order Traversal)
        * Then finally we reverse the traversal list to get the desired Post order
            traversal "Left Right Root"

     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(2n) = O(n)
        * We are using Two stack to do Post Order Traversal
     */
    public List<Integer> postorderTraversal_V1(TreeNode root) {
        // Output ArrayList for Post Order Traversal
        ArrayList<Integer> postOrderTraversal = new ArrayList<>();
        if (root == null)
            return postOrderTraversal;

        // We will use Two stacks for this purpose
        // Stack 1 will be used to store the TreeNodes being used in Post Order Traversal
        Stack<TreeNode> s1 = new Stack<>();
        // Stack 2 will store the Post Order Traversal of Tree in "Reverse Order"
        Stack<Integer> s2 = new Stack<>();
        // Push the Root node
        s1.push(root);

        // Visit every node while Stack 1 is not empty
        while (!s1.isEmpty()){
            TreeNode node = s1.pop();
            // Add the node to stack 2
            s2.push(node.val);

            // Because we are constructing the Post Order Traversal in Reverse Order "Root Right Left"
            // So, Add the left child first to stack 1
            if (node.left != null)
                s1.push(node.left);

            // Add the right child later to stack 1
            if (node.right != null)
                s1.push(node.right);
        }

        // TreeNodes values stored in stack 2 will be the Post Order Traversal but in reverse order
        // Reverse the Stack to obtain the Post Order traversal
        while (!s2.isEmpty())
            postOrderTraversal.add(s2.pop());

        return postOrderTraversal;
    }


    /*********************************** Iterative Post-Order Traversal *********************************
     * Idea is same as Previous Solution, but instead of reversing the "Stack 2" to obtain the desired
        Post-order traversal at end, we can always insert at the 0th index in the Output ArrayList

     * ALTERNATIVE: We can just append the TreeNodes at the end of Output Post-Order Traversal list
        using the same add(Element) (like we usually do). Then we can reverse the whole list to obtain the
        required "Post-Order Traversal"

     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(n)
        * We are using One stack to do Post Order Traversal
     */
    public List<Integer> postorderTraversal_V2(TreeNode root) {
        // Output ArrayList for Post Order Traversal
        ArrayList<Integer> postOrderTraversal = new ArrayList<>();
        if (root == null)
            return postOrderTraversal;

        // We will use Two stacks for this purpose. Push the root node
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // Visit every node while Stack 1 is not empty
        while (!stack.isEmpty()){
            // Pop the node & add it at the first index (0th) in Post-Order Traversal list
            TreeNode node = stack.pop();
            postOrderTraversal.add(0, node.val);

            // Because we are constructing the Post Order Traversal in Reverse Order "Root Right Left"
            // So, Add the left child first
            if (node.left != null)
                stack.push(node.left);

            // Add the right child later
            if (node.right != null)
                stack.push(node.right);
        }
        return postOrderTraversal;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
