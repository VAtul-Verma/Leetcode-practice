import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class l001 {

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ====================================Binary
    // Search=====================================
    public static int BinarySearch(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] > data) {
                r = mid - 1;
            } else if (arr[mid] < data) {
                l = mid + 1;
            }
        }
        return -1;
    }

    // ============================================LEETCODE 34
    // ======================================================
    public static int FirstIndex(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == data) {
                if (mid - 1 >= 0 && arr[mid - 1] == data) {
                    r = mid - 1;
                } else {
                    return mid;
                }
            } else if (arr[mid] < data) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    // this will give the firs index of the data even the data is dublicate also
    public static int FirstIndex_01(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < data) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l < n && arr[l] == data ? l : -1;
    }

    public static int LastIndex(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == data) {
                if (mid + 1 < n && arr[mid + 1] == data) {
                    l = mid + 1;
                    ;
                } else {
                    return mid;
                }
            } else if (arr[mid] < data) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static int LastIndex_01(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= data) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l - 1 >= 0 && arr[l - 1] == data ? l - 1 : -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int ans[] = new int[2];
        ans[0] = FirstIndex(nums, target);
        ans[1] = LastIndex(nums, target);
        return ans;
    }

    // =====================================================LEETCODE
    // 35====================================================
    public static int searchInsert_Helper(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] > data) {
                r = mid - 1;
            } else if (arr[mid] < data) {
                l = mid + 1;
            }
        }
        return l;
    }

    // we can avoid the == check in the above code
    public static int searchInsert_Helper01(int[] arr, int data) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= data) {
                r = mid - 1;
            } else if (arr[mid] < data) {
                l = mid + 1;
            }
        }
        return l;
    }

    // ========================================================GFG Floor in a Sort
    // array==================================
    // Link:
    // https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1
    public static int findFloor(int[] arr, int data) {
        // code here
        int n = arr.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == data) {
                l = mid + 1;
            }

            if (arr[mid] > data) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return r;

    }

    // =========================GFG Find the closest number====================
    // Link: https://www.geeksforgeeks.org/problems/find-the-closest-number5513/1
    public static int findClosest(int[] arr, int data) {
        // code here
        int n = arr.length;
        if (data > arr[n - 1])
            return arr[n - 1];
        if (data < arr[0])
            return arr[0];
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= data) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (l - 1 >= 0 && (arr[l] - data) > data - arr[r]) {
            return arr[r];
        } else {
            return arr[l];
        }

    }

    // ====================================GFG COUNT
    // INVERSION================================================
    public int inversionCount(int arr[]) {
        int N = arr.length;
        if (N == 0)
            return 0;

        int[] sortedArray = new int[N];
        return inversionCount(arr, 0, N - 1, sortedArray);

    }

    public int inversionCount(int arr[], int si, int ei, int[] sortedArray) {
        // code here
        if (si >= ei)
            return 0;

        int mid = si + (ei - si) / 2;
        int ICL = inversionCount(arr, si, mid, sortedArray); // ICL=inversion count left
        int ICR = inversionCount(arr, mid + 1, ei, sortedArray);// ICR=inversion count Right

        return (ICL + ICR + inversonCountAccrossThearray(arr, sortedArray, si, ei, mid));

    }

    public int inversonCountAccrossThearray(int[] arr, int[] sortedArray, int si, int ei, int mid) {
        int i = (int) si, j = (int) mid + 1, k = (int) si;
        int count = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else {
                sortedArray[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid || j <= ei)
            sortedArray[k++] = arr[i <= mid ? i++ : j++];

        while (si <= ei)
            arr[(int) si] = sortedArray[(int) si++];

        return count;
    }

    // =======================================LEETCODE
    // 33===========================================
    public int search(int[] arr, int target) {
        int l = 0;
        int n = arr.length;
        int r = arr.length - 1;
        if (arr[0] == target)
            return 0;
        if (arr[n - 1] == target)
            return n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[l] <= arr[mid]) {
                if (arr[l] <= target && arr[mid] > target)
                    r = mid - 1;
                else {
                    l = mid + 1;
                }
            } else {
                if (target > arr[mid] && target <= arr[r])
                    l = mid + 1;
                else {
                    r = mid - 1;
                }
            }

        }
        return -1;
    }

    // ====================================================LEETCODE
    // 81================================================
    public boolean search81(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;

        while (si <= ei) {

            int mid = (si + ei) / 2;

            if (arr[mid] == tar || arr[ei] == tar)
                return true;
            else if (arr[si] < arr[mid]) {
                if (arr[si] <= tar && tar < arr[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            } else if (arr[mid] < arr[ei]) {
                if (arr[mid] < tar && tar <= arr[ei])
                    si = mid + 1;
                else
                    ei = mid - 1;
            } else
                ei--;
        }

        return false;
    }

    // ======================================LEETCODE 153
    // =================================================
    public int findMin153(int[] nums) {
        int n = nums.length;
        int si = 0;
        int ei = n - 1;
        if (nums[si] <= nums[ei])
            return nums[si];
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] < nums[ei]) {
                ei = mid;
            } else if (nums[si] <= nums[mid]) {
                si = mid + 1;
            }
        }
        return nums[si];
    }

    // =============================================LEETCODE
    // 154==============================
    public int findMin(int[] nums) {
        int n = nums.length;
        int si = 0;
        int ei = n - 1;
        if (nums[si] < nums[ei])
            return nums[si];
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] < nums[ei]) {
                ei = mid;
            } else if (nums[ei] < nums[mid]) {
                si = mid + 1;
            } else {
                ei--;
            }
        }
        return nums[si];
    }

    // ==============================================LEETCODE
    // 167====================
    public int[] twoSum(int[] arr, int tar) {
        int n = arr.length;
        int si = 0;
        int ei = n - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == tar) {
                return new int[] { si + 1, ei + 1 };
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
        return new int[] {};
    }

    // ========================================gfg 2 Sum – Count distinct pairs with
    // given sum==============
    // Link:
    // https://www.geeksforgeeks.org/problems/2-sum-count-distinct-pairs-with-given-sum/1

    public static int countDistinctPairs(int arr[], int tar) {

        // Your code here
        int res = 0;
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == tar) {
                res++;
                while (left < right && arr[left] == arr[left + 1])
                    left++;
                while (left < right && arr[right] == arr[right - 1])
                    right--;
                left++;
                right--;

            } else if (sum < tar) {
                left++;
            } else {
                right--;
            }

        }
        return res;
    }

    // ===========================LEETCODE 15===================================
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return threesum(nums, 0, 0, nums.length - 1);
    }

    public void makeAnswer(List<List<Integer>> ans, List<List<Integer>> smallans, int ele) {

        for (List<Integer> l : smallans) {
            List<Integer> newsmallans = new ArrayList<>();
            newsmallans.add(ele);
            for (int i : l) {
                newsmallans.add(i);
            }
            ans.add(newsmallans);
        }
    }

    public List<List<Integer>> threesum(int[] nums, int tar, int si, int ei) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = si; i < ei;) {

            List<List<Integer>> smallans = twoSum(nums, tar - nums[i], i + 1, ei);
            makeAnswer(ans, smallans, nums[i]);
            i++;
            while (i < ei && nums[i] == nums[i - 1])
                i++;
        }

        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int tar, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        while (si < ei) {
            int sum = nums[si] + nums[ei];
            if (sum == tar) {
                List<Integer> smallans = new ArrayList<>();
                smallans.add(nums[si]);
                smallans.add(nums[ei]);
                ans.add(smallans);
                while (si < ei && nums[si] == nums[si + 1])
                    si++;
                while (si < ei && nums[ei] == nums[ei - 1])
                    ei--;
                si++;
                ei--;

            } else if (sum > tar) {
                ei--;
            } else {
                si++;
            }
        }
        return ans;
    }

    // =======================================================LEETCODE 18
    // ===============================
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return fourSum(nums, (long) target, 0, nums.length - 1);

        }

        public List<List<Integer>> fourSum(int[] nums, long tar, int si, int ei) {
            int n = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = si; i < ei;) {

                List<List<Integer>> smallans = threesum(nums, tar - nums[i], i + 1, ei);
                makeAnswer(ans, smallans, nums[i]);
                i++;
                while (i < ei && nums[i] == nums[i - 1])
                    i++;
            }

            return ans;
        }

        public void makeAnswer(List<List<Integer>> ans, List<List<Integer>> smallans, int ele) {

            for (List<Integer> l : smallans) {
                List<Integer> newsmallans = new ArrayList<>();
                newsmallans.add(ele);
                for (int i : l) {
                    newsmallans.add(i);
                }
                ans.add(newsmallans);
            }
        }

        public List<List<Integer>> threesum(int[] nums, long tar, int si, int ei) {
            int n = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = si; i < ei;) {

                List<List<Integer>> smallans = twoSum(nums, tar - nums[i], i + 1, ei);
                makeAnswer(ans, smallans, nums[i]);
                i++;
                while (i < ei && nums[i] == nums[i - 1])
                    i++;
            }

            return ans;
        }

        public List<List<Integer>> twoSum(int[] nums, long tar, int si, int ei) {
            List<List<Integer>> ans = new ArrayList<>();
            while (si < ei) {
                long sum = nums[si] + nums[ei];
                if (sum == tar) {
                    List<Integer> smallans = new ArrayList<>();
                    smallans.add(nums[si]);
                    smallans.add(nums[ei]);
                    ans.add(smallans);
                    while (si < ei && nums[si] == nums[si + 1])
                        si++;
                    while (si < ei && nums[ei] == nums[ei - 1])
                        ei--;
                    si++;
                    ei--;

                } else if (sum > tar) {
                    ei--;
                } else {
                    si++;
                }
            }
            return ans;
        }
    }

    // ============================================K SUM
    // ======================================
    public List<List<Integer>> kSum(int[] arr, int target, int k, int si, int ei) {
        if (k == 2)
            return twoSum(arr, target, si, ei);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = si; i < ei;) {
            List<List<Integer>> smallAns = kSum(arr, target - arr[i], k - 1, i + 1, ei);
            makeAnswer(ans, smallAns, arr[i]);

            i++;
            while (i < ei && arr[i] == arr[i - 1])
                i++;
        }

        return ans;
    }

    // ==================================================LEETCODE
    // 454=============================
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int arr1[] = new int[n * n];
        int k = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                arr1[k++] = i + j;
            }
        }

        k = 0;
        int arr2[] = new int[n * n];
        for (int i : nums3) {
            for (int j : nums4) {
                arr2[k++] = i + j;
            }
        }

        return twoSuncnt(arr1, arr2, 0);
    }

    public static int twoSuncnt(int[] arr1, int[] arr2, int tar) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int ele : arr1) {
            m.put(ele, m.getOrDefault(ele, 0) + 1);
        }
        int cnt = 0;
        for (int ele : arr2) {
            if (m.containsKey(tar - ele)) {
                cnt += m.get(tar - ele);
            }
        }
        return cnt;
    }

    // using the HashMap Only;
    public int fourSumCountHashMap(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int e1 : nums1)
            for (int e2 : nums2)
                map.put(e1 + e2, map.getOrDefault(e1 + e2, 0) + 1);

        int count = 0, target = 0;
        for (int e1 : nums3)
            for (int e2 : nums4)
                if (map.containsKey(target - e1 - e2))
                    count += map.get(target - e1 - e2);

        return count;
    }

    // ========================================658=========================================
    public int insertationPos(int[] nums, int data) {
        int n = nums.length;
        int si = 0;
        int ei = n - 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return si;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int n = arr.length;
        for (int ele : arr) {
            ans.add(ele);
        }
        if (x < arr[0])
            return ans.subList(0, k);
        else if (x > arr[n - 1])
            return ans.subList(n - k, n);
        else {
            int idx = insertationPos(arr, x);
            int ll = Math.max(0, idx - k);
            int rr = Math.min(n - 1, idx + k);
            while ((rr - ll + 1) > k) {
                if (x - arr[ll] > arr[rr] - x)
                    ll++;
                else
                    rr--;
            }
            return ans.subList(ll, rr + 1);
        }

    }

    // =============================================Solution2
    // ==================================
    public List<Integer> findClosestElements02(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
    // =========================================LEETCODE
    // 300==============================================

    public static void main(String[] args) {

    }
}