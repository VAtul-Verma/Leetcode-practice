import java.util.ArrayList;
import java.util.List;

public class l003LeetCodeQuestion {
    // LeetCode 17
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();

        String codes[] = { "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxzy" };
        List<String> ans = new ArrayList<>();
        int count = Lettercombination(digits, 0, codes, ans, "");
        System.out.println(count);
        return ans;

    }

    public static int Lettercombination(String digits, int idx, String[] codes, List<String> ans, String psf) {
        if (idx == digits.length()) {
            System.out.println(psf);
            ans.add(psf);
            return 1;
        }
        int cnt = 0;
        int cidx = digits.charAt(idx) - '0'; // get the number
        String currcode = codes[cidx - 1];// get the code form the array
        for (int i = 0; i < currcode.length(); i++) {
            cnt += Lettercombination(digits, idx + 1, codes, ans, psf + currcode.charAt(i) + "");
        }
        return cnt;
    }

    // Leetocde 322
    public static int coinChangePermutation(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 0;
        }
        int mincon = (int) 1e9;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                mincon = Math.min(mincon, coinChangePermutation(coins, tar - coins[i], psf + coins[i] + " ") + 1);

            }
        }
        return mincon;

    }

    // LeetCode 46
    public static int permutation(int[] nums, int idx, List<List<Integer>> ans, List<Integer> smallans) {
        if (idx == nums.length) {
            List<Integer> base = new ArrayList<>(smallans);
            ans.add(base);
            return 1;
        }
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > -11) {
                int val = nums[i];
                smallans.add(val); // add the value in the stack
                nums[i] = -11;// make the value
                cnt += permutation(nums, idx + 1, ans, smallans);
                nums[i] = val;// unmakr the value
                smallans.remove(smallans.size() - 1); // remove the last element from temp list
            }
        }
        return cnt;

    }

    // LeetCode 39
    public static int combinationSumHelper(int[] coin, int id, int tar, List<List<Integer>> ans,
            List<Integer> smallans) {
        if (tar == 0 || id == coin.length) {
            if (tar == 0) {
                ArrayList<Integer> base = new ArrayList<>(smallans);
                ans.add(base);

                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coin[id] >= 0) {
            smallans.add(coin[id]);
            count += combinationSumHelper(coin, id, tar - coin[id], ans, smallans);
            smallans.remove(smallans.size() - 1);
        }
        count += combinationSumHelper(coin, id + 1, tar, ans, smallans);
        return count;
    }

    public static void main(String[] args) {

        List<String> ans = letterCombinations("23");
        // for (String i : ans) {
        // System.out.println(i);

        // }
    }
}
