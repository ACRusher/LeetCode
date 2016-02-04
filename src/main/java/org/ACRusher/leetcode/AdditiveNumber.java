package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2015-11-21 下午5:06
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        //if(Double.valueOf(num)>Integer.MAX_VALUE) return false;
        boolean[]arr=new boolean[num.length()];
        for(int i=num.length()-1;i>=0;--i){
            calculate(num,arr,i);
        }
        return arr[0];
    }

    public void calculate(String num,boolean[] result,int position){
        if(position>=num.length()-2) {
            result[position]=false;
            return;
        }

        int size=num.length();
        for(int i=position;i<size-2;++i){
            if(i>position && num.charAt(position)=='0') break;
            for(int j=i+1;j<size-1;++j){
                if(j>i+1 && num.charAt(i+1)=='0') break;
                String tmp=String.valueOf(Long.valueOf(num.substring(position,i+1))+
                        Long.valueOf(num.substring(i+1,j+1)));
                if(tmp.length()>size-1-j || !num.substring(j+1).startsWith(tmp)) continue;
                if(tmp.length()==size-1-j || result[i+1]) {
                    result[position]=true;
                    return;
                }

            }
        }
    }

    public static void main(String[] args) {
        AdditiveNumber additiveNumber=new AdditiveNumber();
        System.out.println(additiveNumber.isAdditiveNumber("12012122436"));
        System.out.println(Integer.MAX_VALUE);
    }
}
