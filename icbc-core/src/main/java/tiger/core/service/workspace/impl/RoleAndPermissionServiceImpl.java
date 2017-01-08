/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.RoleEnum;
import tiger.common.dal.persistence.account.PermissionDO;
import tiger.common.dal.persistence.mapper.PermissionMapper;
import tiger.common.dal.persistence.mapper.RoleMapper;
import tiger.core.service.workspace.RoleAndPermissionService;

import java.util.ArrayList;
import java.util.List;

/**
 * 专门负责角色权限定义的service
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:39 PM yiliang.gyl Exp $
 */
@Service
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {

    private static Logger logger = Logger.getLogger(RoleAndPermissionServiceImpl.class);

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleMapper roleMapper;


    /**
     * @see RoleAndPermissionService#getPermissionsOfRoles(List)
     */
    @Override
    public List<PermissionEnum> getPermissionsOfRoles(List<RoleEnum> roles) {

        List<PermissionEnum> permissions = new ArrayList<>();

        List<String> roleNames = new ArrayList<>();
        roles.stream().forEach(p -> roleNames.add(p.getCode()));
        if (CollectionUtils.isEmpty(roleNames)) {
            return permissions;
        }
        List<Long> roleIds = roleMapper.getRoleIdsOfRoleName(roleNames);

        if (CollectionUtils.isEmpty(roleIds)) {
            return permissions;
        }

        List<PermissionDO> permissionDOs = permissionMapper.selectByRoleIds(roleIds);
        permissionDOs.stream().forEach(p -> {
            if(PermissionEnum.getEnumByCode(p.getName()) != null){
                permissions.add(PermissionEnum.getEnumByCode(p.getName()));
            }else{
                logger.error("权限枚举值配置错误 [" + p.getName() + "]");
            }
        });

        return permissions;
    }

    /**
     * @see RoleAndPermissionService#getPermissionOfRole(RoleEnum)
     */
    @Override
    public List<PermissionEnum> getPermissionOfRole(RoleEnum role) {
        List<RoleEnum> roles = new ArrayList<>();
        roles.add(role);
        return getPermissionsOfRoles(roles);
    }
}
