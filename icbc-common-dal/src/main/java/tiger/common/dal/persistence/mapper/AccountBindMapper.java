package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.account.AccountBindDO;
import tiger.common.dal.persistence.account.example.AccountBindExample;

import java.util.List;

public interface AccountBindMapper {
    int countByExample(AccountBindExample example);

    int deleteByExample(AccountBindExample example);

    List<AccountBindDO> selectByExample(AccountBindExample example);

    int updateByExampleSelective(@Param("record") AccountBindDO record, @Param("example") AccountBindExample example);

    int updateByExample(@Param("record") AccountBindDO record, @Param("example") AccountBindExample example);

    List<AccountBindDO> selectByExampleAndPage(AccountBindExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(AccountBindDO record);

    int insertSelective(AccountBindDO record);

    AccountBindDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountBindDO record);

    int updateByPrimaryKey(AccountBindDO record);
}
