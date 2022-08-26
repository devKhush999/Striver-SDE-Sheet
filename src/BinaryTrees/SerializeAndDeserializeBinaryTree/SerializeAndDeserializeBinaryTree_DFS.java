package BinaryTrees.SerializeAndDeserializeBinaryTree;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree_DFS {
    /************************************* Serialize ********************************************
     * Technique used: Pre-Order DFS Traversal
     * To recover the Tree entirely, we will also add the 'null' nodes in Pre-order Traversal of Tree

     * Time Complexity: O(n)                   DFS takes O(n) time
     * Space Complexity: O(Tree's Height)      Recursion Stack Space
     */
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();

        // Pre-order Traversal from root to convert all the Nodes to a String
        preorder(root, sb);

        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null){
            // To recover the Tree entirely, we will also add the 'null' nodes in Pre-order Traversal
            sb.append("null,");
            return;
        }
        // Add the node value
        sb.append(node.val + ",");
        preorder(node.left, sb);                // Recursion for Left Subtree
        preorder(node.right, sb);               // Recursion for Right Subtree
    }


    /************************************* Deserialize ********************************************
     * Technique used: Pre-Order DFS Traversal
     * We will Traverse the given Pre-order Traversal, in the Pre-order Traversal itself to create the
     new Binary Tree

     * Time Complexity: O(n)                   DFS takes O(n) time
     * Space Complexity: O(Tree's Height)      Recursion Stack Space
     */
    public TreeNode deserialize(String data) {
        if (data.equals(""))        // Edge case
            return null;

        // Pre-order traversal
        String[] preOrder = data.split(",");

        // Queue for Retrieving the next element in Pre-order traversal ony by one
        Queue<Integer> queue = new LinkedList<>();
        // Adding Pre-order traversal to "Pre_Order_Queue"
        for (String node : preOrder){
            if (node.equals("null"))
                queue.add(null);
            else
                queue.add(Integer.parseInt(node));
        }

        // We will Traverse the given Pre-order Traversal, in the Pre-order Traversal itself to
        // create the new Binary Tree
        TreeNode root = preOrder(queue);
        return root;
    }

    private TreeNode preOrder(Queue<Integer> preOrderQueue){
        Integer value = preOrderQueue.remove();

        if (value == null)
            return null;

        // Initialise the current node in the order of Pre-order traversal
        TreeNode node = new TreeNode(value);

        node.left = preOrder(preOrderQueue);            // Get the Left Child
        node.right = preOrder(preOrderQueue);           // Get the Right Child
        return node;
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

/**
 * We can also use In-order Traversal OR Post-Order Traversal for this Question

 * Conclusion:
    * We can construct a Binary Tree using just one Traversal (be it Level order, Pre-order,
        Post-order, In-order), iff we get information about all the Nodes as well as their 'null'
        Child nodes (if any) also.
 * In this way, a Tree can be constructed without any confusion
 */
