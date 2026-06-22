import java.util.Arrays;

public class l001_towPointers {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
            System.out.println();
        }
    }

    public static int fibo(int n) {
        if (n <= 1)
            return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
    }

    public static int fibo_tabu(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n == 0 || n == 1) {
                dp[n] = n;
            } else {
                dp[n] = dp[n - 1] + dp[n - 2];
            }
        }
        return dp[N];
    }

    public static int fibo_optimize(int n) {
        int a = 0;
        int b = 1;
        for (int i = 1; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    // ============================================Leetcode
    // 1137================================================
    // Recursion
    public int tribonacci(int n) {
        if (n <= 1)
            return n;
        if (n == 2)
            return 1;
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);

    }

    // memoization from recursion
    public int tribonacci_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (n == 2)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = tribonacci_memo(n - 1, dp) + tribonacci_memo(n - 2, dp) + tribonacci_memo(n - 3, dp);

    }

    // tabulation from memoization
    public static int tribonacci_tebuo(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            if (n == 2) {
                dp[n] = 1;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3];
        }
        return dp[N];

    }

    // ======================leetcode optimized===========================
    public int tribonacci_optimized(int n) {
        int a = 0, b = 1, c = 1;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                if (i == n)
                    return a;
            } else if (i == 1) {
                if (i == n)
                    return b;
            } else if (i == 2) {
                if (i == n)
                    return c;
            } else {
                int sum = a + b + c;
                a = b;
                b = c;
                c = sum;
            }
        }
        return c;
    }

    // ======================================LeetCode 70 climb
    // stairs=================================================
    // Recursion code
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // memoization from recursion
    public int climbStairs_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    // tabulation from memoization
    public int climbStairs_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            } else
                dp[n] = dp[n - 1] + dp[n - 2];
        }
        return dp[N];
    }

    // ======================leetcode optimized===========================
    public int climbStairs_opti(int N) {
        int a = 1;
        int b = 1;
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                a = 1;
                if (n == N)
                    return a;
            } else if (n == 1) {
                b = 1;
                if (n == N)
                    return b;

            } else {
                int sum = a + b;
                a = b;
                b = sum;
            }

        }
        return b;

    }

    public static int climbStairs_optiII(int N) {
        int a = 1;
        int b = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;

    }

    // ===================================LeetCode
    // 746============================================

    // Recursion code
    public int minCostClimbingStairs_Rec(int[] cost, int n) {
        if (n <= 1)
            return cost[n];
        int firstcall = minCostClimbingStairs_Rec(cost, n - 1);
        int secondcall = minCostClimbingStairs_Rec(cost, n - 2);
        int ans = Math.min(firstcall, secondcall) + (n == cost.length ? 0 : cost[n]);
        return ans;
    }

    // memoization from recursion
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return minCostClimbingStairs_memo(cost, n, dp);

    }

    public int minCostClimbingStairs_memo(int[] cost, int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }
        if (dp[n] != -1)
            return dp[n];
        int fcall = minCostClimbingStairs_memo(cost, n - 1, dp);
        int scall = minCostClimbingStairs_memo(cost, n - 2, dp);
        int ans = Math.min(fcall, scall) + (n == cost.length ? 0 : cost[n]);

        return dp[n] = ans;
    }

    // tabulation from memoization
    public int minCostClimbingStairs_tabu(int[] cost, int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            dp[n] = Math.min(dp[n - 1], dp[n - 2]) + (n == cost.length ? 0 : cost[n]);
        }

        return dp[N];
    }

    // ==============================================gfg Friend Pairing
    // fuction=========================================
    // Link: https://www.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    // Recursion---code
    public long countFriendsPairingshelper(int n) {
        if (n <= 1)
            return 1;
        return (countFriendsPairingshelper(n - 1) + countFriendsPairingshelper(n - 2) * (n - 1)) % 1000000007;

    }

    // memoization from recursion
    public long countFriendsPairingshelper_memo(int n, long[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = (countFriendsPairingshelper_memo(n - 1, dp)
                + countFriendsPairingshelper_memo(n - 2, dp) * (n - 1)) % 1000000007;

    }

    // tabulation from memoization
    public long countFriendsPairingshelper_tabu(int N, long[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            dp[n] = (dp[n - 1] + dp[n - 2] * (n - 1)) % 1000000007;
        }
        return dp[N];

    }

    // optimized
    public long countFriendsPairingshelper_opti(int N, long[] dp) {
        long a = 1;
        long b = 1;
        for (int i = 0; i <= N; i++) {
            if (i <= 1) {
                continue;
            } else {
                long sum = (b + a * (i - 1)) % 1000000007;
                a = b;
                b = sum;
            }
        }
        return b;

    }

    // ==========================================================================================================================
    // ==========================================================================================================================
    // 2d DP
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // ===================================LEETCODE
    // 62=====================================
    // Recursion code
    public int uniquePaths_rec(int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                cnt += uniquePaths_rec(r, c, er, ec, dir, dirs, psf + dirs[d] + " ");
            }
        }
        return cnt;
    }

    // memoization from the recursion
    public static int uniquePaths_memo(int sr, int sc, int er, int ec, int[][] dir,
            int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int cnt = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                cnt += uniquePaths_memo(r, c, er, ec, dir, dp);
            }
        }
        return dp[sr][sc] = cnt;
    }

    // tabulation from memoization
    public int uniquePaths_tabo(int SR, int SC, int ER, int EC, int[][] dir, String[] dirs, String psf, int[][] dp) {
        for (int sr = ER; sr >= 0; sr--) {
            for (int sc = EC; sc >= 0; sc--) {
                if (sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int cnt = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= ER && c <= EC) {
                        cnt += dp[r][c];
                    }
                }
                dp[sr][sc] = cnt;
            }
        }

        return dp[SR][SC];
    }

    // memoization more approach without sr,sc varibale
    public int uniquePaths_memoII(int m, int n) {
        int[][] dp = new int[m][n];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        int er = m - 1;
        int ec = n - 1;
        // int [][]dp=new int[m][n];
        int ans = uniquePaths_memoII_helper(er, ec, dir, dp);
        return ans;
    }

    public int uniquePaths_memoII_helper(int er, int ec, int[][] dir, int[][] dp) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += uniquePaths_memoII_helper(r, c, dir, dp);
            }
        }

        return dp[er][ec] = count;
    }

    // tabulation without sr,sc varible
    public static int uniquePaths_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // unique path with multijumps
    // Dpcode===================================================
    public static int mazePathJump_memo(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            while (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                count += mazePathJump_memo(r, c, dp, dir);
                r += dir[d][0];
                c += dir[d][1];
            }
        }

        return dp[er][ec] = count;
    }

    public static int mazePathJump_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    while (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += mazePathJump_memo(r, c, dp, dir);
                        r += dir[d][0];
                        c += dir[d][1];
                    }
                }

                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public static void main(String[] args) {
        // int m = 3, n = 7;
        // int[] dp = new int[n + 1];
        // // System.out.println(fibo_tabu(n, dp));
        // System.out.println(tribonacci_tebuo(n, dp));
        int m = 3, n = 3;
        int dir[][] = { { 1, 0 }, { 0, 1 } };
        int[][] dp = new int[m][n];
        String[] dirs = { "H", "V" };
        int ans = uniquePaths_memo(0, 0, m - 1, n - 1, dir, dp);

        System.out.println(ans);
        display2D(dp);

    }

}