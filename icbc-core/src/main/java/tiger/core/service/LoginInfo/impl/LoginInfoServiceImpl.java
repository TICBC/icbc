package tiger.core.service.LoginInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.icbc.LoginInfoDO;
import tiger.common.dal.persistence.mapper.LoginInfoDOMapper;
import tiger.core.domain.LoginInfo.LoginInfoDomain;
import tiger.core.domain.LoginInfo.convert.LoginInfoConvert;
import tiger.core.service.LoginInfo.LoginInfoService;

import java.util.List;

/**
 * Created by AUSA on 2017/1/15.
 */
@Service
public class LoginInfoServiceImpl implements LoginInfoService{
    @Autowired
    LoginInfoDOMapper loginInfoDOMapper;
    @Override
    public List<LoginInfoDomain> getAll() {
        List<LoginInfoDO> DOs = loginInfoDOMapper.selectAll();
        return LoginInfoConvert.convert2Domains(DOs);
    }
}
