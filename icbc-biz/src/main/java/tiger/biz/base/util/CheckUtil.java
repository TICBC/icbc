/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.base.util;

import org.apache.log4j.Logger;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;

import java.util.List;

/**
 * 验证工具类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:38 AM yiliang.gyl Exp $
 */
public class CheckUtil {

    public static Logger logger = Logger.getLogger(CheckUtil.class);

    /**
     * 检查一个对象是否为空
     *
     * @param object
     */
    public static void isBlank(Object object) {
        if (object == null) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND.getCode(), "找不到操作对象");
        }
    }


    /**
     * 检查一组id是否连续
     * ~ 如果连续返回 true
     * ~ 不连续返回 false
     *
     * @param ids
     * @return
     * @in [1, 2, 3, 4] return true
     * @in [1, 1, 2, 3] return false
     * @in [1, 3, 4]   reurn false
     */
    public static boolean isConsistence(List<Integer> ids) {
        boolean flag = true;
        if (ids.size() == 0) {
            return false;
        } else if (ids.size() == 1) {
            return true;
        }
        //正向排序
        ids.sort(Integer::compare);
        Integer ps = ids.get(0);
        //循环判断是不是连续
        for (int offset = 1; offset < ids.size(); offset++) {
            if (ids.get(offset) == (++ps)) {
                continue;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }


}
