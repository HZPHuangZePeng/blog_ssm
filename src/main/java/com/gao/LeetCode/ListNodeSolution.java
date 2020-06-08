package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ListNodeSolution {
    public static void main(String[] args) throws IOException {
        ListNodeSolution l = new ListNodeSolution();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums1 = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] nums2 = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        ListNode node1 = l.CreateListNode(nums1);
        ListNode node2 = l.CreateListNode(nums2);
        ListNode res = l.addTwoNumbers(node1, node2);
        System.out.println(res);
    }

    private ListNode CreateListNode(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
//            ListNode temp = new ListNode(nums[i]);
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        int val1 = 0, val2 = 0, c = 0, sum;
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null || l2 != null || c != 0) {
            val1 = (l1 == null ? 0 : l1.val);
            val2 = (l2 == null ? 0 : l2.val);
            sum = val1 + val2 + c;
            c = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return head.next;
    }
}
