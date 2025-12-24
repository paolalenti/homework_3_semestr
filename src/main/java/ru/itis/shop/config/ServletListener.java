package ru.itis.shop.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.shop.accounts.service.AccountsService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {

    private HikariDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SERVLET CONTEXT INITIALIZED");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("ServletListener: Spring initialized");
        AccountsService accountsService = context.getBean(AccountsService.class);
        System.out.println("ServletListener: AccountsService Bean is ready");
        sce.getServletContext().setAttribute("accountsService", accountsService);
        System.out.println("ServletListener: AccountsService Bean is accessed for servlets");

        dataSource = (HikariDataSource) context.getBean("dataSource");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SERVLET CONTEXT DESTROYED");
        if (dataSource != null) {
            dataSource.close();
            System.out.println("ServletListener: HikariDataSource is destroyed");
        }
    }
}
