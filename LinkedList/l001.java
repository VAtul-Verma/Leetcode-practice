import java.lang.classfile.components.ClassPrinter.ListNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    // better appraoch
    public ListNode mergeTwoLists_better(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return list1 == null ? list2 : list1;
        ListNode c1 = list1;
        ListNode c2 = list2;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;

        }
        if (c1 != null)
            prev.next = c1;
        if (c2 != null)
            prev.next = c2;
        return dummy.next;
    }

    // ========================GFG Unflod a
    // linkedlist========================================
    // better approach
    public void unflod(ListNode head) {
        if (head == null)
            return;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            ListNode forward = curr.next.next;
            prev.next = curr.next;
            curr.next = forward;
            prev = prev.next;
            curr = forward;
        }
        ListNode newhead = dummy.next;
        dummy.next = null;
        prev.next = null;
        newhead = reverseList(newhead);
        // Find the last node of the first list means find the tail in the first list to
        // connect it into second list
        ListNode first = head;
        while (first.next != null) {
            first = first.next;
        }

        first.next = newhead;
    }

    // =================================LEETCODE 19
    // ==================================
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode revHead = reverseList_helper02(head);

        ListNode curr = revHead;
        ListNode prev = null;

        while (n > 1) {
            prev = curr;
            curr = curr.next;
            n--;
        }

        // delete first node of reversed list
        if (prev == null) {
            revHead = revHead.next;
        } else {
            prev.next = curr.next;
        }

        return reverseList_helper03(revHead);

    }

    public ListNode reverseList_helper03(ListNode head) {
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

    // better appraoch
    public ListNode removeNthFromEnd_better(ListNode head, int n) {
        if (head == null)
            return head;
        ListNode A = head, B = head;
        while (n-- > 0) {
            B = B.next;
        }

        // when the n==List length then we have to remove the head and new head =
        // head.next
        if (B == null) {
            ListNode romvenode = head;
            head = head.next;
            romvenode.next = null;
            return head;
        }
        while (B.next != null) {
            A = A.next;
            B = B.next;

        }
        // if(B==null){
        // ListNode romvenode = head;
        // head = head.next;
        // romvenode.next = null;
        // return head;
        // }

        ListNode romvenode = A.next;
        A.next = romvenode.next;
        romvenode.next = null;
        return head;

    }

    // ===============================leetcode 2===================
    // Better Approach
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy, tail = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int d1 = l1 == null ? 0 : l1.val;
            int d2 = l2 == null ? 0 : l2.val;
            ListNode temp = new ListNode((d1 + d2 + carry) % 10);
            tail.next = temp;
            tail = temp;
            carry = (d1 + d2 + carry) / 10;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummy.next;
    }

    // ==================================leetcode
    // 445===========================================
    public ListNode addTwoNumbers_(ListNode l1, ListNode l2) {
        l1 = reverse_04(l1);
        l2 = reverse_04(l2);
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = (sum / 10);
            prev.next = new ListNode(sum % 10);
            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }
        // make the list as given
        l1 = reverse_04(l1);
        l2 = reverse_04(l2);

        ListNode head = dummy.next;
        dummy.next = null;
        // return the head after reverse
        head = reverse_04(head);
        return head;

    }

    public ListNode reverse_04(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head, prev = null;
        while (curr != null) {
            ListNode frd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = frd;
        }
        return prev;

    }

    // ====================================GFG SUBTRACT Two
    // number========================
    // Link: https://www.geeksforgeeks.org/problems/subtraction-in-linked-list/1
    static ListNode subLinkedList(ListNode l1, ListNode l2) {
        // code here
        if (isbigger(l1, l2) < 0) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        l1 = reverse(l1);
        l2 = reverse(l2);
        // which one is large number;

        ListNode c1 = l1;
        ListNode c2 = l2;
        int borrow = 0;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (c1 != null || c2 != null) {
            int d1 = (c1 != null ? c1.val : 0);
            int d2 = (c2 != null ? c2.val : 0);
            int val = borrow + (d1 - d2);
            if (val < 0) {
                val += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            ListNode temp = new ListNode(val);
            prev.next = temp;
            prev = temp;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;

        }
        // remove the starting zero's
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode head = dummy.next;
        dummy.next = null;
        head = reverse(head);
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.val != 0) {
                break;
            }
            curr = curr.next;
        }
        return curr;

    }

    static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode fwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fwd;
        }
        return prev;
    }

    static int len(ListNode head) {
        if (head == null)
            return 0;

        ListNode curr = head;
        int mylen = 0;
        while (curr != null) {
            mylen++;
            curr = curr.next;
        }
        return mylen;
    }

    public static int isbigger(ListNode l1, ListNode l2) {
        int len1 = len(l1), len2 = len(l2);
        if (len1 == len2) {
            ListNode curr1 = l1, curr2 = l2;
            while (curr1 != null) {
                if (curr1.val != curr2.val)
                    return curr1.val - curr2.val;
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }
        return len1 - len2;

    }

    // =====================================LEETCODE 83=====================
    // Navie appraoch TC:O(N) and SC: O(N)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ArrayList<ListNode> arr = new ArrayList<>();
        ListNode curr = head;
        arr.add(curr);
        while (curr != null) {
            if (arr.get(arr.size() - 1).val != curr.val) {
                arr.add(curr);
            }
            curr = curr.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        for (int i = 0; i < arr.size(); i++) {
            ListNode temp = new ListNode(arr.get(i).val);
            prev.next = temp;
            prev = temp;
        }
        return dummy.next;
    }

    // ==better approach ===
    public ListNode deleteDuplicates_better(ListNode head) {
        // Your code here
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    // ==================Leetcode 82======================================
    // better approach
    public ListNode deleteAllDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, curr = head.next;
        prev.next = head;
        while (curr != null) {
            boolean isSequence = false;
            while (curr != null && prev.next.val == curr.val) {
                isSequence = true;
                curr = curr.next;
            }
            if (isSequence) {
                prev.next = curr;
            } else {
                prev = prev.next;
            }
            if (curr != null)
                curr = curr.next;
        }
        return dummy.next;

    }

    // ============================Leetcode
    // 328==========================================
    // better appraoch
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummyEven = new ListNode(-1), evenprev = dummyEven;
        ListNode dummyOdd = new ListNode(-1), oddprev = dummyOdd;
        ListNode curr = head;
        int index = 1;
        while (curr != null) {
            if (index % 2 == 1) {
                oddprev.next = curr;
                oddprev = oddprev.next;
            } else {
                evenprev.next = curr;
                evenprev = evenprev.next;
            }
            index++;
            curr = curr.next;

        }
        evenprev.next = null;
        oddprev.next = dummyEven.next;
        dummyEven.next = null;
        return dummyOdd.next;
    }

    // ====================================GFG Segregate Evens and Odds in a Linked
    // List=============
    // Link:https://www.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
    // same as the above quetion

    static ListNode divide(ListNode head) {
        // code here
        if (head == null || head.next == null)
            return head;
        ListNode dummyEven = new ListNode(-1), evenprev = dummyEven;
        ListNode dummyOdd = new ListNode(-1), oddprev = dummyOdd;
        ListNode curr = head;
        int index = 1;
        while (curr != null) {
            if (curr.val % 2 == 1) {
                oddprev.next = curr;
                oddprev = oddprev.next;
            } else {
                evenprev.next = curr;
                evenprev = evenprev.next;
            }
            index++;
            curr = curr.next;

        }
        oddprev.next = null;
        evenprev.next = dummyOdd.next;
        dummyOdd.next = null;
        return dummyEven.next;

    }

    public static void main(String[] args) {
        System.out.println("Hello world ");
    }

}