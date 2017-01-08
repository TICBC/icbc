package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.workspace.WorkspaceVerifyDO;
import tiger.common.dal.persistence.workspace.example.WorkspaceVerifyExample;
import tiger.common.dal.persistence.workspace.query.WorkspaceVerifyQuery;

import java.util.List;

public interface WorkspaceVerifyMapper {
    int countByExample(WorkspaceVerifyExample example);

    int deleteByExample(WorkspaceVerifyExample example);

    List<WorkspaceVerifyDO> selectByExample(WorkspaceVerifyExample example);

    int updateByExampleSelective(@Param("record") WorkspaceVerifyDO record, @Param("example") WorkspaceVerifyExample example);

    int updateByExample(@Param("record") WorkspaceVerifyDO record, @Param("example") WorkspaceVerifyExample example);

    List<WorkspaceVerifyDO> selectByExampleAndPage(WorkspaceVerifyExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(WorkspaceVerifyDO record);

    int insertSelective(WorkspaceVerifyDO record);

    WorkspaceVerifyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkspaceVerifyDO record);

    int updateByPrimaryKey(WorkspaceVerifyDO record);

    int countVerify(@Param("query") WorkspaceVerifyQuery query);

    List<WorkspaceVerifyDO> queryVerify(@Param("query") WorkspaceVerifyQuery query, @Param("offset") int offset,
                                        @Param("limit") int limit);
}
