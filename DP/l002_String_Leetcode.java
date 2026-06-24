import java.util.Arrays;

public class l002_String_Leetcode {

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

    // ===================================LEETCODE
    // 1143============================================
    // Recursion.
    public int longestCommonSubsequence_Recursion(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }
        // character match
        int a = longestCommonSubsequence_Recursion(s1, s2, n - 1, m - 1);
        // not match then reduce from str1
        int b = longestCommonSubsequence_Recursion(s1, s2, n - 1, m);
        // not match then reduce from str2
        int c = longestCommonSubsequence_Recursion(s1, s2, n, m - 1);
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return a + 1;
        } else {
            return Math.max(b, c);
        }

    }

    // memoization
    public static int longestCommonSubsequence_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];
        // character match
        int a = longestCommonSubsequence_memo(s1, s2, n - 1, m - 1, dp);
        // not match then reduce from str1
        int b = longestCommonSubsequence_memo(s1, s2, n - 1, m, dp);
        // not match then reduce from str2
        int c = longestCommonSubsequence_memo(s1, s2, n, m - 1, dp);
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a + 1;
        } else {
            return dp[n][m] = Math.max(b, c);
        }

    }

    // tabulation
    public static int longestCommonSubsequence_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                } else {
                    dp[n][m] = Math.max(dp[n][m - 1], dp[n - 1][m]);
                }
            }
        }
        return dp[N][M];

    }

    // =============================================LEETCODE 72
    // =============================================
    // Recusrion
    public int minDistance_rec(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0) {
            // if(n == 0) return m; // insert m characters
            // if(m == 0) return n; // delete n characters
            return n == 0 ? m : n;
        }

        // insertion
        int insert = minDistance_rec(s1, s2, n, m - 1);
        // deletion
        int delete = minDistance_rec(s1, s2, n - 1, m);
        // replace
        int replace = minDistance_rec(s1, s2, n - 1, m - 1);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return minDistance_rec(s1, s2, n - 1, m - 1);
        } else {
            return Math.min(insert, Math.min(delete, replace)) + 1;
        }

    }

    // memoization
    public static int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            // if(n == 0) return m; // insert m characters
            // if(m == 0) return n; // delete n characters
            return dp[n][m] = n == 0 ? m : n;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        // insertion
        int insert = minDistance_memo(s1, s2, n, m - 1, dp);
        // deletion
        int delete = minDistance_memo(s1, s2, n - 1, m, dp);
        // replace
        int replace = minDistance_memo(s1, s2, n - 1, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = minDistance_memo(s1, s2, n - 1, m - 1, dp);
        } else {
            return dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
        }

    }

    // tabulation
    public int minDistance_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n == 0 ? m : n;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1];
                } else {
                    dp[n][m] = Math.min(dp[n - 1][m - 1], Math.min(dp[n - 1][m], dp[n][m - 1])) + 1;
                }
            }
        }

        return dp[N][M];
    }

    // ============================================LEETCODE
    // 115==========================================
    // recursion
    public static int countWays_Recursion(String s1, String s2, int n, int m) {
        // code here
        if (m == 0)
            return 1;
        else if (n == 0)
            return 0;
        int a = countWays_Recursion(s1, s2, n - 1, m - 1);
        int b = countWays_Recursion(s1, s2, n - 1, m);
        // when both character are same
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return a + b;
        } else {
            return b;
        }
    }

    // memoization
    public static int countWays_memo(String s1, String s2, int n, int m, int[][] dp) {
        // code here
        if (m == 0)
            return dp[n][m] = 1;
        else if (n == 0)
            return dp[n][m] = 0;
        if (dp[n][m] != -1)
            return dp[n][m];
        int a = countWays_memo(s1, s2, n - 1, m - 1, dp);
        int b = countWays_memo(s1, s2, n - 1, m, dp);
        // when both character are same
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a + b;
        } else {
            return dp[n][m] = b;
        }
    }

    // tabulation
    public static int countWays_tabu(String s1, String s2, int N, int M, int[][] dp) {
        // code here
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                } else if (n == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
                } else {
                    dp[n][m] = dp[n - 1][m];
                }
            }
        }
        return dp[N][M];
    }

    // ======================================================LeetCode
    // 583=============================================

    // same the copy code of Longest comman subsequence and in main we calculate the
    // answer
    // length(s1)+length(s2) - 2 * (length of Longest comman subsequence)
    // codes are same as
    public int minDistance_Maincaller(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return n + m - 2 * longestCommonSubsequence_memo(s1, s2, n, m, dp);
    }

    public static int minDistance583_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];
        // character match
        int a = longestCommonSubsequence_memo(s1, s2, n - 1, m - 1, dp);
        // not match then reduce from str1
        int b = longestCommonSubsequence_memo(s1, s2, n - 1, m, dp);
        // not match then reduce from str2
        int c = longestCommonSubsequence_memo(s1, s2, n, m - 1, dp);
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a + 1;
        } else {
            return dp[n][m] = Math.max(b, c);
        }

    }

    // ===============================LEETCODE
    // 1035=============================================
    // aggain same as Longest comman subsequence
    // Dp solution
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;

                }
                int a = dp[n - 1][m - 1];
                int b = dp[n - 1][m];
                int c = dp[n][m - 1];
                if (nums1[n - 1] == nums2[m - 1]) {
                    dp[n][m] = a + 1;
                } else {
                    dp[n][m] = Math.max(b, c);
                }
            }
        }
        return dp[N][M];
    }

    // =============================================Leetcode 5
    // ========================================
    // tabulation
    public String getLongestPal(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int len = 0;
        int startidx = 0;
        int count = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0) {
                    dp[i][j] = 1;
                } else if (gap == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2;
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0 ? dp[i + 1][j - 1] + 2 : 0;
                }
                if (len < dp[i][j]) {
                    len = dp[i][j];
                    startidx = i;
                }
                // total palindrom
                count += dp[i][j] > 0 ? 1 : 0;

            }
        }
        return s.substring(startidx, startidx + len);

    }

    // =======================================================gfg=========================================
    // link:
    // https://www.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    // code
    public int fun(String s) {
        // Write your code here
        int m = 1000000007;
        int acnt = 0, bcnt = 0, ccnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                acnt = (1 + 2 * acnt % m) % m; // we can write this is long form==>(acnt%m+(1+acnt%m)%m)%m;
            } else if (ch == 'b') {
                bcnt = (acnt % m + 2 * bcnt % m) % m; // we can write this is long form==>(bcnt%m+(acnt%m+bcnt%m)%m)%m;
            } else if (ch == 'c') {
                ccnt = (bcnt % m + 2 * ccnt % m) % m; // we can write this is long form==>(ccnt%m+(bcnt%m+ccnt%m))%m;
            }
        }
        return (ccnt % m);
    }

    public static void main(String[] args) {
        String s1 = "babgbag";
        String s2 = "bag";

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        System.out.println(countWays_tabu(s1, s2, n, m, dp));
        display2D(dp);
    }

}
