package BinaryTrees.PreOrder_InOrder_PostOrder_InOneTraversal;
import java.util.ArrayList;
import java.util.Stack;

// https://www.codingninjas.com/codestudio/problems/981269
// https://youtu.be/ySp2epYvgTE
// https://takeuforward.org/data-structure/preorder-inorder-postorder-traversals-in-one-traversal/

public class AllThreeTraversalInOneTraversal_Iterative {
    /************************************* Iterative Efficient Solution *************************************
     * Intuition:
        * In preorder traversal, we print a node at the first visit itself.\
        * Whereas, in inorder traversal at the first visit to a node, we simply traverse to the
            left child. It is only when we return from the left child and visit that node the
            second time, that we print it.
        * Similarly, in postorder traversal, we print a node in its third visit after visiting both
            its children.

     * Approach:
        * We take a stack data structure and push a Pair<node, timesSeen> to it.
        * Here 'node' is the Tree Node and 'timesSeen' the no. of times that node has been visited/seen
        * Initially, the "timesSeen" is 1 as we are visiting the node for the first time.


     * Time Complexity: O(3 * n)
        * Each Node in the Binary Tree will be visited thrice
     * Space Complexity: O(2 * n)
        * We are creating a Stack to store the all the Nodes in the TREE as 'Pairs'
        * Stack can have all the nodes in worst case
        * Each Element of stack will be a Pair (an Object). This will take another O(n) Space
     */
    public void getAllTraversal(BinaryTreeNode<Integer> root, ArrayList<Integer> preOrder,
                                ArrayList<Integer> inOrder, ArrayList<Integer> postOrder){

        // Take a stack data structure and push a Pair<root, 1> to it
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()){
            // In each iteration, Pop the Pair from Top of the Stack and check
            // the 'timesSeen' of that Pair/Node
            Pair pair = stack.pop();

            // This means that we are visiting the node for the very first time, it further means its
            // left & right child haven't been visited yet.
            if (pair.timesSeen == 1){
                // On first visit, push the node value to our preorder list (PreOrder-> "Root Left Right")
                preOrder.add(pair.node.data);
                // push the same node with 'timesSeen = 2' (afterwards it will be visited for 2nd time)
                pair.timesSeen++;
                stack.push(pair);

                // After this, we want to visit the left child. Therefore, we make a new pair for left
                // child, i.e, Pair(leftChild, 1) and push it to the stack (if there exists a left child).
                // The value of 'timesSeen' for left child node is equal to 1, because the Left child
                // will be visited for first time
                if (pair.node.left != null)
                    stack.push(new Pair(pair.node.left, 1));
            }

            // This means that we are visiting the node for the second time, it further means its left
            // child node must have been visited. But the right child node haven't been visited
            else if (pair.timesSeen == 2){
                // On second visit, we push the node value to our inorder list (Inorder-> "Left Root Right")
                inOrder.add(pair.node.data);
                // push the same node with 'timesSeen = 3' ((afterwards it will be visited for 3rd time))
                pair.timesSeen++;
                stack.push(pair);

                // After this, we want to visit the right child. Therefore, we make a new pair for right
                // child, i.e, Pair(rightChild, 1) and push it to the stack (if there exists a right child).
                // The value of 'timesSeen' for right child node is equal to 1, because the right child
                // will be visited for first time
                if (pair.node.right != null)
                    stack.push(new Pair(pair.node.right, 1));
            }

            // This means that we are visiting the node for the third time, it further means its left
            // and right child node must have been visited.
            // Therefore, we will push that node’s value to our postorder list.
            // So, we will not push anything else to the stack (because it left & right child has been
            // visited) and return to the parent node back again (stored int top of stack)
            else if (pair.timesSeen == 3)
                postOrder.add(pair.node.data);
        }
    }


    // Pair Class for storing the Node and the count of times that node has been seen till now
    private static class Pair{
        // Store a Node in Binary Tree in the Pair
        BinaryTreeNode<Integer> node;

        // Count of times that node has been seen till now
        int timesSeen;

        public Pair(BinaryTreeNode<Integer> node, int timesSeen) {
            this.node = node;
            this.timesSeen = timesSeen;
        }
    }


    // Binary Tree Node
    private static class BinaryTreeNode<T> {
        public T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }
}
