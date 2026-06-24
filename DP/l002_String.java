import java.util.Arrays;

public class l002_String {
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

    // =====================================Longest Palindromic
    // Subsequence=============================================
    // Recursion code
    public int longestPalinSubseq_Recursion(String s, int low, int high) {
        // Base case
        if (low >= high)
            return (low == high) ? 1 : 0;

        if (s.charAt(low) == s.charAt(high)) {
            // If the first and last characters match
            return 2 + longestPalinSubseq_Recursion(s, low + 1, high - 1);
        } else {
            // If the first and last characters do not match
            return Math.max(longestPalinSubseq_Recursion(s, low, high - 1),
                    longestPalinSubseq_Recursion(s, low + 1, high));
        }

    }

    // Memoization code
    public static int longestPalinSubseq_memo(String s, int low, int high, int[][] dp) {
        // Base case
        if (low >= high)
            return dp[low][high] = (low == high) ? 1 : 0;
        if (dp[low][high] != -1)
            // if (dp[low][high] != 0)
            return dp[low][high];

        if (s.charAt(low) == s.charAt(high)) {
            // If the first and last characters match
            return dp[low][high] = 2 + longestPalinSubseq_memo(s, low + 1, high - 1, dp);
        } else {
            // If the first and last characters do not match
            return dp[low][high] = Math.max(longestPalinSubseq_memo(s, low, high - 1, dp),
                    longestPalinSubseq_memo(s, low + 1, high, dp));
        }

    }

    // Memoization code Easy to understand
    public static int longestPalinSubseq_memoII(String s, int low, int high, int[][] dp) {
        // Base case
        if (low >= high)
            return dp[low][high] = (low == high) ? 1 : 0;
        // if (dp[low][high] != -1)
        if (dp[low][high] != 0)
            return dp[low][high];

        // If the first and last characters match
        int a = longestPalinSubseq_memoII(s, low + 1, high - 1, dp);

        // If the first and last characters do not match
        int b = longestPalinSubseq_memoII(s, low, high - 1, dp);
        int c = longestPalinSubseq_memoII(s, low + 1, high, dp);

        if (s.charAt(low) == s.charAt(high)) {
            return dp[low][high] = a + 2;
        } else {
            return dp[low][high] = Math.max(b, c);
        }
    }

    // tabulation code
    public static int longestPalinSubseq_tabu(String s, int LOW, int HIGH, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int low = 0, high = gap; high < n; low++, high++) {
                if (low >= high) {
                    dp[low][high] = (low == high) ? 1 : 0;
                    continue;
                }
                if (s.charAt(low) == s.charAt(high)) {
                    // If the first and last characters match
                    dp[low][high] = 2 + dp[low + 1][high - 1];
                } else {
                    // If the first and last characters do not match
                    dp[low][high] = Math.max(dp[low + 1][high],
                            dp[low][high - 1]);
                }
            }
        }

        return dp[LOW][HIGH];

    }

    public static void main(String[] args) {
        String s = "bbabcbcab";
        // String s = "geeksforgeeks";
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] arr : dp) {
            // Arrays.fill(arr, -1);
            Arrays.fill(arr, 0);
        }
        int ans = longestPalinSubseq_memoII(s, 0, n - 1, dp);
        display2D(dp);
        System.out.println(ans);

    }

}