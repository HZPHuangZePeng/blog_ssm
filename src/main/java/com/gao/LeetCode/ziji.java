package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ziji {

    public static void main(String[] args) throws IOException {
        ziji z = new ziji();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int[] nums = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        for (List<Integer> temp : z.combine(4, 2)) {
            System.out.println(temp);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfsSubsets(nums, 0, res, path);
        return res;
    }

    private void dfsSubsets(int[] nums, int start, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfsSubsets(nums, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfsSubsetsWithDup(nums, 0, res, new ArrayList<Integer>());
        return res;
    }

    private void dfsSubsetsWithDup(int[] nums, int start, List<List<Integer>> res, ArrayList<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            path.add(nums[i]);
            dfsSubsetsWithDup(nums, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1 || k < 1) return res;
        dfsCombine(n, 1, k, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfsCombine(int n, int begin, int k, ArrayList<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.add(i);
            dfsCombine(n, i + 1, k, path, res);
            path.remove(path.size() - 1);
        }
    }
}
