public class l005bit {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        // System.out.println(setbit(10, 2));
        // System.out.println(clearbit(7, 2));
        int evenornot = isEven(9);
        // System.out.println(evenornot);
        System.out.println(isPowerOfFour(64));
    }

    public static int setbit(int a, int idx) {
        int mask = 1 << idx;
        return (a | mask);
    }

    public static int clearbit(int a, int idx) {
        int mask = ~(1 << idx);
        return (a & mask);
    }

    public static int isEven(int a) {
        if ((a & 1) == 0)
            return 1;
        else
            return 0;
    }

    // Leetcode 231 power of two
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // Leetcode 342 power of 4
    public static boolean isPowerOfFour(int n) {
        if (isPowerOfTwo(n) == false)
            return false;
        int cnt = 0;
        while (n != 0) {
            if ((n & 1) == 0)
                cnt++;
            n >>>= 1;

        }
        return (cnt & 1) == 0;
    }

    // leetcode 136
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans ^= i;
        }
        return ans;
    }

    // Leetcode 268
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        int i = 0;
        while (i < n) {
            ans ^= nums[i] ^ i;
            i++;
        }
        return ans;
    }

    // Leetcode 191
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += (n & 1);
            n >>= 1;
        }
        return cnt;

    }

    // LeetCode 338 approach 1
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = hammingWeight1(i);
        }
        return ans;

    }

    public int hammingWeight1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n = n & (n - 1);
        }
        return cnt;
    }
    // method 2 (338) O(N)
    // public int[] countBits(int n) {
    // int []ans = new int[n+1];
    // for(int i=1;i<=n;i++){
    // ans[i] = ans[i & (i-1)]+1;
    // }
    // return ans;
    // }

    // =====================LEETCODE 260====================
    public int[] singleNumber2(int[] nums) {
        int xor = 0;
        for (int i : nums) {
            xor ^= i;
        }
        int mask = xor & (-xor);
        int a = 0;
        int b = 0;
        for (int i : nums) {
            if ((i & mask) == 0)
                a ^= i;
            else
                b ^= i;
        }
        return new int[] { a, b };
    }
}
