package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SearchBinaryTree {
    public static void main(String[] args) throws IOException {
        SearchBinaryTree s = new SearchBinaryTree();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        ListNode head = s.CreateListNode(nums);
        TreeNode root = s.sortedListToBST(head);
        System.out.println(root);
        int val = Integer.parseInt(bufferedReader.readLine());
        System.out.println(s.insertIntoBST(root, val));
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < val) {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } else {
                    cur = cur.right;
                }
            } else {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                } else {
                    cur = cur.left;
                }
            }
        }
        return root;
    }

    private ListNode CreateListNode(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return helper(head, null);

    }

    private TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }


    private List<Integer> CengCiBianLi(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.pollFirst();
                res.add(temp.val);
                if (temp.left != null) deque.addLast(temp.left);
                if (temp.right != null) deque.addLast(temp.right);
            }
        }
        return res;
    }

    private TreeNode CreatSearchTree(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        Arrays.sort(nums);
        return dfsCreateSearchTree(nums, 0, nums.length - 1);
    }

    private TreeNode dfsCreateSearchTree(int[] nums, int l, int r) {
        if (l > r) return null;
        if (l == r) return new TreeNode(nums[l]);
        int mid = (l + r) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfsCreateSearchTree(nums, l, mid - 1);
        root.right = dfsCreateSearchTree(nums, mid + 1, r);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length - 1);
    }

    private TreeNode bstFromPreorder(int[] preorder, int begin, int end) {
        if (begin > end) return null;
        TreeNode root = new TreeNode(preorder[begin]);
        int index = begin + 1;
        while (index <= end && preorder[index] < preorder[begin]) {
            index++;
        }
        root.left = bstFromPreorder(preorder, begin + 1, index - 1);
        root.right = bstFromPreorder(preorder, index, end);
        return root;
    }
}
