package BinaryTrees.FlattenBinaryTreeIntoLinkedList;
import java.util.ArrayList;
import java.util.Stack;

// PRE_REQUISITE: "Pre-order Iterative & Recursive"
// https://youtu.be/sWf7k1x9XR4
// https://takeuforward.org/data-structure/flatten-binary-tree-to-linked-list/

public class FlattenBinaryTreeIntoLinkedList_Preorder {
    /************************** Approach 1 : Brute Force : Recursive Pre-order *************************
     * Time Complexity: O(n) + O(n)  ~  O(n)
        * One traversal to find thr Pre-order traversal
        * One more Traversal to arrange the TreeNodes into Linked-list
     * Space Complexity: O(Tree's Height) + O(n)  ~  O(n)
        * DFS Pre-order Recursion Stack space uses O(Tree's Height)
        * O(n) for ArrayList to store all 'n' nodes in Pre-order fashion
     */
    public void preOrder(TreeNode root, ArrayList<TreeNode> preorder){
        if (root!=null){
            preorder.add(root);
            preOrder(root.left, preorder);
            preOrder(root.right, preorder);
        }
    }

    public void flattenTree_Brute(TreeNode root) {
        ArrayList<TreeNode> preOrderTraversal = new ArrayList<>();
        preOrder(root, preOrderTraversal);

        for (int i = 0; i < preOrderTraversal.size() - 1; i++){
            TreeNode temp = preOrderTraversal.get(i);

            temp.right = preOrderTraversal.get(i + 1);
            temp.left = null;
        }
    }


    /************************************** Iterative Pre-order *********************************
     * Iterative Pre-order Traversal (using Stack) is used
     * Time Complexity: O(n)
        * One traversal required
     * Space Complexity: O(n)
        * Stack Space required
     */
    public void flattenBinaryTree_Iterative(TreeNode root){
        if (root == null)
            return;

        // Iterative Preorder traversal
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();

            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);

            // Think...
            if (!stack.isEmpty())
                node.right = stack.peek();
            node.left = null;
        }
    }


    /************************************** Recursive Pre-order Solution ******************************
     * Intuition:
        * The sequence of nodes in the linked list should be that of the Pre-order traversal. So, it is
            obvious that we have to use Pre-order traversal
        * To Convert the Binary Tree into Linked-list, we can just turn its Left & Right SubTree into
            Linked-list. Also, point the 'right pointer' of Root node to Left linked-list. And,
            point (via 'right' pointer) the last node in Left linked-list to the first node
            of Right linked-list.
        * So, we already need the Left SubTree & Right SubTree to be already turned into a Linked-list
            to turn the whole Tree into a Linked-list.
        * For this we need to use a Reverse Pre-order Traversal "Right Left Root" (reverse of Pre-order).
        * Due to this, we will be converting the Tree into the Linked-list from the extreme right child
            of Binary Tree.

     * Time Complexity: O(n)
        * One DFS Pre-order traversal required
     * Space Complexity: O(Tree's Height)
        * Recursion stack space
     */
    public void flatten(TreeNode node, TreeNode[] head){
        if (node == null)
            return;

        // Flatten the Right Subtree into Linked-list
        flatten(node.right, head);
        // Flatten the Left Subtree into Linked-list
        flatten(node.left, head);

        // Make the Right child of current node as head
        node.right = head[0];
        // Left child will be null
        node.left = null;

        // We're creating Linked-list from the "Tail to Head"
        // Make current node as head of Linked-list
        head[0] = node;
    }

    public void flattenBinaryTree(TreeNode root){
        // This stores the Reference of the "head of linked-list"
        TreeNode[] headOfLinkedList = {null};

        flatten(root, headOfLinkedList);
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}