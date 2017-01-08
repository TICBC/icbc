package tiger.web.api;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import tiger.core.domain.account.AccountDomain;
import tiger.web.api.util.AccountAuthUtil;

/**
 * Shiro 领域情况
 */
@Component("shiroRealm")
public class ShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountDomain account = (AccountDomain) principals.fromRealm(getName()).iterator().next();
        //获取account的各个workspace的权限和角色
        if (null != account && CollectionUtils.isNotEmpty(account.getAccountWorkspaceDomains())) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //获取所有的team的权限列表
            info.addRoles(AccountAuthUtil.getRoleStrings(account.getAccountWorkspaceDomains()));
            info.addStringPermissions(AccountAuthUtil.getPermissionStrings(account.getAccountWorkspaceDomains()));
            return info;
        }
        return null;
    }

    /**
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authcToken;
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(),
                getName());
    }
}
