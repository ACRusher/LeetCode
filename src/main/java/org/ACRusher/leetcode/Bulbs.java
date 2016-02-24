package org.ACRusher.leetcode;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiliang.zxl
 * @date 2015-12-20 上午10:09
 */
public class Bulbs {

    private int[] aux=new int[100000];

    private int[] sum=new int[100000];

    private AtomicBoolean isFirts=new AtomicBoolean(false);

    public int bulbSwitch(int n) {
        if(n<=0) return 0;
        int cnt=0;
        for(int i=1;i<=n;++i) {
            int a = (int) Math.sqrt(i);
            if (a * a == i) cnt++;
        }
        return cnt;
    }
    private void init(){
        for(int i=1;i<=100000;++i){
            aux[i-1]=num(i);
        }
        sum[0]=1;
        for(int i=1;i<100000;++i){
            sum[i]=sum[i-1]+(aux[i]%2!=0?1:0);
        }
    }

    private int num(int n){
        if(n==1) return 1;
        int cnt=0;
        int max=n;
        for(int i=1;i<max/2;++i){
            if(n%i==0){
                cnt+=2;
                max=n/i;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Bulbs bulbs=new Bulbs();
        long start=System.currentTimeMillis();
        int n=bulbs.bulbSwitch(99999);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(n);
    }
}
