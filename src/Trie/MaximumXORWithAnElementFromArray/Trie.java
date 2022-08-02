package Trie.MaximumXORWithAnElementFromArray;

// Same as "Maximum XOR of two numbers from the array"

public class Trie {
    // Root of the Trie Data structure
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Insert a number (in the form of 32-bit representation) into the trie
    // While inserting the number into the trie, we consider the binary format (Integer – 32bit)
    // and treat it as a string and insert the value.
    public void insert(int num){
        TrieNode node = root;

        for (int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;

            if (!node.containsBit(bit))
                node.putBit(bit, new TrieNode());

            node = node.get(bit);
        }
    }


    // Find Maximum value of XOR of number "x" with all the numbers present in the Trie
    // i.e, max(arr[i] ^ x)
    public int getMaximumXORWithNumber(int num){
        TrieNode node = root;
        int XOR = 0;

        for (int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;

            // For every ith bit find its opposite bit
            // If found, then the XOR output will have the bit at 'ith' position to be set to '1'
            // Also, move to the opposite bit
            if (node.containsBit(1 - bit)){
                XOR = (1 << i) | XOR;
                node = node.get(1 - bit);
            }
            // If not found, then we are bound to take that bit (there is no option)
            // Here we won't update the XOR output as the bits are same & XOR of same bits is always '0'
            else
                node = node.get(bit);
        }
        return XOR;
    }


    // *************************** TrieNode for Trie Data Structure *********************
    public static class TrieNode {
        private final TrieNode[] bitNodes;

        public TrieNode(){
            this.bitNodes = new TrieNode[2];
        }

        public boolean containsBit(int bit){
            return bitNodes[bit] != null;
        }

        public TrieNode get(int bit){
            return bitNodes[bit];
        }

        public void putBit(int bit, TrieNode node){
            bitNodes[bit] = node;
        }
    }
}
