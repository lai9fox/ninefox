package top.ninefox.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ninefox.blog.interceptor.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getTokenInterceptor(){
        return new TokenInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] includes = {
                "/tag/**",
                "/category/add",
                "/category/update",
                "/category/delete",
                "/comment/add",
                "/comment/update",
                "/comment/delete",
                "/comment/selectaid",
                "/article/add",
                "/article/delete",
                "/article/update/**",
        };

        registry.addInterceptor(getTokenInterceptor())
                .addPathPatterns(includes);
    }
}
