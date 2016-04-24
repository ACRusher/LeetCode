package org.ACRusher.leetcode;

import java.util.*;

/**
 * @author xiliang.zxl
 * @date 2016-03-21 下午11:52
 */
public class ReconstructItinerary {

    private int n=0;
    private String root="JFK";
    public List<String> findItinerary(String[][] tickets) {
        if(tickets==null || tickets.length==0) return new ArrayList<String>();
        Map<String,List<String>> reachablePoints=new HashMap<String, List<String>>();
        Map<String,Integer> reachableCnt=new HashMap<String, Integer>();
        for(int i=0;i<tickets.length;++i){
            List<String> list=reachablePoints.get(tickets[i][0]);
            if(list==null) list=new ArrayList<String>();
            list.add(tickets[i][1]);
            reachablePoints.put(tickets[i][0],list);
        }
        for(String root: reachablePoints.keySet()){
            reachableCnt.put(root,getReachableCnt(root,reachablePoints));
        }
        System.out.println(reachableCnt);
        return null;
    }

    private int getReachableCnt(String root,Map<String,List<String>> map){
        Set<String> set=new HashSet<String>();
        LinkedList<String> linkedList=new LinkedList<String>();
        linkedList.offer(root);
        while (!linkedList.isEmpty()){
            String s=linkedList.poll();
            if(!set.contains(s)){
                set.add(s);
                if(map.get(s)!=null) linkedList.addAll(map.get(s));
            }
        }
        return set.size();
    }



    public static void main(String[] args) {
        String[] arr={
                "0.3340D1+0.0989D2+0.0805D3+0.0427D4+0.1892D5+0.0415D6+0.0241D7+0.1892D8",
                "0.1943D1+0.1943D2+0.1943D3+0.1943D4+0.0785D5+0.0445D6+0.0215D7+0.0785D8",
                "0.2675D1+0.114D2+0.2675D3+0.114D4+0.0489D5+0.114D6+0.0251D7+0.0489D8",
                "0.0791D1+0.0791D2+0.0222D3+0.1951D4+0.1951D5+0.1951D6+0.0392D7+0.1951D8)",
                "0.1248D1+0.1248D2+0.0181D3+0.1248D4+0.0402D5+0.1248D6+0.4022D7+0.0402D8",
                "0.0777D1+0.0777D2+0.0204D3+0.0759D4+0.164D5+0.3625D6+0.0429D7+0.1789D8",
                "0.0777D1+0.0777D2+0.0204D3+0.0759D4+0.164D5+0.3625D6+0.0429D7+0.1789D8",
        };
        double[] arr1={0.6333*0.6333,0.6333*0.1062,0.6333*0.2605,
                0.2605*0.75,0.2605*0.75,
                0.1062*0.1667,0.1062*0.8333};
        double[] result= new double[8];
        for(int i=0;i<8;++i){
            double t=0;
            int cur=0;
            for(String s:arr){
                String num=s.split("\\+")[i].split("D")[0];
                t+=Double.valueOf(num)*arr1[cur++];
            }
            result[i]=t;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=8;++i){
            sb.append(result[i-1]).append("D").append(i).append("+");
        }
        System.out.println(sb.toString());
        double sum=0;
        for(double d :result) sum+=d;
        System.out.println(sum);
    }

}
