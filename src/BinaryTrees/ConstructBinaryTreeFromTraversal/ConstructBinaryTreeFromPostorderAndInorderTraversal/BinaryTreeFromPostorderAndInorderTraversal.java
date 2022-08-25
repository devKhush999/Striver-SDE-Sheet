package BinaryTrees.ConstructBinaryTreeFromTraversal.ConstructBinaryTreeFromPostorderAndInorderTraversal;
import java.util.HashMap;

// PRE_REQUISITE: "Generate Binary Tree From Preorder And Inorder Traversal"
// https://youtu.be/9GMECGQgWrQ
// https://takeuforward.org/binary-tree/how-to-construct-a-binary-tree-using-different-traversals/
// https://youtu.be/LgLRTaEMRVc
// https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/

public class BinaryTreeFromPostorderAndInorderTraversal {
    /************************************ Solution ************************************************
     * Divide and Conquer Solution

     * Intuition:
        * Inorder traversal is a special traversal that helps us to identify a node and its left
            and right subtree.
        * Postorder traversal always gives us the root node as the last element.
        * Using these properties we can construct the unique binary tree.
        * Last element of postOrder in every SubTree/Tree is the Root Node.
        * So we can find its index in the inOrder traversal (say 'ind').
        * The left subtree of the Root will be present to the left side of In-order, before index 'ind'.
        * Whereas the Right subtree of Root will be present on the Right side of 'ind' in the
            inOrder traversal.

     * Approach:
        * To Search for the index of Root Node in the inOrder traversal, we can use HashMap
        * Define a recursive function that creates one node at a time
        * First, we create the root node, and then we can take the help of recursion to create
            its left and right subtrees.
        * In order to make recursion work, we need to provide the correct inOrder and postOrder
            traversal for every SubTree and for every recursive call.
        * We can use pointer variable in each recursive calls, in order to point to the start and end
            of the correct inOrder and postOrder traversal respectively, and avoid copying of arrays.

     * Time Complexity: O(n)
         * We are building a Tree with 'n' nodes by Divide n Conquer
     * Space Complexity: O(n) + O(n)
        * O(n) for HasMap to store the indices of nodes in InOrder traversal
        * Another O(n) for Recursion stack space, in worst case of Skewed Trees
     */
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        // Map to store the Indices of Nodes in Inorder Traversal
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++){
            inOrderMap.put(inOrder[i], i);
        }

        // Get the Root of the Tree built
        TreeNode root = buildTree(0, inOrder.length -1, inOrder,
                                0, postOrder.length -1, postOrder, inOrderMap);
        return root;
    }

    private TreeNode buildTree(int inStart, int inEnd, int[] inOrder, int postStart, int postEnd, int[] postOrder, HashMap<Integer, Integer> inOrderMap){
        // Base case, if Starting index of Inorder exceeds the Ending index of InOrder Traversal
        if (inStart > inEnd)
            return null;

        // Last element of Postorder traversal in every SubTree/Tree is the Root Node
        TreeNode root = new TreeNode(postOrder[postEnd]);

        // Figure out the index of Root node in Inorder traversal
        int rootIndex = inOrderMap.get(root.val);

        // No. of Tree nodes in the Right SubTree of Root node, can be figured out only by Inorder traversal
        int rightNodesCount = inEnd - rootIndex;

        // Call recursively for the Left subtree with correct values of Indices of Post-order & In-order
        // and store the answer received in root->left.
        root.left = buildTree(inStart, rootIndex - 1, inOrder,
                            postStart, postEnd - 1 - rightNodesCount, postOrder, inOrderMap);

        // Call recursively for the Right subtree with correct values of Indices of Post-order & In-order
        // and store the answer received in root->right.
        root.right = buildTree(rootIndex + 1, inEnd, inOrder,
                        postEnd - rightNodesCount, postEnd - 1, postOrder, inOrderMap);
        return root;
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
