import java.util.ArrayList;

import javax.swing.tree.TreeNode;

public class l004Construction {
    // ==========================================LEETCODE 105====================
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei) {
        if (psi > pei)
            return null;

        int idx = isi;
        while (preorder[psi] != inorder[idx]) {
            idx++;
        }
        int totalEle = idx - isi;
        TreeNode root = new TreeNode(preorder[psi]);
        root.left = buildTree(preorder, psi + 1, psi + totalEle, inorder, isi, idx - 1);
        root.right = buildTree(preorder, psi + 1 + totalEle, pei, inorder, idx + 1, iei);
        return root;
    }

    // ==========================================LEETCODE
    // 106========================================
    public TreeNode buildTreeFromPostIN(int[] postorder, int psi, int pei, int[] inorder, int isi, int iei) {
        if (isi > iei) {
            return null;
        }
        int id = isi;
        while (inorder[id] != postorder[pei]) {
            id++;

        }
        int totlele = id - isi;
        TreeNode node = new TreeNode(postorder[pei]);
        node.left = buildTreeFromPostIN(postorder, psi, psi + totlele - 1, inorder, isi, id - 1);
        node.right = buildTreeFromPostIN(postorder, psi + totlele, pei - 1, inorder, id + 1, iei);
        return node;

    }
    // =======================================GFG Construct Tree from Preorder &
    // Postorder

    class Node {
        int data;
        Node left, right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }

    // Link:https://www.geeksforgeeks.org/problems/construct-tree-from-preorder-postorder/1
    public Node constructTree(int[] pre, int psi, int pei, int[] post, int Si, int Ei) {
        // code here
        if (psi > pei)
            return null;
        Node root = new Node(pre[psi]);
        if (psi == pei)
            return root;
        int idx = Si;
        while (pre[psi + 1] != post[idx]) {
            idx++;
        }

        int totalEle = idx - Si + 1;

        root.left = constructTree(pre, psi + 1, psi + totalEle, post, Si, idx);
        root.right = constructTree(pre, psi + 1 + totalEle, pei, post, idx + 1, Ei - 1);
        return root;

    }

    // ==============================================LEETCODE
    // 297===============================

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.val + " ");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    int idx = 0;

    public TreeNode deserialize(String[] arr) {
        if (idx >= arr.length || arr[idx].equals("#")) {
            idx++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[idx++]));
        root.left = deserialize(arr);
        root.right = deserialize(arr);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0)
            return null;
        String[] str = data.split(" ");
        return deserialize(str);

    }

    // =====================================================LEETCODE
    // 110==============================================
    // Approach 1
    boolean isbalanced = true;

    public boolean isBalanced(TreeNode root) {
        Height(root);
        return isbalanced;
    }

    public int Height(TreeNode root) {
        if (root == null)
            return 0;

        int left = Height(root.left);
        int right = Height(root.right);
        if (Math.abs(left - right) > 1) {
            isbalanced = false;
        }
        return Math.max(left, right) + 1;
    }

    // ====================================GFG largest sub-tree
    // Link: https://www.geeksforgeeks.org/problems/largest-bst/1

    // Return the size of the largest sub-tree which is also a BST
    public static class Bpair {
        boolean isbst = true;
        int min = (int) 1e9;
        int max = -(int) 1e9;
        int size = 0;
        Node large = null;
    }

    static Bpair largestBsthel(Node root) {
        if (root == null) {
            Bpair base = new Bpair();
            return base;
        }
        Bpair lp = largestBsthel(root.left);
        Bpair rp = largestBsthel(root.right);
        Bpair mypair = new Bpair();
        mypair.isbst = false;

        if (lp.isbst && rp.isbst && lp.max < root.data && root.data < rp.min) {
            mypair.isbst = true;
            mypair.min = Math.min(lp.min, root.data);
            mypair.max = Math.max(rp.max, root.data);
            mypair.size = lp.size + rp.size + 1;

        } else {
            if (lp.size > rp.size) {
                mypair.size = lp.size;
            } else {
                mypair.size = rp.size;
            }
        }
        // Write your code here
        return mypair;

    }

    static int largestBst(Node root) {
        // Write your code here
        return largestBsthel(root).size;

    }

    // ===============================================GFG Predecessor and Successor
    // Link: https://www.geeksforgeeks.org/problems/predecessor-and-successor/1
    public Node getLeftMost(Node root) {
        if (root == null)
            return null;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public Node getRightMost(Node root) {
        if (root == null)
            return null;

        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    // ceil and floor -> T : O(logN), S : O(1)
    public void predSucc(Node root, int data, ArrayList<Node> ans) {
        Node curr = root, succ = null, pred = null;

        while (curr != null) {
            if (curr.data == data) {

                Node leftMost = getLeftMost(curr.right);
                succ = leftMost != null ? leftMost : succ;

                Node rightMost = getRightMost(curr.left);
                pred = rightMost != null ? rightMost : pred;

                break;

            } else if (curr.data < data) {
                pred = curr;
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
        ans.add(pred);
        ans.add(succ);
    }

    public ArrayList<Node> findPreSuc(Node root, int key) {
        // code here
        ArrayList<Node> ans = new ArrayList<>();
        predSucc(root, key, ans);
        return ans;

    }

    // ==============================================LEETCODE 701
    // =================================================
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode mynode = new TreeNode(val);
            return mynode;
        }
        TreeNode nd = root;
        if (root.val > val) {
            nd.left = insertIntoBST(root.left, val);
        } else {
            nd.right = insertIntoBST(root.right, val);
        }
        return nd;
    }

    // =========================================LEETCODE 450
    // ====================================================
    // T : O(LogN)
    public int getMin(TreeNode root) {

        while (root.left != null)
            root = root.left;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else if (root.val > key)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null || root.right == null) {
                TreeNode rNode = root.left != null ? root.left : root.right;
                root.left = root.right = null;
                // delete root;
                return rNode;
            }

            int minEle = getMin(root.right);
            root.val = minEle;

            root.left = deleteNode(root.right, minEle);

        }

        return root;
    }

    public static void main(String[] args) {

    }

}