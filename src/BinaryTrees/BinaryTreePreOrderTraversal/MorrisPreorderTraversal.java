package BinaryTrees.BinaryTreePreOrderTraversal;
import java.util.ArrayList;

// PRE_REQUISITE: "MORRIS INORDER TRAVERSAL"
// https://youtu.be/80Zug6D1_r4
// https://takeuforward.org/data-structure/morris-preorder-traversal-of-a-binary-tree/

public class MorrisPreorderTraversal {
    /********************************** Morris Preorder ****************************************
     * Concept of Threaded Binary Tree

     * Intuition:
        * Preorder: Root Left Right
        * We need to figure out how we can go back to Root Node from Left-Subtree without using recursion.
            Only, then we can move to Right SubTree (after Root).
        * Observation: In Preorder traversal, Root will be visited before the Left SubTree.
        * Once we come back to Root node after Left SubTree traversal, only then we move to Right SubTree.
        * In Preorder traversal, Root will be visited again after the Left SubTree traversal
        * More Generally, in Preorder traversal, Root will be visited again after the right-most node of
            Left SubTree. Because, Left Subtree will also follow "Root left Right" pattern. Think...
        * So, we make Threads from "Right-most node of Left Sub-tree" to the "current Root node".
        * Due to this, we can Traverse from Left-Subtree to Root Node
        * When, we come to a Node again via a 'Thread', then we break that Thread and move to Right Subtree

     * Time Complexity: ~ O(n)
        * Amortized O(n)
        * Tree will be traversed only by once by outer while loop
        * Inner while loop will traverse till Height of Tree in the entire Tree Traversal, though not
            exactly entire height.
     * Space Complexity: O(1)
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> preOrder = new ArrayList<>();
        if (root == null)
            return preOrder;

        TreeNode curr = root;

        while (curr != null){
            // Case 1: When the current node has no left subtree.
            // In this scenario, there is nothing to be traversed on the left side, so we simply print
            // the value of the current node and move to the right of the current node.
            if (curr.left == null){
                preOrder.add(curr.val);
                curr = curr.right;
            }
            else{
                // Else, Check the Status of Right-most node (last node in Inorder of Left SubTree)
                // in Left SubTree
                TreeNode prev = curr.left;

                // Move to the Right most node in Left Subtree, till it is not null, and
                // it doesn't have Thread to current Root node
                while (prev.right != null  &&  prev.right != curr)
                    prev = prev.right;

                // Case 2: When there is a Left subtree and the Right-most node of this Left subtree
                // is pointing to null. It implies, Left SubTree hasn't been visited yet.
                // In this case we make a "Thread" pointer from Right-most node of Left Subtree
                // to Current Root node, (so that we can easily traverse Left Subtree and go to Root
                // again, later on).
                // In this case, as we know that the Left subtree is not visited, so we need to print
                // the value of the current node and move to the Left of the current node.
                if (prev.right == null){
                    prev.right = curr;          // Make a "Thread"
                    preOrder.add(curr.val);
                    curr = curr.left;
                }
                // Case 3: When there is a Left subtree and the Right-most child of this Left-subtree
                // is already pointing to the current node. It implies there is already a Thread
                // from Right-most node of Left Subtree to Current Root node.
                // In this case, we know that the Left subtree is already visited, amd we come back to
                // Root node again, so it's the time to visit the Right SubTree
                // So, we break the Threads to restore original Tree and move to the Right of the
                // current Root node.
                else{
                    prev.right = null;          // Break "Thread"
                    curr = curr.right;
                }
            }
        }
        return preOrder;
    }


    // TreeNode
    private static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val){
            this.val = val;
        }
    }
}
/*
PreOrder: Root Left Right
To summarize, at a node whether we have to move Left or Right is determined
 by whether the node has a left subtree. If it doesn't we move to the right.

 If there is a left subtree then we see its rightmost child/node. If the rightmost child is pointing
  to NULL, we connect a Thread from Left Subtree to current Root node, we print the current node
  and move the current node to its left, as it hasn't been visited yet.
 If the rightmost child is already pointing towards the current node, (it means there is already a Thread
  to current root node), we remove that Thread and move to the right of the current node.

 We will stop the execution when the current Root node points to null, and we have traversed the whole
  tree.
 */