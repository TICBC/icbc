package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.system.FeedbackDO;
import tiger.common.dal.persistence.system.example.FeedbackExample;
import tiger.common.dal.persistence.system.query.FeedbackQuery;

import java.util.List;

public interface FeedbackMapper {
    int countByExample(FeedbackExample example);

    int deleteByExample(FeedbackExample example);

    List<FeedbackDO> selectByExampleWithBLOBs(FeedbackExample example);

    List<FeedbackDO> selectByExample(FeedbackExample example);

    int updateByExampleSelective(@Param("record") FeedbackDO record, @Param("example") FeedbackExample example);

    int updateByExampleWithBLOBs(@Param("record") FeedbackDO record, @Param("example") FeedbackExample example);

    int updateByExample(@Param("record") FeedbackDO record, @Param("example") FeedbackExample example);

    List<FeedbackDO> selectByExampleWithBLOBsAndPage(FeedbackExample example, RowBounds rowBound);

    List<FeedbackDO> selectByExampleAndPage(FeedbackExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(FeedbackDO record);

    int insertSelective(FeedbackDO record);

    FeedbackDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FeedbackDO record);

    int updateByPrimaryKeyWithBLOBs(FeedbackDO record);

    int updateByPrimaryKey(FeedbackDO record);

    /**
     * 根据query条件计数
     * @param query
     * @return
     */
    int countFeedback(@Param("query") FeedbackQuery query);

    /**
     * 查询长度为 length 的以第 offset 个开始的满足 query 条件的反馈列表
     * @param query
     * @param offset
     * @param length
     * @return
     */
    List<FeedbackDO> query(@Param("query") FeedbackQuery query, @Param("offset") int offset,
                           @Param("limit") int length);
}
