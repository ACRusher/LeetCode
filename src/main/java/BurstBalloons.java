import java.util.HashMap;
import java.util.Map;

/**
 * 这是最直接的实现，基于描述实现，时间复杂度为指数级别
 *
 * @author xiliang.zxl
 * @date 2015-12-13 上午11:55
 */
public class BurstBalloons {

    Map<String, Long> cache = new HashMap<String, Long>(100);

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        if (nums.length == 1) return nums[0];
        cache.clear();
        return (int) search(nums);

    }

    private long search(int[] nums) {
        if (nums.length == 1) return nums[0];
        String key = buildKey(nums);
        if (cache.containsKey(key)) return cache.get(key);
        long max = 0, maxIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            int self = i == 0 ? nums[i] * nums[i + 1] : i == nums.length - 1 ? nums[i] * nums[i - 1] : nums[i - 1] * nums[i] * nums[i + 1];
            long next = search(buildArray(nums, i));
            if (self + next > max) {
                max = self + next;
                maxIndex = i;
            }
        }
        cache.put(key, max);
        return max;
    }

    private int[] buildArray(int[] arr, int deleted) {
        int[] result = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; ++i)
            if (i != deleted) {
                result[j++] = arr[i];
            }
        return result;
    }

    private String buildKey(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BurstBalloons burstBalloons = new BurstBalloons();
        int[] test = {3, 1, 5, 8};
        System.out.println(burstBalloons.maxCoins(test));
    }
}

/**
 * 参考leetcode上面的一个答案，解题思路如下：
 *
 * When I first get this problem, it is far from dynamic programming to me.
 * 当我一开始看到这个问题的时候，我根本没有想到用动态规划来解答。
 * I started with the most naive idea the backtracking.
 * 我使用了最原始的解题思路：暴力破解。
 * <p/>
 * We have n balloons to burst, which mean we have n steps in the game.
 * 我们有n个气球去刺破，这意味着在这个游戏中我们有n步操作。
 * In the i th step we have n-i balloons to burst, i = 0~n-1.
 * 在第i步，我们还剩下n-i个气球去刺破， i从0开始取值。
 * Therefore we are looking at an algorithm of O(n!). Well, it is slow, probably works for n < 12 only.
 * 因此我们的算法复杂度为O(n!) , 好吧，这个算法很慢，或许对n小于12的时候是有效的。
 * <p/>
 * Of course this is not the point to implement it.
 * 当然了，这不是解决这个问题的正确思路。
 * We need to identify the redundant works we did in it and try to optimize.
 * 我们需要辨别出在暴力破解中重复性的工作，并尝试去优化它。
 * <p/>
 * Well, we can find that for any balloons left the maxCoins does not depends on the balloons already bursted.
 * 好吧，我们发现：对任何只剩下n个球的子问题，跟已经刺破的球是没有关系的。
 * This indicate that we can use memorization (top down) or dynamic programming (bottom up)
 * 这意味着我们可以使用备忘录算法 或者 动态规划算法来解决。
 * for all the cases from small numbers of balloon until n balloons.
 * 采用从小数目的气球向n个气球递推的方式。
 * How many cases are there? For k balloons there are C(n, k)
 * cases and for each case it need to scan the k balloons to compare.
 * The sum is quite big still. It is better than O(n!) but worse than O(2^n).
 * <p/>
 * Better idea
 * <p/>
 * We than think can we apply the divide and conquer technique?
 * 分而治之的算法
 * After all there seems to be many self similar sub problems from the previous analysis.
 * <p/>
 * Well, the nature way to divide the problem is burst one balloon and
 * separate the balloons into 2 sub sections one on the left and one one the right.
 * However, in this problem the left and right become adjacent and have effects on the maxCoins in the future.
 * <p/>
 * Then another interesting idea come up. Which is quite often seen dp problem analysis.
 * That is reverse thinking.
 * 另一个想法出现了，也是在dp中经常用的思维方式：逆向思维。
 * Like I said the coins you get for a balloon does not depend on the balloons already burst.
 * Therefore instead of divide the problem by the first balloon to burst,
 * we divide the problem by the last balloon to burst.
 * <p/>
 * Why is that? Because only the first and last balloons we are sure of their adjacent balloons before hand!
 * <p/>
 * For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
 * <p/>
 * OK. Think about n balloons if i is the last one to burst, what now?
 * <p/>
 * We can see that the balloons is again separated into 2 sections.
 * But this time since the balloon i is the last balloon of all to burst,
 * the left and right section now has well defined boundary and
 * do not effect each other! Therefore we can do either recursive method with memorization or dp.
 * <p/>
 * Final
 * <p/>
 * Here comes the final solutions.
 * Note that we put 2 balloons with 1 as boundaries and also burst all the zero balloons in the first round
 * since they won't give any coins.
 * The algorithm runs in O(n^3) which can be easily seen from the 3 loops in dp solution.
 *
 * 总体总结一下，遇到这种求最优解问题，首先想到的就是DP。
 * 带数组的DP问题，首先想到的就是二位数组模板算法，由短向长递推。
 *  V - - - -
 *  - V - - -
 *  - - V - -
 *  - - - V -
 *  - - - - V
 *  如图所示。
 *  那么关键问题是，子问题 或 递推 问题是什么？
 *  这儿很容易想错的是，直接把边界为双1作为子问题的条件，这会导致子问题无法向高纬度递推。
 *  想通这儿，子问题来了： 已知一个数组 arr , 长度为n ，arr[0] arr[n-1] 为最后刺破的气球，求最大得分。
 *  突然感觉跟数学题的转化问题解法 异曲同工。
 *
 */
class BurstBalloonsDP {

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] arr=new int[nums.length+2];
        int n=1;
        for(int v : nums)
            if(v!=0)
                arr[n++]=v;
        arr[0]=arr[n++]=1;
        int[][] aux=new int[n][n];
        for(int i=0;i<n;++i)
            aux[i]=new int[n];
        for(int k=3;k<=n;++k){
            //k代表维度
            for(int i=0;i<=n-k;++i){
                for(int j=i+1;j<i+k-1;++j) {
                    aux[i][i + k - 1] = Math.max(aux[i][i + k - 1], aux[i][j]+aux[j][i+k-1]+arr[i]*arr[i+k-1]*arr[k]);
                }

            }
        }
        return aux[0][n-1];

    }

}
