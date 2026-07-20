import java.util.Random;

public class l002QuickSort {

    public static Random rand = new Random();

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionOverPivot(int[] arr, int si, int ei, int pIdx) {
        swap(arr, pIdx, ei);

        int p = si - 1, itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }

        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si >= ei)
            return;

        int pIdx = ei; // rand.nextInt(ei - si + 1) + si
        int p = partitionOverPivot(arr, si, ei, pIdx);
        quickSort(arr, si, p - 1);
        quickSort(arr, p + 1, ei);
    }

    // =====================================================LEETCODE 74
    // ===============================================
    // Approach 1: TC : O(NLOGN)
    public boolean searchMatrix(int[][] arr, int tar) {
        int m = arr[0].length;
        boolean isPresent = false;
        for (int[] d : arr) {
            isPresent = binarySearch(d, 0, m - 1, tar);
            if (isPresent)
                return isPresent;
        }
        return isPresent;
    }

    boolean binarySearch(int[] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == tar)
                return true;
            else if (arr[mid] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return false;
    }

    // Approach 2
    // TC: O(LOGN) : Binary Search
    public boolean searchMatrix01(int[][] arr, int tar) {
        int n = arr.length;
        int m = arr[0].length;
        int si = 0, ei = n * m - 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            int r = mid / m; // 1D to 2D
            int c = mid % m; // 1D To 2D
            if (arr[r][c] == tar)
                return true;
            else if (arr[r][c] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return false;
    }

    // =========================================LEETCODE
    // 240================================================
    public boolean searchMatrix_(int[][] arr, int tar) {
        int n = arr.length;
        int m = arr[0].length;
        int si = n - 1, ei = 0;
        while (si >= 0 && ei < m) {
            int ele = arr[si][ei];
            if (ele == tar)
                return true;
            else if (ele < tar) {
                ei++;
            } else if (ele > tar) {
                si--;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };
        quickSort(arr, 0, arr.length - 1);

        for (int ele : arr)
            System.out.print(ele + " ");
    }
}
