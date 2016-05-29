package org.ACRusher.hdoj;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author xiliang.zxl
 * @date 2016-05-28 下午12:26
 */
public class HDOJ1075 {
    /**
     *  input :  dictionary  and  a book
     *  output : the translated book
     *  conditions:
     *      you should keep the layout as the origin book be , except
     *      translate the words in the dictionary .
     *   algorithm :
     *      this is a problem than present you scores.
     *
     */

    static HashMap<String,String> dictionary=new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        scanner.nextLine();//eat start
        String line=null;
        while (!"END".equals(line=scanner.nextLine())){
            String[]arr=line.split("\\s+");
            dictionary.put(arr[1],arr[0]);
        }

        scanner.nextLine();//eat start
        while (!"END".equals(line=scanner.nextLine())){
            if(line.isEmpty()){
                System.out.println();
                continue;
            }
            System.out.println(translate(line));
        }
    }

    static  String translate(String line){
        StringBuilder sb=new StringBuilder();
        int i=0,j=0,size=line.length();
        while (i<size){
            while (i<size &&!Character.isAlphabetic(line.charAt(i))){
                sb.append(line.charAt(i++));
            }
            j=i+1;
            while (j<size && Character.isAlphabetic(line.charAt(j))) j++;
            if(i<size) {
                String word = line.substring(i, j);
                if(dictionary.containsKey(word)){
                    sb.append(dictionary.get(word));
                }else {
                    sb.append(word);
                }
            }
            i=j;
        }
        return sb.toString();
    }

}
