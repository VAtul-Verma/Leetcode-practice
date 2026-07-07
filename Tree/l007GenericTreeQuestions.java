import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class l007GenericTreeQuestions {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // =======================================================LEETCODE 337
    // =================================================
    public int rob(TreeNode root) {
        int[] ans = new int[2];
        ans = robHelper(root);
        return Math.max(ans[0], ans[1]);

    }

    // [Rob,Not Rob]
    public int[] robHelper(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };
        int[] lr = robHelper(root.left);
        int[] rr = robHelper(root.right);
        int[] res = new int[2];
        res[0] = lr[1] + root.val + rr[1];
        res[1] = Math.max(lr[0], lr[1]) + Math.max(rr[0], rr[1]);
        return res;

    }

    // =================================================LEETCODE
    // 1372==========================================
    public int longestZigZag(TreeNode root) {
        int ans[] = new int[3];
        ans = longestZigZagHelper(root);
        return ans[2];

    }

    // [ForwardSlope,backWordSlope,Longest ZigZagPathLen]
    public int[] longestZigZagHelper(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1, -1 };
        int[] lr = longestZigZagHelper(root.left);
        int[] rr = longestZigZagHelper(root.right);
        int[] res = new int[3];
        res[0] = lr[1] + 1;
        res[1] = rr[0] + 1;
        res[2] = Math.max(Math.max(res[0], res[1]), Math.max(lr[2], rr[2]));
        return res;
    }

    // ============================================LEETCODE
    // 979===========================================
    int totalMoves = 0;

    public int distributeCoins(TreeNode root) {
        if (root == null)
            return 0;
        distributeCoinsHelper(root);
        return totalMoves;

    }

    public int distributeCoinsHelper(TreeNode root) {
        if (root == null)
            return 0;
        int leftrequiredOrAccess = distributeCoinsHelper(root.left);
        int rightrequiredOrAccess = distributeCoinsHelper(root.right);
        totalMoves += Math.abs(leftrequiredOrAccess) + Math.abs(rightrequiredOrAccess);
        return rightrequiredOrAccess + leftrequiredOrAccess + (root.val - 1);

    }

    // ===================================LEETCODE 1443
    // =========================================
    public int dfs(ArrayList<Integer>[] tree, int root, List<Boolean> hasApple, boolean[] vis) {
        int time = 0;
        vis[root] = true;
        for (int child : tree[root]) {
            if (!vis[child]) {
                time += dfs(tree, child, hasApple, vis);
            }
        }
        if (time != 0)
            return time + 2;
        else if (hasApple.get(root))
            return 2;
        else
            return 0;

    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]); // undirect tree;
            tree[e[1]].add(e[0]);
        }
        boolean[] vis = new boolean[n];
        int ans = dfs(tree, 0, hasApple, vis);
        return ans != 0 ? ans - 2 : ans;
    }

    public static void main(String[] args) {

    }
}