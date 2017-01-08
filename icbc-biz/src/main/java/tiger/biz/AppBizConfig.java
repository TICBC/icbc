/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 服务层基础配置文件
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 10, 2015 9:43:24 PM yiliang.gyl Exp $
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"tiger.biz"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppBizConfig {

}
