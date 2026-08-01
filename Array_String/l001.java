import java.util.HashMap;
import java.util.Map;

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

    // ====================================LEETCODE 3
    // ===================================
    // Naive Approach : this approach will only work with Charactrer
    // Tc:O(n^2)
    // GFG working code not on LeetCode
    public int longestUniqueSubstr(String s) {
        // code here
        int maxLen = Integer.MIN_VALUE;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            boolean[] vis = new boolean[26];
            for (int j = i; j < n; j++) {
                if (vis[s.charAt(j) - 'a'] == true)
                    break;
                else {
                    maxLen = Math.max(maxLen, j - i + 1);
                    vis[s.charAt(j) - 'a'] = true;
                }
            }
        }
        return maxLen;

    }

    // =better approach ==using windows sliding techmique=================
    // TC: o(n)
    public int lengthOfLongestSubstring(String s) {
        // code here
        int si = 0;
        int ei = 0;
        int n = s.length();
        int len = 0;
        int cnt = 0;
        int freq[] = new int[128];
        while (ei < n) {
            char ch = s.charAt(ei);
            if (freq[ch] > 0) {
                cnt++;
            }
            freq[ch]++;
            ei++;

            while (cnt > 0) {
                char startchar = s.charAt(si);
                if (freq[startchar] > 1) {
                    cnt--;
                }
                freq[startchar]--;
                si++;

            }
            len = Math.max(len, ei - si);
        }
        return len;
    }

    // ================================Leetcode
    // 159=======================================
    // Naive Appraoch :
    // TC:O(n^1) Sp always 2 size hashmap so can say the constaint space;
    public static int longSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int gsi = 0;
        int gei = 0;
        ;
        for (int i = 0; i < n; i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.size() > 2) {
                    break;
                }
                int len = j - i + 1;
                if (len > maxLen) {
                    maxLen = len;
                    gsi = i;
                    gei = j;
                }
                maxLen = Math.max(maxLen, j - i + 1);
            }

        }
        System.out.println(s.substring(gsi, gei + 1));
        return maxLen;
    }

    // better approach :
    // TC: O(n) : using the windows sliding technique
    public static int longSubstring_better(String s) {
        int freq[] = new int[128];
        int n = s.length();
        int si = 0;
        int ei = 0;
        int cnt = 0;
        int maxLen = 0;
        while (ei < n) {
            char ch = s.charAt(ei);
            if (freq[ch] == 0) {
                cnt++;
            }
            freq[ch]++;
            ei++;

            while (cnt > 2) {
                char removechar = s.charAt(si);
                freq[removechar]--;
                si++;
                if (freq[removechar] == 0)
                    cnt--;
            }
            maxLen = Math.max(maxLen, ei - si);
        }
        return maxLen;

    }

    // using HashMap
    public static int longSubstring_Map(String s) {
        int n = s.length();
        Map<Character, Integer> count = new HashMap<>();
        int i = 0, maxLen = 0;

        for (int j = 0; j < n; j++) {
            count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);

            // If more than 2 distinct characters, shrink from left
            while (count.size() > 2) {
                char c = s.charAt(i);
                count.put(c, count.get(c) - 1);
                if (count.get(c) == 0)
                    count.remove(c);
                i++;
            }

            // Update maximum length
            maxLen = Math.max(maxLen, j - i + 1);
        }

        return maxLen;
    }

    // ===========================================Leetcode 76
    // =================================================
    // brute froces: check that string str2 all charater are present in
    // the str1's each substring and return the minimum len substring

    // TC: O(n^3) S.C:O(1)
    public static boolean hasAllcharacter(String str, String t) {
        int[] freq = new int[256];
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            freq[ch]++;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch]--;
        }

        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0)
                return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {
        String res = "";
        int minLen = (int) 1e9;
        int ns = s.length();
        int nt = t.length();
        for (int i = 0; i < ns; i++) {
            for (int j = i; j < ns; j++) {
                String substr = s.substring(i, j + 1);
                if (hasAllcharacter(substr, t)) {
                    int currlen = substr.length();
                    if (currlen < minLen) {
                        minLen = currlen;
                        res = substr;
                    }
                }
            }
        }
        return res;
    }

    // Better Approach:
    public String minWindow_better(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++) {
            char ch = t.charAt(i);
            freq[ch]++;
        }
        int si = 0;
        int ei = 0;
        int count = nt;
        int len = (int) 1e9;
        int gsi = 0;
        while (ei < ns) {
            char ch = s.charAt(ei);
            if (freq[ch] > 0) {
                count--;
            }
            freq[ch]--;
            ei++;
            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }
                char removechar = s.charAt(si);
                if (freq[removechar] == 0) {
                    count++;
                }
                freq[removechar]++;
                si++;

            }
        }
        return len == (int) 1e9 ? "" : s.substring(gsi, gsi + len);
    }

    // ===============================Smallest distinct
    // window==============================
    // Link: https://www.geeksforgeeks.org/problems/smallest-distant-window3132/1

    // Navie Approach: TC: O(n^2);

    public int findSubString(String str) {

        boolean vis[] = new boolean[256];
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (!vis[ch]) {
                vis[ch] = true;
            }
        }
        int minlen = (int) 1e9;
        for (int i = 0; i < n; i++) {
            boolean[] curr = new boolean[256];
            for (int j = i; j < n; j++) {
                char ch = str.charAt(j);
                curr[ch] = true;
                if (hascharacter(curr, vis)) {
                    minlen = Math.min(minlen, j - i + 1);
                }
            }
        }
        return minlen;

    }

    public boolean hascharacter(boolean[] curr, boolean[] vis) {
        int n = curr.length;

        for (int i = 0; i < n; i++) {
            if (vis[i] != curr[i])
                return false;
        }
        return true;
    }

    // Better Approach
    // TC: O(2n)
    public int findSubString_better(String str) {
        int[] freq = new int[256];
        int n = str.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (freq[ch] == 0) {
                freq[ch] = 1;
                cnt++;
            }
        }
        int si = 0, ei = 0, len = (int) 1e9;
        int actualLen = cnt;
        while (ei < n) {
            char ch = str.charAt(ei);
            if (freq[ch] > 0) {
                cnt--;
            }
            freq[ch]--;
            ei++;
            while (cnt == 0) {
                if (ei - si < len) {
                    len = ei - si;
                }
                char dropchar = str.charAt(si);
                if (freq[dropchar] == 0)
                    cnt++;
                freq[dropchar]++;
                si++;
            }
            if (len == actualLen)
                break;
        }
        return len;
    }

    // ==============================leetcode
    // 340===========================================
    public static int lengthoglongestSubtringWithkDistinctCharacter(String s, int k) {
        int n = s.length();
        int si = 0;
        int ei = 0;
        int cnt = 0;
        int len = (int) -1e9;
        int freq[] = new int[128];
        while (ei < n) {
            char ch = s.charAt(ei);
            if (freq[ch] == 0) {
                cnt++;

            }
            freq[ch]++;
            ei++;
            while (cnt > k) {
                char remvoechar = s.charAt(si);
                if (freq[ch] == 1) {
                    cnt--;
                }
                freq[ch]--;
                si++;
            }
            if (len < ei - si) {
                len = ei - si;
            }
        }
        return len;
    }

    // ====================================Leetcode
    // 1456========================================
    // Naive Approach TC: O(n^k)
    public int maxVowels(String s, int k) {
        int Maxvowel = 0;
        int n = s.length();
        if (n < k)
            return n;
        for (int i = 0; i <= n - k; i++) {
            int vowelcnt = 0;
            for (int j = i; j < i + k; j++) {
                char ch = s.charAt(j);
                if (ch == 'a' || ch == 'i' || ch == 'e' || ch == 'o' || ch == 'u') {
                    vowelcnt++;
                }
            }
            Maxvowel = Math.max(Maxvowel, vowelcnt);
        }
        return Maxvowel;
    }

    // better Approach :using the window sliding techinge

    // =========================================GFG Longest Substring with K
    // Uniques==================
    // Link:
    // https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1

    // Naive Approach: O(n^2)
    public int longestKSubstr(String s, int k) {
        // code here
        int n = s.length();
        int maxLen = -1;
        for (int i = 0; i < n; i++) {
            int[] freq = new int[128];
            int uniquecnt = 0;
            for (int j = i; j < n; j++) {

                char ch = s.charAt(j);
                if (freq[ch] == 0)
                    uniquecnt++;

                freq[ch]++;

                if (uniquecnt == k)
                    maxLen = Math.max(maxLen, j - i + 1);
                else if (uniquecnt > k)
                    break;
            }

        }
        return maxLen;

    }

    // Better Approach: TC : O(N) using the windows sliding
    public int longestKSubstr_better(String s, int k) {
        // code here
        int n = s.length();
        int si = 0, ei = 0, cnt = 0, maxLen = -1;
        int freq[] = new int[128];

        while (ei < n) {
            char ch = s.charAt(ei);
            if (freq[ch] == 0) {
                cnt++;
            }
            freq[ch]++;
            ei++;
            while (cnt > k) {
                char removechar = s.charAt(si);
                if (freq[removechar] == 1) {
                    cnt--;
                }
                freq[removechar]--;
                si++;

            }
            if (cnt == k) {
                maxLen = Math.max(maxLen, ei - si);
            }
        }
        return maxLen;

    }

    // ===============================================LEETOCDE
    // 992=======================================

    // ==========================================LEETCODE
    // 1248==============================
    // naive appraoch: TC: O(n^2);
    public int numberOfSubarrays(int[] arr, int k) {
        int ans = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] % 2 == 1)
                    cnt++;
                if (cnt == k) {
                    ans++;

                }
            }
        }
        return ans;
    }

    // better appraoch TC : O(N)
    // find the atmost k and atmost k -1 then get the exact by atmost(k)-atmost(k-1)
    public int numberOfSubarrays_better(int[] nums, int k) {
        return AtmostKOddArray(nums, k) - AtmostKOddArray(nums, k - 1);
    }

    public int AtmostKOddArray(int[] arr, int k) {
        int n = arr.length;
        int si = 0;
        int ei = 0;
        int cnt = 0;
        int ans = 0;
        while (ei < n) {
            if (arr[ei] % 2 == 1)
                cnt++;
            ei++;
            while (cnt > k) {
                if (arr[si] % 2 == 1)
                    cnt--;
                si++;
            }
            ans += ei - si;
        }
        return ans;
    }

    // =============================LEETCODE 904=================================
    // naive approach TC: O(N)
    public int totalFruit(int[] arr) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int si = 0, ei = 0, ans = 0, n = arr.length;
        while (ei < n) {
            m.put(arr[ei], m.getOrDefault(arr[ei], 0) + 1);

            ei++;
            while (m.size() > 2) {
                m.put(arr[si], m.getOrDefault(arr[si], 0) - 1);
                if (m.get(arr[si]) == 0)
                    m.remove(arr[si]);
                si++;
            }
            ans = Math.max(ans, ei - si);
        }
        return ans;
    }

    // ==================================LEETCODE 930========================
    // Naive Appraoch : O(N^2)
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal)
                    ans++;
            }
        }
        return ans;
    }

    // Better aprraoch
    // TC: O(N)
    public int numSubarraysWithSum_better(int[] arr, int k) {
        return atmostK_Helper(arr, k) - (k != 0 ? atmostK_Helper(arr, k - 1) : 0);
    }

    public int atmostK_Helper(int[] arr, int k) {

        int n = arr.length, si = 0, ei = 0, sum = 0, count = 0;
        while (ei < n) {
            sum += arr[ei++];

            while (sum > k) {
                sum -= arr[si++];
            }
            count += ei - si;

        }
        return count;

    }

    // ==============================LeetCode 485================================
    // Naive Approach O(n^2)
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int maxlen = 0;
        for (int i = 0; i < n; i++) {
            int len = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0)
                    break;
                else {
                    len++;
                }
            }
            maxlen = Math.max(len, maxlen);
        }
        return maxlen;

    }

    // better Approach
    // TC: O(n);
    public int findMaxConsecutiveOnes_(int[] nums) {
        int n = nums.length;
        int si = 0;
        int ei = 0, len = 0, maxLen = 0;
        while (ei < n) {
            if (nums[ei] == 1)
                ei++;
            else {
                len = ei - si;
                maxLen = Math.max(maxLen, len);
                si = ei + 1;
                ei++;
            }
        }
        // Check the last sequence of 1s
        maxLen = Math.max(maxLen, ei - si);
        return maxLen;
    }

    // ================================LeetCode 487 and 1004 both are
    // same=====================================
    // Naive approach TC: O(N^2)
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxlen = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0)
                    cnt++;

                if (cnt > k) {
                    break;
                }
                maxlen = Math.max(maxlen, j - i + 1);
            }

        }

        return maxlen;
    }

    // Better Approach TC: O(n)
    public static int longestOnes_Better(int[] arr, int k) {
        int si = 0, ei = 0, count = 0, maxLen = 0;
        int n = arr.length;
        while (ei < n) {
            if (arr[ei] == 0)
                count++;
            ei++;
            while (count > k) {
                if (arr[si] == 0)
                    count--;
                si++;
            }
            maxLen = Math.max(maxLen, ei - si);
        }

        return maxLen;
    }

    // ===================================LEETCODE 974
    // ===============================
    // Naive Approach :O(N^2)
    public int subarraysDivByK(int[] arr, int k) {
        int n = arr.length;
        int totalsubarraycount = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum % k == 0)
                    totalsubarraycount++;
            }
        }
        return totalsubarraycount;
    }

    // better Approach : TC:O(n)
    public int subarraysDivByK_Better(int[] arr, int k) {
        int ei = 0, sum = 0, ans = 0, n = arr.length;
        int[] rem = new int[k];
        rem[0] = 1;
        while (ei < n) {
            sum += arr[ei];
            ei++;
            int r = (sum % k + k) % k;
            ans += rem[r];
            rem[r]++;

        }
        return ans;

    }

    // ======================================GFG Subarrays with equal 1s and
    // 0s=============
    // LINK:
    // https://www.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    // NAIVE APPROACH : O(N^2)
    public int countSubarray(int[] arr) {
        // code here
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cntone = 0;
            int cntzero = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] == 1)
                    cntone++;
                else if (arr[j] == 0)
                    cntzero++;
                if (cntone == cntzero) {
                    ans++;
                }
            }
        }
        return ans;

    }

    // BETTER APPRAOCCH TC:O(N)
    public int countSubarray_BETTTER(int[] arr) {
        // code here
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ei = 0, count = 0, sum = 0;
        while (ei < n) {
            int val = arr[ei++];
            sum += val;
            if (val == 0)
                sum += -1;// if val==0 then replace it with -1 and add the -1 in sum
            count += map.getOrDefault(sum, 0);// get from map
            map.put(sum, map.getOrDefault(sum, 0) + 1); // increase the freq of hashMap by 1
        }
        return count;
    }

    // ==========================LEETCODE 524==============================
    // NaIVE APPRAOCH :O(N^2)
    public int findMaxLength(int[] arr) {
        int n = arr.length;
        int maxlen = 0;
        for (int i = 0; i < n; i++) {
            int cntzero = 0;
            int cntone = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] == 1) {
                    cntone++;
                } else {
                    cntzero++;
                }
                if (cntone == cntzero) {
                    maxlen = Math.max(maxlen, j - i + 1);
                }
            }
        }
        return maxlen;
    }

    // better appraoch using HashMap TC: O(N)
    public int findMaxLength_better(int[] arr) {
        int ei = 0;
        int n = arr.length;
        int len = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        while (ei < n) {
            int val = arr[ei];
            sum += val;
            if (val == 0)
                sum += -1;
            map.putIfAbsent(sum, ei);// if not in map then put it
            len = Math.max(len, ei - map.get(sum));
            ei++;
        }
        return len;
    }

    // ============================LEETCODE 239=========================
    // TC:O(nK)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int m = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                m = Math.max(nums[j], m);
            }
            ans[i] = m;
        }
        return ans;
    }

    // ===========LEETCODE 53========================
    // Naive approach Tc: O(n2)
    public int maxSubArray(int[] nums) {
        int maxsum = -(int) 1e9;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int currsum = 0;
            for (int j = i; j < n; j++) {
                currsum += nums[j];
                maxsum = Math.max(currsum, maxsum);
            }
        }
        return maxsum;
    }

    // Better Appraoch TC: O(N);
    public int maxSubArray_better(int[] arr) {
        int curr = arr[0];
        int maxsum = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (curr > 0)
                curr += arr[i];
            else {
                curr = arr[i];
            }
            maxsum = Math.max(maxsum, curr);

        }
        return maxsum;
    }

    // =======================LEETCODE
    // 1191===============================================
    public int kConcatenationMaxSum(int[] arr, int k) {
        int[] modified = new int[arr.length * k];

        for (int i = 0; i < modified.length; i++) {
            modified[i] = arr[i % arr.length];
        }

        long ans = maxSubArray_helper(modified);

        return ans < 0 ? 0 : (int) (ans % 1000000007);
    }

    public long maxSubArray_helper(int[] arr) {
        long curr = arr[0];
        long maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            maxSum = Math.max(maxSum, curr);
        }

        return maxSum;
    }

    // ================Better appraoch TC:O(N)
    int mod = (int) 1e9 + 7;

    public int kConcatenationMaxSum_Better(int[] arr, int k) {
        long one = maxSubArray(arr, 1);
        if (k == 1)
            return (int) one;

        long two = maxSubArray(arr, 2);

        long totalSum = 0;
        for (int x : arr)
            totalSum += x;

        if (totalSum > 0) {
            long ans = (two + (k - 2) * totalSum) % mod;
            return (int) ans;
        } else {
            return (int) (two % mod);
        }
    }

    public int maxSubArray(int[] arr, int k) {
        int n = arr.length;
        long gsum = arr[0];
        long currsum = arr[0];

        for (int i = 1; i < n * k; i++) {
            int ele = arr[i % n];
            currsum = Math.max(ele, currsum + ele);
            gsum = Math.max(gsum, currsum);
        }

        return (int) Math.max(gsum, 0);
    }

    // More_better_approach

    public int kConcatenationMaxSum_2(int[] arr, int k) {
        int kadansSum = maxSubArray_(arr, 1);
        if (k == 1)
            return kadansSum;
        long prefixSum = 0, suffixSum = 0, maxPrefixSum = 0, maxSuffixSum = 0, arraySum = 0;
        int n = arr.length;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            prefixSum += arr[i];
            suffixSum += arr[j];
            arraySum += arr[i];

            maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
            maxSuffixSum = Math.max(maxSuffixSum, suffixSum);
        }
        arraySum = arraySum < 0 ? 0 : arraySum % mod;
        return (int) Math.max(kadansSum, maxPrefixSum + maxSuffixSum + ((k - 2) * arraySum) % mod) % mod;
    }

    public int maxSubArray_(int[] arr, int k) {
        int n = arr.length;
        long gsum = arr[0];
        long currsum = arr[0];

        for (int i = 1; i < n * k; i++) {
            int ele = arr[i % n];
            currsum = Math.max(ele, currsum + ele);
            gsum = Math.max(gsum, currsum);
        }

        return (int) Math.max(gsum, 0);
    }

    public static void main(String args[]) {
        // int[] arr = { -12, 11, -13, -5, 6, -7, 5, -3, -6 };
        // Rearrange_withOrder(arr);

        // int arr[] = { 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0 };
        // int arr[] = { 1, 1, 1, 1, 1 };
        // int arr[] = { 2, 0, 1, 1, 0, 1, 2 };
        // sortColors(arr);
        // print1D(arr);

        System.out.println(longSubstring_better("geeksforgeeks"));

    }

}