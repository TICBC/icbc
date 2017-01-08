/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.system.convert;

import org.springframework.util.CollectionUtils;
import tiger.common.dal.enums.InvitationCodeTypeEnum;
import tiger.common.dal.persistence.account.InvitationCodeDO;
import tiger.common.util.BeanUtil;
import tiger.common.util.ByteUtil;
import tiger.core.domain.system.InvitationCodeDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午9:01 alfred.yx Exp $
 */
public class InvitationCodeConvert {
    private InvitationCodeConvert() {
    }

    ;

    public static InvitationCodeDO convertToDO(InvitationCodeDomain source) {
        if (null == source) {
            return null;
        }
        InvitationCodeDO target = new InvitationCodeDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        target.setIsExpired(ByteUtil.booleanToByte(source.getIsExpired()));
        if (null != source.getType()) {
            target.setType(source.getType().getCode());
        }
        return target;
    }

    public static InvitationCodeDomain convertToDomain(InvitationCodeDO source) {
        if (null == source) {
            return null;
        }
        InvitationCodeDomain target = new InvitationCodeDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (null != source.getIsExpired()) {
            target.setIsExpired(ByteUtil.toBoolean(source.getIsExpired()));
        }
        target.setType(InvitationCodeTypeEnum.getEnumByCode(source.getType()));
        return target;
    }

    public static List<InvitationCodeDomain> convertToDomains(List<InvitationCodeDO> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        }
        List<InvitationCodeDomain> targets = new ArrayList<>(sources.size());
        sources.forEach(invitationCodeDO -> targets.add(InvitationCodeConvert.convertToDomain(invitationCodeDO)));
        return targets;
    }

}

