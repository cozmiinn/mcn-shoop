package com.mcn.shoop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig {
    @Bean
    public PageableHandlerMethodArgumentResolver customPageableResolver(){
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver(){
            @Override
            protected String getPageParameterName(){
                return super.getPageParameterName();
            }
        };
        resolver.setOneIndexedParameters(true);
        return resolver;
    }


//    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(customPageableResolver());
    }

}
