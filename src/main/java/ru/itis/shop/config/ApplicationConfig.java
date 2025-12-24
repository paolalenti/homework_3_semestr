package ru.itis.shop.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.shop")
public class ApplicationConfig {

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

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftlh");

        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        freemarker.template.Configuration freemarkerConfig
                = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_34);
        freemarkerConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        freemarkerConfig.setLogTemplateExceptions(false);
        freemarkerConfig.setWrapUncheckedExceptions(true);

        configurer.setConfiguration(freemarkerConfig);
        return configurer;
    }
}