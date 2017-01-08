/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.persistence.account.AccountSettingDO;
import tiger.common.dal.persistence.account.example.AccountSettingExample;
import tiger.common.dal.persistence.account.query.AccountSettingQuery;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 19:02 alfred_yuan Exp $
 */
public interface AccountSettingMapper {
    int countByExample(AccountSettingExample example);

    int deleteByExample(AccountSettingExample example);

    List<AccountSettingDO> selectByExample(AccountSettingExample example);

    int updateByExampleSelective(@Param("record") AccountSettingDO record, @Param("example") AccountSettingExample example);

    int updateByExample(@Param("record") AccountSettingDO record, @Param("example") AccountSettingExample example);

    List<AccountSettingDO> selectByExampleAndPage(AccountSettingExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(AccountSettingDO record);

    int insertSelective(AccountSettingDO record);

    AccountSettingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountSettingDO record);

    int updateByPrimaryKey(AccountSettingDO record);

    /**
     * 根据查询条件获取账户设置列表
     * @param query
     * @return
     */
    List<AccountSettingDO> query(@Param("query")AccountSettingQuery query);
}
