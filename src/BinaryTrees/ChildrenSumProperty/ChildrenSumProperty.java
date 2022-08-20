package BinaryTrees.ChildrenSumProperty;

// https://youtu.be/fnmisPM6cVo
// https://takeuforward.org/data-structure/check-for-children-sum-property-in-a-binary-tree/
// https://www.codingninjas.com/codestudio/problems/childrensumproperty_790723

public class ChildrenSumProperty {
    /************************************* DFS Solution 1 *********************************************
     * Thought Process:
        * We perform a DFS tree traversal and check whether the current node value is greater than the
            sum of node values of its children.
        * If this is the case, we simply assign its children to the same value of the current node
            and then recurse for the children.
        * We do so?
            Because we are not allowed to decrement a node value (to the sum of children values).
            So we set the children to a large value in order to increment the parent.
        * It can happen in subsequent recursions for left & right, that this child value is further
            changed (but this change will only be incremental in nature).
        * Therefore, it is necessary that when we return to a node after returning from its
            children calls, we set it to the sum of node values of its children explicitly.

     * Time Complexity: O(n)
        * Single DFS Traversal is used
     * Space Complexity: O(Tree's height)
        * Recursion stack space
     */
    public void changeTree(BinaryTreeNode <Integer> root) {
        // For the base case, if the node is pointing to NULL or is the LEAF Node, we simply return.
        if (root == null || (root.left == null && root.right == null))
            return;

        // At every node, first we find the sum of values of the children
        // If "node’s value" > "sum of children node values", we assign both the children’s value to
        // their parent’s node value.
        int childrenSum = getLeftAndRightChildSum(root);
        if (childrenSum < root.data){
            if (root.left != null)
                root.left.data = root.data;
            if (root.right != null)
                root.right.data = root.data;
        }

        // Then we visit the children using recursion to satisfy the children sum property for Left
        // and Right child.
        changeTree(root.left);
        changeTree(root.right);

        // When we return to the node after visiting its children, we set its value to the sum of its
        // children values.
        root.data = getLeftAndRightChildSum(root);
    }

    public int getLeftAndRightChildSum(BinaryTreeNode<Integer> root){
        return (root.left != null ? root.left.data : 0) + (root.right != null ? root.right.data : 0);
    }


    /************************************* DFS Solution 2 *********************************************
     * This Solution will find the minimum values to be added to the Node to satisfy the Children
        Sum property.
     * Thought process: Similar to above solution
     * Approach:
        The approach is simple, we will find the difference between the root node and the child nodes.
        If the difference is positive then we will add the difference to one of the child node and
        call this recursive function for both left and right child nodes and finally update the value
        of the root node equal to the sum of the left and right child node.

     * Time Complexity: O(n)
        * Single DFS Traversal is used
     * Space Complexity: O(Tree's height)
        * Recursion stack space
     */
    public void childrenSumPropertyReorder(BinaryTreeNode <Integer> root) {
        if (root == null || (root.left == null && root.right == null))
            return;

        int childrenSum = getLeftAndRightChildSum(root);
        // After this point root.data will become equal to the Children Sum
        if (root.data > childrenSum){
            if (root.left != null)
                root.left.data += root.data - childrenSum;
            else
                root.right.data += root.data - childrenSum;
        }

        childrenSumPropertyReorder(root.left);
        childrenSumPropertyReorder(root.right);

        root.data = getLeftAndRightChildSum(root);
    }


    private static class BinaryTreeNode <T> {
        T data;
        BinaryTreeNode <T> left, right;
        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }
}