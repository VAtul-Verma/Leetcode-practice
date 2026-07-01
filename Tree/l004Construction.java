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

    public static void main(String[] args) {

    }

}