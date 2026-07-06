import java.util.ArrayList;
import java.util.List;

public class l006DIA {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ========================================================LEETCODE 543 DIA OF A
    // TREET====================================
    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    // T.C O(n^2)
    public static int diaMeter_01(TreeNode root) {
        if (root == null)
            return 0;
        int ld = diaMeter_01(root.left);
        int rd = diaMeter_01(root.right);
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // T.C = O(N) use arr of len 2 where arr[0] =DIA ,arr[1] = height
    public static int[] diaMeter_02(TreeNode root) {
        if (root == null)
            return new int[] { 0, -1 };
        int[] ans = new int[2];
        int[] ld = diaMeter_02(root.left);
        int[] rd = diaMeter_02(root.right);

        ans[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        ans[1] = Math.max(ld[1], rd[1]) + 1;

        return ans;
    }

    // =======================================LEETCODE
    // 112=================================================
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            if (targetSum - root.val == 0)
                return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // =================================================LEETCODE
    // 113==================================================
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallans = new ArrayList<>();
        pathSum(root, targetSum, ans, smallans);
        return ans;
    }

    public void pathSum(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> smallans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && targetSum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallans);
            base.add(root.val);
            ans.add(base);
            return;

        }
        smallans.add(root.val);
        pathSum(root.left, targetSum - root.val, ans, smallans);
        pathSum(root.right, targetSum - root.val, ans, smallans);
        smallans.remove(smallans.size() - 1);

    }

    // ==========================================================gfg
    // Link:https://www.geeksforgeeks.org/problems/maximum-path-sum/1
    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public int maxPathSum(Node root) {
        // code here
        int res[] = new int[2];
        res = maxPathSumHelper(root);
        return res[0] == (int) -1e9 ? res[1] : res[0];

    }

    public int[] maxPathSumHelper(Node root) {
        // code here
        if (root == null) {
            // System.out.println(root == null ? "null" : root.data);
            return new int[] { (int) -1e9, (int) -1e9 };

        }
        if (root.left == null && root.right == null) {
            return new int[] { (int) -1e9, root.data };
        }
        int[] myres = new int[2];

        int[] lr = maxPathSumHelper(root.left);
        int[] rr = maxPathSumHelper(root.right);
        myres[0] = Math.max(rr[0], lr[0]);
        if (root.left != null && root.right != null) {
            // System.out.println("Leaf = " + root.data);
            myres[0] = Math.max(myres[0], lr[1] + rr[1] + root.data);

            myres[1] = Math.max(lr[1], rr[1]) + root.data;
        } else if (root.left != null) {
            myres[1] = lr[1] + root.data;
        } else {
            myres[1] = rr[1] + root.data;
        }

        return myres;
    }

    // ================================================LEETCODE
    // 124============================================
    public int maxPathSum(TreeNode root) {
        return 0;
    }

}
