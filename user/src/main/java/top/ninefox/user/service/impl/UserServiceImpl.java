package top.ninefox.user.service.impl;


import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.codec.cli.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.ninefox.user.entity.User;
import top.ninefox.user.mapper.UserMapper;
import top.ninefox.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ninefox.user.util.JwtUtil;
import top.ninefox.user.util.UserUtil;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<String, Object> signUp(String username, String email, String password, String vertifyCode) {

        String code = (String) redisTemplate.opsForValue().get(email);

        HashMap<String, Object> res = new HashMap<>();

        if (!vertifyCode.equals(code)) {
            res.put("status", "false");
            res.put("msg", "验证码错误");
            return res;
        }

        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(UserUtil.encryPassword(password));

        if (userMapper.insert(u) != 1) {
            res.put("status", "false");
            res.put("msg", "注册失败");
            return res;
        }

        res.put("status", "true");
        res.put("msg", "注册成功");
        return res;
    }

    @Override
    public Map<String, Object> updatePassword(String email, String newPassword, String vertifyCode) {

        String code = (String) redisTemplate.opsForValue().get(email);

        if (!vertifyCode.equals(code)) {
            return new HashMap<>() {{
                put("status", false);
                put("msg", "验证码错误");
            }};
        }

        User u = new User();
        u.setPassword(UserUtil.encryPassword(newPassword));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        if (userMapper.update(u, wrapper) != 1) {
            return new HashMap<>() {{
                put("state", false);
                put("msg", "更新失败");
            }};
        }

        return new HashMap<>() {{
            put("state", true);
            put("msg", "更新成功");
        }};
    }

    /**
     * 用户登录
     * @param user 登录信息
     * @return  登陆成功状态、token
     */
    @Override
    public Map<String, Object> logIn(User user) {

        Map<String, Object> map = new HashMap<>();

//        密码加密
        user.setPassword(UserUtil.encryPassword(user.getPassword()));

        QueryWrapper<User> wrapper = new QueryWrapper<>();


        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", user.getPassword());

        User u = userMapper.selectOne(wrapper);

        if (u != null) {
            String token = JwtUtil.createToken(new HashMap<>() {{
                put("uid", u.getUid());
                put("username", u.getUsername());
                put("email", u.getEmail());
            }});

            map.put("token", token);
            map.put("username", u.getUsername());
            map.put("uid", u.getUid());
            map.put("status", true);

        } else {
            map.put("status", false);
        }

        return map;
    }

    @Override
    public boolean isSignUp(String email) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        User u = userMapper.selectOne(wrapper);

        return u != null;
    }

    /**
     * 验证用户名是否被注册
     * @param username 需要注册的用户名
     * @return true：已经注册；false：未注册
     */
    @Override
    public boolean usernameSignUpCheck(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper) != null;
    }

    /**
     * 进行验证码生成、邮箱发送
     * @param email 注册的邮箱
     * @return 成功状态
     */
    @Override
    public Map<String, Object> vertifyCode(String email) {
        String code = UserUtil.generateVertifyCode();
//        验证码存入 redis 服务器，过期时间 5分钟
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(5));

//        消息发布
        Map<String, Object> res = new HashMap<>();
        res.put("code", code);
        res.put("email", email);
        redisTemplate.convertAndSend("email-send", JSON.toJSONString(res));

//        结果返回
        Map<String, Object> ans = new HashMap<>();
        ans.put("status", true);
        ans.put("msg", "验证码发送成功，请到邮箱查看！");
        return ans;
    }
}
