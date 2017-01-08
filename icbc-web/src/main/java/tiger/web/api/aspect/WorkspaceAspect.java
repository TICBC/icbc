/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.aspect;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tiger.biz.account.support.AccountManager;
import tiger.common.dal.annotation.Permission;
import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.PermissionRelation;
import tiger.common.dal.enums.RoleEnum;
import tiger.common.dal.redis.RedisComponent;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.basic.constants.RedisCachePrefixConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.domain.account.AccountDomain;
import tiger.web.api.StatelessToken;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.util.AccountAuthUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:34 PM yiliang.gyl Exp $
 */
@Aspect
@Component
@Order(2)
public class WorkspaceAspect {


    /**
     * The Constant el.
     */
    private final static String el = "@annotation(tiger.common.dal.annotation.Permission)";

    /**
     * logger
     */
    private static Logger logger = Logger.getLogger(WorkspaceAspect.class);
    /**
     * The request.
     */
    @Autowired
    HttpServletRequest request;

    /**
     * The account service.
     */
    @Autowired
    AccountManager accountManager;

    @Autowired
    RedisComponent redisComponent;

    /**
     * @param p
     * @return
     * @throws Throwable
     */
    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        //1. 先登录,走基础权限
        Permission permission = ((MethodSignature) p.getSignature()).getMethod()
                .getAnnotation(Permission.class);
        AccountDomain accountDomain = findAccountDomainByToken(getToken());
        if (null == accountDomain) {
            return error(isPageResult(p), ErrorCodeEnum.REQUIRED_LOGIN.getCode(), "您需要重新登录!");
        }

        //2. 如果需要workspace权限
        if (permission.workspace()) {
            StatelessToken token = new StatelessToken(accountDomain, getToken());
            Subject user = SecurityUtils.getSubject();
            user.login(token);

            String workSpaceIdStr = getWorkSpaceId();

            if (StringUtil.isBlank(workSpaceIdStr)) {
                return error(isPageResult(p), ErrorCodeEnum.UNAUTHORIZED.getCode(), "你没有权限执行该操作");
            }

            Long workSpaceId = Long.parseLong(workSpaceIdStr);
            //判断角色和权限
            if (permittedRoles(user, AccountAuthUtil.mergeIdAndStrings(accountDomain.getId(),
                    workSpaceId, getRoleArray(permission))) && permitted(user, permission.relation(),
                    AccountAuthUtil.mergeIdAndStrings(accountDomain.getId(), workSpaceId,
                            getPermissionArray(permission)))) {
                return p.proceed();
            } else {
                return error(isPageResult(p), ErrorCodeEnum.UNAUTHORIZED.getCode(), "你没有权限执行该操作");
            }
        } else {
            //不需要判断是否有workspace权限
            return p.proceed();
        }
    }

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

    /**
     * Gets the token.
     *
     * @return the token
     */
    private String getToken() {
        return request.getHeader(APIConstants.HEADER_TOKEN);
    }

    /**
     * 获取工作空间id
     *
     * @return
     */
    private String getWorkSpaceId() {
        return request.getHeader(APIConstants.HEADER_WORKSPACE_ID);
    }

    //~ private method
    private AccountDomain findAccountDomainByToken(String token) {
        AccountDomain accountDomain = null;
        String accountIdStr = redisComponent.getObject(RedisCachePrefixConstants.LOGIN_LOG_ID_PREFIX + token);
        if (StringUtil.isNotBlank(accountIdStr)) {
            String content = redisComponent.getObject(RedisCachePrefixConstants.ACCOUNT_AUTH_DOMAIN_PREFIX + accountIdStr);
            if (StringUtil.isNotBlank(content)) {
                accountDomain = JsonUtil.fromJson(content, AccountDomain.class);
                if (logger.isDebugEnabled()) {
                    logger.info("从缓存中获取了用户对象 [" + content + "]");
                }
            }
        }
        if (accountDomain == null) {
            accountDomain = accountManager.loginByToken(token);
        }
        return accountDomain;
    }

    private boolean permittedRoles(Subject user, List<String> roles) {
        boolean[] bs = user.hasRoles(roles);
        for (boolean b : bs) {
            if (b) {
                return true;
            }
        }
        return false;
    }

    private boolean permitted(Subject user, PermissionRelation relation, String[] permissions) {
        if (permissions == null || permissions.length == 0) {
            return true;
        }
        if (relation.equals(PermissionRelation.AND)) { //需要所有权限
            return user.isPermittedAll(permissions);
        } else if (relation.equals(PermissionRelation.OR)) { //需要任意一个权限
            boolean[] bs = user.isPermitted(permissions);
            for (boolean b : bs) {
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否返回值是PageResult，以防转换错误
     *
     * @param p
     * @return
     */
    private boolean isPageResult(ProceedingJoinPoint p) {
        if (p.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) p.getSignature();
            if (signature.getReturnType() == PageResult.class) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从annotation中获取role列表
     */
    private List<String> getRoleArray(Permission permission) {
        RoleEnum[] roleEnums = permission.role();
        List<String> roles = new ArrayList<>();
        for (RoleEnum roleEnum : roleEnums) {
            roles.add(roleEnum.getCode());
        }
        return roles;
    }

    /**
     * 从annotation中获取permission列表
     */
    private String[] getPermissionArray(Permission permission) {
        //1. 获取权限
        PermissionEnum[] permissionEnums = permission.permission();
        if (permissionEnums.length == 0) {
            return new String[0];
        }
        String[] permissions = new String[permissionEnums.length];
        int i = 0;
        for (PermissionEnum permissionEnum : permissionEnums) {
            permissions[i] = permissionEnum.getCode();
            i++;
        }
        return permissions;
    }

    /**
     * 通用error构建方法
     */
    private BaseResult<?> error(boolean isPageResult, String code, String message) {
        if (isPageResult) {
            return new PageResult<>(code, message);
        }
        return new BaseResult<>(code, message);
    }
}
