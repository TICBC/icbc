/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import tiger.common.dal.DataConfig;

import static org.mockito.Mockito.mock;

/**
 * 配置集成测试使用的mock
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 25, 2015 2:24:42 PM yiliang.gyl Exp $
 */
@Configuration
@Import({AppConfig.class, ShiroConfig.class, DataConfig.class})
public class IntegrationTestsConfig {
    @Primary
    @Bean
    public RestTemplate mockRestTemplate() {
        return mock(RestTemplate.class);
    }
}
