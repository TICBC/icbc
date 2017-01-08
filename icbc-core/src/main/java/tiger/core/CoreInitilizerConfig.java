/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

/**
 * @author yiliang.gyl
 * @version v 0.1 Sep 25, 2015 3:00:20 PM yiliang.gyl Exp $
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"tiger.core.service", "tiger.core.aspect"})
@EnableAspectJAutoProxy
public class CoreInitilizerConfig {
    @Primary
    @Bean
    public RestTemplate mockRestTemplate() {
        return mock(RestTemplate.class);
    }
}
