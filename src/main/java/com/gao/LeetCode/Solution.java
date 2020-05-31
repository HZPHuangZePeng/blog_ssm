package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        Solution solution=new Solution();
        int[] target = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] nums = Arrays.stream(bufferedReader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
//        int pos = Integer.parseInt(bufferedReader.readLine());
//        ListNode head = solution.creatListNode(nums);
//        System.out.println(head);
//        ListNode poshead = solution.createListNodePos(nums,pos);
//        for (int i=0;i<20;i++){
//            System.out.print(poshead.val+" ");
//            poshead = poshead.next;
//        }
//        String s = bufferedReader.readLine();
        System.out.println(solution.canBeEqual(target,nums));
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
