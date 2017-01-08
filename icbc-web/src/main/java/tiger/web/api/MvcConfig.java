package tiger.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * MVC 配置
 * ~ 主要配置访问应用过程中的接口访问定义
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 13, 2015 7:15:56 AM yiliang.gyl Exp $
 */
@Configuration
@ComponentScan(basePackages = {"tiger.web", "tiger.biz", "tiger.common.dal", "tiger.core.service"})
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/" };

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * 配置资源文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    /**
     * 设置默认的页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/index");
    }

    /**
     * 配置文件上传的大小限制
     *
     * @return the multipart resolver
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver mr = new CommonsMultipartResolver();
        mr.setDefaultEncoding("utf-8");
        mr.setMaxUploadSize(10000000L);
        return mr;
    }
}
