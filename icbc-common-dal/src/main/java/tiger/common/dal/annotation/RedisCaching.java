/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.annotation;

import java.lang.annotation.*;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:42 PM yiliang.gyl Exp $
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisCaching {

    /**
     * 默认key
     * @return
     */
    String key() default "";

    String keyGenerator() default "";

    String cacheResolver() default "";

    /**
     * 默认失效时间 3600秒 = 1小时
     * @return
     */
    int expireTime() default 3600;

}
