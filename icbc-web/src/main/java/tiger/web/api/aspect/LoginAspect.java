package tiger.web.api.aspect;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tiger.biz.account.support.AccountManager;
import tiger.common.dal.annotation.LoginRequired;
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

import javax.servlet.http.HttpServletRequest;

/**
 * 登录和权限验证切面
 * <p>
 * <p>
 * Created by HuPeng on 2015/9/7.
 */
@Aspect
@Component
@Order(1)
public class LoginAspect {

    /**
     * The Constant el.
     */
    private final static String el = "@annotation(tiger.common.dal.annotation.LoginRequired)";
    private static Logger logger = Logger.getLogger(LoginAspect.class);
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
     * Before.
     */
    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        LoginRequired annotation = ((MethodSignature) p.getSignature()).getMethod()
                .getAnnotation(LoginRequired.class);
        //获取登录数据
        AccountDomain accountDomain = findAccountDomainByToken(getToken());
        if (null == accountDomain) {
            if (annotation.exception()) {
                if (isPageResult(p)) {
                    return new PageResult<>(ErrorCodeEnum.REQUIRED_LOGIN.getCode(), "您需要重新登录!");
                }
                return new BaseResult<>(ErrorCodeEnum.REQUIRED_LOGIN.getCode(), "您需要重新登录!");
            }
        } else {
            StatelessToken token = new StatelessToken(accountDomain, getToken());
            Subject user = SecurityUtils.getSubject();
            user.login(token);
        }
        return p.proceed();
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
     * Throwing.
     *
     * @param e the e
     */
    @AfterThrowing(value = el, throwing = "e")
    public void throwing(Exception e) {
        e.printStackTrace();
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

    private boolean isPageResult(ProceedingJoinPoint p) {
        if (p.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) p.getSignature();
            if (signature.getReturnType() == PageResult.class) {
                return true;
            }
        }
        return false;
    }
}
