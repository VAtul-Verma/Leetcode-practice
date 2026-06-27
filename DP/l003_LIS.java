import java.util.Arrays;

public class l003_LIS {

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

    // ==============================LEETCODE 300
    // ==============================================

    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, lengthOfLIS_meo(nums, i, dp));
        }
        return ans;

    }

    // Recursion code ;
    public int lengthOfLIS_REC(int[] nums, int idx) {
        int maxlen = 1;
        for (int i = idx - 1; i > 0 - 1; i--) {
            if (nums[i] < nums[idx]) {
                int res = lengthOfLIS_REC(nums, i) + 1;
                maxlen = Math.max(maxlen, res);
            }
        }
        return maxlen;
    }

    // memoization code
    public static int lengthOfLIS_meo(int[] nums, int idx, int[] dp) {
        if (dp[idx] != 0)
            return dp[idx];
        int maxlen = 1;
        for (int i = idx - 1; i > 0 - 1; i--) {
            if (nums[i] < nums[idx]) {
                int res = lengthOfLIS_meo(nums, i, dp) + 1;
                maxlen = Math.max(maxlen, res);
            }
        }
        return dp[idx] = maxlen;
    }

    // tabulation code
    static int lis(int arr[]) {
        // code here
        int len = 0;
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            len = Math.max(len, dp[i]);
        }
        return len;

    }

    // =======================================================LDS===========================================================
    // tabulation code
    static int lds(int arr[]) {
        // code here
        int len = 0;
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            len = Math.max(len, dp[i]);
        }
        return len;

    }

    // =========================================GFG Longest Bitonic
    // subsequence=========================================
    // link:
    // https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    // Tabulation code
    public static int longestBitonicSequence(int n, int[] nums) {
        // code here
        int LIS[] = new int[n];
        int LDS[] = new int[n];
        int maxLen = 0;
        lds(nums, LDS);
        lis(nums, LIS);
        for (int i = 0; i < n; i++) {
            if (LIS[i] > 1 && LDS[i] > 1) {
                maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
            }
        }
        return maxLen;

    }

    static int lds(int arr[], int[] dp) {
        // code here
        int len = 0;
        int n = arr.length;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            len = Math.max(len, dp[i]);
        }
        return len;

    }

    static int lis(int arr[], int[] dp) {
        // code here
        int len = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            len = Math.max(len, dp[i]);
        }
        return len;

    }

    // =====================================================GFG Maximum Sum Bitonic
    // Subsequence======================
    // link:
    // https://www.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    // tabulation code

    public int LIS(int[] nums, int[] dp) {
        int maxsum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            maxsum = Math.max(maxsum, dp[i]);
        }
        return maxsum;
    }

    public int LDS(int[] nums, int[] dp) {
        int maxsum = 0;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            maxsum = Math.max(maxsum, dp[i]);
        }
        return maxsum;
    }

    public int maxSumBS(int nums[], int n) {
        int[] lisdp = new int[nums.length];
        int[] ldsdp = new int[nums.length];
        LIS(nums, lisdp);
        LDS(nums, ldsdp);
        int maxsum = 0;
        for (int i = 0; i < nums.length; i++) {
            maxsum = Math.max(maxsum, lisdp[i] + ldsdp[i] - nums[i]);
        }
        return maxsum;

    }

    // ====================================================GFG Max Sum Increasing
    // Subsequence
    // Link:
    // https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    // code
    public int maxSumIS(int nums[]) {
        // code here
        int[] lisdp = new int[nums.length];
        LISSUM(nums, lisdp);
        int maxsum = 0;
        for (int i = 0; i < nums.length; i++) {
            maxsum = Math.max(maxsum, lisdp[i]);
        }
        return maxsum;
    }

    public int LISSUM(int[] nums, int[] dp) {
        int maxsum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            maxsum = Math.max(maxsum, dp[i]);
        }
        return maxsum;
    }

    // =======================================================GFG Longest Reverse
    // Bitonic Sequence =======================
    // Link: https://www.geeksforgeeks.org/dsa/longest-reverse-bitonic-sequence/
    // reverse_longestBitonicSequnece

    // left to right
    public static int LIS_DP_LR(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int LIS_DP_RL(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // left to right
    public static int reverse_LDS_DP_LR(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // Left to right
    public static int reverse_LDS_DP_RL(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public int reverse_LongestBitonicSequence(int[] nums) {
        int n = nums.length;

        int[] LIS = new int[n];
        int[] LDS = new int[n];

        reverse_LDS_DP_LR(nums, LIS);
        reverse_LDS_DP_RL(nums, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }
        return maxLen;
    }

    // ======================================================LEETCODE
    // 354================================================
    // code

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] - b[0];
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if ((envelopes[j][1] < envelopes[i][1]) && (envelopes[j][0] < envelopes[i][0])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    // ============================================LEETCODE
    // 673====================================
    // code
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] dp = new int[n];// len array
        int maxLen = 0, maxCount = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 == dp[i])
                        count[i] += count[j];
                    else if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLen == dp[i]) {
                maxCount += count[i];
            } else if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            }
        }
        return maxCount;
    }

    // ==============================================GFG
    // minimum-number-of-deletions-to-make-a-sorted-sequence============================================
    // Link :
    // https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence3248/1

    public int minDeletions(int[] arr) {
        // code here
        int n = arr.length;
        int dp[] = new int[n];
        int maxlen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        return n - maxlen;

    }

    // ==========================================================GFG Building
    // Bridges======================================
    // code
    public static int BuildingBridge(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });
        int n = arr.length;
        int maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] < arr[i][1] && arr[j][0] < arr[i][0]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 10, 2, 11 };

        int ans = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, lengthOfLIS_meo(nums, i, dp));
        }
        display(dp);
        System.out.println(ans);

    }

}