/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace.convert;

import tiger.common.dal.enums.ActivityOperationTypeEnum;
import tiger.common.dal.enums.BizObjectTypeEnum;
import tiger.common.dal.persistence.workspace.ActivityDO;
import tiger.common.util.BeanUtil;
import tiger.common.util.JsonConverterUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.workspace.ActivityDomain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 个人动态转换
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 12:09 PM yiliang.gyl Exp $
 */
public class ActivityConvert {

    /**
     * Convert 2 domain
     *
     * @param source
     * @return
     */
    public static ActivityDomain convert2Domain(ActivityDO source) {
        if (null == source) {
            return null;
        }
        ActivityDomain target = new ActivityDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (StringUtil.isNotBlank(source.getObjectType())) {
            target.setObjectType(BizObjectTypeEnum.getEnumByCode(source.getObjectType()));
        }
        if (StringUtil.isNotBlank(source.getOperation())) {
            target.setOperation(ActivityOperationTypeEnum.getEnumByCode(source.getOperation()));
        }
        //extParams
        if(StringUtil.isNotBlank(source.getExtParams())){
            HashMap<String,String> extParams = JsonUtil.fromJson(source.getExtParams(), HashMap.class);
            // ~ 1. OperationParams
            target.setOperationParams(JsonConverterUtil.deserialize(extParams.get("OperationParams"),String[].class));
        }

        //OperationMessage
        if(null != target.getOperationParams())
            target.setOperationMessage(MessageFormat.format(target.getOperation().getValue(),target.getOperationParams()));
        else
            target.setOperationMessage(target.getOperation().getValue());
        return target;
    }

    /**
     * Convert 2 domain.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<ActivityDomain> convert2Domain(List<ActivityDO> sourceList) {
        List<ActivityDomain> targetList = new ArrayList<>();
        for (ActivityDO source : sourceList) {
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
    public static ActivityDO convert2DO(ActivityDomain source) {
        ActivityDO target = new ActivityDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (source.getObjectType() != null) {
            target.setObjectType(source.getObjectType().getCode());
        }
        if (source.getOperation() != null) {
            ActivityOperationTypeEnum operationTypeEnum = source.getOperation();
            //检查操作类型对应的参数是否足够
            int paramNum;
            if(null == source.getOperationParams())
                paramNum = 0;
            else
                paramNum = source.getOperationParams().length;
            if(!operationTypeEnum.checkParamNum(paramNum))
                throw new TigerException(ErrorCodeEnum.BIZ_FAIL,"操作类型所需参数不够");
            target.setOperation(operationTypeEnum.getCode());
        }
        //extParams
        //  ~ 1. OperationParams
        if(null != source.getOperationParams()){
            HashMap<String,String> extParams = new HashMap<>();
            extParams.put("OperationParams",JsonConverterUtil.serialize(source.getOperationParams()));
            target.setExtParams(JsonConverterUtil.serialize(extParams));
        }
        return target;
    }

    /**
     * Convert 2 do.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<ActivityDO> convert2Dos(List<ActivityDomain> sourceList) {
        List<ActivityDO> targetList = new ArrayList<>();
        for (ActivityDomain source : sourceList) {
            targetList.add(convert2DO(source));
        }
        return targetList;
    }
}
