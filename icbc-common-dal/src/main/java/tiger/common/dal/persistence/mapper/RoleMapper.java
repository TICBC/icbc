package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import tiger.common.dal.persistence.account.RoleDO;

import java.util.List;

/**
 * Created by Hupeng on 2015/10/9.
 */
public interface RoleMapper {

    /**
     * 获取用户的 role
     * @param accountId
     * @return
     */
    List<RoleDO> findByAccountId(@Param("accountId") long accountId);

    /**
     * 通过角色名获取id
     *
     * @param roleNames
     * @return
     */
    List<Long> getRoleIdsOfRoleName(@Param("roleNames") List<String> roleNames);

}
