package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class quanpailie {

    public static void main(String[] args) throws IOException {
        quanpailie q = new quanpailie();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        for (List<Integer> temp : q.permute(nums)) {
            System.out.println(temp);
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) return res;
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfspermute2(nums, 0, nums.length, visited, res, path);
        return res;
    }

    private void dfspermute2(int[] nums, int pos, int len, boolean[] visited, List<List<Integer>> res, List<Integer> path) {
        if (pos >= len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            path.add(nums[i]);
            visited[i] = true;
            dfspermute2(nums, pos + 1, len, visited, res, path);
            visited[i] = false;
            path.remove(pos);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) return res;
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfspermute(nums, 0, nums.length, visited, res, path);
        return res;
    }

    private void dfspermute(int[] nums, int pos, int len, boolean[] visited, List<List<Integer>> res, List<Integer> path) {
        if (pos >= len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                dfspermute(nums, pos + 1, len, visited, res, path);
                path.remove(pos);
                visited[i] = false;
            }
        }
    }
}
