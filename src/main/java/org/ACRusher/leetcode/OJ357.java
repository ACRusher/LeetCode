package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2016-06-19 下午3:51
 */
public class OJ357 {

    private static final int MAX_N=100;

    private static int[] aux=new int[11];

    static {
        aux[0]=1;
        for(int i=1;i<11;++i){
            aux[i]=numbers(i);
        }
        for(int i=2;i<11;++i){
            aux[i]=aux[i]+aux[i-1];
        }
    }


    public int countNumbersWithUniqueDigits(int n) {
        if(n>10) n=10;
        return aux[n];
    }

    public static int numbers(int n){
        // A 10 ^ n  - A 9 ^ (n-1)
        if(n<=0) return 0;
        if(n==1) return 10;
        if(n>=10) n=10;
        int cnt=1;
        for(int i=0;i<n;++i){
            cnt*=(10-i);
        }
        int cnt1=1;
        for(int i=0;i<n-1;++i) cnt1*=(9-i);
        return cnt-cnt1;
    }

    public static void main(String[] args) {
        OJ357 oj357=new OJ357();
        System.out.println(oj357.countNumbersWithUniqueDigits(3));
    }
}
