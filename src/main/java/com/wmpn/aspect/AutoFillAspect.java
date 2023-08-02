package com.wmpn.aspect;

import com.wmpn.annotation.AutoFill;
import com.wmpn.constant.AutoFillConstant;
import com.wmpn.context.BaseContext;
import com.wmpn.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自定義切面實現公共字段填充
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 定義切入點
     */
    @Pointcut("execution(* com.wmpn.mapper.*.*(..)) &&  @annotation(com.wmpn.annotation.AutoFill)")
    public void autoFillpointCut() {

    }

    @Before("autoFillpointCut()")
    public void autoFill(JoinPoint joinPoint) {
            log.info("開始進行公共字段的自動填充...");

        //獲取當前被攔截的方法上的資料庫操作類型EX:insert or update
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); //獲得方法簽名對象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class); //獲得方法上的註解對象
        OperationType value = autoFill.value();//獲得數據庫的操作類型
        //獲取當前被攔截到的方法參數--實體對象
        Object[] args = joinPoint.getArgs();//獲取當前參數
        if(args == null || args.length == 0){
            return;
        }
        Object entity = args[0];

        //準備負值的數據
        LocalDateTime now =LocalDateTime.now();
        Integer currentId = BaseContext.getCurrentId();

        //根據當前不同操作類型賦值
        if(value == OperationType.INSERT){
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateBy = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Integer.class);
                Method setUpdateBy = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Integer.class);

                //通過反射為對象賦值
                setCreateTime.invoke(entity,now );
                setUpdateTime.invoke(entity,now );
                setCreateBy.invoke(entity,currentId );
                setUpdateBy.invoke(entity,currentId );


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if(value == OperationType.UPDATE){
        try {
            Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateBy = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Integer.class);

            //通過反射為對象賦值
            setUpdateTime.invoke(entity,now );
            setUpdateBy.invoke(entity,currentId );
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        }




    }

}
