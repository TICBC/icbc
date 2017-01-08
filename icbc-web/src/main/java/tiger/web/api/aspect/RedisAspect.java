package tiger.web.api.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tiger.common.dal.annotation.RedisCaching;
import tiger.common.dal.redis.RedisComponent;
import tiger.common.dal.redis.RedisConstants;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一 Redis缓存切面
 * <p>
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
@Aspect
@Component
public class RedisAspect {

    private final static String el = "@annotation(tiger.common.dal.annotation.RedisCaching)";

    private static Logger logger = Logger.getLogger(RedisAspect.class);

    @Autowired
    RedisComponent redisComponent;

    /**
     * Before.
     */
    @Before(el)
    public void before() {
    }

    /**
     * After.
     */
    @After(el)
    public void after() {

    }

    @AfterReturning(pointcut = el, returning = "rvt")
    public void afterReturn(Object rvt) {
    }

    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        if (!RedisConstants.IS_OPEN) {
            return p.proceed();
        }

        MethodSignature signature = (MethodSignature) p.getSignature();
        //获取缓存key值
        String cacheKey = getKeyOfCache(signature, p.getArgs());

        //根据key查询缓存
        String content = redisComponent.getObject(cacheKey);
        if (StringUtil.isNotBlank(content)) {
            if (logger.isDebugEnabled()) {
                logger.debug("从缓存中获取了数据 key [" + cacheKey + "] 内容[" + content + "]");
            }
            return JsonUtil.fromJson(content, signature.getReturnType());
        } else {
            return p.proceed();
        }
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


    // ~ private methods

    /**
     * 根据 Annotation 的配置构建缓存 key 值
     *
     * @param signature
     * @param args
     * @return
     * @throws Throwable
     */
    private String getKeyOfCache(MethodSignature signature, Object[] args) throws Throwable {
        Class[] classes = signature.getParameterTypes();
        List<ParamType> paramTypes = parsePramTypes(signature.getMethod().
                getAnnotation(RedisCaching.class).key()); //缓存参数配置解析

        //获取缓存key值
        String cacheKey = "";
        for (ParamType paramType : paramTypes) {
            int offset = 0;
            for (String name : signature.getParameterNames()) {
                if (StringUtil.equals(name, paramType.getParamName())) {
                    Method method = classes[offset].getMethod(paramType.getFunctionName());
                    cacheKey = buildCacheKey(cacheKey, method.invoke(classes[offset].cast(args[offset])));
                    break;
                }
                offset++;
            }
            //没有找到对应的方法，直接toString
            if (offset == signature.getParameterNames().length) {
                cacheKey = buildCacheKey(cacheKey, paramType.getParamName());
            }
        }
        return cacheKey;
    }

    // ~ 以下是构建内部参数类型的方法和内部类

    private String buildCacheKey(String srcKey, Object item) {
        if (StringUtil.isBlank(srcKey)) {
            return new StringBuffer(srcKey).append(item.toString()).toString();
        } else {
            return new StringBuffer(srcKey).append("_").append(item.toString()).toString();
        }
    }

    private List<ParamType> parsePramTypes(String str) {
        List<ParamType> paramTypes = new ArrayList<>();

        String[] args = str.split("\\+");

        for (String inStr : args) {
            paramTypes.add(castFromString(inStr));
        }

        return paramTypes;
    }


    private ParamType castFromString(String str) {
        String[] args = str.split("\\.");
        if (args.length > 2) {
            throw new TigerException(ErrorCodeEnum.UNKNOW_ERROR);
        }
        ParamType paramType = new ParamType();
        paramType.setParamName(args[0]);
        if (args.length == 1) {
            paramType.setFunctionName("toString");
        } else {
            paramType.setFunctionName(args[1]);
        }
        return paramType;
    }


    class ParamType {
        private String paramName;

        private String functionName;


        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public String getFunctionName() {
            return functionName;
        }

        public void setFunctionName(String functionName) {
            this.functionName = functionName;
        }


    }
}
