/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 5:05 PM yiliang.gyl Exp $
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"tiger.core.service"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CoreConfig {

}
