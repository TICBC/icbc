package tiger.web.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import tiger.core.basic.BaseResult;
import tiger.core.basic.enums.ErrorCodeEnum;

/**
 * 统一表单验证类切面
 * ~ 拦截 spring 表单验证类的请求
 */
@Aspect
@Component
public class FormValidAspect {

    /**
     * The Constant el.
     */
    private final static String el = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";

    @Before(el)
    public void before() {

    }

    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        Object[] args = p.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    //获取第一条错误信息返回
                    return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER.getCode(),
                            bindingResult.getAllErrors().get(0).getDefaultMessage());
                }
            }
        }
        return p.proceed();
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
