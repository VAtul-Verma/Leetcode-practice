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

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int ans = coinChangePermutation_Single(arr, tar, "");
        System.out.println(ans);
    }

}
