package org.ACRusher.hdoj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiliang.zxl
 * @date 2016-05-24 下午9:24
 */
public class HDOJ1070 {
    /**
     *  input : milk[] , milk = { price : n , volume : v , brand : s }
     *  output : cheapest  brand
     *  conditions :
     *      1. 每天只喝200ml
     *      2. 牛奶超过5天就扔掉
     *      3. 瓶子里剩下小于200ml,则扔掉
     *      4. 只买一瓶
     *   思路:
     *      1. 遍历每个brand 计算买一瓶可以喝的天数
     *      2. 由于 价格/天数 可能是个无限不循环小数,故不能简单相除
     *      3. 基于分数 和 容量做排序
     *
     */

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        HDOJ1070 hdoj1070=new HDOJ1070();
        int cases=scanner.nextInt();
        while (cases-->0){
            int milkCnt=scanner.nextInt();
            List<Milk> milks=new ArrayList<>();
            while (milkCnt-->0) {
                Milk milk = new Milk(scanner.next(), scanner.nextInt(), scanner.nextInt());
                if(milk.days>0) milks.add(milk);
            }
            System.out.println(hdoj1070.entrance(milks));
        }
    }

    String entrance(List<Milk> milks){
        Collections.sort(milks);
        return milks.get(0).brand;
    }

    static class Milk implements Comparable<Milk> {
        String brand;
        Integer volume;
        long price;
        Integer days;//可以喝的天数

        public Milk(String brand, Integer price, Integer volume) {
            this.brand = brand;
            this.volume = volume;
            this.price = price;
            init();
        }

        void init(){
            if(volume<200){
                days=0;
            }else{
                days=Math.min(5,volume/200);
            }
        }

        @Override
        public int compareTo(Milk m) {
            long t=this.price*m.days-m.price*this.days;
            if(t!=0) return (int)t;
            return m.volume-this.volume;
        }
    }
}
