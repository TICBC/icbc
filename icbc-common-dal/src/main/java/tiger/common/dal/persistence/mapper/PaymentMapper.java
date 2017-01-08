package tiger.common.dal.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.system.PaymentDO;
import tiger.common.dal.persistence.system.example.PaymentExample;

public interface PaymentMapper {
    int countByExample(PaymentExample example);

    int deleteByExample(PaymentExample example);

    List<PaymentDO> selectByExample(PaymentExample example);

    int updateByExampleSelective(@Param("record") PaymentDO record, @Param("example") PaymentExample example);

    int updateByExample(@Param("record") PaymentDO record, @Param("example") PaymentExample example);

    List<PaymentDO> selectByExampleAndPage(PaymentExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(PaymentDO record);

    int insertSelective(PaymentDO record);

    PaymentDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentDO record);

    int updateByPrimaryKey(PaymentDO record);
}