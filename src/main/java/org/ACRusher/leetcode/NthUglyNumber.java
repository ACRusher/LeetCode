package org.ACRusher.leetcode;

import java.util.Arrays;

/**
 * @author xiliang.zxl
 * @date 2015-12-13 下午9:12
 */
public class NthUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n==1) return 1;
        int[]ugly=new int[n];
        ugly[0]=1;
        int[] index=new int[primes.length];
        Arrays.fill(index,0);
        for (int pointer=1; pointer<n;pointer++){
            int nextValue=Integer.MAX_VALUE,nextIndex=0;
            for(int i=0;i<index.length;++i){
                while (ugly[index[i]]*primes[i]<=ugly[pointer-1]) index[i]++;
                if(ugly[index[i]]*primes[i] < nextValue){
                    nextValue=ugly[index[i]]*primes[i];
                    nextIndex=i;
                }
            }
            ugly[pointer]=nextValue;
            index[nextIndex]++;
        }
        return ugly[n-1];
    }

    public static void main(String[] args) {
//        int [] arr={2,7,13,19};
//        System.out.println(new NthUglyNumber().nthSuperUglyNumber(12,arr));
        String s="我钱懿斐";
        System.out.println(s.length());
    }
}
