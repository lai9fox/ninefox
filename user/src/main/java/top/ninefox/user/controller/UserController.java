package top.ninefox.user.controller;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ninefox.user.entity.User;
import top.ninefox.user.service.IUserService;
import top.ninefox.user.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-04
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

//  验证码发送
    @RequestMapping("/vertify")
    public Map<String, Object> vertifyCode(String email) {
        return userService.vertifyCode(email);
    }

//    注册账号
    @RequestMapping("/signup")
    public Map<String, Object> signUp(String username, String email, String password, String vertify) {
        return userService.signUp(username,email,password,vertify);
    }

//    验证该邮箱是否被注册
    @RequestMapping("/issignup")
    public boolean isSignUp(String email) {
        return userService.isSignUp(email);
    }

//    验证用户名是否被注册
    @RequestMapping("/namecheck")
    public boolean usernameCheck(String username) {
        return userService.usernameSignUpCheck(username);
    }

//    登录
    @RequestMapping("/login")
    public Map<String, Object> logIn(User user) {
        return userService.logIn(user);
    }

//    修改密码
    @PostMapping("/update")
    public Map<String, Object> updatePassword(String password, String code, HttpServletRequest request) {

        if (!tokenCheck(request)) {
            return new HashMap<>() {{
                put("status", 401);
                put("msg", "用户认证失败！");
            }};
        }

        Claims claims = JwtUtil.decodeToken(request.getHeader("Authorization"));

        return userService.updatePassword(String.valueOf(claims.get("email")), password, code);
    }

    @RequestMapping("/decodetoken")
    public Claims decokeToken(HttpServletRequest request) {
        return JwtUtil.decodeToken(request.getHeader("Authorization"));
    }

    //
    @RequestMapping("/tokencheck")
    public boolean tokenCheck(HttpServletRequest request) {
        return JwtUtil.checkToken(request.getHeader("Authorization"));
    }


}

