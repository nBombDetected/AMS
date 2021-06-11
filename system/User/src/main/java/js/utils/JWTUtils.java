package js.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;


public class JWTUtils {

    private static final String SIGNATURE = "Ca!fa@#4*5qsa#GU";

    /** 生成token */
    public static String getToken(Map<String, String> userInfo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1); //指定过期时间为1天
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //写入payload
        userInfo.forEach(builder::withClaim);
        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGNATURE));
    }

    /** 验证token */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }
}


