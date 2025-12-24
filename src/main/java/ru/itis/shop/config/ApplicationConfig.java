package ru.itis.shop.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.cache.ClassTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.shop")
public class ApplicationConfig {

    @Bean
    public freemarker.template.Configuration freemarkerConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_34);
        configuration.setDefaultEncoding("UTF-8");

        configuration.setTemplateLoader(new ClassTemplateLoader(ApplicationConfig.class.getClassLoader(), "\\templates"));

        return configuration;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource(Environment environment) {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(environment.getProperty("dataSourceClassName"));
        config.setUsername(environment.getProperty("dataSource.user"));
        config.setPassword(environment.getProperty("dataSource.password"));
        config.setJdbcUrl(environment.getProperty("dataSource.url"));
        config.setMaximumPoolSize(environment.getProperty("dataSource.hikari.maxPoolSize", Integer.class));

        return new HikariDataSource(config);
    }
}