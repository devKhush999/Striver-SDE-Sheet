package BinaryTrees.TopViewOfBinaryTree;

import java.util.*;

// PRE-REQUISITE: "VERTICAL ORDER TRAVERSAL OF BINARY TREE"
// https://youtu.be/Et9OCDNvJ78
// https://takeuforward.org/data-structure/top-view-of-a-binary-tree/
// https://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/

public class TopViewOfBinaryTree {
    /********************************** Efficient TreeMap Solution **********************************
     * Intuition:
        * We group all the TreeNode in the Set of 'Verticals' like we did in problem
            "VERTICAL ORDER TRAVERSAL OF BINARY TREE"
        * The first Tree node in every vertical will be inside the Top view.

     * Approach:
        * We will do the BFS Traversal, along with maintaining Vertical number of every node.
        * We just have to check that, Is this current node the first node seen in that Vertical?
            OR, Did we saw any TreeNode in that Vertical earlier?
        * If the answer is Yes, then current node is part of our Top View.
        * Else, we have seen a node in that Vertical before. So, current node is not part of our Top View.
        * After BFS is done, we can add Tree nodes in the increasing order of Verticals.
        * A "TreeMap" cane be used easily, as the keys() in TreeMap are arranged in sorted order.

     * Time Complexity: O(n * log(n))
        * We would traverse all the nodes in Tree only once
        * We are also adding nodes to TreeMap, that takes log(n) for key updation & key retrieval
        * In worst case of skewed Tree, all 'n' can be included in Binary Tree
     * Space Complexity: O(n/2) ~ O(n)
        * Due to Queue used in BFS Traversal, whose max. size can be O(n/2) in last level, in worst case.
     */
    public static ArrayList<Integer> getTopView_TreeMap(BinaryTreeNode root) {
        ArrayList<Integer> topView = new ArrayList<>();
        if (root == null)
            return topView;

        // We need a Map data structure to store the Vertical Lines (Vertical no.) and the
        // first Tree node in that Vertical line. This map will store the data in the form of
        // sorted orders of keys (Vertical Lines). So, TreeMap is used
        TreeMap<Integer, BinaryTreeNode> topViewMap = new TreeMap<>();

        // Queue of pair which have Tree Nodes and their respective Vertical numbers
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 0));       // Add root node and its Vertical number (which is 0) to Queue

        // Do a BFS
        while (!queue.isEmpty()){
            Node node = queue.remove();

            // If the current Node's Vertical number doesn't contain any first node (top node) yet.
            // Then, mark the current node as first node (top node) of that Vertical number
            // Check if that Vertical line is present in the TreeMap or not
            // If not present then store that Vertical line and the node->value to the map
            if (!topViewMap.containsKey(node.vertical))
                topViewMap.put(node.vertical, node.treeNode);

            // Add the Left & Right child node to Queue along with their respective Vertical number
            // For Left child, Vertical no. -> node's_vertical_number - 1
            // For Right child, Vertical no. -> node's_vertical_number + 1
            if (node.treeNode.left != null)
                queue.add(new Node(node.treeNode.left, node.vertical - 1));

            if (node.treeNode.right != null)
                queue.add(new Node(node.treeNode.right, node.vertical + 1));
        }

        // Extract all the Vertical number from the TreeMap one by one in sorted order.
        // And add the Top node (first node) of that vertical no. to the 'TopView' List
        for (int vertical : topViewMap.keySet())
            topView.add(topViewMap.get(vertical).val);

        return topView;
    }


    /******************************** Most Efficient HashMap Solution ********************************
     * The time Complexity of previous Solution is O(n * log(n)) in worst case. Can we improve this?
     * The expensive operation we were doing is to maintain the Vertical numbers in sorted order.
     * Do we really need to maintain so?
     * Do we need to maintain TreeMap for sorted Vertical numbers?
     * Can't the Arbitrary Vertical no. order given by HashMap work for us?

     * Intuition:
        * If we can figure out the "Least possible Vertical number" of any node in the Tree, then
            we don't really need to maintain the Vertical numbers in sorted order.
        * Successive Vertical numbers will differ only by one (1), starting from "Least Vertical number",
            i.e, Next_Vertical_Number = Previous_Vertical_Number + 1
        * This is bcoz, if we have a vertical number < 0, then there must exist a parent TreeNode with
            Vertical number greater than it. Think...
        * Similarly, if we have a vertical number > 0, then there must exist a parent TreeNode with
            Vertical number lesser than it. Think...
        * Therefore, we can just keep track of "Least possible Vertical number" of all Vertical number,
            and traverse all the Vertical number one by one.
        * So, a HashMap can be used.

     * Time Complexity: O(n)
        * We would traverse all the nodes in Tree only once
        * We are also adding nodes to HashMap, that takes O(1) for key updation & key retrieval on average
        * So, with 'n' nodes in Tree, Time Complexity is O(1)
     * Space Complexity: O(n/2) ~ O(n)
         * Due to Queue used in BFS Traversal, whose max. size can be O(n/2) in last level, in worst case.
     */
    public static ArrayList<Integer> getTopView_HashMap(BinaryTreeNode root) {
        ArrayList<Integer> topView = new ArrayList<>();
        if (root == null)
            return topView;

        // We need a Map data structure to store the Vertical Lines (Vertical no.) and the
        // first Tree node in that Vertical line. Order of Vertical numbers doesn't matter.
        // So, TreeMap is used
        HashMap<Integer, BinaryTreeNode> topViewMap = new HashMap<>();

        // Keep track of "Least possible Vertical number" seen.
        int leastVertical = Integer.MAX_VALUE;

        // Queue of pair which have Tree Nodes and their respective Vertical numbers
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 0));     // Add root node and its Vertical number (which is 0) to Queue

        // Do a BFS
        while (!queue.isEmpty()){
            Node node = queue.remove();

            // If the current Node's Vertical number doesn't contain any first node (top node) yet.
            // Then, mark the current node as first node (top node) of that Vertical number.
            // Check if that Vertical line is present in the HashMap or not?
            // If not present then store that Vertical line and the node->value to the map
            if (!topViewMap.containsKey(node.vertical))
                topViewMap.put(node.vertical, node.treeNode);

            // Update the least Vertical number
            leastVertical = Math.min(leastVertical, node.vertical);

            // Add the Left & Right child node to Queue along with their respective Vertical number
            // For Left child, Vertical no. -> node's_vertical_number - 1
            // For Right child, Vertical no. -> node's_vertical_number + 1
            if (node.treeNode.left != null)
                queue.add(new Node(node.treeNode.left, node.vertical - 1));

            if (node.treeNode.right != null)
                queue.add(new Node(node.treeNode.right, node.vertical + 1));
        }

        // Starting from the Least Vertical number, add the Top node (first node) of all
        // vertical no. to the 'TopView' List
        int vertical = leastVertical;
        while (topViewMap.containsKey(vertical)){
            topView.add(topViewMap.get(vertical).val);
            vertical++;
        }

        return topView;
    }


    // ***************************** Pair Class for Binary Tree Node ************************************
    // To store the TreeNode and its Vertical column (in which that node lies) together
    private static class Node{
        BinaryTreeNode treeNode;
        int vertical;

        public Node(BinaryTreeNode treeNode, int vertical) {
            this.treeNode = treeNode;
            this.vertical = vertical;
        }
    }

    // ***************************** Binary Tree Node Structure ************************************
    private static class BinaryTreeNode {
        int val;
        BinaryTreeNode left, right;

        BinaryTreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
