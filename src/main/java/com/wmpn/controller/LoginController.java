package com.wmpn.controller;

import com.google.code.kaptcha.Producer;
import com.wmpn.annotation.Log;
import com.wmpn.entity.*;
import com.wmpn.service.AuthService;
import com.wmpn.service.UserService;
import com.wmpn.utils.DigestUtil;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Resource(name = "captchaProducer")
    private Producer producer;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenUtils tokenUtils;


    /**
     * 生成驗證碼
     *
     * @param response
     */
    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            //生成驗證碼圖片的文件
            String text = producer.createText();
            //使用驗證碼本文生成驗證碼圖片
            BufferedImage producerImage = producer.createImage(text);
            //將驗證碼本文保存到redis
            redisTemplate.opsForValue().set(text, "", 60 * 5, TimeUnit.SECONDS);
            //將驗證碼圖片響應給前端，並設置響應文image/jpeg
            response.setContentType("image/jpeg");
            //將驗證碼圖片寫給前端
            outputStream = response.getOutputStream();
            ImageIO.write(producerImage, "jpg" , outputStream); //使用響應對象的IO輸出流寫入驗證碼圖片
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {
        String code = loginUser.getVerificationCode();
        if(!redisTemplate.hasKey(code)){
            return Result.err(Result.CODE_ERR_BUSINESS,"驗證碼錯誤");
        }

        User user = userService.queryUserByCode(loginUser.getUserCode());
        if (user != null) {
            if (user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)) {//已啟用
                String userPwd = loginUser.getUserPwd();
                userPwd = DigestUtil.hmacSign(userPwd);
                if (userPwd.equals(user.getUserPwd())) {
//                     生成jwt
                    CurrentUser currentUser = new CurrentUser(user.getUserId(),user.getUserCode(),user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, userPwd);
                    return Result.ok("登入成功",token);
                } else {
                    return Result.err(Result.CODE_ERR_BUSINESS, "密碼錯誤");
                }
            } else {//未啟用
                return Result.err(Result.CODE_ERR_BUSINESS, "用戶已停用");
            }
        } else {
            return Result.err(Result.CODE_ERR_BUSINESS, "帳號不存在");
        }

    }

    /**
     * 將當前登入的用戶的信息回傳給前端渲染
     * @param token
     * @return
     */
    @RequestMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        return Result.ok(currentUser);
    }

    /**
     * 加載用戶的權限菜單
     */
    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        List<Auth> authTreeList = authService.authTreeByUid(currentUser.getUserId());

        return Result.ok(authTreeList);


    }

    /**
     * 登出功能
     * @param token
     * @return
     */

    @RequestMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        redisTemplate.delete(token);

        return Result.ok("退出系統");

    }


}
