import java.lang.classfile.components.ClassPrinter.ListNode;
import java.util.ArrayList;
import java.util.Collections;

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

    // Better Approach O(N)
    public boolean isPalindrome_better(ListNode head) {
        ListNode midhead = middleoftheList(head);
        ListNode newhead = midhead.next;
        midhead.next = null;
        boolean res = true;
        newhead = reverseList_helper(newhead);
        ListNode c1 = head;
        ListNode c2 = newhead;
        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            ;
            c1 = c1.next;
            c2 = c2.next;
        }

        // make the Linked List as Given in starting
        newhead = reverseList_helper(newhead);
        midhead.next = newhead;
        ListNode curr = head;
        // print the list
        // while (curr != null) {
        // System.out.print(curr.val);
        // curr = curr.next;
        // }
        return res;
    }

    // this will give the second mid
    public ListNode middleoftheList(ListNode head) {
        if (head == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverseList_helper(ListNode head) {
        if (head == null)
            return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode fast = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fast;
        }
        return prev;
    }

    // ==============================================Leetcode
    // 143==================================
    // Naive appraoch TC:O(N) SC:O(N)
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ArrayList<Integer> arr = new ArrayList<>();

        // Store all values
        ListNode curr = head;
        while (curr != null) {
            arr.add(curr.val);
            curr = curr.next;
        }

        // Create reordered values
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0, j = arr.size() - 1;

        while (i < j) {
            ans.add(arr.get(i));
            ans.add(arr.get(j));
            i++;
            j--;
        }

        if (i == j) {
            ans.add(arr.get(i));
        }

        // Copy values back into the original list
        curr = head;
        int idx = 0;
        while (curr != null) {
            curr.val = ans.get(idx++);
            curr = curr.next;
        }
    }

    // ====Better Approach ===========================
    public ListNode middleoftheList02(ListNode head) {
        if (head == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverseList_helper02(ListNode head) {
        if (head == null)
            return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode fast = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fast;
        }
        return prev;
    }

    public void reorderList_better(ListNode head) {
        if (head == null)
            return;
        ListNode mid = middleoftheList02(head);
        ListNode newhead = mid.next;
        mid.next = null;
        newhead = reverseList_helper02(newhead);
        ListNode c1 = head;
        ListNode c2 = newhead;
        while (c2 != null) {
            ListNode f1 = c1.next;
            ListNode f2 = c2.next;
            c1.next = c2;
            c2.next = f1;
            c1 = f1;
            c2 = f2;

        }

    }

    // ==================================LEETCODE 21 ===========================
    // Naive approach using arraylist
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ArrayList<Integer> arr = new ArrayList<>();

        while (head1 != null) {
            arr.add(head1.val);
            head1 = head1.next;

        }
        while (head2 != null) {
            arr.add(head2.val);
            head2 = head2.next;

        }
        Collections.sort(arr);

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        for (int i = 0; i < arr.size(); i++) {
            prev.next = new ListNode(arr.get(i));
            prev = prev.next;
        }

        return dummy.next;

    }

    public static void main(String[] args) {
        System.out.println("Hello world ");
    }

}