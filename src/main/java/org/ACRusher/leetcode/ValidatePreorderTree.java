package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2016-03-21 上午12:35
 */
public class ValidatePreorderTree {

    private String[] tokens;
    private int index;

    public boolean isValidSerialization(String preorder) {
        index=0;
        this.tokens=preorder.split(",");
        if(!validate()) return false;
        return index==tokens.length;
    }

    private boolean validate(){
        if(tokens[index].equals("#")){
            index++;
            return true;
        }
        if(++index>=tokens.length) return false;
        if(!validate()) return false;
        if(index>=tokens.length) return false;
        if(!validate()) return false;
        return true;
    }

    public static void main(String[] args) {
        ValidatePreorderTree validatePreorderTree=new ValidatePreorderTree();
        String s="1,#,#";
        System.out.println(validatePreorderTree.isValidSerialization(s));
    }
}
