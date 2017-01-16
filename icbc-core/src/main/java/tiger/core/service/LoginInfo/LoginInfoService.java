package tiger.core.service.LoginInfo;


import tiger.core.domain.LoginInfo.LoginInfoDomain;

import java.util.List;

/**
 * Created by AUSA on 2017/1/15.
 */
public interface LoginInfoService {

    /**
     * 获取所有的登陆信息
     *
     * @return
     */
    List<LoginInfoDomain> getAll();
}
