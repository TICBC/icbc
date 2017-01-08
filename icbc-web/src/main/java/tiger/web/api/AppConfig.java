package tiger.web.api;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tiger.biz.AppBizConfig;
import tiger.common.dal.DataConfig;
import tiger.core.CoreConfig;

/**
 * APP 主配置
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 13, 2015 7:14:03 AM yiliang.gyl Exp $
 */
@SpringBootApplication
@ComponentScan("tiger.web.api")
@Import({DataConfig.class, AppBizConfig.class, CoreConfig.class})
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableScheduling
public class AppConfig {

    /**
     * 配置基础的 rest 模板
     * ~ 待研究有什么用
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate(
                new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));

    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);
        return cmfb;
    }


}
