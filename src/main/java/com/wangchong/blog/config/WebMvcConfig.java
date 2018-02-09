package com.wangchong.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 跳转配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin.do").setViewName("/login");
        registry.addViewController("/").setViewName("redirect:/article/index.do");
        registry.addViewController("/toArticle.do").setViewName("/article");
        registry.addViewController("/admin/listArticle.do").setViewName("/admin/article");
        registry.addViewController("/admin/listComment.do").setViewName("/admin/comment");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/**");
    }
}
