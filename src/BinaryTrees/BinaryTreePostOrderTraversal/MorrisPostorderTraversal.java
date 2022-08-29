package BinaryTrees.BinaryTreePostOrderTraversal;
import java.util.ArrayList;
import java.util.Collections;

// PRE_REQUISITE: "MORRIS INORDER & PREORDER TRAVERSAL"
// https://youtu.be/80Zug6D1_r4
// https://www.geeksforgeeks.org/morris-traversal-for-postorder/

public class MorrisPostorderTraversal {
    /********************************** Morris Postorder ****************************************
     * Concept of Threaded Binary Tree

     * Intuition:
        * Postorder: Left Right Root
        * We need to figure out how we can go back to Root Node after visiting both Left-Subtree and
            Right-Subtree without using recursion.
        * Observation: In Postorder traversal, Root will be visited after the Left & Right SubTree.
        * In Postorder traversal, Root will be visited again after the Left & Right SubTree traversal
        * Observation: Post-Order traversal is reverse of Traversal "Root Right Left"
        * We already know how to find the "Morris Preorder traversal" of "Root Left Right",
            same thing can be implicated to obtain a traversal similar to "Morris Preorder" to get
            the Traversal of form "Root Right Left"
        * Finally, we can reverse this traversal to obtain the Postorder Traversal

     * How to Traverse the Tree in fashion : "Root Right Left"?
        * This is basically a variant of Morris Preorder, "Root Left Right"
        * In this traversal, Root node will be visited again after Left Subtree traversal to go for
            Right Subtree traversal.
        * In above traversal, Root will be visited again after the Left-most node of
            Right SubTree. Because, Right Subtree will also follow "Root Right Left" pattern. Think...
        * We make Threads from "Left-most node of Right Sub-tree" to the "current Root node".
        * Due to this, we can Traverse from Right-Subtree to Root Node
        * When, we come to a Node again via a 'Thread', then we break that Thread and move to Left Subtree

     * Time Complexity: ~ O(n)
        * Amortized O(n)
        * Tree will be traversed only by once by outer while loop
        * Inner while loop will traverse till Height of Tree in the entire Tree Traversal, though not
            exactly entire height.
     * Space Complexity: O(1)
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> postOrder = new ArrayList<>();
        if (root == null)
            return postOrder;

        TreeNode curr = root;

        while (curr != null){
            // Case 1: When the current node has no Right subtree.
            // In this scenario, there is nothing to be traversed on the Right side, so we simply print
            // the value of the current node and move to the Left of the current node.
            if (curr.right == null){
                postOrder.add(curr.val);
                curr = curr.left;
            }
            else{
                // Else, Check the Status of Left-most node (last node in "Root Right Left" traversal
                // of Right SubTree) in Right SubTree
                TreeNode prev = curr.right;

                // Move to the Left most node in Right Subtree, till it is not null, and
                // it doesn't have Thread to current Root node
                while (prev.left != null && prev.left != curr)
                    prev = prev.left;

                // Case 2: When there is a Right subtree and the Left-most node of this Right subtree
                // is pointing to null. It implies, Right SubTree hasn't been visited yet.
                // In this case we make a "Thread" pointer from Left-most node of Right Subtree
                // to Current Root node, (so that we can easily traverse Right Subtree and go to Root
                // again, later on).
                // In this case, as we know that the Right subtree is not visited, so we need to print
                // the value of the current node and move to the Right of the current node.
                if (prev.left == null){
                    prev.left = curr;               // Make a "Thread"
                    postOrder.add(curr.val);
                    curr = curr.right;
                }
                // Case 3: When there is a Right subtree and the Left-most child of this Right-subtree
                // is already pointing to the current node. It implies there is already a Thread
                // from Left-most node of Right Subtree to Current Root node.
                // In this case, we know that the Right subtree is already visited, as we come back to
                // Root node again, so it's the time to visit the Left SubTree
                // So, we break the Threads to restore original Tree and move to the Left of the
                // current Root node.
                else {
                    prev.left = null;
                    curr = curr.left;
                }
            }
        }
        // Reverse the "Root Right Left" Traversal to obtain the Postorder traversal
        Collections.reverse(postOrder);

        return postOrder;
    }

    static private class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
}
