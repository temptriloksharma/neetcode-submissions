class Solution {
    public int numIslands(char[][] grid) {
        // Handle edge case: empty grid
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islandCount = 0;        // Count of islands found
        int numRows = grid.length;  // Cache rows (avoid repeated grid.length calls)
        int numCols = grid[0].length; // Cache cols (avoid repeated grid[0].length calls)

        // Scan every cell - islands can start anywhere
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Found land! New island discovered
                if (grid[row][col] == '1') {
                    islandCount++;              // Increment island count
                    floodIsland(grid, row, col, numRows, numCols);  // Mark entire island
                }
            }
        }
        
        return islandCount;  // Total islands found
    }
    
    // DFS: Flood fill to mark entire island as explored (change '1' -> '0')
    private void floodIsland(char[][] grid, int row, int col, int numRows, int numCols) {
        // Base case: out of bounds OR already water OR not land
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || grid[row][col] != '1') {
            return;
        }

        // Mark current land cell as explored (change to water)
        grid[row][col] = '0';

        // Recurse to 4 adjacent cells (up, down, left, right)
        // This explores the entire connected island component
        floodIsland(grid, row + 1, col, numRows, numCols);  // down
        floodIsland(grid, row - 1, col, numRows, numCols);  // up
        floodIsland(grid, row, col + 1, numRows, numCols);  // right
        floodIsland(grid, row, col - 1, numRows, numCols);  // left
    }
}