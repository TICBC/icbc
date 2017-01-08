/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util;

import java.util.Random;

/**
 * @author zhangbin
 * @version v0.1 2015/9/19 19:07
 */
public class RandomCodeUtil {

    /**
     * 获取一个指定位数的随机数字
     *
     * @param length 随机数字的长度
     * @return
     */
    public static String getRandomNumer(int length) {
        if (length > 9 || length < 1) {
            return null;
        }
        int maxNum = (int) Math.pow(10, length);
        Random random = new Random(System.currentTimeMillis());
        int code = random.nextInt(maxNum - 1);
        return String.format("%0" + length + "d", code);
    }

}
