public class TrieNode {
    private final TrieNode[] children = new TrieNode[26];
    boolean endOfWord = false;
    
    private boolean containsKey(char c) {
        return children[c - 'a'] != null;
    }
    
    TrieNode get(char c) {
        return children[c - 'a'];
    }
    
    void put(char c, TrieNode node) {
        children[c - 'a'] = node;
    }
}

class PrefixTree {
    private final TrieNode root;
    
    public PrefixTree() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.endOfWord = true;
    }
    
    private TrieNode findNode(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                return null;
            }
            curr = curr.get(c);
        }
        return curr;
    }
    
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.endOfWord;
    }
    
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }
}