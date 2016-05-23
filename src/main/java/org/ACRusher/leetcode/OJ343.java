package org.ACRusher.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-04-25 下午10:02
 */
public class OJ343 {

    public int integerBreak(int n) {
        if(n==2)  return 1;
        if(n==3) return 2;
        int n3=n/3;
        int left=n-3*n3;
        int n2=0;
        if(left==1){
            n3-=1;
            n2=2;
        }else if(left==2){
            n2=1;
        }
        return Double.valueOf(Math.pow(3, n3) *  Math.pow(2, n2)).intValue();


    }

    public String reverseVowels(String s) {
        if(s==null || s.isEmpty()) return s;
        StringBuilder sb=new StringBuilder(s);
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<sb.length();++i){
            if("aeiouAEIOU".contains(""+sb.charAt(i))){
                list.add(i);
            }
        }
        Integer[]arr=list.toArray(new Integer[]{});
        int i=0,j=arr.length-1;
        while (i<j){
            char ch=sb.charAt(arr[i]);
            sb.setCharAt(arr[i],sb.charAt(arr[j]));
            sb.setCharAt(arr[j],ch);
            i++;
            j--;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        OJ343 oj343=new OJ343();
        System.out.println(oj343.reverseVowels("hello"));
    }

}
