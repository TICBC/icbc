package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.account.example.ActivityExample;
import tiger.common.dal.persistence.system.query.ActivityQuery;
import tiger.common.dal.persistence.workspace.ActivityDO;

import java.util.List;

public interface ActivityMapper {
    int countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    List<ActivityDO> selectByExample(ActivityExample example);

    int updateByExampleSelective(@Param("record") ActivityDO record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") ActivityDO record, @Param("example") ActivityExample example);

    List<ActivityDO> selectByExampleAndPage(ActivityExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityDO record);

    int batchInsert(@Param("records") List<ActivityDO> records);

    int insertSelective(ActivityDO record);

    ActivityDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityDO record);

    int updateByPrimaryKey(ActivityDO record);

    int countActivities(@Param("query") ActivityQuery query);

    List<ActivityDO> queryActivities(@Param("query") ActivityQuery query, @Param("offset") int offset, @Param("limit") int limit);
}
