package top.ninefox.comp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import top.ninefox.comp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!userService.tokenCheck()) {
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            Map<String, Object> res = new HashMap<>();
            res.put("status", 401);
            res.put("msg", "用户身份认证失效");
            writer.write(res.toString());
            writer.close();
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
