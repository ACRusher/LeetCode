package org.ACRusher.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author xiliang.zxl
 * @date 2016-06-18 下午10:55
 */
public class OJ352 {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
        public String toString(){
            return "("+start+","+end+")";
        }
    }

    /**
     * internal aux class.
     */
    static class Node implements Comparable<Node> {
        int value;
        /*the sorted previous node*/
        Node prev;
        /*the sorted next node*/
        Node next;
        /*the smallest node which can make up sequence numbers to this node */
        Node seqPrev;
        /*the largest node which can make up sequence numbers from this node */
        Node seqNext;

        public Node(int value) {
            this.value = value;
            this.prev = this.next = null;
            this.seqPrev = this;
            this.seqNext = this;
        }


        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static class SummaryRanges {

        private TreeSet<Node> treeSet = new TreeSet<>();

        /* Initialize your data structure here. */
        public SummaryRanges() {

        }

        /**
         * add a number to the internal data structure.
         *
         * @param val
         */
        public void addNum(int val) {
            Node node = new Node(val);
            if(!treeSet.add(node)) return;
            Node prev = treeSet.lower(node);
            Node next = treeSet.higher(node);
            if (prev != null) {
                node.prev = prev;
                prev.next=node;
                if (prev.value == val - 1) {
                    node.seqPrev = prev.seqPrev;
                    prev.seqPrev.seqNext=node;
                    if( next!=null && next.value==val+1){
                        prev.seqPrev.seqNext=next.seqNext;
                    }
                }
            }

            if (next != null) {
                node.next = next;
                next.prev=node;
                if (next.value == val + 1) {
                    node.seqNext = next.seqNext;
                    next.seqNext.seqPrev=node;
                    if(prev!=null && prev.value==val-1){
                        next.seqNext.seqPrev=prev.seqPrev;
                    }
                }
            }
        }

        /**
         * the interval means sequence numbers {@link Interval}
         * <p>
         * this time complex is o(N) , N is the scale of interval
         *
         * @return
         */
        public List<Interval> getIntervals() {
            List<Interval> result=new ArrayList<>();
            if(treeSet.isEmpty()){
                return result;
            }
            Node worker=treeSet.first();
            while (worker!=null){
                Interval interval=new Interval(worker.value,worker.seqNext.value);
                result.add(interval);
                worker=worker.seqNext.next;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges=new SummaryRanges();
        summaryRanges.addNum(6);
        summaryRanges.addNum(6);
        summaryRanges.addNum(0);
        summaryRanges.addNum(4);
        summaryRanges.addNum(8);
        summaryRanges.addNum(7);
        summaryRanges.addNum(6);
        summaryRanges.addNum(4);
        summaryRanges.addNum(7);
        summaryRanges.addNum(5);
        System.out.println(summaryRanges.getIntervals());
    }
}
