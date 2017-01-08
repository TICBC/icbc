/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace;

import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.RoleEnum;

import java.util.List;

/**
 * 专门负责角色权限定义的service
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:38 PM yiliang.gyl Exp $
 */
public interface RoleAndPermissionService {

    /**
     * 获取一组角色的去重后的权限列表
     *
     * @param roles
     * @return
     */
    List<PermissionEnum> getPermissionsOfRoles(List<RoleEnum> roles);

    /**
     * 获取角色的权限列表
     * @param role
     * @return
     */
    List<PermissionEnum> getPermissionOfRole(RoleEnum role);


}
