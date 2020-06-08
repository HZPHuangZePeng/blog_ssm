package com.gao.LeetCode;

//二叉搜索树的实现
public class BST {
    //根结点
    public static Node root = null;

    //节点类
    public static class Node {
        int key;
        Node p;
        Node left;
        Node right;

        public Node(int key, Node p, Node left, Node right) {
            this.key = key;
            this.p = p;
            this.left = left;
            this.right = right;
        }
    }

    public static void insert(Node t) {

    }
}
