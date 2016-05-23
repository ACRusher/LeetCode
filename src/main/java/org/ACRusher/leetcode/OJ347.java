package org.ACRusher.leetcode;

import java.util.*;

/**
 * @author xiliang.zxl
 * @date 2016-05-02 上午11:52
 */
public class OJ347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> aux=new HashMap<>();
        for(int i : nums){
            Integer cnt=0;
            if(aux.containsKey(i)) cnt=aux.get(i);
            aux.put(i,++cnt);
        }
        PriorityQueue<Node> priorityQueue=new PriorityQueue<>();
        for(Map.Entry<Integer,Integer> entry : aux.entrySet()){
            priorityQueue.add(new Node(entry.getKey(),entry.getValue()));
            if(priorityQueue.size()>k) priorityQueue.poll();
        }
        List<Integer> result=new ArrayList<>();
        for(Node n : priorityQueue) result.add(n.i);
        return result;
    }
    static  class  Node implements Comparable<Node>{
        public Integer i;
        public Integer cnt;

        public Node(Integer i, Integer cnt) {
            this.i = i;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt-o.cnt;
        }
    }

    public static void main(String[] args) {
        int[] test={1,1,1,2,2,3};
        int k=2;
        OJ347 oj347=new OJ347();
        oj347.topKFrequent(test,k);
    }
}
