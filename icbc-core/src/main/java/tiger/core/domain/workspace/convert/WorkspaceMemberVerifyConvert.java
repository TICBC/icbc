/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace.convert;

import tiger.common.dal.persistence.workspace.WorkspaceVerifyDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.workspace.WorkspaceMemberVerifyDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:27 PM yiliang.gyl Exp $
 */
public class WorkspaceMemberVerifyConvert {

    /**
     * Convert 2 domain
     *
     * @param source
     * @return
     */
    public static WorkspaceMemberVerifyDomain convert2Domain(WorkspaceVerifyDO source) {
        if (null == source) {
            return null;
        }
        WorkspaceMemberVerifyDomain target = new WorkspaceMemberVerifyDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        return target;
    }

    /**
     * Convert 2 domain.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<WorkspaceMemberVerifyDomain> convert2Domain(List<WorkspaceVerifyDO> sourceList) {
        List<WorkspaceMemberVerifyDomain> targetList = new ArrayList<>();
        for (WorkspaceVerifyDO source : sourceList) {
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
    public static WorkspaceVerifyDO convert2DO(WorkspaceMemberVerifyDomain source) {
        WorkspaceVerifyDO target = new WorkspaceVerifyDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        return target;
    }

    /**
     * Convert 2 domain.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<WorkspaceVerifyDO> convert2Dos(List<WorkspaceMemberVerifyDomain> sourceList) {
        List<WorkspaceVerifyDO> targetList = new ArrayList<>();
        for (WorkspaceMemberVerifyDomain source : sourceList) {
            targetList.add(convert2DO(source));
        }
        return targetList;
    }
}
