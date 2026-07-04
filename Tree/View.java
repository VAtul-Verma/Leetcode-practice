import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class View {
    // =================================================LEETCODE
    // 102=======================
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void levelOrder(TreeNode root, List<List<Integer>> ans) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        int level = 0;
        while (que.size() != 0) {
            List<Integer> smallans = new ArrayList<>();
            int size = que.size();
            System.out.println("Level:" + level);
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                smallans.add(rn.val);
                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            level++;
            ans.add(smallans);

        }
    }

    // =================================================GFG Left View of Binary
    // Tree==================================
    // Link:https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1
    class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    public void leftView(Node root, ArrayList<Integer> ans) {
        LinkedList<Node> que = new LinkedList<>();
        que.add(root);
        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getFirst().data);
            while (size-- > 0) {
                Node rn = que.removeFirst();

                if (rn.left != null) {
                    que.addLast(rn.left);
                }
                if (rn.right != null) {
                    que.addLast(rn.right);
                }
            }
        }
    }

    // ===============================================================LeetCode 199
    // ============================================
    public void rightSideView(TreeNode root, List<Integer> ans) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getLast().val);
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                if (rn.left != null) {
                    que.addLast(rn.left);
                }
                if (rn.right != null) {
                    que.addLast(rn.right);
                }
            }
        }
    }

    // ============================================GFG :Vertical View of the
    // tree==================================================
    // Link :
    // https://www.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    public static class vpair {
        TreeNode node;
        int vl;

        vpair(TreeNode node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static void widthofShadow(TreeNode root, int vl, int[] widtharr) {
        if (root == null)
            return;
        widtharr[0] = Math.min(widtharr[0], vl);
        widtharr[1] = Math.max(widtharr[1], vl);

        widthofShadow(root.left, vl - 1, widtharr);
        widthofShadow(root.right, vl + 1, widtharr);

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        int[] widtharr = new int[2];
        widthofShadow(root, 0, widtharr);
        int width = Math.abs(widtharr[1]) + Math.abs(widtharr[0]) + 1;

        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }
        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(widtharr[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair rn = que.removeFirst();
                TreeNode node = rn.node;
                int vl = rn.vl;
                ans.get(vl).add(node.val);
                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));
            }
        }

        return ans;
    }

    // ================================================GFG Bottom View
    // =============================================
    // Link :https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1

    // {min,max}
    public static void widthOfShadow(TreeNode root, int vl, int[] minMax) {
        if (root == null)
            return;

        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);

        widthOfShadow(root.left, vl - 1, minMax);
        widthOfShadow(root.right, vl + 1, minMax);
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;

        for (int i = 0; i < width; i++) {
            ans.add(null);
        }
        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {

                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));

            }
        }

        return ans;
    }

    // ===============================================GFG Top View
    // ===================================================
    // Link: https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
    static ArrayList<Integer> topView(TreeNode root) {
        // add your code
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(null);

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                if (ans.get(vl) == null)
                    ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));
            }
        }

        return ans;

    }

    // ====================================================GFG vertical
    // sum===========================================
    // Link:
    public ArrayList<Integer> verticalSum(TreeNode root) {
        // add your code here
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(0);

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {

                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.set(vl, ans.get(vl) + node.val);

                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));

            }
        }

        return ans;
    }

    // ============================================GFG DiaGonal Traversal
    // ================================================
    // LINK:
    // https://www.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
    public static ArrayList<ArrayList<Integer>> DigonalOrder_geeks(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (que.size() != 0) {
            int size = que.size();

            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                while (rn != null) {
                    smallAns.add(rn.val);
                    if (rn.left != null)
                        que.addLast(rn.left);

                    rn = rn.right;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    // digonal generic traversal code
    public static ArrayList<ArrayList<Integer>> DigonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = 0 - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.get(vl).add(node.val);
                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl));
            }
        }

        return ans;
    }

    // ===================================================GFG Diagonal
    // sum==================================
    // Link: https://www.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree/1
    public static ArrayList<Integer> DigonalSum_geeks(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (que.size() != 0) {
            int size = que.size();

            int sum = 0;
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                while (rn != null) {
                    sum += rn.val;
                    if (rn.left != null)
                        que.addLast(rn.left);

                    rn = rn.right;
                }
            }

            ans.add(sum);
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}