/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.basic.constants;

/**
 * 缓存前缀
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 7:14 PM yiliang.gyl Exp $
 */
public class RedisCachePrefixConstants {

    /**
     * 带有权限的账户 domain
     */
    public static String ACCOUNT_AUTH_DOMAIN_PREFIX = "account_auth_domain_";

    public static String LOGIN_LOG_ID_PREFIX = "login_log_id_";

    public static String WORKSPACE_VERIFY_PREFIX = "workspace_verify_domain_";

    public static String WORKSPACE_VERIFY_KEYS_PREFIX = "workspace_verify_domain_keys_";


    /**
     * 构建主键
     *
     * @param key
     * @param end
     * @return
     */
    public static String concreteKey(String key, Object end) {
        return new StringBuffer(key).append(end.toString()).toString();
    }
}
