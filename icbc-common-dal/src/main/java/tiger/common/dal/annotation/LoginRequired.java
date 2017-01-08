/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.annotation;

import java.lang.annotation.*;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:43 PM yiliang.gyl Exp $
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

    /**
     * 没有登录是否直接报错
     * @return
     */
    boolean exception() default true;
}
