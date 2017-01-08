/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.system.convert;

import org.apache.log4j.Logger;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.dal.persistence.system.SystemParamsDO;
import tiger.common.util.BeanUtil;
import tiger.common.util.ByteUtil;
import tiger.common.util.StringUtil;
import tiger.core.domain.system.SystemParamDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数类转换器.
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:17:27 PM yiliang.gyl Exp $
 */
public class SystemParamConvert {
    private static Logger logger = Logger.getLogger(SystemParamConvert.class);

    /**
     * Convert to do.
     *
     * @param systemParamDomain the system param domain
     * @return the system params do
     */
    public static SystemParamsDO convertToDo(SystemParamDomain systemParamDomain) {
        if (systemParamDomain == null) {
            return null;
        }
        SystemParamsDO systemParamsDO = new SystemParamsDO();
        BeanUtil.copyPropertiesWithIgnores(systemParamDomain, systemParamsDO);
        systemParamsDO.setParamType(systemParamDomain.getParamType().getCode());
        systemParamsDO.setIsActive(ByteUtil.getAsBytes(systemParamDomain.getIsActive())[0]);

        return systemParamsDO;

    }

    /**
     * Convert to domain.
     *
     * @param systemParamsDO the system params do
     * @return the system param domain
     */
    public static SystemParamDomain convertToDomain(SystemParamsDO systemParamsDO) {
        if (systemParamsDO == null) {
            return null;
        }
        SystemParamDomain systemParamDomain = new SystemParamDomain();
        BeanUtil.copyPropertiesWithIgnores(systemParamsDO, systemParamDomain);

        systemParamDomain.setParamType(SystemParamTypeEnum.getEnumByCode(systemParamsDO.getParamType()));
        systemParamDomain.setIsActive(ByteUtil.toBoolean(systemParamsDO.getIsActive()));
        return systemParamDomain;
    }

    public static List<SystemParamDomain> convert2Domains(List<SystemParamsDO> sources) {
        if (sources == null) {
            return null;
        }
        List<SystemParamDomain> targets = new ArrayList<>(sources.size());
        for (SystemParamsDO source : sources) {
            targets.add(convertToDomain(source));
        }
        return targets;
    }

    /**
     * 将systemParams列表转化为Map<key, value>对
     *
     * @param systemParams
     * @return
     */
    public static Map<String, String> convertToMap(List<SystemParamsDO> systemParams) {
        Map<String, String> paramMap = new HashMap<>();
        for (SystemParamsDO param : systemParams) {
            if (StringUtil.isEmpty(param.getParamName()) || StringUtil.isEmpty(param.getParamValue())) {
                logger.error("错误的系统设置: ["+ param +"]");
            }
            paramMap.put(param.getParamName(), param.getParamValue());
        }
        return paramMap;
    }
}
