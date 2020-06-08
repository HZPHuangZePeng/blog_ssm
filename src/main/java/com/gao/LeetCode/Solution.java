package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        Solution solution=new Solution();
//        int n = Integer.parseInt(bufferedReader.readLine());
//        int[][] ints = new int[n][];
//        for (int i=0;i<n;i++){
//        int[] nums = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
//        }
        int m = Integer.parseInt(bufferedReader.readLine());
        int n = Integer.parseInt(bufferedReader.readLine());
        System.out.println(solution.uniquePaths(m, n));
//        for(int i:solution.spiralOrder(ints)){
//            System.out.print(i+" ");
//        }
    }

    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length < 1) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        int beginx = 0, beginy = 0, endx = 0, endy = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    beginx = i;
                    beginy = j;
                } else if (grid[i][j] == 2) {
                    endx = i;
                    endy = j;
                }
            }
        }
        for (int i = beginx; i < endx; i++) {
            if (grid[i][0] == -1) {
                dp[i][0] = 0;
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = beginy; i < endy; i++) {
            if (grid[0][i] == -1) {
                dp[0][i] = 0;
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = beginx; i < endx; i++) {
            for (int j = beginy; j < endy; j++) {
                if (grid[i][j] == -1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[endx - 1][endy - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length < 1) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                break;
            } else
                dp[i][0] = 1;
        }
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                break;
            } else
                dp[0][i] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[row - 1][col - 1];
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return find(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int find(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = 0;
        if (sum == root.val) res++;
        int left = find(root.left, sum - root.val);
        int right = find(root.right, sum - root.val);
        return res + left + right;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i : nums) set.add(i);
        for (int i : set) {
            if (!set.contains(i - 1)) {
                int temp = 1;
                while (set.contains(i + 1)) {
                    temp++;
                    i++;
                }
                res = Math.max(res, temp);
            }
        }
        return res;
    }

    public int[] spiralOrder(int[][] matrix) {
        int l = 0, r = matrix.length - 1, t = 0, b = matrix[0].length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i];
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r];
            if (--r < l) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i];
            if (b-- < t) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l];
            if (++l > r) break;
        }
        return res;
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

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return CreateMaxTree(nums, 0, nums.length - 1);
    }

    private TreeNode CreateMaxTree(int[] nums, int begin, int end) {
        if (begin > end) return null;
//        if(begin==end)return new TreeNode(nums[begin]);
        int index = begin, max = nums[begin];
        for (int i = begin; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = CreateMaxTree(nums, begin, index - 1);
        root.right = CreateMaxTree(nums, index + 1, end);
        return root;
    }


    public boolean canBeEqual(int[] target, int[] arr) {
        int[] targetIndex = new int[1001];
        int[] arrIndex = new int[1001];
        for (int i=0;i<arr.length;i++){
            targetIndex[target[i]] ++;
            arrIndex[arr[i]]++;
        }
        for (int i=0;i<1001;i++){
            if(targetIndex[i]!=arrIndex[i])return false;
        }
        return true;
    }
    public int largestRectangleArea(int[] heights) {
        int[] nums = new int[heights.length+2];
        System.arraycopy(heights,0,nums,1,heights.length);
        for (int i:nums){
            System.out.print(i + " ");
        }
        Stack<Integer> stack=new Stack<>();
        int area = 0;
        for (int i=0;i<nums.length;i++){
            while (!stack.isEmpty() && nums[i]<nums[stack.peek()]){
                int height = nums[stack.pop()];
                int weight = i - stack.peek()-1;
                area = Math.max(area,height*weight);
            }
            stack.push(i);
        }
        return area;
    }
    public boolean canPermutePalindrome(String s) {
        Set<Character> set=new HashSet<>();
        for (char c:s.toCharArray()){
            if(set.contains(c))set.remove(c);
            else set.add(c);
        }
        return set.size()<=1;
    }
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null)return true;
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = TurnListNode(slow);
        fast = head;
        while (fast!=null && slow!=null){
            if(fast.val!=slow.val)return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode TurnListNode(ListNode slow) {
        if(slow==null || slow.next==null)return slow;
        ListNode pre = null;
        while (slow!=null){
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        return pre;
    }





    public int rob(int[] nums){
        if(nums==null || nums.length<1)return 0;
        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i=2;i<nums.length;i++){
            dp[i] = Math.max(nums[i]+dp[i-2],dp[i-1]);
        }
        return dp[dp.length-1];
    }
    public ListNode creatListNode(int[] nums){
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode temp = new ListNode(nums[i]);
            cur.next = temp;
            cur = cur.next;
        }
        return head;
    }
    public ListNode createListNodePos(int[] nums,int pos){
        if(pos==-1)return creatListNode(nums);
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head,posNode = head,panduan = head;
        for(int i=1;i<nums.length;i++){
            ListNode temp = new ListNode(nums[i]);
            cur.next = temp;
            cur = cur.next;
        }
        for (int i=0;i<pos;i++){
            posNode = posNode.next;
        }
        while (panduan.next!=null){
            panduan = panduan.next;
        }
        panduan.next = posNode;
        return head;
    }
    public int subarraysDivByK2(int[] A,int K){
        int[] modK = new int[A.length];
        modK[0] = 1;
        ArrayList<Integer> pre=new ArrayList<>();
        int preSum = 0;//前缀和
        int ans = 0;
        for (int temp:A){
            preSum += temp;
            pre.add(preSum);
            int mod = ((preSum)%K + K)%K; //求余，这样写是防止有负数的出现
            ans += modK[mod];
            modK[mod]++;
        }
        for(int i:modK){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println(pre);
        return ans;
    }
    public int subarraysDivByK(int[] A,int K){
        int[] modK = new int[A.length];
        modK[0]=1;
        int preSum = 0;
        int ans = 0;
        for (int temp:A){
            preSum+=temp;
            int mod = ((preSum%K)+K)%K;
            ans += modK[mod];
            modK[mod]+=1;
        }
        return ans;
    }
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            sum = nums[i];
            for (int j=i+1;j<nums.length;j++){
                sum+=nums[j];
                if(k!=0){
                    if(sum%k==0)return true;
                }else if (k==0 && sum==0)return true;
            }
        }
        return false;
    }
    public boolean checkSubarraySum2(int[] nums,int k){
        if(nums.length<2)return false;
        for (int i=0;i<nums.length-1;i++)
            if(nums[i]==0 && nums[i+1]==0)return true;
        if(k==0)return false;
        if(k<0) k = -k;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int preSum = 0;
        for(int i=0;i<nums.length;i++){
            preSum+=nums[i];
            int mod = preSum%k;
            if(map.containsKey(mod)){
                if(i - map.get(mod) > 1){
                    return true;
                }
            }else {
                map.put(mod,i);
            }
        }
        return false;
    }
    //可以用栈来解决
    public String decodeString(String s) {
        Stack<String> strings=new Stack<>();
        for (int i=0;i<s.length();i++){
            if(s.charAt(i)==']'){
                String string="";
                while (!strings.peek().equals("[")){
                    string = strings.pop()+string;
                }
                strings.pop();

                String countString = "";
                while (!strings.isEmpty() && (strings.peek().charAt(0)>='0' && strings.peek().charAt(0)<='9')){
                    countString = strings.pop()+countString;
                }
                int count = Integer.parseInt(countString);
                String resString = "";
                for (int j=0;j<count;j++){
                    resString = resString + string;
                }
                strings.push(resString);
            }else {
                String str = ""+s.charAt(i);
                strings.push(str);
            }
        }
        String res = "";
        while (!strings.isEmpty()){
            res = strings.pop() + res;
        }
        return res;
    }
}
