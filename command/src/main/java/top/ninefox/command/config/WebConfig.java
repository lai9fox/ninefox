package top.ninefox.command.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ninefox.command.interceptor.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getTokenInterceptor(){
        return new TokenInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] includes = {
                "/type/put",
                "/type/update",
                "/type/delete",
                "/content/put",
                "/content/update",
                "/content/delete"
        };

        registry.addInterceptor(getTokenInterceptor())
                .addPathPatterns(includes);
    }
}
