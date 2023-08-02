package com.wmpn.filter;


import com.alibaba.fastjson.JSON;
import com.wmpn.context.BaseContext;
import com.wmpn.entity.CurrentUser;
import com.wmpn.entity.Result;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginCheckFilter implements Filter {

    private StringRedisTemplate redisTemplate;

    private TokenUtils tokenUtils;

    public void setTokenUtils(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //白名單放行
        List<String> urlList = new ArrayList<>();
        urlList.add("/captcha/captchaImage");
        urlList.add("/logout");
        urlList.add("/login");
        urlList.add("/product/img-upload");
        String url = request.getServletPath();
        if (urlList.contains(url) || url.contains("/img/upload")) {//包含直接放行
            filterChain.doFilter(request, response);
            return;
        }
        //其他請求是否攜帶token，以及判斷redis中是否存在token
        String token = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        //有就放行
        if (StringUtils.hasText(token) && redisTemplate.hasKey(token)) {
            CurrentUser currentUser = tokenUtils.getCurrentUser(token);
            Integer userId = currentUser.getUserId();
            BaseContext.setCurrentId(userId); //將此使用者加入到線程器
            filterChain.doFilter(request, response);
            return;
        }
        //沒有說明未登入或是token過期，不放行，並給前端做出響應
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "您尚未登入！");
        String jsonString = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(jsonString);
        writer.flush();
        writer.close();


    }
}
