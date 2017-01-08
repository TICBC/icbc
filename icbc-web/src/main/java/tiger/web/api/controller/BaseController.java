package tiger.web.api.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.PermissionRelation;
import tiger.common.util.StringUtil;
import tiger.core.basic.constants.SystemConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.util.AccountAuthUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuPeng on 2015/9/8.
 */
public class BaseController {
    private static Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    HttpServletRequest request;

    public Subject currentUser() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取用户信息(空会抛出Exception)
     *
     * @return
     */
    public AccountDomain currentAccount() {
        AccountDomain currentUser = (AccountDomain) currentUser().getPrincipal();
        /**如果是未登录用户，直接抛异常返回*/
        if (currentUser == null) {
            throw new TigerException(ErrorCodeEnum.REQUIRED_LOGIN, "登录失效,请重新登录。");
        }
        return currentUser;
    }

    /**
     * 获取用户id(可空)
     *
     * @return
     */
    public Long currentAccountIdWithoutException() {
        AccountDomain currentUser = (AccountDomain) currentUser().getPrincipal();
        if (currentUser == null) {
            return null;
        } else {
            return currentUser.getId();
        }
    }

    /**
     * 获取当前workspaceId
     *
     * @return
     */
    public Long currentWorkspaceId() {
        String idStr = request.getHeader(APIConstants.HEADER_WORKSPACE_ID);
        if (StringUtil.isBlank(idStr)) {
            return null;
        } else {
            return Long.parseLong(idStr);
        }
    }

    /**
     * 检查当前用户是否拥有permission权限
     *
     * @param permission
     * @return
     */
    public boolean isPermitted(PermissionEnum permission) {
        List<PermissionEnum> permissions = new ArrayList<>(SystemConstants.SIZE_ONE);
        permissions.add(permission);

        return isPermitted(permissions, PermissionRelation.AND);
    }

    /**
     * 检查当前用户在当前工作组下的权限, 默认OR
     *
     * @param permissions
     * @return
     */
    public boolean isPermitted(List<PermissionEnum> permissions) {
        return isPermitted(permissions, PermissionRelation.OR);
    }

    /**
     * 检查当前用户在当前工作组下的权限, 支持 AND, OR
     *
     * @param permissions
     * @return
     */
    public boolean isPermitted(List<PermissionEnum> permissions, PermissionRelation relation) {
        if (CollectionUtils.isEmpty(permissions)) {
            return true;
        }

        String[] permissionList = new String[permissions.size()];

        int index = 0;
        for (PermissionEnum permission : permissions) {
            permissionList[index] = permission.getCode();
            ++index;
        }

        permissionList = AccountAuthUtil.mergeIdAndStrings(currentAccount().getId(), currentWorkspaceId(), permissionList);

        // AND
        if (relation == PermissionRelation.AND) {
            return currentUser().isPermittedAll(permissionList);
        }

        // OR
        boolean[] results = currentUser().isPermitted(permissionList);

        for (boolean b : results) {
            if (b) {
                return true;
            }
        }

        return false;
    }
}
