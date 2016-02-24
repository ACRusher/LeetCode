package org.ACRusher.leetcode;

import java.util.Arrays;

/**
 * @author xiliang.zxl
 * @date 2015-12-31 上午12:10
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if(amount<0 || coins==null || coins.length==0) return -1;
        if(amount==0) return 0;
        int[]aux=new int[amount+1];
        Arrays.fill(aux,amount+1);
        for(int i=1;i<=amount;++i){
            for(int j=0;j<coins.length;++j){
                if(coins[j]<=i){
                    aux[i]=Math.min(aux[i],aux[i-coins[j]]+1);
                }
            }
        }
        return aux[amount]<=amount?aux[amount]:-1;
    }
}
