package org.ACRusher.hdoj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiliang.zxl
 * @date 2016-05-28 上午10:32
 */
public class HDOJ1072 {
    /**
     * input : a puzzle
     * output : the less time to escape
     * limit :
     * the timed bomb will explode in 6 minutes ,
     * conditions :
     * type 4 means the bomb will be reset . type 3 means it's a gate to out
     * type 1 means normal room , type 2 means start point .
     * type 0 means wall that can't walk on it.
     * algorithm:
     * 1. 每次到达 4 ,意味着子问题
     * 2. 按照广度进行探索
     * 3. 无路可走返回 -1
     */

    static int[][] layout;
    static int[][] leastTime;
    static int[][] visited;

    static void init(int r, int c) {
        layout = new int[r][];
        for (int i = 0; i < r; ++i) {
            layout[i] = new int[c];
        }
        leastTime = new int[r][c];
        for (int i = 0; i < r; ++i) {
            leastTime[i] = new int[c];
            for (int j = 0; j < c; ++j) leastTime[i][j] = Integer.MAX_VALUE;
        }
        visited = new int[r][];
        for (int i = 0; i < r; ++i) {
            visited[i] = new int[c];
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        while (cases-->0){
            int r=scanner.nextInt(),c=scanner.nextInt();
            init(r,c);
            for(int i=0;i<r;++i){
                for(int j=0;j<c;++j){
                    layout[i][j]=scanner.nextInt();
                }
            }
            System.out.println(entrance());
        }
    }

    static int entrance() {
        int x = 0, y = 0;
        for (int i = 0; i < layout.length; ++i)
            for (int j = 0; j < layout[i].length; ++j) {
                if (layout[i][j] == 2) {
                    x = i;
                    y = j;
                    break;
                }
            }
        leastTime[x][y] = 0;
        List<Point> resetPoints = new ArrayList<>(layout.length);
        List<Point> endPoint = new ArrayList<>(1);
        resetPoints.add(new Point(x, y));
        while (!resetPoints.isEmpty()) {
            List<Point> nextResetPoints = new ArrayList<>(layout.length);
            for(Point point : resetPoints){
                babyRun(point.x,point.y,leastTime[point.x][point.y],0,nextResetPoints,endPoint);
            }
            if(!endPoint.isEmpty()){
                Point end=endPoint.get(0);
                return leastTime[end.x][end.y];
            }
            resetPoints=nextResetPoints;
        }
        return -1;
    }

    static void babyRun(int x, int y, /*到当前点走的步数*/int steps,
                        /*递归深度*/int deep,
                        List<Point> resetPoints, List<Point> endPoint) {
        if (layout[x][y] == 0) return;
        if (steps < leastTime[x][y]) leastTime[x][y] = steps;
        if (layout[x][y] == 3 && visited[x][y] == 0) endPoint.add(new Point(x, y));
        if (layout[x][y] == 4 && visited[x][y] == 0) resetPoints.add(new Point(x, y));
        visited[x][y] = 1;
        if (deep >= 5) return;
        if (x - 1 >= 0) {
            babyRun(x - 1, y, steps + 1, deep + 1, resetPoints, endPoint);
        }
        if (x + 1 < layout.length) {
            babyRun(x + 1, y, steps + 1, deep + 1, resetPoints, endPoint);
        }
        if (y - 1 >= 0) {
            babyRun(x, y - 1, steps + 1, deep + 1, resetPoints, endPoint);
        }
        if (layout.length > 0 && y + 1 < layout[0].length) {
            babyRun(x, y + 1, steps + 1, deep + 1, resetPoints, endPoint);
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }


}
