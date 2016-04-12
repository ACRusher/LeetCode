package org.ACRusher.leetcode;

import java.util.HashMap;

/**
 * Created by zhouxiliang on 2016/4/10.
 */
public class HouseRobberIII {


    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        HashMap<TreeNode,int[]> aux=new HashMap<TreeNode, int[]>();
        postOrderTraversal(root,aux);
        return Math.max(aux.get(root)[0],aux.get(root)[1]);
    }

    public void postOrderTraversal(TreeNode root,HashMap<TreeNode,int[]> aux){
        if(root.left!=null) postOrderTraversal(root.left,aux);
        if(root.right!=null) postOrderTraversal(root.right,aux);
        int[] tmp=new int[4];
        if(root.left!=null){
            tmp[0]=aux.get(root.left)[0];
            tmp[1]=aux.get(root.left)[1];
        }
        if(root.right!=null){
            tmp[2]=aux.get(root.right)[0];
            tmp[3]=aux.get(root.right)[1];
        }
        int[]cur=new int[2];
        cur[0]=Math.max(tmp[0],tmp[1])+Math.max(tmp[2],tmp[3]);
        cur[1]=tmp[0]+tmp[2]+root.val;
        aux.put(root,cur);
    }


    public static void main(String[] args) {

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
