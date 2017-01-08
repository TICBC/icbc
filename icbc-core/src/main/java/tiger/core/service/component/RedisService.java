/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.component;

import java.util.List;

/**
 * Redis操作的封装服务类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:03 AM yiliang.gyl Exp $
 */
public interface RedisService {

    /**
     * 获取数据
     *
     * @param key
     * @param beanClass
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> beanClass);

    /**
     * 获取一组数据
     *
     * @param keys
     * @param beanClass
     * @param <T>
     * @return
     */
    <T> List getMulti(List<String> keys, Class<T> beanClass);

    /**
     * 插入数据
     * ~ 遇到相同的直接覆盖
     *
     * @param key
     * @param object
     * @return
     */
    Boolean set(String key, Object object);

    /**
     * 删除单个数据
     *
     * @param key
     * @return
     */
    Boolean delete(String key);

    /**
     * 删除多个数据
     *
     * @param keys
     * @return
     */
    Boolean deleteMulti(List<String> keys);

}
