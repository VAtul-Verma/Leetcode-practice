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

    public static void main(String[] args) {
        int n = 7;
        int[] dp = new int[n + 1];
        // System.out.println(fibo_tabu(n, dp));
        System.out.println(tribonacci_tebuo(n, dp));
        display(dp);

    }

}