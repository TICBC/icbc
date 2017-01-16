package tiger.biz.LoginInfo.support;

import tiger.core.basic.PageResult;
import tiger.core.domain.LoginInfo.LoginInfoDomain;

import java.util.List;

/**
 * Created by AUSA on 2017/1/15.
 */
public interface LoginInfoManager {
    //获取所有登陆信息
    PageResult<List<LoginInfoDomain>> getAll();
}
