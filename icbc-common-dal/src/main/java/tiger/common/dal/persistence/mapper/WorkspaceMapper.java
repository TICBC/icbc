package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.system.CountDTO;
import tiger.common.dal.persistence.workspace.WorkspaceAccountDTO;
import tiger.common.dal.persistence.workspace.WorkspaceDO;
import tiger.common.dal.persistence.workspace.example.WorkspaceExample;

import java.util.List;

public interface WorkspaceMapper {
    int countByExample(WorkspaceExample example);

    int deleteByExample(WorkspaceExample example);

    List<WorkspaceDO> selectByExampleWithBLOBs(WorkspaceExample example);

    List<WorkspaceDO> selectByExample(WorkspaceExample example);

    int updateByExampleSelective(@Param("record") WorkspaceDO record, @Param("example") WorkspaceExample example);

    int updateByExampleWithBLOBs(@Param("record") WorkspaceDO record, @Param("example") WorkspaceExample example);

    int updateByExample(@Param("record") WorkspaceDO record, @Param("example") WorkspaceExample example);

    List<WorkspaceDO> selectByExampleWithBLOBsAndPage(WorkspaceExample example, RowBounds rowBound);

    List<WorkspaceDO> selectByExampleAndPage(WorkspaceExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(WorkspaceDO record);

    int insertSelective(WorkspaceDO record);

    WorkspaceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkspaceDO record);

    int updateByPrimaryKeyWithBLOBs(WorkspaceDO record);

    int updateByPrimaryKey(WorkspaceDO record);

    //~ user defined functions
    int removeAllUser(@Param("workspaceId") Long workspaceId);

    int removeUser(@Param("workspaceId") Long workspaceId, @Param("accountId") Long accountId);

    int addUserAndRoles(@Param("WorkspaceAccountDTOs") List<WorkspaceAccountDTO> WorkspaceAccountDTO);

    /**
     * 获取用户指定的角色
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    List<WorkspaceAccountDTO> getUserWorkSpaceRoles(@Param("workspaceId") Long workspaceId, @Param("accountId") Long accountId);

    /**
     * 获取用户所有的团队的角色列表
     *
     * @param accountId
     * @return
     */
    List<WorkspaceAccountDTO> getAllUserWorkSpaceRoles(@Param("accountId") Long accountId);

    /**
     * 获取团队的用户列表
     *
     * @param workspaceId
     * @return
     */
    List<WorkspaceAccountDTO> getWorkSpaceUsers(@Param("workspaceId") Long workspaceId);

    /**
     * 获取用户参与的团队
     *
     * @param accountId
     * @return
     */
    List<WorkspaceDO> getWorkspacesByAccountId(@Param("accountId") Long accountId);

    /**
     * 获取用户的团队Id
     *
     * @param accountId
     * @return
     */
    List<Long> getWorkspaceIdsByAccountId(@Param("accountId") Long accountId);

    /**
     * 删除 accountId 在 workspaceId 中的 roles 角色
     *
     * @param workspaceId
     * @param accountId
     * @param roles
     * @return
     */
    int removeUserRoles(@Param("workspaceId") Long workspaceId, @Param("accountId") Long accountId, @Param("roles") List<String> roles);

    /**
     * 获取workspaceIds工作空间下的成员计数
     *
     * @param workspaceIds
     * @return
     */
    List<CountDTO> countWorkspaceMembers(@Param("workspaceIds") List<Long> workspaceIds);
}
