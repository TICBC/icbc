package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.account.AccountBaseDO;
import tiger.common.dal.persistence.account.AccountDO;
import tiger.common.dal.persistence.account.example.AccountExample;
import tiger.common.dal.persistence.account.query.AccountQuery;

import java.util.List;

public interface AccountMapper {

    int countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    List<AccountDO> selectByExample(AccountExample example);

    int updateByExampleSelective(@Param("record") AccountDO record,
                                 @Param("example") AccountExample example);

    int updateByExample(@Param("record") AccountDO record,
                        @Param("example") AccountExample example);

    List<AccountDO> selectByExampleAndPage(AccountExample example, RowBounds rowBound);

    int deleteByPrimaryKey(long id);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    AccountDO selectByPrimaryKey(long id);

    AccountBaseDO getBaseInfo(long id);

    List<AccountBaseDO> getBaseInfos(AccountExample example);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);

    /**
     * @param query
     * @return
     */
    int countAccount(@Param("query") AccountQuery query);

    /**
     * @param query
     * @param offset
     * @param length
     * @return
     */
    List<AccountDO> queryAccount(@Param("query") AccountQuery query, @Param("offset") int offset,
                                 @Param("limit") int length);

    List<AccountDO> queryAllAccount(@Param("query") AccountQuery query);

    List<AccountDO> fuzzySearchByName(@Param("stringArray") String[] stringArray);
}
