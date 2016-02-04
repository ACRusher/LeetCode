package org.ACRusher.leetcode;

import java.util.Arrays;

/**
 * @see :  https://leetcode.com/problems/wiggle-sort-ii/
 *
 * Created by zhouxiliang on 2016/2/4.
 */
public class WiggleSortII {

    /**
     * 短短几行代码，却需要很多思考
     *
     * 1. 首先必须想到，分割线（中位数)的存在
     * 2. 其次必须想到，如何合理分配位置，保证相邻数字在排序数组中的下标差足够大（大于等于n/2)
     *
     * 算法：
     * 1. 强数组排序，分为 大于中位数的数组 A 、小于等于中位数的数组 B
     * 2. 偶数位置填写较小数组中的数，奇数位置填写较大数组中的数，
     * 3. 为了避免相同的值相邻，填写时必须保证任意相邻的数字，在排序中的index距离大于N/2 , N为数组长度
     *    如何保证？ A和B从最大的开始依次写入nums ， 呵呵（从最小的开始就错了）
     *
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        int[] sorted=Arrays.copyOfRange(nums,0,nums.length);
        Arrays.sort(sorted);
        int minEnd=(nums.length-1)/2 , maxEnd=nums.length-1;
        for(int i=0;i<nums.length;++i){
            if(i%2==0){
                nums[i]=sorted[minEnd--];
            }else{
                nums[i]=sorted[maxEnd--];
            }
        }
    }

    /**
     * 1. 首先想到的是，做两次交换：
     * 首先: 0-1 2-3 ... 小的在前
     * 其次: 1-2 3-4 ... 大的在前
     * 在数组没有重复元素的情况下，算法结束，复杂度 O(N)
     *
     * 但是如果有重复的元素，上述方法失效。
     * 然后开始思考如何在O(N)时间内，将数组元素打散，保证没有3个相同元素相邻。
     * 陷入僵局。。。
     *
     * 放弃尝试，看答案： https://leetcode.com/discuss/77133/o-n-o-1-after-median-virtual-indexing
     *
     */

}
