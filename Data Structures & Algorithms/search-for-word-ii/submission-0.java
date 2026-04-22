class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word = null;  // Store full word at end
}

class Solution {
    private final int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        // Insert all words
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new TrieNode();
                }
                node = node.children[i];
            }
            node.word = word;
        }
        
        List<String> result = new ArrayList<>();
        Set<String> found = new HashSet<>();  // Avoid duplicates
        
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, root, r, c, result, found);
            }
        }
        return result;
    }
    
    private void dfs(char[][] board, TrieNode node, int r, int c, 
                     List<String> result, Set<String> found) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || 
            board[r][c] == '#' || node.children[board[r][c] - 'a'] == null) {
            return;
        }
        
        node = node.children[board[r][c] - 'a'];
        if (node.word != null && !found.contains(node.word)) {
            result.add(node.word);
            found.add(node.word);
        }
        
        char temp = board[r][c];
        board[r][c] = '#';
        
        for (int[] d : dirs) {
            dfs(board, node, r + d[0], c + d[1], result, found);
        }
        
        board[r][c] = temp;  // Backtrack
    }
}