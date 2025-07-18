package com.rong.heartcommon.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类 (生成、解析和验证 JWT) --- 用于标记用户身份
 */
public class JwtUtils {
    // 设置默认的密钥
    private static final String DEFAULT_SECRET_KEY = "defalfjaljflkajlkfalkkjflkaklfjalkjlfdajlfjlakjdl";

    // 静态方法，用于生成密钥和设置算法
    public static SecretKey generateKey(String secretKey, String algorithm) {
        secretKey = secretKey == null ? DEFAULT_SECRET_KEY : secretKey; // 如果密钥为null，则使用默认密钥
        return new SecretKeySpec(secretKey.getBytes(), algorithm); // 使用密钥和算法创建一个 SecretKey 对象
    }

    /**
     * 生成 JWT
     *
     * @param claims 要包含在 JWT 中的自定义声明
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims, SecretKey SECRET_KEY, long EXPIRATION_TIME,
            String header) {
        Date now = new Date(); // 获取当前时间
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME); // 设置过期时间为当前时间加上 24 小时

        return Jwts.builder()
                .header().add("type", header).and() // 添加自定义头部信息
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用指定的密钥和 HS256 算法签名 JWT
                .setClaims(claims) // 设置自定义声明
                .setIssuedAt(now) // 设置 JWT 的签发时间
                .setExpiration(expirationDate) // 设置过期时间
                .compact(); // 生成 JWT 字符串
    }

    /**
     * 解析 JWT
     *
     * @param token JWT 字符串
     * @return JWT 中的 Claims 对象
     * @throws io.jsonwebtoken.JwtException 如果 token 无效或已过期
     */
    public static Claims parseToken(String token, SecretKey SECRET_KEY) {
        return Jwts.parser() // 构建 JWT 解析器
                .setSigningKey(SECRET_KEY) // 设置用于验证签名的密钥
                .build() // 构建解析器
                .parseClaimsJws(token) // 解析 JWT
                .getBody(); // 获取 JWT 的主体 (Claims)
    }

    /**
     * 验证 JWT 是否有效且未过期
     *
     * @param token JWT 字符串
     * @return 如果 token 有效且未过期，则返回 true，否则返回 false
     */
    public static boolean isTokenValid(String token, SecretKey SECRET_KEY) {
        try {
            Claims claims = parseToken(token, SECRET_KEY); // 解析 JWT 的token(主体)
            Date expiration = claims.getExpiration(); // 获取 JWT 的过期时间
            return expiration != null && !expiration.before(new Date()); // 检查过期时间是否在当前时间之后
        } catch (Exception e) {
            // Token 解析失败或已过期
            System.out.println("Token 验证失败或已过期");
            return false;
        }
    }

}