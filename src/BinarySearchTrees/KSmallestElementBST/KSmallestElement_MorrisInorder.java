package BinarySearchTrees.KSmallestElementBST;

// PRE_REQUISITE: "MORRIS INORDER TRAVERSAL"
// https://youtu.be/9TJYWh0adfk

public class KSmallestElement_MorrisInorder {
    /*********************************** Morris Solution *****************************************
     * Intuition:
        * Inorder Traversal "Left Root Right" of the BST is always in Sorted fashion.
        * So, we will do an Inorder traversal to traverse the BST in sorted fashion,
            till we haven't seen 'k' nodes in Inorder traversal.
        * We will use Morris Traversal

     * Time Complexity: O(k)
     * Space Complexity: O(1)
     */
    public int kthSmallest(TreeNode root, int K) {
        // counter for 'k'
        int count = 0;
        TreeNode curr = root;

        while (curr != null){
            if (curr.left == null){
                count++;
                if (count == K)
                    return curr.val;
                curr = curr.right;
            }

            else{
                TreeNode prev = curr.left;
                while (prev.right != null  &&  prev.right != curr)
                    prev = prev.right;

                if (prev.right == null){
                    prev.right = curr;
                    curr = curr.left;
                }
                else{
                    prev.right = null;
                    count++;
                    if (count == K)
                        return curr.val;
                    curr = curr.right;
                }
            }
        }
        return -1;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
