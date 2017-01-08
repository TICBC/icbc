package tiger.web.api.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;

import java.sql.SQLException;

/**
 * 统一异常抓取
 */
@Aspect
@Component
@Order(100)
public class TigerExceptionAspect {

    /**
     * The Constant el.
     */
    private final static String el = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";
    private static Logger logger = Logger.getLogger(TigerExceptionAspect.class);

    @Before(el)
    public void before() {

    }

    /**
     * 统一异常处理函数
     * ~ 可以处理基本返回异常 (BaseResult) 和 PageResult 返回异常
     *
     * @param p
     * @return
     */
    @Around(el)
    public Object around(ProceedingJoinPoint p) {
        boolean isPageResult = false;
        if (p.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) p.getSignature();
            if (signature.getReturnType() == PageResult.class) {
                isPageResult = true;
            }
        }
        try {
            return p.proceed();
        } catch (TigerException e) { //拦截tiger异常
            if (isPageResult) {
                return new PageResult<>(e.getCode(), e.getMessage());
            }
            return new BaseResult<>(e.getCode(), e.getMessage());
        } catch (DataAccessException | SQLException e) { //拦截数据库异常
            if (isPageResult) {
                return new PageResult<>(ErrorCodeEnum.DB_EXCEPTION);
            }
            return new BaseResult<>(ErrorCodeEnum.DB_EXCEPTION);
        } catch (Throwable e) { //拦截其他异常
            if (isPageResult) {
                return new PageResult<>(ErrorCodeEnum.UNKNOW_ERROR);
            }
            return new BaseResult<>(ErrorCodeEnum.UNKNOW_ERROR);
        }
    }

    @After(el)
    public void after() {

    }

    /**
     * Throwing.
     *
     * @param e the e
     */
    @AfterThrowing(value = el, throwing = "e")
    public void throwing(Exception e) {
        e.printStackTrace();
    }

}
