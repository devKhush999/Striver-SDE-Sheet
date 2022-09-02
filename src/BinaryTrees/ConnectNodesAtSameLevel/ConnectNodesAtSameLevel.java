package BinaryTrees.ConnectNodesAtSameLevel;
import java.util.ArrayDeque;
import java.util.Queue;

// https://www.codingninjas.com/codestudio/problems/connect-nodes-at-same-level_985347

public class ConnectNodesAtSameLevel {
    /************************************ Efficient BFS Solution *************************************
     * Solution:
        Traverse all the levels one by one, for each level assign the 'next' pointer of previous node
        to current node.
        Basically, convert each level into a linked-list using 'next' pointer

     * Time Complexity: O(n)                  BFS takes O(n) Time
     * Space Complexity: O(n/2) ~ O(n)        BFS takes O(n) space
     */
    public void connectNodes_BFS(TreeNode root) {
        if (root == null)
            return;

        // BFS Queue
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        // Simple BFS
        while (!queue.isEmpty()){
            int size = queue.size();

            // Keep track of Linked-list in the same level
            TreeNode tail = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();

                if (node.left != null)
                    queue.add(node.left);

                if (node.right != null)
                    queue.add(node.right);

                // Attach 'next' pointer of previous 'tail node' to the current node, and make 'tail' node
                // to current node
                if (tail != null)
                    tail.next = node;
                tail = node;
            }
        }
    }


    /************************************ Efficient DFS Solution *************************************
     * Solution:
        * Traverse two nodes (l1 & l2) in the same level one at a time,
        * Connect it to the another node by 'next' pointer
        * Also, we have to connect it in three ways:
            * Connect the Left of l1 & Right of l1 in same fashion
            * Connect the Left of l2 & Right of l2 in same fashion
            * Connect the Right of l1 & Left of l1 in same fashion
            * Think... and Do dry Run

     * Time Complexity: O(n)                     DFS takes O(n) Time
     * Space Complexity: O(Tree's Height)        DFS takes O(H) space
     */
    public TreeNode connect(TreeNode root) {
        if (root == null)
            return null;

        connect(root.left, root.right);
        return root;
    }

    public void connect(TreeNode l1, TreeNode l2){
        if (l1 == null || l2 == null)
            return;

        l1.next = l2;
        connect(l1.left, l1.right);
        connect(l2.left, l2.right);
        connect(l1.right, l2.left);
    }


    // TreeNode
    private static class TreeNode {
        public int data;
        public TreeNode left, right, next;
        TreeNode(int data) {
            this.data = data;
        }
    }
}
