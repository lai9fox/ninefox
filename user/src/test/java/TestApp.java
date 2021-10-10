import top.ninefox.user.util.JwtUtil;
import top.ninefox.user.util.UserUtil;

import java.util.HashMap;
import java.util.Map;

public class TestApp {


    public static void main(String[] args) {
        Map<String, Object> payload = new HashMap<>(){{
            put("username", "lai");
            put("uid", "1445616045636775938");
        }};

        System.out.println(JwtUtil.createToken(payload));
    }

}
