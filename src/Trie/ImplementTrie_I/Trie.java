package Trie.ImplementTrie_I;

// https://youtu.be/dBGUmUQhjaM
// https://takeuforward.org/data-structure/implement-trie-1/
// https://www.geeksforgeeks.org/trie-insert-and-search/

public class Trie {
    private final TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }

    //Inserts a word into the trie
    // Time Complexity: O(n)    where n is length of word
    public void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                node.put(ch, new TrieNode());

            node = node.get(ch);
        }
        node.setWordEnd();
    }


    //Returns if the word is in the trie
    // Time Complexity: O(n)    where n is length of word
    public boolean searchWord(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                return false;

            node = node.get(ch);
        }
        return node.isWordEnd();
    }


    //Returns if there is any word in the trie that starts with the given prefix
    // Time Complexity: O(n)    where n is length of prefix word
    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()){
            if (!node.containsKey(ch))
                return false;

            node = node.get(ch);
        }
        return true;
    }
}


class TrieNode{
    // Each TrieNode in array represents a possible alphabetical character
    private final TrieNode[] trieNodes;

    // "wordEnd" is true if the node represents end of a word
    private boolean wordEnd;

    public TrieNode() {
        this.trieNodes = new TrieNode[26];
        this.wordEnd = false;
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

    public boolean isWordEnd() {
        return wordEnd;
    }

    public void setWordEnd() {
        this.wordEnd = true;
    }
}
