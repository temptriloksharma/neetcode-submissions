class WordDictionary {
    private static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean endOfWord = false;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }

    private boolean dfs(TrieNode node, String word, int i) {
        if (i == word.length()) {
            return node.endOfWord;
        }
        char c = word.charAt(i);
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (dfs(child, word, i + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            TrieNode child = node.children.get(c);
            return child != null && dfs(child, word, i + 1);
        }
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }
}