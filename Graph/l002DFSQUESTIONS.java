public class l002DFSQUESTIONS {
    // ======================================leetcode
    // 200=====================================
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int cnt = 0;
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (vis[i][j] == false && grid[i][j] == '1') {
                    cnt++;
                    dfs(grid, vis, i, j);
                }
            }
        }
        return cnt;
    }

    public static void dfs(char[][] grid, boolean[][] vis, int i, int j) {

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && vis[r][c] == false && grid[r][c] == '1') {
                vis[r][c] = true;
                dfs(grid, vis, r, c);

            }
        }
    }

    // ===========================LEETCODE 695=============================
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxArea = 0;
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == false && grid[i][j] == 1) {
                    int area = dfs(grid, vis, i, j);
                    maxArea = Math.max(maxArea, area);

                }
            }
        }
        return maxArea;

    }

    public static int dfs(int[][] grid, boolean[][] vis, int i, int j) {
        vis[i][j] = true;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int cnt = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && vis[r][c] == false && grid[r][c] == 1) {
                vis[r][c] = true;
                cnt += dfs(grid, vis, r, c);

            }
        }
        return cnt + 1;
    }

    // =====================LEETCODE 463==========================================
    public int islandPerimeter(int[][] board) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int n = board.length;
        int m = board[0].length;
        int onecnt = 0;
        int nbrcnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    onecnt++;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 1) {
                            nbrcnt++;
                        }
                    }
                }
            }
        }
        return 4 * onecnt - nbrcnt;
    }

    // =========================leetcode 130
    // ===================================================
    public void solve(char[][] board) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0 || i == 0 || i == n - 1 || j == m - 1) {
                    if (board[i][j] == 'O')
                        dfs_surround(board, i, j, dir);

                }
            }
        }

        // change the matrix now
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '$') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    public static void dfs_surround(char[][] grid, int i, int j, int[][] dir) {
        int n = grid.length;
        int m = grid[0].length;
        grid[i][j] = '$';
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O') {
                dfs_surround(grid, r, c, dir);
            }
        }
    }

    public static void main(String args[]) {

    }

}
