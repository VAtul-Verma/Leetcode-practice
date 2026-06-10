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

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int ans = coinChangeComibnation_INF(arr, 0, tar, "");
        System.out.println(ans);
    }

}
