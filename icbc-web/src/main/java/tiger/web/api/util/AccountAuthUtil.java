/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.util;

import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.RoleEnum;
import tiger.core.domain.workspace.AccountWorkspaceDomain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:35 AM yiliang.gyl Exp $
 */
public class AccountAuthUtil {

    /**
     * 获取角色list
     *
     * @param workspaceDomains
     * @return
     */
    public static Set<String> getRoleStrings(List<AccountWorkspaceDomain> workspaceDomains) {
        Set<String> roles = new HashSet<>();

        workspaceDomains.forEach(p -> {
            for (RoleEnum role : p.getRoles()) {
                roles.add(mergeIdAndString(p.getAccountId(),
                        p.getWorkspaceId(), role.getCode()));
            }
        });

        return roles;
    }

    /**
     * 获取权限list
     *
     * @param workspaceDomains
     * @return
     */
    public static Set<String> getPermissionStrings(List<AccountWorkspaceDomain> workspaceDomains) {
        Set<String> permissions = new HashSet<>();

        workspaceDomains.forEach(p -> {
            for (PermissionEnum permission : p.getPermissions()) {
                if(permission != null) {
                    permissions.add(mergeIdAndString(p.getAccountId(),
                            p.getWorkspaceId(), permission.getCode()));
                }
            }
        });

        return permissions;
    }

    /**
     * 获取权限拼接key
     *
     * @param accountId
     * @param workspaceId
     * @param str
     * @return
     */
    public static String mergeIdAndString(Long accountId, Long workspaceId, String str) {
        return new StringBuffer().append(accountId).append("_").
                append(workspaceId).append("_").
                append(str).toString();
    }

    /**
     * 拼接一组keys
     *
     * @param accountId
     * @param workspaceId
     * @param strs
     * @return
     */
    public static List<String> mergeIdAndStrings(Long accountId, Long workspaceId, List<String> strs) {
        List<String> ps = new ArrayList<>();
        for (String str : strs) {
            ps.add(mergeIdAndString(accountId, workspaceId, str));
        }
        return ps;
    }

    /**
     * 拼接一组keys
     *
     * @param accountId
     * @param workspaceId
     * @param strs
     * @return
     */
    public static String[] mergeIdAndStrings(Long accountId, Long workspaceId, String[] strs) {
        String[] ps = new String[strs.length];
        int i = 0;
        for (String str : strs) {
            ps[i] = mergeIdAndString(accountId, workspaceId, str);
            i++;
        }
        return ps;
    }
}
