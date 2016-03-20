package org.ACRusher.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @link http://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * @author xiliang.zxl
 * @date 2016-03-20 下午5:30
 */
public class MaxIncreasingPathInMatrix {

//    public int longestIncreasingPath(int[][] matrix) {
//        //入参检验
//        if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
//        int row=matrix.length,column=matrix[0].length;
//        //创建递推辅助矩阵
//        int[][] aux1=new int[row][];
//        for(int i=0;i<row;++i){
//            aux1[i]=new int[column];
//            for(int j=0;j<column;++j){
//                aux1[i][j]=1;
//            }
//        }
//        int[][] aux2=new int[row][];
//        for(int i=0;i<row;++i){
//            aux2[i]=new int[column];
//            for(int j=0;j<column;++j){
//                aux2[i][j]=1;
//            }
//        }
//        //
//        int[][][][] possibleFromPoints=getPossibleFromPoints(row,column,matrix);
//        //递推
//        for(int i=0;i<row*column;++i){
//            if(i%2==0) bizCopy(aux1,aux2,matrix,possibleFromPoints);
//            else bizCopy(aux2,aux1,matrix,possibleFromPoints);
//        }
//        //搜集结果
//        int result=0;
//        for(int i=0;i<row;++i)
//            for(int j=0;j<column;++j)
//                result=result>aux1[i][j]?result:aux1[i][j];
//        return result;
//
//    }
//    private void bizCopy(int[][]from,int[][]to,int[][]origin,int[][][][] possibleFromPoints){
//        int row=origin.length,column=origin[0].length;
//
//        for(int i=0;i<row;++i){
//            for(int j=0;j<column;++j){
//                int v=to[i][j];
//                for(int[] point: possibleFromPoints[i][j]){
//                    if(from[point[0]][point[1]]+1 > v) v=from[point[0]][point[1]]+1;
//                }
//                to[i][j]=v;
//            }
//        }
//    }

    private int[][][][] getPossibleFromPoints(int row,int column,int[][] origin){
        int[][][][] result=new int[row][][][];
        for(int i=0;i<row;++i) {
            result[i]=new int[column][][];
            for(int j=0;j<column;++j){
                List<int[]> points=new ArrayList<int[]>();
                if(i-1>=0 && origin[i][j]>origin[i-1][j]) points.add(new int[]{i-1,j});
                if(j-1>=0 && origin[i][j]>origin[i][j-1]) points.add(new int[]{i,j-1});
                if(i+1<row && origin[i][j]>origin[i+1][j]) points.add(new int[]{i+1,j});
                if(j+1<column && origin[i][j]>origin[i][j+1]) points.add(new int[]{i, j + 1});
                result[i][j]= (int[][]) points.toArray(new int[][]{});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxIncreasingPathInMatrix maxIncreasingPathInMatrix=new MaxIncreasingPathInMatrix();
        int[][] input = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int[][] input1 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        System.out.println(maxIncreasingPathInMatrix.longestIncreasingPathII(null));
        System.out.println(maxIncreasingPathInMatrix.longestIncreasingPathII(new int[0][]));
        System.out.println(maxIncreasingPathInMatrix.longestIncreasingPathII(input));
        System.out.println(maxIncreasingPathInMatrix.longestIncreasingPathII(input1));
    }

    /**
     * 上面的方法复杂度为 (m*n)^2  oj 上 exceed time limit
     * 改造如下：
     */

    public int longestIncreasingPathII(int[][] matrix){
        //入参检验
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
        int row=matrix.length,column=matrix[0].length;
        //创建递推辅助矩阵
        int[][] aux=new int[row][];
        for(int i=0;i<row;++i){
            aux[i]=new int[column];
            for(int j=0;j<column;++j){
                aux[i][j]=1;
            }
        }
        //
        int[][][][] possibleFromPoints=getPossibleFromPoints(row,column,matrix);
        Map<Integer,List<int[]>> treeMap=new TreeMap<Integer, List<int[]>>();
        for(int i=0;i<row;++i){
            for(int j=0;j<column;++j){
                List<int[]> list=treeMap.get(matrix[i][j]);
                if(list==null) list=new ArrayList<int[]>();
                list.add(new int[]{i,j});
                treeMap.put(matrix[i][j],list);
            }
        }
        for(Map.Entry<Integer,List<int[]>> entry : treeMap.entrySet()){
            doWork(aux,entry.getValue(),possibleFromPoints);
        }
        int result=aux[0][0];
        for(int i=0;i<row;++i)
            for(int j=0;j<column;++j)
                result=Math.max(aux[i][j],result);
        return result;

    }

    private void doWork(int[][] aux,List<int[]> points,int[][][][] possibleFromPoints){
        for(int[] point : points){
            int[][] fromPoints=possibleFromPoints[point[0]][point[1]];
            for(int[] fromPoint : fromPoints){
                if(aux[fromPoint[0]][fromPoint[1]]+1>aux[point[0]][point[1]]){
                    aux[point[0]][point[1]]=aux[fromPoint[0]][fromPoint[1]]+1;
                }
            }
        }
    }


}
