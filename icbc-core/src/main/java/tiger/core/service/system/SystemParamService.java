/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system;

import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.dal.persistence.system.query.SystemParamsQuery;
import tiger.core.domain.system.SystemParamDomain;

import java.util.List;
import java.util.Map;

/**
 * 系统参数服务类
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:10:08 PM yiliang.gyl Exp $
 */
public interface SystemParamService {

    /**
     * 通过参数类型和 key 获取参数值得
     *
     * @param paramType
     * @param key
     * @return
     */
    String getValueByTypeAndKey(SystemParamTypeEnum paramType, String key);

    /**
     * 通过 type 获取一组 parameters
     *
     * @param paramType
     * @return
     */
    Map<String, String> getParamsByType(SystemParamTypeEnum paramType);

    /**
     * 查询满足query条件的系统变量
     * @param query
     * @return
     */
    List<SystemParamDomain> query(SystemParamsQuery query);

    /**
     * 根据id获取系统参数信息
     * @param id
     * @return
     */
    SystemParamDomain read(Long id);

    /**
     * 更新系统参数
     * @param updateDomain
     * @return
     */
    boolean update(SystemParamDomain updateDomain);

    /**
     * 根据id删除系统参数
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 新增系统参数
     */
    boolean add(SystemParamDomain createDomain);

}
