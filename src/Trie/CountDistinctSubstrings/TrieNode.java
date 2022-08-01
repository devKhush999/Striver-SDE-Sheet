package Trie.CountDistinctSubstrings;

public class TrieNode {
    private final TrieNode[] trieNodes;

    public TrieNode() {
        this.trieNodes = new TrieNode[26];
    }

    public boolean containsKey(char ch){
        return trieNodes[ch - 'a'] != null;
    }

    public void put(char ch, TrieNode node){
        trieNodes[ch - 'a'] = node;
    }

    public TrieNode get(char ch){
        return trieNodes[ch -'a'];
    }
}
