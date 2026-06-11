public class l002_RecursionTrees {

    public static int coinChangePermutation_INF(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += coinChangePermutation_INF(coins, tar - coins[i], psf + coins[i] + " ");

            }
        }
        return count;

    }

    public static int coinChangePermutation_Single(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0 && tar - coins[i] >= 0) {
                int val = coins[i];
                coins[i] = -coins[i]; // mark the coin as negative
                count += coinChangePermutation_Single(coins, tar - val, psf + val + " ");
                coins[i] = -coins[i]; // mark it again positive;

            }
        }
        return count;

    }

    // ================================================Permutation With infinte
    // coins using the Susequence
    // method=======================================================
    // public static int coinChangePermutation_INF_Subsequence(int[] coins, int idx,
    // int tar, String psf) {
    // if (tar == 0 || idx == coins.length) {
    // if (tar == 0) {
    // System.out.println(psf);
    // return 1;
    // }
    // return 0;
    // }
    // int count = 0;

    // if (tar - coins[idx] >= 0) {
    // count += coinChangePermutation_INF_Subsequence(coins, 0, tar - coins[idx],
    // psf + coins[idx] + " ");
    // }
    // count += coinChangePermutation_INF_Subsequence(coins, idx + 1, tar, psf);

    // return count;

    // }

    // ===========================================GFGTLE
    // https://www.geeksforgeeks.org/problems/coin-change2448/1===============
    public static int coinChangeComibnation_INF(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += coinChangeComibnation_INF(coins, i, tar - coins[i], psf + coins[i] + " ");

            }
        }
        return count;

    }

    public static int coinChangeComibnation_Single(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += coinChangeComibnation_Single(coins, i + 1, tar - coins[i], psf + coins[i] + " ");

            }
        }
        return count;

    }
    // ------------------------------------------------------------------------------------------------------------------------
    // =================================================SUBSEQUENCE METHOD FOR
    // PERMUTATION AND COMBINATION==========================

    // ================================================Permutation With infinte
    // coins using the Susequence
    // method=======================================================
    public static int coinChangePermutation_INF_Subsequence(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - coins[idx] >= 0) {
            count += coinChangePermutation_INF_Subsequence(coins, 0, tar - coins[idx],
                    psf + coins[idx] + " ");
        }
        count += coinChangePermutation_INF_Subsequence(coins, idx + 1, tar, psf);

        return count;

    }

    public static int coinChangePermutation_Single_Subsequence(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (coins[idx] > 0 && tar - coins[idx] >= 0) {
            int val = coins[idx];
            coins[idx] = -coins[idx]; // mark the coin as negative
            count += coinChangePermutation_Single_Subsequence(coins, 0, tar - val,
                    psf + val + " ");
            coins[idx] = -coins[idx]; // unmark the coin as positive again
        }
        count += coinChangePermutation_Single_Subsequence(coins, idx + 1, tar, psf);

        return count;

    }

    public static int coinChangeComibnation_INF_Subsequence(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - coins[idx] >= 0) {
            count += coinChangeComibnation_INF_Subsequence(coins, idx, tar - coins[idx],
                    psf + coins[idx] + " ");
        }
        count += coinChangeComibnation_INF_Subsequence(coins, idx + 1, tar, psf);

        return count;

    }

    public static int coinChangeComibnation_Single_subsequenceMethod(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - coins[idx] >= 0) {
            count += coinChangeComibnation_Single_subsequenceMethod(coins, idx + 1, tar - coins[idx],
                    psf + coins[idx] + " ");
        }
        count += coinChangeComibnation_Single_subsequenceMethod(coins, idx + 1, tar, psf);

        return count;

    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int ans = coinChangePermutation_Single_Subsequence(arr, 0, tar, "");
        System.out.println(ans);
    }

}
