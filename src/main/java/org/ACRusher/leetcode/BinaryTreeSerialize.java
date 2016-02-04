package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2015-10-28 下午11:34
 */
public class BinaryTreeSerialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        preTraversal(root, sb);
        return sb.toString();
    }
    private void preTraversal(TreeNode node,StringBuilder sb){
        if(sb.length()>0) sb.append(",");
        if(node==null) sb.append("NULL");
        else{
            sb.append(node.val);
            preTraversal(node.left, sb);
            preTraversal(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("NULL")) return  null;
        String[]nodes=data.split(",");
        return rebuild(nodes,new int[]{0});
    }
    private TreeNode rebuild(String[]nodes,int[]index){
        String data=nodes[index[0]++];
        if(data.equals("NULL")) return null;
        TreeNode node=new TreeNode(Integer.parseInt(data));
        if(index[0]<nodes.length){
            TreeNode left=rebuild(nodes,index);
            node.left=left;
        }
        if(index[0]<nodes.length){
            TreeNode right=rebuild(nodes,index);
            node.right=right;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode treeNode1=new TreeNode(1);
        TreeNode treeNode2=new TreeNode(2);
        TreeNode treeNode3=new TreeNode(3);
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        BinaryTreeSerialize binaryTreeSerialize=new BinaryTreeSerialize();
        System.out.println(binaryTreeSerialize.serialize(treeNode1));
        TreeNode t=binaryTreeSerialize.deserialize(binaryTreeSerialize.serialize(treeNode1));
        assert t.left.val==2;
        assert t.right.val==3;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
