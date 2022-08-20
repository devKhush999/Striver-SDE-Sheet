package BinaryTrees.PrintAllTheNodesAtDistanceOfK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// https://youtu.be/i9ORlEy6EsI
// Thinking: Tree are Directed Acyclic Graph (DAG)

public class PrintAllTheNodesAtDistanceOfK_DFS {
    /**************************************** DFS Solution ***********************************************
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
        * Then do a DFS Traversal starting from the Target Node, and reach all the Tree Nodes at the
            distance of 'k'. Think this step as the simple Graph Traversal

     * Time Complexity : O(n) + O(n) = O(n)
        * We use DFS Traversal to mark the Parent node of all the Nodes in the Tree, this takes O(n) time
        * We use DFS Traversal to find all the node at the distance of 'k' from the Target node
     * Space Complexity: O(n) + O(n) + O(n) = O(n)
        * O(n) Space required for HashMap, to store the Parent node of every node
        * O(n) Space required for HashSet, to account for the visited nodes in DFS to avoid cycles
        * O(n) Space required for DFS
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> distanceK = new ArrayList<>();
        if (root == null)
            return distanceK;

        // Create a map to store the parent of each node in the tree.
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();

        // Find the Parent node of all the Nodes in Tree
        markParentNodes_dfs(root, null, parents);

        // HashSet to account for the visited nodes in Tree, which is now Undirected Graph
        HashSet<TreeNode> visited = new HashSet<>();

        // Start a DFS Traversal from the Target Node, till we reach at a Distance of 'k' from the Target
        // node.
        findDistantKNodes_dfs(target, 0, k, visited, parents, distanceK);

        return distanceK;
    }


    // Similar to DFS of Undirected Graph. (While Traversing, we also mark it visited in the Set)
    private void findDistantKNodes_dfs(TreeNode node, int distance, int k, HashSet<TreeNode> visited,
                                       HashMap<TreeNode, TreeNode> parents, ArrayList<Integer> distanceK){
        if (node == null)
            return;

        // Travelling till 'k' Edges, starting from the Target Node. Once we traversed k edges, current
        // node will be at the distance of k from the Target
        if (distance == k){
            distanceK.add(node.val);
            return;
        }
        // Mark the current node as visited in DFS Set
        visited.add(node);

        // Traverse its Left Child Neighbour
        if (node.left != null  &&  !visited.contains(node.left))
            findDistantKNodes_dfs(node.left, distance + 1, k, visited, parents, distanceK);

        // Traverse its Right Child Neighbour
        if (node.right != null  &&  !visited.contains(node.right))
            findDistantKNodes_dfs(node.right, distance + 1, k, visited, parents, distanceK);

        // Traverse its Parent Node Neighbour
        if (parents.get(node) != null  &&  !visited.contains(parents.get(node)))
            findDistantKNodes_dfs(parents.get(node), distance + 1, k, visited, parents, distanceK);
    }


    // Find the Parent node of all the Nodes in Tree, and put the Parent to the HashMap (each node will
    // have only Parent node)
    // Traverse the tree (via Depth-first search)
    private void markParentNodes_dfs(TreeNode node, TreeNode parent, HashMap<TreeNode, TreeNode> parents){
        if (node == null) return;

        // We will pass both the current node and its parent node
        // Mark/Put the Parent of current node in the HashMap
        parents.put(node, parent);

        // Call DFS for Left Child with current node as Parent
        markParentNodes_dfs(node.left, node, parents);
        // Call DFS for Right Child with current node as Parent
        markParentNodes_dfs(node.right, node, parents);
    }

    static private class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
}