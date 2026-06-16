import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

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

}
