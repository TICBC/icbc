/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.system.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.system.support.CachingManager;
import tiger.common.dal.redis.RedisComponent;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.constants.RedisCachePrefixConstants;
import tiger.core.domain.workspace.WorkspaceInvitationDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 1:52 PM yiliang.gyl Exp $
 */
@Service
public class CachingManagerImpl implements CachingManager {

    @Autowired
    private RedisComponent redisComponent;

    /**
     * @see CachingManager#removeAccountCache(Object)
     */
    @Override
    public Boolean removeAccountCache(Object bizId) {
        if (bizId == null) {
            return false;
        }
        String key = RedisCachePrefixConstants.concreteKey(RedisCachePrefixConstants.ACCOUNT_AUTH_DOMAIN_PREFIX,
                bizId.toString());
        return redisComponent.deleteObject(key) > 0;
    }

    /**
     * @see CachingManager#getInvitationDomain(String)
     */
    @Override
    public WorkspaceInvitationDomain getInvitationDomain(String code) {
        String key = RedisCachePrefixConstants.concreteKey(
                RedisCachePrefixConstants.WORKSPACE_VERIFY_PREFIX, code);
        String content = redisComponent.getObject(key);

        if (StringUtil.isNotBlank(content)) {
            return JsonUtil.fromJson(content, WorkspaceInvitationDomain.class);
        }
        return null;
    }

    /**
     * @see CachingManager#modifyInvitationCodeList(Object, String, Boolean)
     */
    @Override
    public Boolean modifyInvitationCodeList(Object workspaceId, String code, Boolean isAdd) {
        String key = RedisCachePrefixConstants.concreteKey(RedisCachePrefixConstants.WORKSPACE_VERIFY_KEYS_PREFIX,
                workspaceId.toString());
        String content = redisComponent.getObject(key);
        if (StringUtil.isNotBlank(content)) {
            List<String> keys = JsonUtil.fromJson(content, List.class);
            if (isAdd) {
                keys.add(code);
            } else {
                if (keys.contains(code)) {
                    keys.remove(code);
                }
            }
            return redisComponent.saveObject(key, JsonUtil.toJson(keys)) > 0;
        } else {
            if (isAdd) {
                List<String> list = new ArrayList<>();
                list.add(code);
                return redisComponent.saveObject(key, JsonUtil.toJson(list)) > 0;
            } else {
                return true;
            }
        }
    }


}
