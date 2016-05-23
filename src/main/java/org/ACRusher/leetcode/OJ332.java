package org.ACRusher.leetcode;

import java.util.*;

/**
 * 旅行者线路规划问题
 * 也是 一个字符串排序问题
 *
 * @author xiliang.zxl
 * @date 2016-04-24 上午9:51
 */
public class OJ332 {


    public List<String> findItinerary(String[][] tickets) {
        Map<String,PriorityQueue<String>> ticketMap=new HashMap<String, PriorityQueue<String>>();
        for(String[] ticket : tickets){
//            ticketMap.computeIfAbsent(ticket[0],k->new PriorityQueue<String>()).add(ticket[1]);
        }
        List<String> itinerary=new ArrayList<String>();
        //递归解法
        find(ticketMap,"JFK",itinerary);
        //迭代解法
//        Stack<String> stack=new Stack<String>();
//        stack.push("JFK");
//        while (!stack.isEmpty()){
//            while (ticketMap.containsKey(stack.peek()) && !ticketMap.get(stack.peek()).isEmpty()){
//                stack.push(ticketMap.get(stack.peek()).poll());
//            }
//            itinerary.add(0,stack.pop());
//        }
        return itinerary;
    }

    private void find(Map<String,PriorityQueue<String>>  tickets, String cur,List<String> result){
        while (tickets.containsKey(cur) && !tickets.get(cur).isEmpty()) {
            find(tickets, tickets.get(cur).poll(), result);
        }
        result.add(0,cur);
        return;
    }




    public static void main(String[] args) {
        String[][] test={{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        System.out.println(new OJ332().findItinerary(test));
    }
}
