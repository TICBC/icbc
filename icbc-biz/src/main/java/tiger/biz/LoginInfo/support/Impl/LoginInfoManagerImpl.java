package tiger.biz.LoginInfo.support.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.LoginInfo.support.LoginInfoManager;
import tiger.core.basic.PageResult;
import tiger.core.domain.LoginInfo.LoginInfoDomain;
import tiger.core.service.LoginInfo.LoginInfoService;

import java.util.List;

/**
 * Created by AUSA on 2017/1/15.
 */
@Service
public class LoginInfoManagerImpl implements LoginInfoManager{
    @Autowired
    LoginInfoService loginInfoService;
    @Override
    public PageResult<List<LoginInfoDomain>> getAll() {
        return new PageResult<List<LoginInfoDomain>>(loginInfoService.getAll());
    }
}
