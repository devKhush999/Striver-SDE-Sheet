package BinaryTrees.VerticalOrderTraversal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

// https://youtu.be/q_a6lpbKJdw
// GREAT READING: https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
// https://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/

public class VerticalOrderTraversal_V2 {
    /****************************** Efficient "DFS + TreeMap" Solution **********************************
     * Approach & Thought Process (Same as before):
        * First, we need to assign a vertical and a level to every node.
        * Once we assign them, we need to figure out a suitable data structure to store them.
        * This data structure should give us the nodes with left-side vertical first and in every
            vertical, top-level node should appear first.
        * Idea is to store all the nodes in increasing order of their verticals (vertical no.)
        * For Tree nodes in same Verticals, we store them in increasing order of their Levels (level no.)
        * For Tree nodes in same Verticals & Levels, we store them in order of sorted values
        * If we think carefully, a TreeMap can serve this purpose, as TREEMAP STORE KEYS IN SORTED ORDER

     * Data Structure Used:  "TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>"
        * First (outer) TreeMap will map the vertices (in sorted order) to another TreeMap.
        * First (outer) TreeMap store nodes according to their vertical value and give us the nodes
            with the least vertical values first.
        * Second (inner) TreeMap will map the Levels in same Verticals (in sorted order) to PriorityQueue.
        * In a single vertical we want to get the nodes by their increasing levels. So, we will again
            use TreeMap (inner) to store nodes level wise inside the same Vertical.
        * So, basically each "Vertical in outer TreeMap" has another "inner TreeMap to order Levels
            in Sorted order"
        * Each inner TreeMap to store levels in sorted order, has a Priority Queue to store nodes in
            increasing order in case their Vertical & Levels are same.
        * There can be a case that two or more nodes overlap at the same vertical and level. In such a
            case we will print nodes with lesser value first. So, PriorityQueue can store node values
            in a sorted order.
        * This will do exactly same thing as Previous Priority Queue Solution


     * Time Complexity: O(2 * n * log(n))  ~  O(n * log(n))
        * We are first doing DFS Traversal for Assigning vertical and level to every node that will take
            O(n*log(n)) time (as we will also add nodes in our TreeMap as well)
        * Then we again traverse all the Nodes as per order in TreeMap, this
            will also take O(n*log(n)) time.
     * Space Complexity: O(n) + O(n)  ~  O(n)
        * O(n) for Space required for Recursion Stack used by DFS Traversal
        * Size of Data Structure "TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>" will be
            O(n) as it will store information of 'n' Nodes
     */
    public List<List<Integer>> verticalTraversal_UsingDFS(TreeNode root) {
        List<List<Integer>> verticalOrderTraversal = new ArrayList<>();
        if (root == null)
            return verticalOrderTraversal;

        // Our Data Structure used for this purpose
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> sortedVerticalMap = new TreeMap<>();

        // DFS Traversal for Assigning vertical and level to every node
        dfsPreOrder(root, 0, 0, sortedVerticalMap);

        // Printing vertical order traversal from our data structure
        // We iterate over our verticals (in Sorted Order) by using the above data structure
        for (int sortedColumns : sortedVerticalMap.keySet()){
            TreeMap<Integer, PriorityQueue<Integer>> sortedLevelsMap = sortedVerticalMap.get(sortedColumns);

            //  In every iteration, we take a list to store all nodes of that vertical
            List<Integer> currentVertical = new ArrayList<>();
            verticalOrderTraversal.add(currentVertical);

            // And again iterate over the levels
            for (int sortedRows : sortedLevelsMap.keySet()){
                PriorityQueue<Integer> nodesInSameVerticalAndLevel = sortedLevelsMap.get(sortedRows);

                // We then push the nodes of the same level & same Vertical
                while (!nodesInSameVerticalAndLevel.isEmpty())
                    currentVertical.add(nodesInSameVerticalAndLevel.remove());
            }
        }
        return verticalOrderTraversal;
    }


    // DFS (Pre-Order) Traversal for Assigning vertical and level to every node
    public void dfsPreOrder(TreeNode root, int level, int vertical,
                            TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> sortedVerticalMap){
        if (root == null)
            return;

        // Get all the Levels in the Same Vertical (this will return all the Levels in the current vertical)
        TreeMap<Integer, PriorityQueue<Integer>> sortedLevelsMap = sortedVerticalMap.get(vertical);

        // Handle the case if Levels (in current Verticals) are null
        if (sortedLevelsMap == null){
            sortedLevelsMap = new TreeMap<>();
            sortedVerticalMap.put(vertical, sortedLevelsMap);
        }

        // Get all the Nodes in the Same Vertical & Levels (this will return all the Nodes in the
        // current vertical & level)
        PriorityQueue<Integer> nodesInSameVerticalAndLevel = sortedLevelsMap.get(level);

        // Handle the case if Nodes (in current Verticals & level) are null
        if (nodesInSameVerticalAndLevel == null){
            nodesInSameVerticalAndLevel = new PriorityQueue<>();
            sortedLevelsMap.put(level, nodesInSameVerticalAndLevel);
        }

        nodesInSameVerticalAndLevel.add(root.val);

        dfsPreOrder(root.left, level + 1, vertical - 1, sortedVerticalMap);
        dfsPreOrder(root.right, level + 1, vertical + 1, sortedVerticalMap);
    }


    // ********************************* Tree Node Structure *********************************
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int data) {
            this.val = data;
        }
    }
}
