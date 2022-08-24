package BinaryTrees.ConstructBinaryTreeFromTraversal.ConstructBinaryTreeFromPreorderAndInorderTraversal;
import java.util.HashMap;

// https://youtu.be/9GMECGQgWrQ
// https://takeuforward.org/binary-tree/how-to-construct-a-binary-tree-using-different-traversals/
// https://youtu.be/aZNaLrVebKQ
// https://takeuforward.org/data-structure/construct-a-binary-tree-from-inorder-and-preorder-traversal/
// https://lh4.googleusercontent.com/s_Npp9OQGbH1GAPAt95teoSDPyeSRH8rH24G8ySJy29YkvsVQ5S_A_V8aucbqKoVHH6nFUMQ9vCbJAKF0OITaJyDf0Aoz60Fmi3KVJjPnr1_hnXrzQ2IpZVwBs8e1YulAei1dj0T

// Problem: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

public class BinaryTreeFromPreorderAndInorderTraversal {
    /************************************ Solution ************************************************
     * Divide and Conquer Solution

     * Intuition:
        * Inorder traversal is a special traversal that helps us to identify a node and its left
            and right subtree.
        * Preorder traversal always gives us the root node as the first element.
        * Using these properties we can construct the unique binary tree.
        * First element of preOrder in every SubTree/Tree is the Root Node.
        * So we can find its index in the inOrder traversal (say 'ind').
        * The left subtree of the Root will be present to the left side of In-order, before index 'ind'.
        * Whereas the Right subtree of Root will be present on the Right side of 'ind' in the
            inOrder traversal.

     * Approach:
        * To Search for the index of Root Node in the inOrder traversal, we can use HashMap
        * Define a recursive function that creates one node at a time
        * First, we create the root node, and then we can take the help of recursion to create
            its left and right subtrees.
        * In order to make recursion work, we need to provide the correct inOrder and preOrder
            traversal for every SubTree and for every recursive call.
        * We can use pointer variable in each recursive calls, in order to point to the start and end
            of the correct inOrder and preOrder traversal respectively, and avoid copying of arrays.

     * Time Complexity: O(n)
        * We are building a Tree with 'n' nodes by Divide n Conquer
     * Space Complexity: O(n) + O(n)
        * O(n) for HasMap to store the indices of nodes in InOrder traversal
        * Another O(n) for Recursion stack space, in worst case of Skewed Trees
     */
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {

        // Map to store the Indices of Nodes in Inorder Traversal
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++){
            inOrderMap.put(inOrder[i], i);
        }

        // Get the Root of the Tree built
        TreeNode root = buildTree( preOrder, 0, preOrder.length - 1,
                        inOrder, 0, inOrder.length - 1, inOrderMap);
        return root;
    }

    public TreeNode buildTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> inOrderMap){
        // Base case, if Starting index of Inorder exceeds the Ending index of InOrder Traversal
        if (inStart > inEnd)
            return null;

        // First element of Preorder traversal in every SubTree/Tree is the Root Node
        TreeNode root = new TreeNode(preOrder[preStart]);

        // Figure out the index of Root node in Inorder traversal
        int rootIndex = inOrderMap.get(root.val);

        // No. of Tree nodes in the Left SubTree of Root node, can be figured out only by Inorder traversal
        int leftNodeCount = rootIndex - inStart;

        // Call recursively for the Left subtree with correct values of Indices of Pre-order & In-order
        // and store the answer received in root->left.
        root.left = buildTree(preOrder, preStart + 1, preStart + leftNodeCount,
                            inOrder, inStart, rootIndex - 1, inOrderMap);

        // Call recursively for the Right subtree with correct values of Indices of Pre-order & In-order
        // and store the answer received in root->right.
        root.right = buildTree(preOrder, preStart + 1 + leftNodeCount, preEnd,
                            inOrder, rootIndex + 1, inEnd, inOrderMap);
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
