package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.system.SmsVerifyCodeDO;
import tiger.common.dal.persistence.system.example.SmsVerifyCodeExample;

import java.util.Date;
import java.util.List;

public interface SmsVerifyCodeMapper {
    int countByExample(SmsVerifyCodeExample example);

    int deleteByExample(SmsVerifyCodeExample example);

    List<SmsVerifyCodeDO> selectByExample(SmsVerifyCodeExample example);

    int updateByExampleSelective(@Param("record") SmsVerifyCodeDO record, @Param("example") SmsVerifyCodeExample example);

    int updateByExample(@Param("record") SmsVerifyCodeDO record, @Param("example") SmsVerifyCodeExample example);

    List<SmsVerifyCodeDO> selectByExampleAndPage(SmsVerifyCodeExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsVerifyCodeDO record);

    int insertSelective(SmsVerifyCodeDO record);

    SmsVerifyCodeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsVerifyCodeDO record);

    int updateByPrimaryKey(SmsVerifyCodeDO record);

    /**
     * 得到从begin到end发送给accountId类型为smsType的验证码短信个数
     * @param accountId
     * @param smsType
     * @param begin
     * @param end
     * @return
     */
    int countVerifySmses(@Param("accountId") Long accountId, @Param("smsType")String smsType,
                         @Param("begin")Date begin, @Param("end")Date end);
}
