/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.message.convert;

import tiger.common.dal.enums.BizObjectTypeEnum;
import tiger.common.dal.enums.MessageTypeEnum;
import tiger.common.dal.persistence.message.MessageDO;
import tiger.common.util.BeanUtil;
import tiger.common.util.ByteUtil;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.message.MessageDomain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class MessageConvert.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 23, 2015 10:43:25 AM alfred Exp $
 */
public class MessageConvert {

    /**
     * Convert Domain to DO.
     *
     * @param messageDomain the message Domain
     * @return the message DO
     */
    public static MessageDO convertDomainToDO(MessageDomain messageDomain) {
        if (null == messageDomain) {
            return null;
        }
        MessageDO messageDO = new MessageDO();
        BeanUtil.copyPropertiesWithIgnores(messageDomain, messageDO);
        // 为messageDO设置type和bizType
        if (null != messageDomain.getType()) {
            messageDO.setType(messageDomain.getType().getCode());
        }
        if (null != messageDomain.getBizType()) {
            messageDO.setBizType(messageDomain.getBizType().getCode());
        }
        // 为MessageDO设置is系列的值
        if (null != messageDomain.getIsArchived()) {
            messageDO.setIsArchived(ByteUtil.booleanToByte(messageDomain.getIsArchived()));
        } else {
            messageDO.setIsArchived(ByteUtil.BYTE_ZERO);
        }
        if (null != messageDomain.getIsDeleted()) {
            messageDO.setIsDeleted(ByteUtil.booleanToByte(messageDomain.getIsDeleted()));
        } else {
            messageDO.setIsDeleted(ByteUtil.BYTE_ZERO);
        }
        if (null != messageDomain.getIsRead()) {
            messageDO.setIsRead(ByteUtil.booleanToByte(messageDomain.getIsRead()));
        } else {
            messageDO.setIsRead(ByteUtil.BYTE_ZERO);
        }

        //解析动态类message的title
        if(null != messageDomain.getType()){
            MessageTypeEnum messageTypeEnum = messageDomain.getType();
            int paramNum;
            if(null == messageDomain.getTypeParams())
                paramNum = 0;
            else
                paramNum = messageDomain.getTypeParams().length;
            if(!messageTypeEnum.checkParamNum(paramNum))
                throw new TigerException(ErrorCodeEnum.BIZ_FAIL,"操作类型所需参数不够");
            if(!messageDomain.getType().equals(MessageTypeEnum.LOAN_NOTIFICATION)) {
                if (null != messageDomain.getTypeParams())
                    messageDO.setTitle(MessageFormat.format(messageTypeEnum.getValue(), messageDomain.getTypeParams()));
                else
                    messageDO.setTitle(messageTypeEnum.getValue());
            }
        }

        return messageDO;
    }

    /**
     * Convert Domains To DOs.
     *
     * @param messageDomains
     * @return
     */
    public static List<MessageDO> convertDomainsToDOs(List<MessageDomain> messageDomains) {
        if (messageDomains.isEmpty()) {
            return null;
        }
        List<MessageDO> messageDOs = new ArrayList<>(messageDomains.size());
        messageDomains.forEach(messageDomain -> messageDOs.add(convertDomainToDO(messageDomain)));
        return messageDOs;
    }

    /**
     * Convert DOs to Domain.
     *
     * @param messageDO the message DO
     * @return the message Domain
     */
    public static MessageDomain convertDOToDomain(MessageDO messageDO) {
        if (null == messageDO) {
            return null;
        }
        MessageDomain messageDomain = new MessageDomain();
        BeanUtil.copyPropertiesWithIgnores(messageDO, messageDomain);
        // 为messageDomain设置type和bizType
        messageDomain.setType(MessageTypeEnum.getEnumByCode(messageDO.getType()));
        messageDomain.setBizType(BizObjectTypeEnum.getEnumByCode(messageDO.getBizType()));
        // 为messageDomain设置is系列的值
        if (null != messageDO.getIsArchived()) {
            messageDomain.setIsArchived(ByteUtil.toBoolean(messageDO.getIsArchived()));
        }
        if (null != messageDO.getIsDeleted()) {
            messageDomain.setIsDeleted(ByteUtil.toBoolean(messageDO.getIsDeleted()));
        }
        if (null != messageDO.getIsRead()) {
            messageDomain.setIsRead(ByteUtil.toBoolean(messageDO.getIsRead()));
        }
        return messageDomain;
    }


    /**
     * Convert DOs to Domains.
     *
     * @param messageDOs the message DOs
     * @return the list
     */
    public static List<MessageDomain> convertDOsToDomains(List<MessageDO> messageDOs) {
        if (messageDOs.isEmpty()) {
            return null;
        }
        List<MessageDomain> messageDomains = new ArrayList<>(messageDOs.size());
        messageDOs.forEach(messageDO -> messageDomains.add(MessageConvert.convertDOToDomain(messageDO)));
        return messageDomains;
    }
}
