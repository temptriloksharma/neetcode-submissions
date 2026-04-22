class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word = null;  // Only this needed
}

class Solution {
    private final int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        // Build Trie (O(total chars))
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.computeIfAbsent(ch, k -> new TrieNode());
                node = node.children.get(ch);
            }
            node.word = word;
        }
        
        List<String> result = new ArrayList<>();
        Set<String> found = new HashSet<>();
        
        // DFS from each cell
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, root, r, c, result, found);
            }
        }
        return result;
    }
    
    private void dfs(char[][] board, TrieNode node, int r, int c, 
                     List<String> result, Set<String> found) {
        // Prune invalid moves
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || 
            board[r][c] == '#' || !node.children.containsKey(board[r][c])) {
            return;
        }
        
        // Advance in Trie
        node = node.children.get(board[r][c]);
        
        // Check if word found
        if (node.word != null && !found.contains(node.word)) {
            result.add(node.word);
            found.add(node.word);
        }
        
        // Backtrack
        char temp = board[r][c];
        board[r][c] = '#';
        
        for (int[] d : dirs) {
            dfs(board, node, r + d[0], c + d[1], result, found);
        }
        
        board[r][c] = temp;
    }
}