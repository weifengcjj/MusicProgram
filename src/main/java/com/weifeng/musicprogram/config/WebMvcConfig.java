package com.weifeng.musicprogram.config;

import com.weifeng.musicprogram.handle.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

/*

* */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html", "/path1/**", "/path2/**")
        ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * SpringBoot自动配置本身并不会把/swagger-ui.html
         * 这个路径映射到对应的目录META-INF/resources/下面
         * 采用WebMvcConfigurerAdapter将swagger的静态文件进行发布;
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX +"/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
    /*
    解决跨域问题
    * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowCredentials(true);//访问需要验证
    }

}
