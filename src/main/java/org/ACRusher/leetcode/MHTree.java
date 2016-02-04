package org.ACRusher.leetcode;

import java.util.*;

/**
 * 给定一个图,就计算最小高度树的所有根节点
 *
 * @author xiliang.zxl
 * @date 2015-11-29 上午10:12
 */
public class MHTree {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //1. 通过邻接链表的方式创建图
        LinkedList[] graph=createAdjacentLinkedListGraph(n,edges);
        //2. 计算所有根节点的树高度
        HashMap<Integer,Integer> heightMap=new HashMap<Integer, Integer>();
        int min=Integer.MAX_VALUE;
        //2.1 优化 按照邻接点的多少排序
        List<Integer> roots=getSortedNodes(graph);
        for(int i=0;i<n;++i){
            int curRoot=roots.get(i);
            Integer curHeight=getTreeHeight(curRoot,graph,min);
            if(curHeight!=null) {
                heightMap.put(curHeight,curRoot);
                if(curHeight<min) min=curHeight;
            }
        }
        //3. 找出最低高度的所有节点
        return parseResult(heightMap,min);
    }

    protected LinkedList[] createAdjacentLinkedListGraph(int n,int[][]edges){
        LinkedList[] adjacentList=new LinkedList[n];
        for(int i=0;i<n;++i) adjacentList[i]=new LinkedList();
        for(int i=0;i<edges.length;++i){
            adjacentList[edges[i][0]].add(edges[i][1]);
            adjacentList[edges[i][1]].add(edges[i][0]);
        }
        return adjacentList;
    }

    private List<Integer> getSortedNodes(LinkedList[] graph){
        List<Pair<Integer,Integer>> list= new ArrayList<Pair<Integer, Integer>>();
        for(int i=0;i<graph.length;++i){
            list.add(new Pair<Integer, Integer>(i,graph[i].size()));
        }
        Collections.sort(list, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        List<Integer> result=new ArrayList<Integer>();
        for(Pair<Integer,Integer> pair: list){
            result.add(pair.getKey());
        }
        return result;

    }

    /**
     * 获取以root为根节点的树高度
     *
     * @param root
     * @param graph
     * @param threshold  树高度的临界限制
     * @return  返回数的高度,如果高度超过threshold时,将停止计算并返回null
     */
    protected Integer getTreeHeight(int root,LinkedList<Integer>[] graph,int threshold){
        LinkedList<Integer> stack=new LinkedList<Integer>();
        boolean[] visited=new boolean[graph.length];
        int height=0;
        stack.push(root);
        stack.push(-1);
        visited[root]=true;
        while (!stack.isEmpty()){
            for(int i=stack.pollLast();i!=-1;i=stack.pollLast()){
                LinkedList<Integer> adjacentNodes=graph[i];
                for(Integer node : adjacentNodes){
                    if(!visited[node]){
                        visited[node]=true;
                        stack.push(node);
                    }
                }
            }
            if(++height>threshold) return null;
            if(!stack.isEmpty()){
                stack.push(-1);

            }
        }
        return height;
    }

    /**
     * 运算结果处理
     *
     * @param heightMap key 为 树高度 , value为 root节点
     * @param min 最小高度
     * @return
     */
    private List<Integer> parseResult(HashMap<Integer,Integer> heightMap,int min){
        List<Integer> list=new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> entry : heightMap.entrySet()){
            if(entry.getKey().equals(min)){
                list.add(entry.getValue());
            }
        }
        return list;
    }

    private static class Pair<K,V>{
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MHTree tree=new MHTree();
        int[][]arr={{1,0},{1,2},{1,3}};
        int n=4;
        List list=tree.findMinHeightTrees(n,arr);
        System.out.println(list);
    }
}
