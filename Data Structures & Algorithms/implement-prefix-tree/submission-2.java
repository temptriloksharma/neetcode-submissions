class PrefixTree {
    private static class TrieNode {
        private final HashMap<Character, TrieNode> children = new HashMap<>();
        private boolean endOfWord = false;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }

    private TrieNode findNode(String str) {
        TrieNode curr = root;
        for (char c : str.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) return null;
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