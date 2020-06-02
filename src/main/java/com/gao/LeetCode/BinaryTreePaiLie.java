package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BinaryTreePaiLie {
    //    3,9,20,15,7
    //    9,3,15,20,7
    public static void main(String[] args) throws IOException {
        BinaryTreePaiLie binaryTreePaiLie = new BinaryTreePaiLie();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] preorder = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] inorder = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        TreeNode root = binaryTreePaiLie.buildTree(preorder, inorder);
        System.out.println(root);
        System.out.println(binaryTreePaiLie.inorderTraversal(root));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfsbuildTree2(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode dfsbuildTree2(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        if (preBegin == preEnd || inBegin == inEnd) return null;
        int val = preorder[preBegin];
        TreeNode root = new TreeNode(val);
        int index = 0;
        for (int i = inBegin; i < inEnd; i++) {
            if (inorder[i] == val) {
                index = i;
                break;
            }
        }
        int left_num = index - inBegin;
        //递归构造左子树
        root.left = dfsbuildTree2(preorder, preBegin + 1, preBegin + left_num + 1, inorder, inBegin, index);
        //递归构造右子树
        root.right = dfsbuildTree2(preorder, preBegin + left_num + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfsInorderTraversal(root, res);
        return res;
    }

    public List<Integer> inorderTraversalDieDai(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        return res;
    }

    private void dfsInorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) return;
        dfsInorderTraversal(root.left, res);
        res.add(root.val);
        dfsInorderTraversal(root.right, res);
    }

}
