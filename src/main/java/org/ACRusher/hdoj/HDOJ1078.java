package org.ACRusher.hdoj;

import java.util.*;

/**
 * @author xiliang.zxl
 * @date 2016-05-25 上午12:02
 */
public class HDOJ1078 {
    /**
     *  input : matrix[][] ,  k
     *  output : max path value
     *  conditions :
     *      1. 起点为 0,0
     *      2. 每次移动不成超过k步,每步只能移动到邻接点
     *      3. 第n次移动的终点,必须比n-1次的终点 value更大
     *      4. n , k  ∈  [1,100]
     *  算法:
     *      1. 起点为0 ,0
     *      2. 从当前位置,找到 k 步内所有可达的点
     *      3. 可达的点中,value比当前值大的为可选下一节点
     *      4. 将到达过的节点 标记为 visited
     *      5. 递归遍历所有的路径
     *
     *      基于以上思路,可以完成一个最直观的递归算法。
     *
     *      时间复杂度: min( k^n, n!)
     *
     *      6. 很明显,朴素递归算法会超时
     *      7. 递归过程中有很多重复的子问题
     *      8. 但是重复的子问题过多,无法使用备忘录缓存
     *
     *      9. 考虑使用贪心或DP来优化算法
     *      10. 将所有点 按照value 排序, 转化问题为:
     *          从左边向右边移动,求最大路径权重
     *          很明显,可以使用从右边到左的递推算法,减少子问题
     *      11. 到此新的动态规划算法思路很清晰了
     *      12. 算法实现提交后 超时, O(N^4) 是不符合要求的,因为 N可以取100
     *      13. 考虑将算法复杂度转化为 O(N^2*K^2) 参考 entrance 入口实现
     *          很遗憾还是超时了
     *
     *      14. 妈的,理解错题意了,原来k步不可以拐弯
     *      15. 不拐弯 时间复杂度降低为 O(N^2*k)提交还是超时了
     *      16. 参考了别人的实现,通过正向dp可以加上一个常数因子 1/m
     *          时间复杂度变为: O(N^2 * k * 1/m) 700ms AC
     *          最终参考实现 entrance1
     *
     */

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n,k;
        while ((n=scanner.nextInt())>0){
            k=scanner.nextInt();
            List<Point> list=new ArrayList<>(n*n);
            for(int i=0;i<n;++i){
                for(int j=0;j<n;++j){
                    list.add(new Point(i,j,scanner.nextInt()));
                }
            }
            Collections.sort(list);
            System.out.println(entrance1(list,k,n));
        }
        scanner.nextInt();//读取最后一个输入
    }

    static int entrance1(List<Point> points,int k,int n){
        int start=0,max=0;
        for(int i=0;i<points.size();++i) {
            Point p=points.get(i);
            if (p.x == 0 && p.y == 0) {
                start = i;
                p.maxPathValue=p.value;
                max=p.value;
                break;
            }
        }
        Point[][]  arr=new Point[n][n];
        for(int i=0;i<n;++i) arr[i]=new Point[n];
        for(Point point : points) arr[point.x][point.y]=point;
        for(int i=start;i<points.size();++i){
            Point p=points.get(i);
            if(p.maxPathValue==0) continue;
            for(int j=p.x-k;j<=p.x+k;++j){
                if(j<0 || j>=n) continue;
                if(arr[j][p.y].value<=p.value) continue;
                Point t=arr[j][p.y];
                if(t.maxPathValue<t.value+p.maxPathValue){
                    t.maxPathValue=t.value+p.maxPathValue;
                }
                if(max<t.maxPathValue){
                    max=t.maxPathValue;
                }
            }
            for(int j=p.y-k;j<=p.y+k;++j){
                if(j<0 || j>=n) continue;
                if(arr[p.x][j].value<=p.value) continue;
                Point t=arr[p.x][j];
                if(t.maxPathValue<t.value+p.maxPathValue){
                    t.maxPathValue=t.value+p.maxPathValue;
                }
                if(max<t.maxPathValue){
                    max=t.maxPathValue;
                }
            }
        }
        return max;
    }

    static int entrance(List<Point> points,int k){
        int size=points.size();
        Map<String,Integer> indexHash=new HashMap<>();//坐标hash
        Map<String,Point> pointHash=new HashMap<>();//point hash
        for(int i=0;i<size;++i){
            Point p=points.get(i);
            indexHash.put(p.x+","+p.y,i);
            pointHash.put(p.x+","+p.y,p);
        }
        for(int i=size-1;i>=0;--i){
            Point from=points.get(i);
            int max=0;
            Set<Point> rangePoints=rangePonints(from,k,pointHash);
            for(Point p : rangePoints){
                Integer index=indexHash.get(p.x+","+p.y);
                if(index!=null && index>i && p.maxPathValue > max){
                    max=p.maxPathValue;
                }
            }
            from.maxPathValue=max+from.value;
            if(from.x==0 && from.y==0) return from.maxPathValue;
        }
        return -1;//never happen
    }

    static Set<Point> rangePonints(Point from,int k,Map<String,Point> pointMap){
        Set<Point> result=new HashSet<>();
        for(int i=0;i<=k;++i){
            collectPoint(pointMap,result,from,i,0);
            collectPoint(pointMap,result,from,-i,0);
            collectPoint(pointMap,result,from,0,i);
            collectPoint(pointMap,result,from,0,-i);
        }
        return result;
    }

    static void collectPoint(Map<String,Point> pointMap,Set<Point> result,Point from,int difX,int difY){
        Point point=pointMap.get((from.x+difX)+","+(from.y+difY));
        if(point!=null && point.value>from.value) result.add(point);
    }

    private static class Point implements Comparable<Point>{
        int x;
        int y;
        int value;
        int maxPathValue;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Point o) {
            return value-o.value;
        }

    }

    static boolean adjacent(Point from,Point to,int k){
        return Math.abs(from.x-to.x)+Math.abs(from.y-to.y) <= k;
    }

}
