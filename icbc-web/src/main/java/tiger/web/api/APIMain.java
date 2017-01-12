/*
 *
 */
package tiger.web.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tiger.biz.TigerApplication;

/**
 * Spring boot 启动类
 *
 * @author Hupeng
 * @version v 0.1 2015年10月9日 上午12:40:29 Hupeng Exp $
 */
public class APIMain extends SpringBootServletInitializer {


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    /**
     * API 程序入口
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        new TigerApplication(AppConfig.class).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfig.class, APIMain.class);
    }

}
