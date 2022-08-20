package BinaryTrees.PrintAllTheNodesAtDistanceOfK;
import java.util.*;

// https://youtu.be/i9ORlEy6EsI
// Thinking: Tree are Directed Acyclic Graph (DAG)

public class PrintAllTheNodesAtDistanceOfK_BFS {
    /**************************************** BFS Solution ***********************************************
     * Intuition:
        * We need to find all the nodes at the distance 'k' from the Target Node.
        * Some nodes (at distance k) might also be present above that Target Node, while some nodes will
            be present below the Target node.
        * But in Tree, we can't travel from Child to Parent, but only from Parent to child
        * So, we will convert our Tree to an Undirected Graph, due to this from a Node we can travel
            to its Child Node (below the Tree) as well as to its Parent Node (above the Tree)

     * Steps/Approach:
        * Convert the Tree to an Undirected Graph
        * This can be done by storing the Address of Parent Node of each Child Node (in some Data Structure)
        * A HashMap can be used to point a Node to its Parent Node
        * Now each node will have Three Neighbours: Left child, Right child, Parent Node
        * Observe that in a binary tree a node can be connected to maximum 3 other nodes i.e.
            left child, right child, and the parent.
        * We have left and right pointers, and we have also stored the parent node.
        * Now, we from a Node we can travel to its Child node (via Tree left & right nodes) and Parent Node
            as well (via Parent HashMap)
        * Then do a BFS Traversal starting from the Target Node, and reach all the Tree Nodes at the
            distance of 'k'. Think this step as the simple Graph Traversal

     * Time Complexity : O(n) + O(n) = O(n)
        * We use BFS Traversal to mark the Parent node of all the Nodes in the Tree, this takes O(n) time
        * We use BFS Traversal to find all the node at the distance of 'k' from the Target node
     * Space Complexity: O(n) + O(n) + O(n) = O(n)
        * O(n) Space required for HashMap, to store the Parent node of every node
        * O(n) Space required for HashSet, to account for the visited nodes in BFS to avoid cycles
        * O(n) Space required for BFS Queue
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> distanceK = new ArrayList<>();
        if (root == null)
            return distanceK;

        // Create a map to store the parent of each node in the tree.
        HashMap<TreeNode, TreeNode> parents = markParentNodes(root);

        // Visited Array/Set to keep track of Visited Nodes, (as used in Undirected graph to avoid cycles)
        HashSet<TreeNode> visited = new HashSet<>();

        // Distance variable to keep track of Distance travelled till now, starting from Target
        int distance = 0;

        // Similar to BFS of Undirected Graph
        // Start a BFS Traversal from the Target Node, till we reach at a Distance of 'k' from the Target
        // node. While Traversing, we also mark it visited in the Set
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(target);
        visited.add(target);

        // Travelling till 'k' Edges, starting from the Target Node
        while (distance < k){
            int size = queue.size();

            while (size-- > 0){
                // For the current node
                TreeNode node = queue.remove();

                // Traverse its Left Child Neighbour
                if (node.left != null  &&  !visited.contains(node.left)){
                    queue.add(node.left);
                    visited.add(node.left);
                }
                // Traverse its Right Child Neighbour
                if (node.right != null  &&  !visited.contains(node.right)){
                    queue.add(node.right);
                    visited.add(node.right);
                }
                // Traverse its Parent Node Neighbour
                if (parents.get(node) != null  &&  !visited.contains(parents.get(node))){
                    queue.add(parents.get(node));
                    visited.add(parents.get(node));
                }
            }
            // Increment the Distance from the Target Node, on crossing the Next Tree Edge
            distance++;
        }

        // After travelling till 'k' edges, all node in Queue will be at the Distance of 'k' from the
        // Target node
        while (!queue.isEmpty()){
            TreeNode distantKNode = queue.remove();
            distanceK.add(distantKNode.val);
        }

        return distanceK;
    }


    // Find the Parent node of all the Nodes in Tree, and put the Parent to the HashMap (each node will
    // have only Parent node)
    // Traverse the tree (via Breadth-first search)
    private HashMap<TreeNode, TreeNode> markParentNodes(TreeNode root){
        // HashMap to store the Parent node of each Node
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();

        // Root node will not have any Parent
        parents.put(root, null);

        // BFS Queue for Traversal
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        // Simple BFS
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            // The current node will be the Parent of its Left child. So, mark parent of Left child as
            // current node into the HashMap. Also, Add left child to the Queue
            if (node.left != null){
                parents.put(node.left, node);
                queue.add(node.left);
            }
            // The current node will be the Parent of its Right child. So, mark parent of Right child as
            // current node into the HashMap. Also, Add Right child to the Queue
            if (node.right != null){
                parents.put(node.right, node);
                queue.add(node.right);
            }
        }
        return parents;
    }


    // Tree Node
    static private class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
}