import java.util.Arrays;

public class l005AVL {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        int bal = 0;
        int height = 0;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right, int bal, int height) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.bal = bal;
            this.height = height;
        }
    }

    public static void updateBalandHeight(TreeNode root) {
        int lh = root.left != null ? root.left.height : -1;
        int rh = root.right != null ? root.right.height : -1;
        int bal = lh - rh;
        root.height = Math.max(lh, rh) + 1;
        root.bal = bal;
    }

    // O(1)
    public static TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;
        updateBalandHeight(A);
        updateBalandHeight(B);

        return B;
    }

    // O(1)
    public static TreeNode lefttRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;
        updateBalandHeight(A);
        updateBalandHeight(B);

        return B;
    }

    public static TreeNode getRotation(TreeNode root) {
        updateBalandHeight(root);
        if (root.bal == 2) { // ll,lr
            if (root.left.bal == 1) { // ll

                return rightRotation(root);
            } else { // lr

                root.left = lefttRotation(root.left);
                return rightRotation(root);
            }

        } else if (root.bal == -2) { // rr,rl
            if (root.right.bal == -1) { // rr
                return lefttRotation(root);
            } else { // rl
                root.right = rightRotation(root.right);
                return lefttRotation(root);
            }

        }
        return root;

    }

    public static TreeNode addData(TreeNode root, int data) {
        if (root == null)
            return new TreeNode(data);
        if (root.val < data)
            root.right = addData(root.right, data);
        else
            root.left = addData(root.left, data);
        return getRotation(root);
    }

    public static int getMax(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.val;
    }

    public static TreeNode removeData(TreeNode root, int data) {
        if (root == null)
            return null;
        if (root.val < data)
            root.right = removeData(root.right, data);
        else if (root.val > data)
            root.left = removeData(root.left, data);
        else {
            if (root.left == null || root.right == null) {
                return root.left != null ? root.left : root.right;
            }
            int maxEle = getMax(root.left);
            root.val = maxEle;
            root.left = removeData(root.left, maxEle);
        }
        return getRotation(root);
    }

    public static void display(TreeNode root) {
        if (root == null)
            return;

        String ans = "";

        ans += (root.left != null) ? String.valueOf(root.left.val) : ".";
        ans += " -> " + root.val + " <- ";
        ans += (root.right != null) ? String.valueOf(root.right.val) : ".";

        System.out.println(ans);

        display(root.left);
        display(root.right);
    }

    // ============================================LEETCODE
    // 1382====================================
    int[] height = new int[(int) 1e5 + 1];

    public TreeNode balanceBST(TreeNode root) {
        Arrays.fill(height, -1);
        return reconstructTree(root);
    }

    private TreeNode reconstructTree(TreeNode root) {
        if (root == null)
            return null;

        root.left = reconstructTree(root.left);
        root.right = reconstructTree(root.right);

        return getRotation(root);
    }

    private void updateHeight(TreeNode root) {
        int lh = (root.left != null) ? height[root.left.val] : -1;
        int rh = (root.right != null) ? height[root.right.val] : -1;

        height[root.val] = Math.max(lh, rh) + 1;
    }

    private int getBal(TreeNode root) {
        int lh = (root.left != null) ? height[root.left.val] : -1;
        int rh = (root.right != null) ? height[root.right.val] : -1;

        return lh - rh;
    }

    private TreeNode rightRotationII(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        B.right = getRotationII(A);
        return getRotationII(B);
    }

    private TreeNode leftRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        B.left = getRotationII(A);
        return getRotationII(B);
    }

    private TreeNode getRotationII(TreeNode root) {

        updateHeight(root);

        if (getBal(root) >= 2) { // LL or LR

            if (getBal(root.left) >= 1) { // LL
                return rightRotationII(root);
            } else { // LR
                root.left = leftRotation(root.left);
                return rightRotationII(root);
            }

        } else if (getBal(root) <= -2) { // RR or RL

            if (getBal(root.right) <= -1) { // RR
                return leftRotation(root);
            } else { // RL
                root.right = rightRotationII(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int i = 1; i <= 15; i++) {
            root = addData(root, i * 10);
        }
        display(root);
    }

}
