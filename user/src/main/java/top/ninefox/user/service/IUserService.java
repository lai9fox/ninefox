package top.ninefox.user.service;

import top.ninefox.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-04
 */
public interface IUserService extends IService<User> {


    Map<String, Object> vertifyCode(String email);

    Map<String, Object> signUp(String username, String email, String password, String vertifyCode);

    Map<String, Object> updatePassword(String email, String newPassword, String vertifyCode);

    Map<String, Object> logIn(User user);

    boolean isSignUp(String email);

    boolean usernameSignUpCheck(String username);

}
