package org.ACRusher.hdoj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xiliang.zxl
 * @date 2016-05-23 下午7:14
 */
public class HDOJ1069 {
    /**
     * input : 不限制使用数量的 n种长方体
     * output : 叠在一起的最高 高度
     * conditions : 第n层必须比n-1层小
     * <p>
     * 思路:
     * 1. 头脑风暴 , 随便尝试, 查找规律
     * 2. 底层的应该是比较大的
     * 3. 如何选择最底层的应该是关键问题,剩下的子问题递归便解决
     * 4. 推翻 3的判断,底层大 并不能保证高度高, 3的论断是个直觉错误
     * 5. 将所有的长方体拆成平面,可以得到 m(<=3*n) 种长方形, 可以得到暴力破解方法
     * 时间复杂度:  < n!
     * 定义 f(底座) 为 最大tower高度, 则  result= Max( f(x) ) + H(x)  , x in 所有底座.
     * 其中 f(x) = Max( f(y) ) + H(y) , height 为当前高度, y 为所有可能的x的下一层底座
     * 6. 思考是否可以优化算法,降低时间复杂度
     * 7. 暴力破解的递归过程中,有很多子问题是重复的,因此可以对 f(x) 做备忘录
     */

    //尝试备忘录算法

    private Map<Rectangular, Integer> input = new HashMap<>();

    private Map<Rectangular, Long> cache = new HashMap<>();

    private boolean smaller(Rectangular base, Rectangular upper) {
        if (base.length > upper.length && base.width > upper.width) return true;
        return false;
    }

    public long height(Rectangular base) {
        if (cache.containsKey(base)) return cache.get(base);
        long currentHeight = input.get(base), max = currentHeight;
        for (Rectangular rectangular : input.keySet()) {
            if (smaller(base, rectangular)) {
                max = Math.max(max, height(rectangular) + currentHeight);
            }
        }
        return max;
    }

    public long entrance() {
        long max = 0;
        for (Rectangular rectangular : input.keySet()) {
            max = Math.max(height(rectangular), max);
        }
        return max;
    }

    private void init() {
        input.clear();
        cache.clear();
        ;
    }

    private void handleInput(int x, int y, int z) {
        handleMap(new Rectangular(Math.max(x, y), Math.min(x, y)), z);
        handleMap(new Rectangular(Math.max(x, z), Math.min(x, z)), y);
        handleMap(new Rectangular(Math.max(y, z), Math.min(y, z)), x);
    }

    private void handleMap(Rectangular rectangular, int height) {
        Integer t = input.get(rectangular);
        if (t == null || height > t) {
            input.put(rectangular, height);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0, cnt = 1;
        HDOJ1069 hdoj1069 = new HDOJ1069();
        while ((n = scanner.nextInt()) > 0) {
            hdoj1069.init();
            while (n-- > 0) {
                hdoj1069.handleInput(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }
            System.out.println(String.format("Case %s: maximum height = %s", cnt++, hdoj1069.entrance()));
        }
    }

    static class Rectangular {
        int length;
        int width;

        public Rectangular(int length, int width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Rectangular that = (Rectangular) o;

            if (length != that.length) return false;
            return width == that.width;

        }

        @Override
        public int hashCode() {
            int result = length;
            result = 31 * result + width;
            return result;
        }
    }

}
