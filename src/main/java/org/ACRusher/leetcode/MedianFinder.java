package org.ACRusher.leetcode;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by zhouxiliang on 2015/10/23.
 */
public class MedianFinder {

    private Node root=null;
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(root==null){
            root=new Node(num);
        }else{
            addNum(root,num);
        }
    }
    private void addNum(Node node,int value){
        node.nodeCnt++;
        if(value<=node.value){
            if(node.leftTree==null){
                Node t=new Node(value);
                node.leftTree=t;
            }else{
                addNum(node.leftTree,value);
            }
        }else{
            if(node.rightTree==null){
                Node t=new Node(value);
                node.rightTree=t;
            }else{
                addNum(node.rightTree,value);
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return findMedian(root,0,0);
    }

    public double findMedian(Node node ,int leftCnt,int rightCnt){
        int nodeLeftCnt=node.leftTree==null?0:node.leftTree.nodeCnt;
        int nodeRightCnt=node.rightTree==null?0:node.rightTree.nodeCnt;
        int diff=nodeLeftCnt+leftCnt-nodeRightCnt-rightCnt;
        if(diff==0) return node.value;
        if(diff==-1) return (node.value+min(node.rightTree)+0.0)/2;
        if(diff==1) return (node.value+max(node.leftTree)+0.0)/2;
        if(diff<-1) return findMedian(node.rightTree,leftCnt+node.nodeCnt-node.rightTree.nodeCnt,rightCnt);
        return findMedian(node.leftTree,leftCnt,rightCnt+node.nodeCnt-node.leftTree.nodeCnt);
    }

    private int min(Node node){
        while (node.leftTree!=null) node=node.leftTree;
        return node.value;
    }
    private int max(Node node){
        while (node.rightTree!=null) node=node.rightTree;
        return node.value;
    }

    private static class Node{
        public Node(int value) {
            this.value = value;
            this.nodeCnt=1;
        }
        public int value;
        public int nodeCnt;
        public Node leftTree;
        public Node rightTree;
    }
};

// Your org.ACRusher.leetcode.MedianFinder object will be instantiated and called as such:
// org.ACRusher.leetcode.MedianFinder mf = new org.ACRusher.leetcode.MedianFinder();
// mf.addNum(1);
// mf.findMedian();
