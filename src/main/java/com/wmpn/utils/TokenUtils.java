package com.wmpn.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wmpn.entity.CurrentUser;
import com.wmpn.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * token工具類
 */
@Component
public class TokenUtils {

    //注入redis模板
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //注入配置文件中的warehouse.expire-time属性 -- token的过期时间
    @Value("${warehouse.expire-time}")
    private int expireTime;

    /**
     * 常量:
     */
    //token中存放用户id对应的名字
    private static final String CLAIM_NAME_USERID = "CLAIM_NAME_USERID";
    //token中存放用户名对应的名字
    private static final String CLAIM_NAME_USERCODE = "CLAIM_NAME_USERCODE";
    //token中存放用户真实姓名对应的名字
    private static final String CLAIM_NAME_USERNAME = "CLAIM_NAME_USERNAME";

    /**
     * 真正生成jwt的方法
     * @param currentUser
     * @param securityKey
     * @return
     */
    private String sign(CurrentUser currentUser, String securityKey){
        String token = JWT.create()
                .withClaim(CLAIM_NAME_USERID, currentUser.getUserId())
                .withClaim(CLAIM_NAME_USERCODE, currentUser.getUserCode())
                .withClaim(CLAIM_NAME_USERNAME, currentUser.getUserName())
                .withIssuedAt(new Date())//給jwt时间
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime *1000))//過期時間(ms)
                .sign(Algorithm.HMAC256(securityKey));
        return token;
    }

    /**
     * 將當前用户信息以用户密碼为密鑰生成token的方法
     */
    public String loginSign(CurrentUser currentUser, String password){
        //生成token
        String token = sign(currentUser, password);
        //将token保存到redis中,并设置token在redis中的过期时间
        stringRedisTemplate.opsForValue().set(token, "", expireTime , TimeUnit.SECONDS);
        return token;
    }

    /**
     * 客户端歸還的token中獲取用户信息的方法
     */
    public CurrentUser getCurrentUser(String token) {
        if(StringUtils.isEmpty(token)){
            throw new BusinessException("令牌為空，請登入！");
        }
        //对token进行解码,获取解码后的token
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw new BusinessException("令牌格式錯誤，请登入！");
        }
        int userId = decodedJWT.getClaim(CLAIM_NAME_USERID).asInt();
        String userCode = decodedJWT.getClaim(CLAIM_NAME_USERCODE).asString();
        String userName = decodedJWT.getClaim(CLAIM_NAME_USERNAME).asString();
        if(StringUtils.isEmpty(userCode) || StringUtils.isEmpty(userName)){
            throw new BusinessException("令牌缺失，请登入！");
        }
        return new CurrentUser(userId, userCode, userName);
    }

}
