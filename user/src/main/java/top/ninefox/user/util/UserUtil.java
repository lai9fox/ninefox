package top.ninefox.user.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class UserUtil {

    public static String generateVertifyCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i <= 5; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

//    邮箱格式验证
    public static boolean emailCheck(String email) {
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return email.matches(regex);
    }

    public static String encryPassword(String password) {
        return DigestUtils.sha1Hex(password);
    }


}
