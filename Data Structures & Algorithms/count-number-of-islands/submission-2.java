class Solution {
    
    // 4-directional moves: up, right, down, left
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int numIslands(char[][] grid) {
        // Edge cases: null/empty/zero-width
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        final int ROWS = grid.length;
        final int COLS = grid[0].length;
        int islands = 0;
        
        // Grid scan
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == '1') {
                    islands++;  // New island
                    bfs(grid, r, c, ROWS, COLS);  // Flood from source
                }
            }
        }
        
        return islands;
    }
    
    /**
     * BFS: Queue-based flood fill. Marks island as '0'.
     */
    private void bfs(char[][] grid, int startR, int startC, int ROWS, int COLS) {
        // Queue stores cell coordinates as [row, col]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        grid[startR][startC] = '0';  // Sink source
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            // Explore 4 neighbors
            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Valid land neighbor?
                if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && grid[nr][nc] == '1') {
                    grid[nr][nc] = '0';  // Sink
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}