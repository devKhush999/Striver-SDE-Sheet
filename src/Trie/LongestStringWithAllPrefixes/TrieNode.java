package Trie.LongestStringWithAllPrefixes;

public class TrieNode {
    private final TrieNode[] trieNodes;
    private boolean endOfWord;

    public TrieNode() {
        this.trieNodes = new TrieNode[26];
        this.endOfWord = false;
    }

    public boolean containsKey(char ch){
        return trieNodes[ch - 'a'] != null;
    }

    public void put(char ch, TrieNode node){
        trieNodes[ch - 'a'] = node;
    }

    public TrieNode get(char ch){
        return trieNodes[ch - 'a'];
    }

    public void setEndOfWord(){
        this.endOfWord = true;
    }

    public boolean isEndOfWord(){
        return endOfWord;
    }
}
