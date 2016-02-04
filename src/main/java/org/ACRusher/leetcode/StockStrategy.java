package org.ACRusher.leetcode;

/**
 * 购买股票策略算法
 * 规则: 1. 买卖必须有1天缓冲 2. 可以尽可能多的买卖
 *
 * @author xiliang.zxl
 * @date 2015-11-28 下午2:16
 */
public class StockStrategy {

    /**
     * 解题思路:
     * 1. 首先本题是一个最优解问题,自然联想到可以使用DP算法
     * 2. 可以得到递推公式: (m>n)
     * f(n)=f(m) , 如果 n -> m 递减;
     * f(n)=max(f(m)+(arr[m-2]-arr[n])) , m为 从n开始的第一个波峰的下一个位置 或 下第二个位置;
     *
     * @param prices 某股票一段时间的价格 arr[i] 代表第i天的价格
     * @return
     */
    public int maxProfit(int[] prices) {
        //算法题一定要考虑验证入参
        if(prices==null || prices.length<=1){
            return 0;
        }
        int size=prices.length;
        //初始化一个辅助数组 aux[i] 代表 f(i)
        int[] aux=new int[size];
        if(prices[size-2]<prices[size-1]){
            aux[size-2]=prices[size-1]-prices[size-2];
        }
        //
        for(int i=size-3;i>=0;--i){
            calculate(aux,prices,i);
        }
        return aux[0];
    }

    private void calculate(int[] aux,int[] prices,int i){
        int max=0;
        for( int j=i+1;j<aux.length;++j){
            int temp=0;
            if(prices[j]>prices[i]) temp+=prices[j]-prices[i];
            if(j+2<aux.length){
                temp+=aux[j+2];
            }
            if(temp>max) max=temp;
            if(aux[j]>max) max=aux[j];
        }
        aux[i]=max;
    }

    public static void main(String[] args) {
        StockStrategy stockStrategy=new StockStrategy();
        int[] arr={6,1,3,2,4,7};
        System.out.println(stockStrategy.maxProfit(arr));
    }
}
