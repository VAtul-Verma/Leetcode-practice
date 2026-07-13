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

    public static void main(String[] args) {

    }
}