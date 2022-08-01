package Trie.CountDistinctSubstrings;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }


    // Time Complexity  :  O(n^2)
    // Space Complexity :  O(n^2)
    public int countDistinctSubstrings(String word, int n){
        // This will hold the count of unique substrings
        int distinctSubstringCount = 0;

        for (int i = 0; i < n; i++){
            // Start from root node of Trie, each time new Substring is about to start (as new starting point)
            TrieNode node = root;

            for (int j = i; j < n; j++){
                char ch = word.charAt(j);

                // When character is not present, it will become the part of the new Substring.
                // Hence, add it to the trie & increment the count of unique substring count
                if (!node.containsKey(ch)){
                    node.put(ch, new TrieNode());
                    distinctSubstringCount++;
                }
                // Move on to the next character TrieNode
                node = node.get(ch);
            }
        }
        return distinctSubstringCount;
    }
}
