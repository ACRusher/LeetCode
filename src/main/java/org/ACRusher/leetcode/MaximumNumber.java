package org.ACRusher.leetcode;

import java.util.Arrays;

/**
 * @author xiliang.zxl
 * @date 2016-03-05 下午10:01
 */
public class MaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if(k<=0) return new int[0];
        int[] result={};
        for(int i=0;i<=k;++i){
            int[] a=search(nums1,i);
            int[] b=search(nums2,k-i);
            result=max(result,merger(a,b));
        }
        return result;
    }

    /**
     * merger 成一个最大数
     * @param a
     * @param b
     * @return
     */
    public int[] merger(int[] a , int[] b){
        int curA=0,curB=0,curResult=0;
        int[] result=new int[a.length+b.length];
        while (curA<a.length && curB<b.length){
            if(a[curA]>b[curB]){
                result[curResult++]=a[curA++];
            }else if(a[curA]<b[curB]){
                result[curResult++]=b[curB++];
            }else{
                if(max(a,curA,b,curB)){
                    result[curResult++]=a[curA++];
                }else{
                    result[curResult++]=b[curB++];
                }
            }
        }
        if(curA<a.length){
            while (curA<a.length)
                result[curResult++]=a[curA++];
        }
        if(curB<b.length){
            while (curB<b.length)
                result[curResult++]=b[curB++];
        }
        return result;
    }

    /**
     * 第一个数组是否比第二个数组大
     * @param a
     * @param curA
     * @param b
     * @param curB
     * @return
     */
    public boolean max(int[] a, int curA,int[]b,int curB){
        while (curA<a.length && curB<b.length){
            if(a[curA]<b[curB]) return false;
            else if(a[curA]>b[curB]) return true;
            curA++;curB++;
        }
        return curA!=a.length;
    }

    /**
     * 返回组装数较大的数组
     * @param a
     * @param b
     * @return
     */
    public int[] max(int[] a, int[]b){
        if(a.length<b.length) return b;
        if(b.length<a.length) return a;
        for(int i=0;i<a.length;++i){
            if(a[i]>b[i]) return a;
            else if(a[i]<b[i]) return b;
        }
        return a;
    }

    /**
     *  从数组a生产k个数字（构成最大数），保持数字顺序不变
     * @param a
     * @param k
     * @return
     */
    public int[] search(int[] a,  int k){
        if(a==null || k>a.length) return new int[0];
        int[]result=new int[k];
        int cur=-1;
        for(int i=0;i<k;++i){
            cur=max(a,cur,a.length-k+1+i);
            result[i]=a[cur];
        }
        return result;
    }

    /**
     * 返回数组中的最大数字的坐标
     * @param a
     * @param leftExclusive
     * @param rightExclusive
     * @return
     */
    public int max(int[]a,int leftExclusive,int rightExclusive){
        int maxIndex=leftExclusive+1;
        for(int i=leftExclusive+1;i<rightExclusive;++i){
            if(a[i]>a[maxIndex]) maxIndex=i;
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        //test search
        int[]test={2,5,6,4,4,0},test1={7,3,8,0,6,5,7,6,2};
        MaximumNumber maximumNumber=new MaximumNumber();
        System.out.println(Arrays.toString(maximumNumber.search(test, 6)));
        System.out.println(Arrays.toString(maximumNumber.search(test1, 9)));
        //73825644

        //test max number
        System.out.println(Arrays.toString(maximumNumber.maxNumber(test,test1, 15)));

    }
}
