package org.ACRusher.leetcode;

import sun.text.normalizer.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-04-24 下午8:07
 */
public class OJ336 {


    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root=new TrieNode('_');
        buildDictionary(root,words);
        List<List<Integer>> result=new ArrayList<>();
        for(int i=0;i<words.length;++i){
            String[] arr=getRightPossibleWords(words[i]);
            for(String word : arr){
                int index=getWordIndex(word,root);
                if(index>=0 && index!=i) {
                    List<Integer> pair=new ArrayList<>();
                    pair.add(i);
                    pair.add(index);
                    result.add(pair);
                }

            }

            arr=getLeftPossibleWords(words[i]);
            for(String word : arr){
                int index=getWordIndex(word,root);
                if(index>=0 && index!=i) {
                    List<Integer> pair=new ArrayList<>();
                    pair.add(index);
                    pair.add(i);
                    result.add(pair);
                }

            }
        }
        int emptyIndex=-1;
        for(int i=0;i<words.length;++i){
            if(words[i].isEmpty()){
                emptyIndex=i;
            }
        }
        if(emptyIndex!=-1){
            for(int i=0;i<words.length;++i){
                List<Integer> list=new ArrayList<>();
                list.add(i);
                list.add(emptyIndex);
                result.add(list);
                list=new ArrayList<>();
                list.add(emptyIndex);
                list.add(i);
                result.add(list);
            }
        }
        return result;
    }

    private String[] getRightPossibleWords(String origin){
        List<String> result=new ArrayList<>();
        result.add(origin);
        for(int i=1;i<origin.length();++i){
            if(isPalindrome(origin.substring(origin.length()-i))){
                result.add(origin.substring(0,origin.length()-i));
            }
        }
        List<String> reverse=new ArrayList<>();
        for(String s : result){
            StringBuilder sb=new StringBuilder();
            for(int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
            reverse.add(sb.toString());
        }
        return reverse.toArray(new String[reverse.size()]);
    }
    private String[] getLeftPossibleWords(String origin){
        List<String> result=new ArrayList<>();
//        result.add(origin);
        for(int i=1;i<origin.length();++i){
            if(isPalindrome(origin.substring(0,origin.length()-i))){
                result.add(origin.substring(origin.length()-i));
            }
        }
        List<String> reverse=new ArrayList<>();
        for(String s : result){
            StringBuilder sb=new StringBuilder();
            for(int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
            reverse.add(sb.toString());
        }
        return reverse.toArray(new String[reverse.size()]);
    }


    private boolean isPalindrome(String s){
        int i=0,j=s.length()-1;
        while (i<j && s.charAt(i)==s.charAt(j)) {
            i++;
            j--;
        }
        return i>=j;
    }

    public int getWordIndex(String word,TrieNode root){
        for(char ch : word.toCharArray()){
            if((root=root.getAdj(ch))==null) return -1;
        }
        return root.index;
    }

    public void buildDictionary(TrieNode root,String[] words){
        for(int i=0;i<words.length;++i){
            TrieNode cur=root;
            for(int j=0;j<words[i].length();++j){
                TrieNode next=null;
                if((next=cur.getAdj(words[i].charAt(j)))==null){
                    next=new TrieNode(words[i].charAt(j));
                    cur.addAdj(next);
                }
                cur=next;
                if(j==words[i].length()-1) cur.index=i;
            }
        }
    }


    static class TrieNode{
        public char ch;
        public int index=-1;
        public TrieNode[] adj=new TrieNode[52];
        public TrieNode getAdj(char ch){
            return adj[getIndex(ch)];
        }

        public int getIndex(char ch){
            int i=0;
            if(Character.isLowerCase(ch)) i=ch-'a'+26;
            else i=ch-'A';
            return i;
        }
        public void addAdj(TrieNode node){
            adj[getIndex(node.ch)]=node;
        }
        public TrieNode(char ch){
            this.ch=ch;
        }
    }
}
