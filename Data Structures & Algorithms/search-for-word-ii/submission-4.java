class Solution {

    // Trie node for storing prefixes of all words.
    // If 'word' is not null, that means a complete word ends at this node.
    private static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        // Build a Trie from all input words so that during DFS
        // we can quickly prune paths that do not match any prefix.
        TrieNode root = buildTrie(words);

        List<String> result = new ArrayList<>();

        // Start DFS from every cell because any cell can be the
        // starting point of a valid word.
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, root, r, c, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        // Insert every word into the Trie.
        for (String word : words) {
            insertWord(root, word);
        }

        return root;
    }

    private void insertWord(TrieNode root, String word) {
        TrieNode curr = root;

        // Traverse/create Trie nodes for each character in the word.
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }

        // Store the full word at the terminal node.
        // This lets us directly add the word to result during DFS.
        curr.word = word;
    }

    private void dfs(char[][] board, TrieNode node, int r, int c, List<String> result) {
        // Stop if cell is outside board boundaries.
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return;
        }

        char ch = board[r][c];

        // Stop if:
        // 1. cell is already used in current DFS path, or
        // 2. current character does not continue any Trie prefix.
        if (ch == '#' || !node.children.containsKey(ch)) {
            return;
        }

        // Move to the next Trie node for this character.
        TrieNode curr = node.children.get(ch);

        // If a complete word ends here, add it once.
        // Then set curr.word = null so we do not add duplicates again.
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }

        // Mark current cell as visited so we do not reuse it
        // in the same word path.
        board[r][c] = '#';

        // Explore all 4 neighboring cells.
        dfs(board, curr, r + 1, c, result); // down
        dfs(board, curr, r - 1, c, result); // up
        dfs(board, curr, r, c + 1, result); // right
        dfs(board, curr, r, c - 1, result); // left

        // Restore the original character while backtracking
        // so this cell can be used in other DFS paths.
        board[r][c] = ch;
    }
}