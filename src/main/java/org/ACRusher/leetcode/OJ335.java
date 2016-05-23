package org.ACRusher.leetcode;

/**
 * @author xiliang.zxl
 * @date 2016-04-24 下午4:31
 */
public class OJ335 {



    public boolean isSelfCrossing(int[] x) {
        if (x.length >= 5 && x[1] == x[3] && x[2] - x[4] <= x[0]) { // 5th line merges with the 1st one
            return true;
        }
        for (int i = 3; i < x.length; ++i) {
            if (x[i - 1] <= x[i - 3] && x[i] >= x[i - 2] // crosses the line three steps behind
                    || i >= 5 && x[i - 2] >= x[i - 4]
                    && x[i - 3] - x[i - 1] >= 0 && x[i - 3] - x[i - 1] <= x[i - 5]
                    && x[i] >= x[i - 2] - x[i - 4]) { // crosses the line five steps behind
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] test={2,2,3,3,3,2};
        System.out.println(new OJ335().isSelfCrossing(test));
    }
}
