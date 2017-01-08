/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace.convert;

import tiger.common.dal.enums.WorkSpaceTypeEnum;
import tiger.common.dal.persistence.workspace.WorkspaceDO;
import tiger.common.util.BeanUtil;
import tiger.common.util.JsonConverterUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.domain.workspace.WorkspaceDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 团队空间转换
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 12:09 PM yiliang.gyl Exp $
 */
public class WorkspaceConvert {

    /**
     * Convert 2 domain
     *
     * @param source
     * @return
     */
    public static WorkspaceDomain convert2Domain(WorkspaceDO source) {
        if (null == source) {
            return null;
        }
        WorkspaceDomain target = new WorkspaceDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (StringUtil.isNotBlank(source.getType())) {
            target.setType(WorkSpaceTypeEnum.getEnumByCode(source.getType()));
        }
        if (StringUtil.isNotBlank(source.getExtParams())) {
            target.setExtParams(JsonUtil.fromJson(source.getExtParams(), HashMap.class));
        }
        return target;
    }

    /**
     * Convert 2 domain.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<WorkspaceDomain> convert2Domains(List<WorkspaceDO> sourceList) {
        List<WorkspaceDomain> targetList = new ArrayList<>();
        for (WorkspaceDO source : sourceList) {
            targetList.add(convert2Domain(source));
        }
        return targetList;
    }

    /**
     * Convert 2 do.
     *
     * @param source the source
     * @return the customer do
     */
    public static WorkspaceDO convert2DO(WorkspaceDomain source) {
        WorkspaceDO target = new WorkspaceDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (source.getType() != null) {
            target.setType(source.getType().getCode());
        }
        if (null != source.getExtParams()) {
            target.setExtParams(JsonConverterUtil.serialize(source.getExtParams()));
        }
        return target;
    }

    /**
     * Convert 2 domain.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<WorkspaceDO> convert2Dos(List<WorkspaceDomain> sourceList) {
        List<WorkspaceDO> targetList = new ArrayList<>();
        for (WorkspaceDomain source : sourceList) {
            targetList.add(convert2DO(source));
        }
        return targetList;
    }
}
