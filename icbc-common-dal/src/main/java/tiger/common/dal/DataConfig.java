/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tiger.common.util.StringUtil;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author Domi.hp
 * @version v 0.1 2015年9月13日 下午4:05:53 Domi.hp Exp $
 */
@Configuration
@PropertySource(value = {"classpath:mysql.properties", "mysql.properties"}, ignoreResourceNotFound = true)
@MapperScan({"tiger.common.dal.persistence.mapper"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"tiger.common.dal"})
public class DataConfig {

    private static Logger logger = Logger.getLogger(DataConfig.class);

    /**
     * The environment.
     */
    @Autowired
    Environment environment;

    /**
     * 配置数据映射.
     *
     * @return the object mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * 配置 mysql 数据类.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("c3p0.MaxPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("c3p0.MinPoolSize")));
        dataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("c3p0.MaxIdleTime")));
        dataSource.setJdbcUrl(environment.getProperty("c3p0.url"));
        dataSource.setPassword(environment.getProperty("c3p0.password"));
        dataSource.setUser(environment.getProperty("c3p0.username"));
        dataSource.setDriverClass(environment.getProperty("c3p0.driverClassName"));
        return dataSource;
    }

    /**
     * 数据 vo 映射包.
     *
     * @return the sql session factory
     * @throws Exception the exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliasesPackage("tiger.common.dal.persistence.mapper");
        return sessionFactoryBean.getObject();
    }

    /**
     * 事务管理器注册.
     *
     * @return the data source transaction manager
     */
    @Bean
    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 按照默认配置配置 Redis Factory
     *
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(StringUtil.defaultIfBlank(environment.getProperty("redis.host"), "localhost"));
        factory.setPort(Integer.parseInt(StringUtil.defaultIfBlank(environment.getProperty("redis.port"), "6379")));
        return factory;
    }

    /**
     * 获取 Redis Template
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}
