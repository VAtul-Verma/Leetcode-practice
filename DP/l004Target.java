import java.util.Arrays;

public class l004Target {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
            System.out.println();
        }
    }

    // Recursion
    public static int permutationTarget(int[] arr, int tar) {
        if (tar == 0) {
            return 1;
        }
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                cnt += permutationTarget(arr, tar - arr[i]);
            }
        }
        return cnt;
    }

    // memoization
    public static int permutationTarget_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                cnt += permutationTarget_memo(arr, tar - arr[i], dp);
            }
        }
        return dp[tar] = cnt;
    }

    // tabulation
    public static int permutationTarget_tabu(int[] arr, int TAR, int[] dp) {
        for (int tar = 0; tar <= TAR; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }
            for (int i = 0; i < arr.length; i++) {
                if (tar - arr[i] >= 0) {
                    dp[tar] += dp[tar - arr[i]];
                }
            }
        }
        return dp[TAR];
    }
    // ========================Target
    // Combination==========================================

    // memoization
    public static int combination(int[] arr, int n, int tar, int[][] dp) {
        if (tar == 0)
            return dp[n][tar] = 1;
        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;
        for (int i = n; i > 0; i--) {
            if (tar - arr[i - 1] >= 0)
                count += combination(arr, i, tar - arr[i - 1], dp);
        }

        return dp[n][tar] = count;
    }

    // tabulation
    public static int combination_DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele];
            }
        }
        return dp[Tar];
    }

    // ===========================================LEETCODE
    // 322===========================================
    // Recursion
    public int coinChangePermutation(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 0;
        }
        int minCoin = (int) 1e9;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                minCoin = Math.min(minCoin, coinChangePermutation(coins, tar - coins[i], psf + coins[i] + "") + 1);
            }
        }
        return minCoin;
    }

    // memoization
    public int coinChangePermutationmemo(int[] coins, int tar, String psf, int[] dp) {
        if (tar == 0) {
            System.out.println(psf);
            return dp[tar] = 0;
        }
        if (dp[tar] != -1)
            return dp[tar];
        int minCoin = (int) 1e9;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                minCoin = Math.min(minCoin,
                        coinChangePermutationmemo(coins, tar - coins[i], psf + coins[i] + "", dp) + 1);
            }
        }
        return dp[tar] = minCoin;
    }

    // tabulation
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);
        return coinChangePermutationtabu(coins, amount, "", dp) == 1000000000 ? -1
                : coinChangePermutationtabu(coins, amount, "", dp);
    }

    public int coinChangePermutationtabu(int[] coins, int TAR, String psf, int[] dp) {
        for (int tar = 0; tar <= TAR; tar++) {
            if (tar == 0) {
                dp[tar] = 0;
                continue;
            }
            for (int i = 0; i < coins.length; i++) {
                if (tar - coins[i] >= 0) {
                    dp[tar] = Math.min(dp[tar - coins[i]] + 1, dp[tar]);
                }
            }
        }
        return dp[TAR];
    }

    // ========================================GFG SUB-SET-SUM
    // PROBLEM================================================
    // Recursion
    static Boolean isSubsetSumHelper(int arr[], int id, int sum) {
        // code here
        if (id == arr.length || sum == 0) {
            if (sum == 0) {
                return true;
            }
            return false;

        }
        boolean include = false;
        if (sum - arr[id] >= 0)
            include = isSubsetSumHelper(arr, id + 1, sum - arr[id]);
        boolean exclude = isSubsetSumHelper(arr, id + 1, sum);
        return (include || exclude);

    }

    // memoization
    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        Boolean[][] dp = new Boolean[arr.length + 1][sum + 1];
        for (Boolean[] d : dp) {
            Arrays.fill(d, null);
        }
        return isSubsetSumHelper(arr, 0, sum, dp);

    }

    static Boolean isSubsetSumHelper(int arr[], int id, int sum, Boolean[][] dp) {
        // code here
        if (id == arr.length || sum == 0) {
            if (sum == 0) {
                return dp[id][sum] = true;
            }
            return dp[id][sum] = false;

        }
        if (dp[id][sum] != null)
            return dp[id][sum];
        boolean include = false;

        if (sum - arr[id] >= 0) {
            include = isSubsetSumHelper(arr, id + 1, sum - arr[id], dp);

        }
        boolean exclude = isSubsetSumHelper(arr, id + 1, sum, dp);
        return dp[id][sum] = (include || exclude);

    }

    // tabulation
    static boolean isSubsetSumHelpertabu(int arr[], int N, int Tar, boolean[][] dp) {
        // code here
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n] >= 0) {
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                }
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];

            }
        }

        return dp[N][Tar];

    }

    // print total subset path using DP reverse engineering

    public static int targetsum(int[] arr, int N, int[][] dp, int tar, String psf) {
        if (N == 0 || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int cnt = 0;
        // include
        if (tar - arr[N - 1] >= 0 && dp[N - 1][tar - arr[N - 1]] == 1) {
            cnt += targetsum(arr, N - 1, dp, tar - arr[N - 1], psf + arr[N - 1] + " ");
        }

        // exclude
        if (dp[N - 1][tar] == 1) {
            cnt += targetsum(arr, N - 1, dp, tar, psf + " ");
        }
        return cnt;
    }

    // ==================================================GFG
    // 01Kanpsake========================================
    // Link: https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
    // Recursion:
    public int knapsack(int W, int val[], int wt[], int n) {
        // code here
        if (n == 0 || W == 0) {
            return 0;
        }

        int ans = 0;
        if (W - wt[n - 1] >= 0) {
            ans = Math.max(ans, knapsack(W - wt[n - 1], val, wt, n - 1) + val[n - 1]);
        }

        ans = Math.max(ans, knapsack(W, val, wt, n - 1));
        return ans;

    }

    // memoization
    public int knapsack(int W, int val[], int wt[]) {
        // code here
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return knapsack_memo(W, val, wt, n, dp);

    }

    public int knapsack_memo(int W, int val[], int wt[], int n, int[][] dp) {
        // code here
        if (n == 0 || W == 0) {
            return dp[n][W] = 0;
        }
        if (dp[n][W] != -1)
            return dp[n][W];
        int ans = 0;
        if (W - wt[n - 1] >= 0) {
            ans = Math.max(ans, knapsack_memo(W - wt[n - 1], val, wt, n - 1, dp) + val[n - 1]);
        }

        ans = Math.max(ans, knapsack_memo(W, val, wt, n - 1, dp));
        return dp[n][W] = ans;

    }

    // ==============================================GFG KNAPSHAKE with DUBLICATE
    // (UNBOUNDED KANPSHAKE)===================
    // memoization
    public int unboundknapsack_memo(int W, int val[], int wt[], int n, int[][] dp) {
        // code here
        if (n == 0 || W == 0) {
            return dp[n][W] = 0;
        }
        if (dp[n][W] != -1)
            return dp[n][W];
        int ans = 0;
        if (W - wt[n - 1] >= 0) {
            ans = Math.max(ans, knapsack_memo(W - wt[n - 1], val, wt, n, dp) + val[n - 1]);
        }

        ans = Math.max(ans, knapsack_memo(W, val, wt, n - 1, dp));
        return dp[n][W] = ans;

    }

    // tabulation
    public int unboundknapsack_tabu(int W, int val[], int wt[]) {
        int[] dp = new int[W + 1];
        for (int weight = 0; weight <= W; weight++) {
            for (int i = 0; i < wt.length; i++) {
                if (weight - wt[i] >= 0) {
                    dp[weight] = Math.max(dp[weight], dp[weight - wt[i]] + val[i]);
                }
            }
        }
        return dp[W];

    }

    // =============================================LEETCODE
    // 416==============================================
    // Recursion
    public boolean canPartition(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int ele : arr) {
            sum += ele;
        }
        if (sum % 2 != 0)
            return false;
        else {
            return canPartition_Recursion(arr, n, sum / 2);
        }
    }

    public boolean canPartition_Recursion(int arr[], int n, int tar) {
        if (n == 0 || tar == 0) {
            return (tar == 0) ? true : false;
        }

        boolean include = false;
        if (tar - arr[n - 1] >= 0) {
            include = canPartition_Recursion(arr, n - 1, tar - arr[n - 1]);
        }
        boolean exclude = canPartition_Recursion(arr, n - 1, tar);
        return (include | exclude);
    }

    // memoization
    public boolean canPartition_memo(int arr[], int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            dp[n][tar] = (tar == 0) ? 1 : 0;
            return (dp[n][tar] == 1);
        }

        if (dp[n][tar] != -1)
            return dp[n][tar] == 1;
        boolean include = false;
        if (tar - arr[n - 1] >= 0) {
            include = canPartition_memo(arr, n - 1, tar - arr[n - 1], dp);
        }
        boolean exclude = canPartition_memo(arr, n - 1, tar, dp);
        dp[n][tar] = (include || exclude) ? 1 : 0;
        return dp[n][tar] == 1;
    }

    // =======================================LEETCODE
    // 494===============================================
    // Recursion
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (target < -sum || target > sum)
            return 0;
        return targetsumhelper_Recursion(nums, n, target);
    }

    public int targetsumhelper_Recursion(int[] nums, int n, int tar) {
        if (n == 0) {
            return (tar == 0) ? 1 : 0;
        }
        int cnt = 0;
        cnt += targetsumhelper_Recursion(nums, n - 1, tar - nums[n - 1]); // num is positive
        cnt += targetsumhelper_Recursion(nums, n - 1, tar - (-nums[n - 1])); // nums is negative
        return cnt;

    }

    // memoization
    public int findTargetSumWaysmemo(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (target < -sum || target > sum)
            return 0;
        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return targetsumhelper_memo(nums, n, sum, target + sum, dp);
    }

    public int targetsumhelper_memo(int[] nums, int n, int sum, int tar, int[][] dp) {
        if (n == 0) {
            return dp[n][tar] = (tar == sum) ? 1 : 0;
        }
        if (dp[n][tar] != -1)
            return dp[n][tar];
        int cnt = 0;
        if (tar - (nums[n - 1]) >= 0)
            cnt += targetsumhelper_memo(nums, n - 1, sum, tar - nums[n - 1], dp); // num is positive
        if (tar - (-nums[n - 1]) <= 2 * sum)
            cnt += targetsumhelper_memo(nums, n - 1, sum, tar - (-nums[n - 1]), dp); // nums is negative
        return dp[n][tar] = cnt;

    }

    // memoization more better
    public int targetsumhelper_memobetter(int[] nums, int n, int sum, int tar, int[][] dp) {
        if (n == 0) {
            return dp[n][sum] = (tar == sum) ? 1 : 0;
        }
        if (dp[n][sum] != -1)
            return dp[n][sum];
        int cnt = 0;
        cnt += targetsumhelper_memobetter(nums, n - 1, sum - nums[n - 1], tar, dp); // num is positive
        cnt += targetsumhelper_memobetter(nums, n - 1, sum + nums[n - 1], tar, dp); // nums is negative
        return dp[n][sum] = cnt;

    }

    // ============================================LEETCODE
    // 698===============================
    // recursion
    public boolean canPartitionKSubsets(int[] arr, int k) {
        int n = arr.length;
        int maxEle = 0;
        int sum = 0;
        for (int ele : arr) {
            sum += ele;
            maxEle = Math.max(maxEle, ele);
        }
        if (sum % k != 0 || maxEle > sum)
            return false;

        boolean vis[] = new boolean[n];
        return canPartitionKSubsets_recursion(arr, 0, k, 0, sum / k, vis);

    }

    public boolean canPartitionKSubsets_recursion(int[] arr, int idx, int k, int sumSF, int tar, boolean[] vis) {
        if (k == 0)
            return true;
        if (sumSF > tar)
            return false;
        if (tar == sumSF) {
            return canPartitionKSubsets_recursion(arr, 0, k - 1, 0, tar, vis);
        }
        boolean res = false;
        for (int i = idx; i < arr.length; i++) {
            if (vis[i])
                continue;

            vis[i] = true;
            res = res || canPartitionKSubsets_recursion(arr, i, k, sumSF + arr[i], tar, vis);
            vis[i] = false;

        }
        return res;

    }

    public static void main(String[] args) {
        // System.out.println("Hello world ")
        // int[] arr = { 2, 3, 5, 7 };
        int[] arr = { 1, 2, 3 };
        int target = 4;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        permutationTarget_memo(arr, target, dp);
        display(dp);

    }
}
