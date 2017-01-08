package tiger.core.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.annotation.RedisCaching;
import tiger.common.dal.persistence.account.AccountLoginLogDO;
import tiger.common.dal.persistence.mapper.AccountLoginLogMapper;
import tiger.common.dal.redis.RedisComponent;
import tiger.core.basic.constants.RedisCachePrefixConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountLoginLogDomain;
import tiger.core.domain.account.convert.AccountLoginLogConvert;
import tiger.core.service.account.LoginLogService;

/**
 * Created by Hupeng on 2015/10/9.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    @Autowired
    private RedisComponent redisComponent;

    /**
     * 通过token获取用户数据
     *
     * @param token
     * @return
     */
    @Override
    @RedisCaching(key = "login_log_id+token")
    public long getAccountIdByToken(String token) {
        AccountLoginLogDO accountLoginLogDO = accountLoginLogMapper.findByToken(token);
        if (accountLoginLogDO == null) {
            return 0;
        }
        redisComponent.saveObject(RedisCachePrefixConstants.LOGIN_LOG_ID_PREFIX + token, accountLoginLogDO.getAccountId() + "");
        return accountLoginLogDO.getAccountId();
    }

    @Override
    public void createLog(AccountLoginLogDomain loginLogDomain) {
        if (null == loginLogDomain) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }

        accountLoginLogMapper.save(AccountLoginLogConvert.convertToDO(loginLogDomain));
    }
}
