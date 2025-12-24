package ru.itis.shop.app;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.shop.config.ApplicationConfig;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // контекст Spring, который мы свяжем с DispatcherServlet
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        // зарегистрировали нашу конфигурацию Spring
        springContext.register(ApplicationConfig.class);
        // почитайте про pattern Listener/Observer
        // мы здесь создаем "наблюдателя", который ожидает загрузки контекста сервлетов
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(springContext);
        // когда загрузится сервлеты, автоматически загрузится контекст Spring
        servletContext.addListener(contextLoaderListener);
        // дополнительная задача - подключить DispatcherServlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(springContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
