package Trie.ImplementTrie_II;

// https://youtu.be/K5pcpkEMCN0
// https://takeuforward.org/data-structure/implement-trie-ii/
// https://www.codingninjas.com/codestudio/problems/implement-trie_1387095

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Time Complexity: O(length(word))
    public void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                node.put(ch, new TrieNode());

            node = node.get(ch);
            node.incrementPrefixCount();
        }
        node.incrementCountOfEndWord();
    }


    // Time Complexity: O(length(word))
    public int countWordsEqualTo(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                return 0;

            node = node.get(ch);
        }
        return node.getCountOfEndWord();
    }


    // Time Complexity: O(length(word))
    public int countWordsStartingWith(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                return 0;

            node = node.get(ch);
        }
        return node.getPrefixCount();
    }


    // Time Complexity: O(length(word))
    public void deleteWord(String word) {
        // If Trie doesn't contain the word, simply return
        if (!this.containsWord(word)) {
            return;
        }
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            node = node.get(ch);
            node.decrementPrefixCount();
        }
        node.decrementCountOfEndWord();
    }


    // Time Complexity: O(length(word))
    public boolean containsWord(String word){
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                return false;

            node = node.get(ch);
        }
        return node.getCountOfEndWord() > 0;
    }
}


// Trie Node
class TrieNode {
    private final TrieNode[] trieNodes;
    private int prefixCount;
    private int countOfEndWord;

    public TrieNode() {
        this.trieNodes = new TrieNode[26];
        prefixCount = 0;
        countOfEndWord = 0;
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

    public void incrementPrefixCount(){
        prefixCount++;
    }

    public void incrementCountOfEndWord(){
        countOfEndWord++;
    }

    public void decrementPrefixCount(){
        prefixCount--;
    }

    public void decrementCountOfEndWord(){
        countOfEndWord--;
    }

    public int getPrefixCount() {
        return prefixCount;
    }

    public int getCountOfEndWord() {
        return countOfEndWord;
    }
}
