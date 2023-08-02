package com.wmpn.exception;

import com.wmpn.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
全局異常處理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//捕獲所有異常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return  Result.err(Result.CODE_ERR_BUSINESS,"對不起，操作失敗請通知管理員");
    }
}
