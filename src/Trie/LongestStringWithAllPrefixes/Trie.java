package Trie.LongestStringWithAllPrefixes;

public class Trie {
    // Root Node of the Trie
    private final TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }


    // Insert a word into the Trie
    // Time Complexity: O(length(word))
    public void insert(String word){
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                node.put(ch, new TrieNode());

            node = node.get(ch);
        }
        node.setEndOfWord();
    }


    // Function to Check whether the "Trie" contains all the Prefix of the "word"
    // Time Complexity: O(length(word))
    public boolean containsAllPrefixOfWord(String word){
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                return false;

            node = node.get(ch);

            if (!node.isEndOfWord())
                return false;
        }
        return true;
    }
}
