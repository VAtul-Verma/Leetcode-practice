import java.lang.classfile.components.ClassPrinter.ListNode;
import java.util.ArrayList;

public class l001 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // =================================LEETCODE
    // 876=====================================
    // naive approach TC:O(N) SC:O(N);
    public ListNode middleNode(ListNode head) {
        ArrayList<ListNode> arr = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            ListNode n = curr;
            arr.add(n);
            curr = curr.next;
        }
        return arr.get(arr.size() / 2);
    }

    // better appracoh TC: O(N)
    public ListNode middleNode_better(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // ==================================LEETCODE
    // 206========================================
    // naive approach TC:O(N) SC:O(N);
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            arr.add(curr.val);
            curr = curr.next;
        }
        int n = arr.size();
        int val = arr.get(n - 1);
        ListNode newHead = new ListNode(val);
        ListNode newcurr = newHead;

        for (int i = n - 2; i >= 0; i--) {
            int currval = arr.get(i);
            ListNode temp = new ListNode(currval);
            newcurr.next = temp;
            newcurr = temp;
        }
        return newHead;

    }

    // better appraoch TC:O(N)
    public ListNode reverseList_better(ListNode head) {
        if (head == null)
            return head;
        ListNode prev = null;
        ListNode curr = head;
        ListNode forward = head.next;
        while (curr != null) {
            ListNode fwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fwd;

        }
        return prev;
    }

    // =========================================LEETCODE
    // 234==========================
    // Naive appraoch TC:O(N) SC:O(N)
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return false;
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            arr.add(curr.val);
            curr = curr.next;
        }
        int i = 0, j = arr.size() - 1;
        while (i <= j) {
            if (arr.get(i) != arr.get(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello world ");
    }

}