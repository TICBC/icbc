package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.account.InvitationCodeDO;
import tiger.common.dal.persistence.account.example.InvitationCodeExample;

import java.util.List;


public interface InvitationCodeMapper {
    int countByExample(InvitationCodeExample example);

    int deleteByExample(InvitationCodeExample example);

    List<InvitationCodeDO> selectByExample(InvitationCodeExample example);

    int updateByExampleSelective(@Param("record") InvitationCodeDO record, @Param("example") InvitationCodeExample example);

    int updateByExample(@Param("record") InvitationCodeDO record, @Param("example") InvitationCodeExample example);

    List<InvitationCodeDO> selectByExampleAndPage(InvitationCodeExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(InvitationCodeDO record);

    int insertSelective(InvitationCodeDO record);

    InvitationCodeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvitationCodeDO record);

    int updateByPrimaryKey(InvitationCodeDO record);

    InvitationCodeDO selectByCode(String code);

    int batchInsert(@Param("records") List<InvitationCodeDO> records);

    List<String> getAllInvitationCodes(@Param("isActive") Boolean isActive);

    int getUsedInvitationCodeNumber(@Param("inviterId") Long inviterId, @Param("type") String type);

    List<InvitationCodeDO> getInvitationCodeList(@Param("inviterId") Long inviterId, @Param("type") String type);

    // 获取inviterId所生成的所有邀请码
    List<InvitationCodeDO> getAllInvitationCodeList(@Param("inviterId") Long inviterId);
}
