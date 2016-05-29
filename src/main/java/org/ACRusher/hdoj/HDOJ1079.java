package org.ACRusher.hdoj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author xiliang.zxl
 * @date 2016-05-29 上午9:54
 */
public class HDOJ1079 {
    /**
     *  input : a  random date between 1900.1.1 and 2001.12.4
     *  output : whether there is a strategy to absolutely win
     *  conditions :
     *      1. A will walk first
     *      2. A/B walk at each turn by on step
     *      3. the guy can walk on day or one month if the next month has the same day.
     *
     *   算法:
     *      1. 策略题
     *      2. 送分题
     *      3. 肯定用动态规划
     *      4. 倒着推
     *          假设 f(n)  true为必胜 false 为不能必胜
     *          f(n)=!f(n+1) || !f(n+month)
     *
     *
     */
    static final boolean[] aux=new boolean[37198];
    static  Date endDay;
    static  Date startDay;
    static long milliSecondsOnyDay=24*3600000;

    static void init(){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            startDay= sdf.parse("1900-01-01 00:00:00.000");
            endDay= sdf.parse("2001-12-04 00:00:00.000");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws Exception {
        init();
        entrance();
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        while (cases-->0){
            System.out.println(aux[getIndex(scanner.nextInt(),scanner.nextInt(),scanner.nextInt())]?"YES":"NO");
        }
//        System.out.println(getIndex(2001,11,3));
    }

    static int getIndex(int year,int month ,int day){
        Calendar calendar=new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DATE,day);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        return (int)((calendar.getTime().getTime()-startDay.getTime())/milliSecondsOnyDay);
    }

    static void entrance(){
        int max=37197;
        aux[max]=true;
        aux[max-1]=true;
        for(int i=max-2;i>=0;--i){
            if(!aux[i+1]){
                aux[i]=true;
                continue;
            }
            int nextMonthDay=(int)nextMonthDay(i);
            if(nextMonthDay>0 && nextMonthDay<=max){
                aux[i]=!aux[nextMonthDay];
            }
        }
    }

    static long nextMonthDay(int index){
        long start=startDay.getTime();
        start+=index*milliSecondsOnyDay;
        Calendar calendar=new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        calendar.setTime(new Date(start));
        int thisMonthDay=calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.MONTH,1);
        int nextMonthDay=calendar.get(Calendar.DAY_OF_MONTH);
        if(thisMonthDay!=nextMonthDay){
            return -1;
        }
        return index+ (calendar.getTime().getTime()-start)/milliSecondsOnyDay;
    }



}
