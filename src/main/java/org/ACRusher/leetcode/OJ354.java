package org.ACRusher.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xiliang.zxl
 * @date 2016-06-19 上午11:31
 */
public class OJ354 {

//    public int maxEnvelopes(int[][] envelopes) {
//        if(envelopes==null) return 0;
//        if(envelopes.length<=1) return envelopes.length;
//        for(int[] arr : envelopes){
//            if(arr[0]<arr[1]){
//                arr[0]=arr[0]^arr[1];
//                arr[1]=arr[0]^arr[1];
//                arr[0]=arr[0]^arr[1];
//            }
//        }
//        Arrays.sort(envelopes, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0]!=o2[0]) return o1[0]-o2[0];
//                return o1[1]-o2[1];
//            }
//        });
//        int[] current=envelopes[0];
//        System.out.println("("+current[0]+","+current[1]+")");
//        int result=1;
//        for(int i=1;i<envelopes.length;++i){
//            if(current[0]<envelopes[i][0] && current[1]<envelopes[i][1]){
//                result++;
//                current=envelopes[i];
//                System.out.println("("+current[0]+","+current[1]+")");
//            }
//        }
//        return result;
//    }

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes==null) return 0;
        if(envelopes.length<=1) return envelopes.length;
        int[] aux=new int[envelopes.length];
        Arrays.fill(aux,1);
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]) return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
        });
        for(int i=1;i<aux.length;++i){
            int[] now=envelopes[i];
            for(int j=0;j<i;++j){
                int[] left=envelopes[j];
                if(now[0]>left[0] && now[1]>left[1]){
                    aux[i]=Math.max(aux[i],aux[j]+1);
                }
            }
        }
        int max=1;
        for(int i : aux) max=Math.max(i,max);
        return max;
    }

    public static void main(String[] args) {
        OJ354 oj354=new OJ354();
        //[5,4],[6,4],[6,7],[2,3]
        //[[1,15],[7,18],[7,6],[7,100],[2,200],[17,30],[17,45],[3,5],[7,8],[3,6],[3,10],[7,20],[17,3],[17,45]]
//        int[][] test={{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},
//                {17,3},{17,45}};
        int[][] test={{5,4},{6,4},{6,7},{2,3},};
        int max= oj354.maxEnvelopes(test);
        System.out.println(max);
    }
}
