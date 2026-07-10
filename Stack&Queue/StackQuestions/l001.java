package StackQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class l001 {
    // next Greater to the Right
    public static int[] NGOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i]) {
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);

        }
        return ans;
    }

    // next smaller to the Right
    public static int[] NSOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i]) {
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);

        }
        return ans;
    }

    // next Greater to the LEFT
    public static int[] NGOL(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = n - 1; i >= 0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i]) {
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);

        }
        return ans;
    }

    // next Smaller to the LEFT
    public static int[] NSOL(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = n - 1; i >= 0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i]) {
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);

        }
        return ans;
    }

    // =======================================================LEETCODE
    // 503=================================================
    public int[] NGOR503(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < 2 * n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i % n]) {
                ans[st.removeFirst()] = arr[i % n];
            }
            if (i < n)
                st.addFirst(i);
        }
        return ans;
    }

    // =====================================================GFG Stock span
    // problem======================================
    // Link: https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1

    public ArrayList<Integer> calculateSpan(int[] arr) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        NGOR(arr, ans);
        return ans;

    }

    public void NGOR(int[] arr, ArrayList<Integer> ans) {
        int n = arr.length;

        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i]) {
                st.removeFirst();
            }
            ans.add(i - st.getFirst());
            st.addFirst(i);

        }

    }

    // =====================================================LEETCODE
    // 901=========================================================
    class StockSpanner {

        int day = 0;
        LinkedList<int[]> st = new LinkedList<>();

        public StockSpanner() {
            // {idx, val}
            st.addFirst(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.getFirst()[0] != -1 && st.getFirst()[1] <= price)
                st.removeFirst();

            int span = day - st.getFirst()[0];
            st.addFirst(new int[] { day++, price });
            return span;
        }
    }

    // ======================================================LEETCODE
    // 20====================================================
    public boolean isValid(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.getFirst() == '(' && ch != ')')
                    return false;
                else if (st.getFirst() == '[' && ch != ']')
                    return false;
                else if (st.getFirst() == '{' && ch != '}')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0;
    }
    // =================================================735 Leetcode
    // =================================================

    public int[] asteroidCollision(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();

        for (int ele : arr) {
            if (ele > 0) {
                st.addFirst(ele);
                continue;
            }
            while (st.size() != 0 && st.getFirst() > 0 && st.getFirst() < -ele) {
                st.removeFirst();
            }
            if (st.size() != 0 && st.getFirst() == -ele) {
                st.removeFirst();
            } else if (st.size() == 0 || st.getFirst() < 0) {
                st.addFirst(ele);
            } else {
                // nothing to do
            }
        }
        int[] res = new int[st.size()];
        int index = res.length - 1;
        while (st.size() != 0) {
            res[index--] = st.removeFirst();
        }
        return res;
    }

    // ================================================================946
    // LeetCode========================================
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int ptr = 0;
        LinkedList<Integer> st = new LinkedList<>();

        for (int ele : pushed) {
            st.addFirst(ele);
            while (st.size() != 0 && st.getFirst() == popped[ptr]) {
                ptr++;
                st.removeFirst();
            }
        }
        return ptr == n;

    }

    // ==========================================================856 LeetCode
    // ==============================================
    public int scoreOfParentheses(String s) {
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(0);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.addFirst(0);
            } else {
                int a = st.removeFirst();
                int b = st.removeFirst();
                int val = b + Math.max(2 * a, 1);
                st.addFirst(val);
            }
        }
        return st.removeLast();
    }

}
