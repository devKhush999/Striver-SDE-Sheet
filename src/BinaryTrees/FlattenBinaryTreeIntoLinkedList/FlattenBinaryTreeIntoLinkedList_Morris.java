package BinaryTrees.FlattenBinaryTreeIntoLinkedList;

// PRE_REQUISITE: "Morris Pre-order Traversal"
// https://www.codingninjas.com/codestudio/problems/981269
// https://youtu.be/ySp2epYvgTE

public class FlattenBinaryTreeIntoLinkedList_Morris {
    /******************************** Morris Preorder Solution ****************************************
     * We will use the intuition behind morris’s traversal. In Morris Traversal we use the concept
        of the Threaded Binary tree.
     * To Convert the Binary Tree into Linked-list, we need to convert it via Preorder traversal.
        "Root -> Left -> Right", pointers used are 'right' pointers.
     * Since we need to connect "Left -> Right", instead of making a Thread from "rightmost node in Left
        Subtree" to "Root node", we will make Thread from "Rightmost node in Left Subtree" to
        "Root node in Right Subtree". By this we will make connect "Left -> Right", via 'right' pointer
     * Then we will make connection, "Root -> Left" via 'right' pointer by simply assigning
        'Left subtree' to 'right subtree' and make 'left' pointer to null.
     * Thus, we made desired connection, "Root -> Left -> Right", via 'right' pointers.
     * We can repeat this procedure for Left Subtree and Right Subtree by moving to Right (since Left
        has been made null).

     * Time Complexity: O(n)
        * One traversal of Tree is done
     * Space Complexity: O(1)
     */
    public void flatten(TreeNode root){
        TreeNode curr = root;

        while (curr != null){
            // At a Node (say cur) if there exists a left child, we will find the rightmost node in the
            // left subtree (say prev).
            if (curr.left != null){
                TreeNode prev = curr.left;

                while (prev.right != null)
                    prev = prev.right;

                // We will set prev’s right child to curr’s right child
                // This is done to connect "Left -> Right" via 'right' pointer
                prev.right = curr.right;

                // We will then set curr’s right child to it’s left child.
                // This is done to connect "Root -> Left" via 'right' pointer
                curr.right = curr.left;

                // 'left' pointer of curr will point to null, in Linked-list
                curr.left = null;

                // By this, we will connect every Tree and its SubTree into pattern of
                // "Root -> Left -> Right" via 'right' pointers
            }
            // We will then move curr to the next node by assigning curr it to its right child
            curr = curr.right;
        }
    }

    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
