package ru.kpfu.formsvalidation.config;

import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.formsvalidation.controller"})
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        JtwigViewResolver resolver = new JtwigViewResolver();
        resolver.setRenderer(JtwigRenderer.defaultRenderer());
        resolver.setPrefix("/WEB-INF/jtwig/");
        resolver.setSuffix(".twig");
        resolver.setOrder(0);
        return resolver;
    }

//    @Bean
//    public ViewResolver viewResolver() {
//        UrlBasedViewResolver resolver = new CustomInternalViewResolver();
//        resolver.setOrder(0);
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        resolver.setRedirectContextRelative(false);
//        return resolver;
//    }

//    @Bean
//    public ViewResolver errorViewResolver(){
//        UrlBasedViewResolver resolver = new CustomInternalViewResolver();
//        resolver.setOrder(1);
//        resolver.setPrefix("/WEB-INF/jsp/errors/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        return resolver;
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/").setViewName("main");
//        registry.addViewController("/regist/user/").setViewName("e404");
    }
}
