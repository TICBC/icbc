/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.dal.persistence.system.SystemParamsDO;
import tiger.common.dal.persistence.system.example.SystemParamsExample;
import tiger.common.dal.persistence.mapper.SystemParamsMapper;
import tiger.common.dal.persistence.system.query.SystemParamsQuery;
import tiger.common.util.ByteUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.system.SystemParamDomain;
import tiger.core.domain.system.convert.SystemParamConvert;
import tiger.core.service.account.AccountService;
import tiger.core.service.system.SystemParamService;

import java.util.List;
import java.util.Map;

/**
 * 系统参数实现类
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:48:55 PM yiliang.gyl Exp $
 */
@Service
public class SystemParamServiceImpl implements SystemParamService {

    protected static Logger logger = Logger.getLogger(SystemParamServiceImpl.class);

    @Autowired
    private SystemParamsMapper systemParamsMapper;

    /**
     * @see SystemParamService#getValueByTypeAndKey(SystemParamTypeEnum, String)
     */
    @Override
    public String getValueByTypeAndKey(SystemParamTypeEnum paramType, String key) {
        SystemParamsExample example = new SystemParamsExample();
        SystemParamsExample.Criteria criteria = example.createCriteria();
        criteria.andParamNameEqualTo(key);
        criteria.andParamTypeEqualTo(paramType.getCode());
        List<SystemParamsDO> paramsDOs = systemParamsMapper.selectByExample(example);
        if (paramsDOs.size() == 1) {
            SystemParamsDO paramsDO = paramsDOs.get(0);
            return paramsDO.getParamValue();
        } else if (paramsDOs.size() > 1) {
            logger.error("获取到" + paramsDOs.size() + "个系统参数实例，无法选择, paramType=" + paramType
                    + ", paramName=" + key + ".");
            throw new TigerException(ErrorCodeEnum.DB_EXCEPTION.getCode(),
                    "获取到" + paramsDOs.size() + "个系统参数实例，无法选择");
        } else {
            logger.error("没有获取到任何参数");
            return null;
        }

    }

    /**
     * @see SystemParamService#getParamsByType(SystemParamTypeEnum)
     */
    @Override
    public Map<String, String> getParamsByType(SystemParamTypeEnum paramType) {
        SystemParamsExample example = new SystemParamsExample();
        SystemParamsExample.Criteria criteria = example.createCriteria();
        criteria.andParamTypeEqualTo(paramType.getCode());
        criteria.andIsActiveEqualTo(ByteUtil.BYTE_ONE);
        return SystemParamConvert.convertToMap(systemParamsMapper.selectByExample(example));
    }


    /**
     * @see AccountService
     */
    @Override
    public List<SystemParamDomain> query(SystemParamsQuery query) {
        SystemParamsExample example = new SystemParamsExample();
        SystemParamsExample.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotBlank(query.getParamType())) {
            criteria.andParamTypeEqualTo(query.getParamType());
        }

        return SystemParamConvert.convert2Domains(systemParamsMapper.selectByExample(example));
    }

    @Override
    public SystemParamDomain read(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("查询ID为 [" + id + "] 的系统参数");
        }
        if (id == null) return null;
        return SystemParamConvert.convertToDomain(systemParamsMapper.selectByPrimaryKey(id));
    }

    @Override
    public boolean update(SystemParamDomain updateDomain) {
        if (logger.isInfoEnabled()) {
            logger.info("更新系统参数为 [" + updateDomain + "]");
        }
        return systemParamsMapper
                .updateByPrimaryKeySelective(SystemParamConvert.convertToDo(updateDomain)) > 0;
    }

    @Override
    public boolean delete(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("删除ID为 [" + id + "] 的系统参数");
        }
        return systemParamsMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean add(SystemParamDomain createDomain) {
        if (logger.isInfoEnabled()) {
            logger.info("新增系统参数 [" + createDomain + "]");
        }
        return systemParamsMapper.insertSelective(SystemParamConvert.convertToDo(createDomain)) > 0;
    }
}
