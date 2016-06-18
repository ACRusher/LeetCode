package org.ACRusher.hdoj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiliang.zxl
 * @date 2016-05-30 下午10:49
 */
public class HDOJ1077 {
    /**
     *  input : n 个点 , 坐标为 6位数
     *  output : 半径为 100000 的圆 最多包含多少个点
     *  conditions :
     *      1. n <= 300
     *      2. (x,y)  x<=1000000 y<=1000000
     *  algorithm :
     *      1. 1000000*1000000 个像素点,如果穷举时间复杂度过高
     *      2. 抛弃暴力搜索,探索问题转化
     *      3. 观察包含最多点的单位圆, 发现圆心可以用 两点之间的中点代替
     *      4. 这样只需要遍历 n^2 个中点, 复杂度为 n^3 ≈ 27000000
     *      5. 由于存在近似值,如果 x^2 +y^2 <=100001^2 则认为包含在内
     *
     *
     */
    private static final long INCLUDE_DISTANCE = 100001*100001L;

    private static boolean inCircle(int x,int y,int x1,int y1){
        long diffX=Math.abs(x1-x),diffY=Math.abs(y1-y);
        if( diffX>100001 || diffY> 100001){
            return false;
        }
        return diffX*diffX+diffY*diffY<=INCLUDE_DISTANCE;

    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        while (cases-->0){
            int n=scanner.nextInt();
            List<Point> pointList=new ArrayList<>();
            while (n-->0){
                pointList.add(new Point(scanner.nextDouble(),scanner.nextDouble()));
            }
            System.out.println(entrance(pointList));
        }
    }

    private static int entrance(List<Point> points){
        int size=points.size(),result=0;
        for(int i=0;i<size-1;++i){
            for(int j=i+1;j<size;++j){
                Point middle=middle(points.get(i),points.get(j));
                result=Math.max(result,inCircleCnt(middle,points));
            }
        }
        return result;
    }

    private static Point middle(Point left,Point right){
        return new Point((left.x+right.x)/2,(left.y+right.y)/2);
    }

    private static int inCircleCnt(Point root, List<Point> points){
        int cnt=0;
        for (Point p : points)
            if(inCircle(root.x,root.y,p.x,p.y)){
                cnt++;
            }
        System.out.println(root.x+","+root.y+" "+cnt);
        return cnt;
    }

    private static class Point{
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(double x,double y){
            this.x=(int)(x*100000);
            this.y=(int)(y*100000);
        }
    }




}
