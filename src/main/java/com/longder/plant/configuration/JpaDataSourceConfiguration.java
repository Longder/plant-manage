package com.longder.plant.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Hibernate数据源，与Spring整合配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        basePackages = "com.longder.plant.repository"
)
public class JpaDataSourceConfiguration {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    /**
     * Hibernate的EntityManager配置
     */
    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.longder.plant.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    /**
     * 数据源
     */
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .url(jdbcUrl)
                .build();
    }

    /**
     * 数据库事物管理
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
    }

    /**
     * 针对Hibernate管理的额外配置
     */
    private Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hdm2ddl.auto","none");
        properties.setProperty("hibernate.show_sql","false");
        properties.setProperty("hibernate.format_sql","false");
        //Hibernate数据库方言
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        return properties;
    }
}
