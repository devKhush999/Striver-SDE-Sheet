package BinaryTrees.BottomViewOfBinaryTree;

import java.util.*;

// PRE-REQUISITE: "TOP VIEW OF BINARY TREE"
// https://youtu.be/0FtVY6I4pB8
// https://takeuforward.org/data-structure/bottom-view-of-a-binary-tree/

public class BottomViewOfBinaryTree {
    /********************************** Efficient TreeMap Solution **********************************
     * Intuition:
        * We group all the TreeNode in the Set of 'Verticals' like we did in problem
            "TOP VIEW OF BINARY TREE"
        * The Last Tree node in every vertical will be inside the Bottom view.
        * The logic for top view and bottom view is exactly same.
            Just use map.putIfAbsent(line,node.val) for Top view and map.put(line,node.val) for
            Bottom view.
        * In top view, we put the node on a Vertical (on the map) only once, because all other nodes
            on lower level are hidden.
        * In bottom view, we replace the previous node on same vertical, because we got another node
            on lower node that hides the upper node.

     * Approach:
        * We will do the BFS Traversal, along with maintaining Vertical number of every node.
        * We just have to check that, Is this current node, the Last node seen in that Vertical?
        * Whenever we encountered any TreeNode, we just mark that node as the last Node in that vertical.
        * If, that node is actually the last node in that vertical, then it's ok.
        * Else, if that node is not the last node in that vertical, then we will visit it later during BFS
            So, it can be added later. See Code Description.
        * After BFS is done, we can add Tree nodes in the increasing order of Verticals.
        * A "TreeMap" cane be used easily, as the keys() in TreeMap are arranged in sorted order.

     * Time Complexity: O(n * log(n))       in worst case
        * We would traverse all the nodes in Tree only once
        * We are also adding nodes to TreeMap, that takes log(n) for key updation & key retrieval
        * In worst case of skewed Tree, all 'n' can be included in Binary Tree
     * Space Complexity: O(n/2) ~ O(n)
        * Due to Queue used in BFS Traversal, whose max. size can be O(n/2) in last level, in worst case.
     */
    public ArrayList<Integer> bottomView_TreeMap(BinaryTreeNode root) {
        ArrayList<Integer> bottomView = new ArrayList<>();
        if (root == null)
            return bottomView;

        // We need a Map data structure to store the Vertical Lines (Vertical no.) and the
        // Last Tree node in that Vertical line. This map will store the data in the form of
        // sorted orders of keys (Vertical Lines). So, TreeMap is used
        TreeMap<Integer, BinaryTreeNode> bottomViewMap = new TreeMap<>();

        // Queue of pair which have Tree Nodes and their respective Vertical numbers
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 0));

        // Do a BFS
        while (!queue.isEmpty()){
            Node node = queue.remove();

            // If the current Node's Vertical number doesn't contain any Last node (Bottom node) yet.
            // Then, mark the current node as Last node (Bottom Node) of that Vertical number
            // Two cases can arise:
            // 1) If current Vertical no. is not present in TreeMap, then store that Vertical line
            //    and the node->value to the map, indicating it is the Bottom-most node in that vertical.
            // 2) Else if current Vertical no. is present in TreeMap, then previous node in that vertical
            //    is not the Bottom-most node, because we are already travelling Level wise (BFS) & we
            //    encountered a new node in the same Vertical. So, it must be the Bottom node & not the previous one.
            bottomViewMap.put(node.vertical, node.treeNode);

            // Add the Left & Right child node to Queue along with their respective Vertical number
            // For Left child, Vertical no. -> node's_vertical_number - 1
            // For Right child, Vertical no. -> node's_vertical_number + 1
            if (node.treeNode.left != null)
                queue.add(new Node(node.treeNode.left, node.vertical  -1));

            if (node.treeNode.right != null)
                queue.add(new Node(node.treeNode.right, node.vertical + 1));
        }

        // Extract all the Vertical number from the TreeMap one by one in sorted order.
        // And add the Bottom node (last node) of that vertical no. to the 'TopView' List
        for (int verticals : bottomViewMap.keySet())
            bottomView.add(bottomViewMap.get(verticals).val);

        return bottomView;
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
    public ArrayList<Integer> bottomView_HashMap(BinaryTreeNode root) {
        ArrayList<Integer> bottomView = new ArrayList<>();
        if (root == null)
            return bottomView;

        // Keep track of "Least possible Vertical number" seen.
        int leastVertical = Integer.MAX_VALUE;

        // We need a Map data structure to store the Vertical Lines (Vertical no.) and the
        // Last Tree node in that Vertical line. Order of Vertical numbers doesn't matter.
        // So, TreeMap is used
        HashMap<Integer, BinaryTreeNode> bottomViewMap = new HashMap<>();

        // Queue of pair which have Tree Nodes and their respective Vertical numbers
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 0));     // Add root node and its Vertical number (which is 0) to Queue

        // Do a BFS
        while (!queue.isEmpty()){
            Node node = queue.remove();

            // If the current Node's Vertical number doesn't contain any Last node (Bottom node) yet.
            // Then, mark the current node as Last node (Bottom Node) of that Vertical number
            // Two cases can arise:
            // 1) If current Vertical no. is not present in HashMap, then store that Vertical line and
            //    the node->value to the map, indicating it is the Bottom-most node in that vertical
            // 2) Else if current Vertical no. is  present in HashMap, then previous node in that vertical
            //    is not the Bottom-most node, because we are already travelling Level wise (BFS) & we
            //    encountered a new node in the same Vertical. So, it must be the Bottom node & not the previous one.
            bottomViewMap.put(node.vertical, node.treeNode);

            // Update the least Vertical number
            leastVertical = Math.min(leastVertical, node.vertical);

            // Add the Left & Right child node to Queue along with their respective Vertical number
            // For Left child, Vertical no. -> node's_vertical_number - 1
            // For Right child, Vertical no. -> node's_vertical_number + 1
            if (node.treeNode.left != null)
                queue.add(new Node(node.treeNode.left, node.vertical  -1));

            if (node.treeNode.right != null)
                queue.add(new Node(node.treeNode.right, node.vertical + 1));
        }

        // Starting from the Least Vertical number, add the Bottom node (Last node) of all
        // vertical no. to the 'TopView' List
        while (bottomViewMap.containsKey(leastVertical)){
            bottomView.add(bottomViewMap.get(leastVertical).val);
            leastVertical++;
        }
        return bottomView;
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
