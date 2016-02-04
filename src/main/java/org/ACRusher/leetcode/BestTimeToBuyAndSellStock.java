package org.ACRusher.leetcode;

import java.util.ArrayList;
import java.util.List;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int k, int[] prices) {
        if(k>(prices.length+1)/2){
            k=(prices.length+1)/2;
        }
        if(k==0) return 0;
        prices=handle(prices);
        return f(k,prices);
// 下面是另一种解法,使用的标准DP思想,复杂度也是多项式: N*N*N ,但是会超时
//       Integer[][] aux = new Integer[prices.length + 1][];
//        for (Integer i = 0; i < prices.length + 1; ++i) {
//            aux[i] = new Integer[k+1];
//            for (int j = 0; j < aux[i].length; ++j) {
//                aux[i][j] = new Integer(0);
//            }
//        }
//        for (Integer i = prices.length - 2; i >= 0; --i) {
//            for (Integer j = 1; j <=k; ++j) {
//                if (j > prices.length - i) {
//                    aux[i][j] = aux[i][j - 1];
//                    continue;
//                }
//                Integer t = aux[i + 1][j];
//                for (Integer m = i + 1; m < prices.length; ++m) {
//                    if (prices[m] - prices[i] + aux[m + 1][j-1] > t) {
//                        t = prices[m] - prices[i] + aux[m + 1][j-1];
//                    }
//                }
//                aux[i][j] = t;
//            }
//        }
//        return aux[0][k];
    }
     int f(int k, int[] prices) {
     //这种解法的最大时间复杂度为 N*N*1/k 其中k是大于2的常数
        if (prices.length < 2) return 0;
        //dif代表要达到"宇宙"最大收益 还需要(不够)的买入卖出机会
        int dif = prices.length / 2 - k;
        for (int i = 0; i < dif; ++i) {
            prices = merge(prices);
        }
        int result = 0;
        for (int i = 0; i < prices.length; ++i) {
            result += (i % 2 == 0 ? -1 : 1) * prices[i];
        }
        return result;
    }
    /**
    * 去掉一次买入卖出机会 返回一个最优状态
    */
    int[] merge(int[] arr){
        int min=arr[1]-arr[0];
        int index=0;
        for(int i=0;i<arr.length;i+=2){
            if(arr[i+1]-arr[i]<min){
                min=arr[i+1]-arr[i];
                index=i;
            }
            if(i+3<arr.length && arr[i+2]>arr[i] && arr[i+3]>arr[i+1]){
                if(arr[i+1]-arr[i+2]<min){
                    min=arr[i+1]-arr[i+2];
                    index=i+1;
                }
            }
        }
        int[] result=new int[arr.length-2];
        int j=0;
        for(int i=0;i<arr.length;++i){
            if(i!=index && i!=index+1){
                result[j++]=arr[i];
            }
        }
        return result;
    }
    /*
     * 初始化输入数组,去掉不必要的数据,只保留波峰和波谷
     */
    int[] handle(int[] arr) {
        if (arr.length == 1) {
            return new int[]{};
        }
        List<Integer> list = new ArrayList<Integer>();
        //1.处理第一个数
        int tt=1;
        while(tt<arr.length && arr[tt]==arr[0]) tt++;
        if ( tt<arr.length && arr[tt] > arr[0]) {
            list.add(arr[0]);
        }
        //2.处理中间的数
        Boolean isPositive = null;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] - arr[i - 1] > 0) {
                if (isPositive != null && !isPositive) {
                    list.add(arr[i - 1]);
                }
                isPositive = true;
            } else if (arr[i] - arr[i - 1] < 0) {
                if (isPositive != null && isPositive) {
                    list.add(arr[i - 1]);
                }
                isPositive = false;
            }
        }
        //3.处理最后一个数
        int t=arr.length-2;
        while(t>=0 && arr[t]==arr[arr.length-1]){
            t--;
        }
        if (t>=0 && arr[arr.length - 1] > arr[t]) {
            list.add(arr[arr.length - 1]);
        }
        //4.
        int[] result=new int[list.size()];
        for(int i=0;i<list.size();++i){
            result[i]=list.get(i);
        }
        return result;
    }
}
