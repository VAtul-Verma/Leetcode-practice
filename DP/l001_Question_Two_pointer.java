import java.util.Arrays;

public class l001_Question_Two_pointer {

    // =============================================LEETCODE
    // 396=====================================
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int rotationsum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            rotationsum += i * nums[i];
        }
        int maxsum = rotationsum;
        for (int i = 0; i < n; i++) {
            rotationsum = rotationsum - sum + n * nums[i];
            maxsum = Math.max(maxsum, rotationsum);
        }
        return maxsum;
    }

    // =========================================LEETCODE
    // 64==========================================
    // Recursion
    public int minPathSumHelper(int[][] grid, int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return grid[sr][sc];
        }
        int minCost = (int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                minCost = Math.min(minCost, minPathSumHelper(grid, r, c, er, ec, dir, dirs, psf + dirs[d] + " "));
            }
        }
        return grid[sr][sc] + minCost;
    }

    // memoization from reursion;

    public int minPathSumHelper_memo(int[][] grid, int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = grid[sr][sc];
        }
        if (dp[sr][sc] != -1)
            return dp[sr][sc];
        int minCost = (int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                minCost = Math.min(minCost, minPathSumHelper_memo(grid, r, c, er, ec, dir, dp));
            }
        }
        return dp[sr][sc] = grid[sr][sc] + minCost;
    }

    // Tabulation from memoization

    public int minPathSumHelper_tablu(int[][] grid, int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (sr == ER && sc == EC) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                int minCost = (int) 1e9;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r <= ER && c <= EC) {
                        minCost = Math.min(minCost, dp[r][c]);
                    }
                }
                dp[sr][sc] = grid[sr][sc] + minCost;
            }
        }
        return dp[SR][SC];

    }

    // ===============================================GFG GOLD
    // MINE=================================================
    // Link: https://www.geeksforgeeks.org/problems/gold-mine-problem2608/1
    // Recursion Code
    public int maxGoldrec(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int maxCoin = 0;
        for (int r = 0; r < n; r++) {
            maxCoin = Math.max(maxCoin, maxGoldhelper_Recursion(r, 0, n - 1, m - 1, dir, mat));
        }
        return maxCoin;

    }

    public int maxGoldhelper_Recursion(int sr, int sc, int er, int ec, int[][] dir, int[][] mat) {
        if (sc == ec) {
            return mat[sr][sc];
        }

        int maxCoin = -(int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                maxCoin = Math.max(maxCoin, maxGoldhelper_Recursion(r, c, er, ec, dir, mat));
            }
        }
        return maxCoin + mat[sr][sc];
    }

    // memoition from recursion
    public int maxGold_memo(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int maxCoin = 0;
        for (int r = 0; r < n; r++) {
            maxCoin = Math.max(maxCoin, maxGoldhelper_memo(r, 0, n - 1, m - 1, dir, mat, dp));
        }
        return maxCoin;

    }

    public int maxGoldhelper_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] mat, int[][] dp) {
        if (sc == ec) {
            return dp[sr][sc] = mat[sr][sc];
        }

        if (dp[sr][sc] != -1)
            return dp[sr][sc];
        int maxCoin = -(int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                maxCoin = Math.max(maxCoin, maxGoldhelper_memo(r, c, er, ec, dir, mat, dp));
            }
        }
        return dp[sr][sc] = maxCoin + mat[sr][sc];
    }

    // DP Tabulation/
    public int maxGold(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        // code here
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int maxcoin = 0;
        GoldmineHelper_tablu(mat, 0, 0, n - 1, m - 1, dir, dp);
        for (int r = 0; r < n; r++) {
            maxcoin = Math.max(maxcoin, dp[r][0]);
        }

        return maxcoin;

    }

    public int GoldmineHelper_tablu(int[][] grid, int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
        for (int sc = EC; sc >= SC; sc--) {
            for (int sr = ER; sr >= SR; sr--) {
                if (sc == EC) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                int maxCost = -(int) 1e9;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r <= ER && c <= EC) {
                        maxCost = Math.max(maxCost, dp[r][c]);
                    }
                }
                dp[sr][sc] = grid[sr][sc] + maxCost;
            }

        }
        return dp[SR][SC];
    }

    public static void main(String[] args) {

    }

}
