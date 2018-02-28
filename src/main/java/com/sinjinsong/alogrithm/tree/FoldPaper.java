package com.sinjinsong.alogrithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/24
 */
public class FoldPaper {
    /**
     * 请把纸条竖着放在桌⼦上，然后从纸条的下边向上⽅对折，压出折痕后再展开。
     * 此时有1条折痕，突起的⽅向指向纸条的背⾯，这条折痕叫做“下”折痕 ；
     * 突起的⽅向指向纸条正⾯的折痕叫做“上”折痕。如果每次都从下边向上⽅ 对折，
     * 对折N次。请从上到下计算出所有折痕的⽅向。
     * 给定折的次数n,请返回从上到下的折痕的数组，若为下折痕则对应元素为"down",
     * 若为上折痕则为"up".
     * <p>
     * 从纸的上面到下面打印就是二叉树的RVL（右根左）的遍历。
     *
     * @param n
     * @return
     */
    public static String[] foldPaper(int n) {
        List<String> result = new ArrayList<>();
        fold(1, n, "down", result);
        return result.toArray(new String[result.size()]);
    }

    private static void fold(int level, int n, String type, List<String> result) {
        if (level <= n) {
            //R
            fold(level + 1, n, "down", result);
            //V
            result.add(type);
            //L
            fold(level + 1, n, "up", result);
        }
    }

    public static void main(String[] args) {
        String[] result = foldPaper(3);
        System.out.println(Arrays.toString(result));
    }
}
