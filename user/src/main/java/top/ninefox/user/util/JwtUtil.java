package top.ninefox.user.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {


//    private static String

    private static final Map<String, Object> HEADER = new HashMap<>(){{
        put("typ", "JWT");
        put("alg", "HS256");
    }};
    private static final String SECRET_KEY = "8801421455";

    public static String createToken(Map<String, Object> payLoad) {

        JwtBuilder jwtBuilder = Jwts.builder();
//      头部信息
        jwtBuilder.setHeader(HEADER);
//      载荷
        payLoad.forEach(jwtBuilder::claim);
//      过期时间
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7));

        jwtBuilder.signWith(SignatureAlgorithm.HS256, SECRET_KEY);

        return jwtBuilder.compact();
    }

    public static Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public static boolean checkToken(String token) {

        if (token == null) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            return false;
        }
        return true;
    }

}
