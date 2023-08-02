package com.wmpn.aspect;

import com.alibaba.fastjson.JSONObject;
import com.wmpn.entity.CurrentUser;
import com.wmpn.entity.OperateLog;
import com.wmpn.mapper.OperateLogMapper;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest servletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @Around("@annotation(com.wmpn.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人的id，當前登入的id :獲取請求頭的jwt，並解析jwt
        String jwt = servletRequest.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        CurrentUser currentUser = tokenUtils.getCurrentUser(jwt);
        Integer operateUser = currentUser.getUserId();


        //操作時間
        LocalDateTime operateTime = LocalDateTime.now();
        //操作類名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法參數
        Object[] args = joinPoint.getArgs();
        String operateArg = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //調用原始方法執行
        Object resulet = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //方法返回值
        String returnValue = JSONObject.toJSONString(resulet);

        //操作耗時
        long costTime = end - begin;


        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,operateArg,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP紀錄操作日誌：{}",operateLog);

        return resulet;


    }
}
