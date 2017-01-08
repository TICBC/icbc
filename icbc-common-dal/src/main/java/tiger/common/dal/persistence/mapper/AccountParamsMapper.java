package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.account.AccountParamsDO;
import tiger.common.dal.persistence.account.example.AccountParamsExample;

import java.util.List;

public interface AccountParamsMapper {
    int countByExample(AccountParamsExample example);

    int deleteByExample(AccountParamsExample example);

    List<AccountParamsDO> selectByExample(AccountParamsExample example);

    int updateByExampleSelective(@Param("record") AccountParamsDO record, @Param("example") AccountParamsExample example);

    int updateByExample(@Param("record") AccountParamsDO record, @Param("example") AccountParamsExample example);

    List<AccountParamsDO> selectByExampleAndPage(AccountParamsExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(AccountParamsDO record);

    int insertSelective(AccountParamsDO record);

    AccountParamsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountParamsDO record);

    int updateByPrimaryKey(AccountParamsDO record);
}
