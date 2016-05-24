package org.ACRusher.hdoj;

import java.util.Scanner;

/**
 * @author xiliang.zxl
 * @date 2016-05-24 下午9:52
 */
public class HDOJ1073 {
    /**
     *  input : string a , string b
     *  output : Accepted , Presentation Error ,Wrong Answer
     *  conditions :
     *      1. ' ' '\t' '\n' 被标示为空白符
     *      2. a b 完全相同则 Accepted
     *      3. 如果 a 与 b 只有空白符不同,则输出Presentation Error 否则 Wrong Answer
     *  算法:
     *      1. 如果得到a b 后, 首先判断是否完全相等,是则Accepted
     *      2. 否则通过 replaceAll 处理 a b ,再次判断完全相等则 Presentation Error
     *      3. 输出 Wrong Answer
     *      4. 难点应该是如何得到 a b
     *
     *
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases=scanner.nextInt();
        scanner.nextLine();//去掉第一行的enter
        while (cases-->0){
            System.out.println(judge(getInput(scanner),getInput(scanner)));
        }
    }

    static String getInput(Scanner scanner){
        StringBuilder a=new StringBuilder();
        String temp=null;
        scanner.nextLine();//忽略掉开始的start
        while (!"END".equals((temp=scanner.nextLine()))){
            a.append(temp).append('\n');
        }
        return a.toString();
    }

    static String judge(String a,String b){
        if(a.equals(b)){
            return ACCEPTED;
        }
        a=a.replaceAll("[ \n\t]+","");
        b=b.replaceAll("[ \n\t]+","");
        if(a.equals(b)){
            return PRESENTATION_ERROR;
        }
        return WRONG_ANSWER;
    }

    static final String ACCEPTED = "Accepted";
    static final String PRESENTATION_ERROR = "Presentation Error";
    static final String WRONG_ANSWER = "Wrong Answer";

}
