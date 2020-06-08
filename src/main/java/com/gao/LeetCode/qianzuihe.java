package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class qianzuihe {
    public static void main(String[] args) throws IOException {
        qianzuihe q = new qianzuihe();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i : q.productExceptSelf(nums)) {
            System.out.print(i + " ");
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = k;
            k *= nums[i];
        }
        k = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= k;
            k *= nums[i];
        }
        return res;
    }
}
