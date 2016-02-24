package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2015-12-16 下午11:09
 */
public class MaxProductOfWordLength {

    public int maxProduct(String[] words) {
        if(words==null || words.length<2) return 0;
        int[]arr=new int[words.length];
        int[]len=new int[words.length];
        for(int i=0;i<words.length;++i){
            arr[i]=toInt(words[i]);
            len[i]=words[i].length();
        }
        int max=0;
        for(int i=0;i<words.length;++i){
            for(int j=i+1;j<words.length;++j){
                if((arr[i]&arr[j])==0){
                   max=Math.max(max,len[i]*len[j]);
                }
            }
        }
        return max;
    }
    private  int toInt(String s){
        int result=0;
        for(char ch : s.toCharArray()){
            result|=(1<<(ch-'a'));
        }
        return result;
    }

    public static void main(String[] args) {
        Integer i=new MaxProductOfWordLength().toInt("abc");
        System.out.println(i.toBinaryString(i));
    }
}
