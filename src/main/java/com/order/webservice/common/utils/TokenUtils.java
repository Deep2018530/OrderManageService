package com.order.webservice.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * created by zhangdingping at 2019/10/23
 * 用于维护jwt的token
 */
public class TokenUtils {

    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    private static final String SIGNATURE = "secret";
    private static final String AUDIENCE = "DATA_GOVERN";
    private static final String ISSUER = "SERVICE";
    private static final Map<String, Object> map = init();

    private static Map<String, Object> init() {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return map;
    }

    /**
     * 获取对象属性名数组
     *
     * @param o
     * @return
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0, len = fields.length; i < len; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            StringBuilder sb = new StringBuilder();
            sb.append("get").append(firstLetter).append(fieldName.substring(1));
            return o.getClass().getMethod(sb.toString(), new Class[]{}).invoke(o, new Object[]{});
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 构造可带自定义信息的token
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String createToken(T t) {
        String[] fieldNames = getFiledName(t);
        JWTCreator.Builder b = JWT.create();
        if (fieldNames.length == 0) return null;

        for (int i = 0, len = fieldNames.length; i < len; i++) {
            Object value = getFieldValueByName(fieldNames[i], t);
            if (Objects.isNull(value)) continue;
            b.withClaim(fieldNames[i], value.toString());
        }
        return b.withHeader(map)
                .withAudience(AUDIENCE)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static Map<String, Claim> decode(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaims();
    }

    public static String getStringParam(String token, String key) throws JWTDecodeException {
        return JWT.decode(token).getClaims().get(key).asString();
    }

    public static boolean verify(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT
                .require(Algorithm.HMAC256(SIGNATURE))
                .withIssuer(ISSUER)
                .withAudience(AUDIENCE)
                .build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
