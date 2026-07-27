public class l001 {

    // ===================================LeetCode
    // 189=========================================
    // Navie Approach
    // Time Complexcity: O(nk) SC: O(1)

    public void rotate(int[] arr, int k) {
        int n = arr.length;
        System.out.println(n);
        k = k % n;
        for (int j = 0; j < k; j++) {
            int temp = arr[n - 1];
            for (int l = n - 1; l >= 1; l--) {
                arr[l] = arr[l - 1];
            }
            arr[0] = temp;
        }
    }

    // Better Approach
    // TC: O(n) SC: O(n)
    public void rotate_Better(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        int[] temp = new int[n];
        int j = 0;
        for (int i = n - k; i < n; i++) {
            temp[j++] = arr[i];
        }
        for (int i = 0; i < n - k; i++) {
            temp[j++] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    // optimal Approac
    // :TC : o(n) SC:O(1)
    public void rotate_opt(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        rotate_given_idx(0, n - 1, arr);
        rotate_given_idx(0, k - 1, arr);
        rotate_given_idx(k, n - 1, arr);
    }

    public void rotate_given_idx(int si, int ei, int[] arr) {
        while (si < ei) {
            swap(si, ei, arr);
            si++;
            ei--;
        }
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ===============Rearrange positive and negative numbers with constant extra
    // space===================
    // ====================Tricky ====Order should be maintain after the
    // rearrangement====================
    // If we are not required to maintain the order. We can solve this problem with
    // O(n) Time and (1) Space. Please refer Segregate Even and Odd for reference

    // TC: O(n^2)
    // Link:https://www.geeksforgeeks.org/dsa/segregating-negative-and-positive-maintaining-order-and-o1-space/
    public static void Rearrange_withOrder(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i] > 0) {
                continue;
            }
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] >= 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void print1D(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    // ++++++++++++++++++++++++Pattern++++++++++++if we want to segregate two things
    // order not matter then===========
    // segregate odd even segregate 0,1 segregate -ve , +ve
    // ===========================
    // Link:https://www.geeksforgeeks.org/problems/segregate-even-and-odd-numbers4629/1
    // Link: https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1

    // Naive approach
    // TC: O(3N)
    public static void segregate0and1_Naive(int[] arr) {
        int n = arr.length;
        int countzero = 0;
        int countone = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                countzero++;
            } else {
                countone++;
            }
        }

        int k = 0;
        for (int i = 0; i < countzero; i++) {
            arr[k++] = 0;
        }

        for (int i = countzero; i < n; i++) {
            arr[k++] = 1;
        }

    }

    // TC: O(n)
    public static void segregate0and1(int[] arr) {
        // code here
        int n = arr.length;
        int ptr = -1;
        int itr = 0;
        while (itr < n) {
            if (arr[itr] == 0) {
                ptr++;
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
            }
            itr++;
        }

    }

    // ======================================LeetCode
    // 75===========================================
    // ============Navie appraoch or 4 Time array traversal appraoch
    public static void sortColors(int[] nums) {
        int cnt0 = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        int n = nums.length;
        for (int ele : nums) {
            if (ele == 0) {
                cnt0++;
            } else if (ele == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
        }
        int k = 0;
        for (int i = 0; i < cnt0; i++) {
            nums[k++] = 0;
        }
        for (int i = cnt0; i < cnt0 + cnt1; i++) {
            nums[k++] = 1;
        }
        for (int i = cnt0 + cnt1; i < n; i++) {
            nums[k++] = 2;
        }
    }

    // Better Approach=================One pass solution
    public static void sortColors_Better(int[] nums) {
        int n = nums.length;
        int ptr = -1;
        int itr = 0;
        int ptr2 = n - 1;
        while (itr <= ptr2) {
            if (nums[itr] == 0) {
                ptr = ptr + 1;
                swap(ptr, itr, nums);
                itr++;
            } else if (nums[itr] == 1) {
                itr++;
            } else {
                swap(itr, ptr2, nums);
                ptr2--;
            }
        }
    }

    // ============================================GFG MAX sun in the
    // Configuration==================================
    // Link : https://www.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    // Naive approach
    // TC: O(n^2);
    int maxSum(int[] arr) {
        // code here
        int k = arr.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            int temp = arr[0];
            int sum = 0;

            for (int j = 1; j < k; j++) {
                sum += (j * arr[j]);
                arr[j - 1] = arr[j];
            }
            maxSum = Math.max(sum, maxSum);
            arr[k - 1] = temp;
        }
        return maxSum;

    }

    // better appraoch -> Single Time array traversal;
    int maxSum_better(int[] arr) {
        // code here
        int n = arr.length;
        int sum = 0;
        int sumidx = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sumidx += i * arr[i];
        }
        int maxSum = sumidx;

        for (int i = 1; i < n; i++) {
            sumidx = (sumidx - sum) + n * arr[i - 1];
            maxSum = Math.max(maxSum, sumidx);

        }
        return maxSum;

    }

    // ========================================LEETCODE
    // 11=================================================
    // Naive Approach
    // TC: O(n^2)
    public int maxWater(int arr[]) {
        // Code Here
        int n = arr.length;
        if (n == 0)
            return 0;
        int maxwater = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int width = j - i;
                int height = Math.min(arr[i], arr[j]);
                maxwater = Math.max(maxwater, width * height);
            }
        }
        return maxwater;

    }

    // better approach
    // TC: O(n)
    public int maxWater_better(int arr[]) {
        // Code Here
        int n = arr.length;
        if (n == 0)
            return 0;
        int si = 0;
        int ei = n - 1;
        int maxwater = 0;
        while (si < ei) {
            int width = ei - si;
            int height = Math.min(arr[si], arr[ei]);
            maxwater = Math.max(maxwater, width * height);
            if (arr[si] < arr[ei]) {
                si++;
            } else {
                ei--;
            }

        }
        return maxwater;
    }

    public static void main(String args[]) {
        // int[] arr = { -12, 11, -13, -5, 6, -7, 5, -3, -6 };
        // Rearrange_withOrder(arr);

        // int arr[] = { 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0 };
        // int arr[] = { 1, 1, 1, 1, 1 };
        int arr[] = { 2, 0, 1, 1, 0, 1, 2 };
        sortColors(arr);
        print1D(arr);

    }

}