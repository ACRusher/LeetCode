package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2016-03-20 下午11:26
 */
public class PatchingArray {

    public int minPatches(int[] nums, int n) {
        long curN=1;
        int curIndex=0;
        int result=0;
        while (curN<=n){
            if(curIndex<nums.length && nums[curIndex]<=curN){
                curN=curN+nums[curIndex];
                curIndex++;
            }else{
                result++;
                curN=curN*2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PatchingArray patchingArray=new PatchingArray();
        int[]nums={1,3};
        int n=6;
        System.out.println(patchingArray.minPatches(nums,n));
    }
}
