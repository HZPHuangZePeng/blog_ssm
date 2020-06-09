package com.gao.LeetCode;

/*并查集*/
public class bingchaji {
    class UnionFind {
        private int[] parent;
        private int size;

        public UnionFind(int size) {
            this.size = size;
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int element) {
            while (element != parent[element]) {
                element = parent[element];
            }
            return element;
        }

        public boolean isConnected(int firstElement, int secondElement) {
            return find(firstElement) == find(secondElement);
        }

        public void unionElements(int firstElement, int secondElement) {
            int firstRoot = find(firstElement);
            int secondRoot = find(secondElement);
            if (firstRoot == secondRoot) {
                return;
            }
            parent[firstRoot] = secondRoot;
        }

        private void printArr() {
            for (int parent : this.parent) {
                System.out.print(parent + "\t");
            }
            System.out.println();
        }

    }

    public boolean equationsPossible(String[] equations) {
        int length = equations.length;
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) return false;
            }
        }
        return true;
    }

    private void union(int[] parent, int index1, int index2) {
        int root1 = find(parent, index1);
        int root2 = find(parent, index2);
        parent[root1] = root2;
    }

    private int find(int[] parent, int element) {
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }

    public static void main(String[] args) {

    }
}
