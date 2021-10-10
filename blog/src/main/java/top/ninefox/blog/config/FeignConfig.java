package top.ninefox.blog.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 解决 feign 服务调用 token 丢失的情况
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            //将token信息放入header中
            requestTemplate.header("Authorization",request.getHeader("Authorization"));
        }

    }


//    private HttpServletRequest getHttpServletRequest() {
//        try {
//            return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private Map<String, String> getHeaders(HttpServletRequest request) {
//        Map<String, String> map = new LinkedHashMap<>();
//        Enumeration<String> enumeration = request.getHeaderNames();
//        while (enumeration.hasMoreElements()) {
//            String key = enumeration.nextElement();
//            String value = request.getHeader(key);
//            map.put(key, value);
//        }
//        return map;
//    }


}
