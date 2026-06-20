import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class HeapQuestions {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    // GFG: kthSmallest Link:
    // https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1
    public int kthSmallest(int[] arr, int k) {
        // Code here
        int n = arr.length;

        // bydefault it is minHeap so for maxHeap other-this
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i : arr) {
            maxHeap.add(i);
            if (maxHeap.size() > k)
                maxHeap.remove();
        }
        return maxHeap.peek();

    }

    // Kth largest with better TC: O(N);
    public static boolean compareTo(int[] arr, int x, int y, boolean isIncreasing) {
        if (isIncreasing)
            return arr[x] > arr[y];
        return arr[y] > arr[x];
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void downHeapify(int[] arr, int pi, int li, boolean isIncreasing) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxIdx = pi;

        if (lci <= li && compareTo(arr, lci, maxIdx, isIncreasing))
            maxIdx = lci;
        if (rci <= li && compareTo(arr, rci, maxIdx, isIncreasing))
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(arr, maxIdx, pi);
            downHeapify(arr, maxIdx, li, isIncreasing);
        }

    }

    public int kthSmallestbetter(int[] arr, int k) {
        int n = arr.length;
        int r = k;
        boolean isIncreasing = false;
        int li = arr.length - 1;

        // we will create a max heap
        for (int i = li; i >= 0; i--)
            downHeapify(arr, i, li, isIncreasing);

        // sort array
        while (li > 0 && k-- > 0) {
            swap(arr, 0, li--);
            downHeapify(arr, 0, li, isIncreasing);
        }
        return arr[n - r];

    }

    // =======================================Leetcode 703
    // PriorityQueue<Integer>maxHeap = new PriorityQueue<>();
    // int K;
    // public KthLargest703(int k, int[] nums) {
    // K=k;

    // for(int i:nums){
    // // if(maxHeap.size()>k) maxHeap.remove();
    // maxHeap.add(i);
    // if(maxHeap.size()>k) maxHeap.remove();
    // }

    // }

    // public int add(int val) {
    // maxHeap.add(val);
    // if(maxHeap.size()>K) maxHeap.remove();
    // return maxHeap.peek();
    // }

    // =========================================LeetCode
    // 378============================================

    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;
            return mat[i1][j1] - mat[i2][j2];

        });

        // put the first column idx in heap
        for (int i = 0; i < n; i++) {
            heap.add(i * m + 0);
        }
        int r = 0, c = 0;
        while (k-- > 0) {
            // get top 1D idx
            int idx = heap.remove();
            // convert it inot 2D
            r = idx / m;
            c = idx % m;
            if (c + 1 < m)
                heap.add(r * m + c + 1); // put the next element of that row into heap
        }
        return mat[r][c];
    }

    // ============================LeetCode
    // 347========================================

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans[] = new int[k];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Store the number itself in the heap and compare using its frequency in the
        // map:
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a, b) -> map.get(a) - map.get(b));
        for (int num : map.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        for (int j = k - 1; j >= 0; j--) {
            ans[j] = heap.remove();
        }
        return ans;
    }

    // ==================================================LEETCODE
    // 973=============================================
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
            int d1 = points[a][0] * points[a][0] + points[a][1] * points[a][1];
            int d2 = points[b][0] * points[b][0] + points[b][1] * points[b][1];
            return d2 - d1;

        });

        for (int i = 0; i < points.length; i++) {
            heap.add(i);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        int ans[][] = new int[k][];
        int i = 0;
        while (heap.size() != 0) {
            int idx = heap.remove();
            ans[i++] = points[idx];
        }
        return ans;
    }

    // ==================================Leetcode 692
    // ======================================
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {

            int fa = map.get(a);
            int fb = map.get(b);
            // if the frequency is same return the lexicographical order
            if (fa == fb) {
                return b.compareTo(a);
            }
            return fa - fb;
        });
        for (String s : map.keySet()) {
            heap.add(s);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        List<String> ans = new LinkedList<>();
        while (heap.size() != 0) {
            ans.addFirst(heap.remove());
        }
        return ans;
    }

    // leetcode 778====================================
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;

            return grid[i1][j1] - grid[i2][j2];
        });

        boolean[][] vis = new boolean[n][m];
        heap.add(0);
        vis[0][0] = true;
        int minheight = 0;
        while (heap.size() != 0) {
            int idx = heap.remove();
            int i = idx / m;
            int j = idx % m;
            int height = grid[i][j];
            minheight = Math.max(minheight, height);

            if (i == n - 1 && j == m - 1)
                break;

            for (int[] d : dir) {
                int r = i + d[0];
                int c = j + d[1];
                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    vis[r][c] = true;
                    heap.add(r * m + c);

                }
            }
        }
        return minheight;
    }

    // ======================================Leetcode
    // 1642=============================================
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = heights.length;
        for (int i = 1; i < n; i++) {
            int diff = heights[i] - heights[i - 1];
            if (diff > 0)
                heap.add(diff);
            if (heap.size() > ladders) {
                bricks -= heap.remove();
            }
            if (bricks < 0)
                return i - 1;
        }

        return n - 1;
    }

    // ======================================LeetCode 632
    // =========================================
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            int r1 = a[0], c1 = a[1];
            int r2 = b[0], c2 = b[1];
            return nums.get(r1).get(c1) - nums.get(r2).get(c2);
        });

        int maxValue = -(int) 1e9;

        for (int i = 0; i < n; i++) {
            heap.add(new int[] { i, 0 });
            maxValue = Math.max(maxValue, nums.get(i).get(0));
        }

        int range = (int) 1e9, sp = -1, ep = -1;

        while (heap.size() == n) {
            int[] res = heap.remove();
            int r = res[0];
            int c = res[1];
            int val = nums.get(r).get(c);
            if (maxValue - val < range) {
                range = maxValue - val;
                sp = val;
                ep = maxValue;
            }
            c++;
            if (c < nums.get(r).size()) {
                heap.add(new int[] { r, c });
                maxValue = Math.max(maxValue, nums.get(r).get(c));
            }
        }
        return new int[] { sp, ep };
    }

    // leetocde 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int i : nums)
            map.add(i);
        int len = 0;
        for (int i : nums) {
            if (!map.contains(i))
                continue;
            int prev = i - 1, next = i + 1;
            map.remove(i);
            while (map.contains(prev))
                map.remove(prev--);
            while (map.contains(next))
                map.remove(next++);

            len = Math.max(len, next - prev - 1);
        }
        return len;
    }

    // ==============Leetcode 781===========

    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = answers.length;
        int ans = 0;
        for (int ele : answers) {
            if (!map.containsKey(ele)) {
                ans += ele + 1;
                map.put(ele, 1);
            } else {
                map.put(ele, map.get(ele) + 1);
            }

            if (map.get(ele) == ele + 1)
                map.remove(ele);
        }
        return ans;
    }

    // =====================================LeetCode 1218=========================
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        for (int ele : arr) {
            map.put(ele, map.getOrDefault(ele - difference, 0) + 1);
            maxLen = Math.max(maxLen, map.get(ele));
        }
        return maxLen;
    }

    // ========================================LeetCode 954=====
    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        int k = 0;
        Integer[] ARR = new Integer[n];
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            ARR[k++] = i;

        }
        Arrays.sort(ARR, (a, b) -> {
            return Math.abs(a) - Math.abs(b);
        });

        for (Integer ele : ARR) {
            if (map.get(ele) == 0)
                continue;
            if (map.getOrDefault(2 * ele, 0) <= 0)
                return false;

            map.put(ele, map.get(ele) - 1);
            map.put(2 * ele, map.get(2 * ele) - 1);

        }
        return true;
    }

    // =============================================LEETCODE
    // 295======================================

    private PriorityQueue<Integer> maxheap = new PriorityQueue<>((a, b) -> {
        return b - a;
    });

    private PriorityQueue<Integer> minheap = new PriorityQueue<>();

    // public MedianFinder() {

    // }

    public void addNum(int num) {
        if (maxheap.size() == 0 || num <= maxheap.peek()) {
            maxheap.add(num);
        } else {
            minheap.add(num);
        }
        if (maxheap.size() - minheap.size() == 2)
            minheap.add(maxheap.remove());
        if (maxheap.size() - minheap.size() == -1)
            maxheap.add(minheap.remove());
    }

    public double findMedian() {
        if (maxheap.size() - minheap.size() == 0) {
            return (maxheap.peek() + minheap.peek()) / 2.0;
        } else {
            return maxheap.peek() * 1.0;
        }
    }

    // =======================================LEETCODE 380=========================
    Random r;
    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    // public RandomizedSet() {
    // r=new Random();
    // map=new HashMap<>();
    // list=new ArrayList<>();

    // }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int validx = map.get(val);
        int lastvalidx = list.size() - 1;
        int lastval = list.get(lastvalidx);
        list.set(validx, lastval);
        list.set(lastvalidx, val);
        map.put(val, lastvalidx);
        map.put(lastval, validx);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int idx = r.nextInt(list.size());
        return list.get(idx);
    }

    // ========================================LEETCODE 149
    // =======================================
    public int maxPoints(int[][] points) {
        HashMap<String, Integer> map = new HashMap<>();
        int ans = 0, n = points.length;
        for (int i = 0; i < n; i++) {
            int overlap = 0, max = 0;
            for (int j = i + 1; j < n; j++) {
                int xdiff = points[j][0] - points[i][0];
                int ydiff = points[j][1] - points[i][1];

                int gcd = gcd(xdiff, ydiff);

                xdiff /= gcd;
                ydiff /= gcd;

                String s = xdiff + "@" + ydiff;
                map.put(s, map.getOrDefault(s, 0) + 1);
                max = Math.max(map.get(s), max);
            }

            ans = Math.max(max + 1, ans);
            map.clear();
        }

        return ans;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

}
