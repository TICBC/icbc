/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tiger.common.dal.enums.InvitationCodeTypeEnum;
import tiger.common.dal.persistence.account.InvitationCodeDO;
import tiger.common.dal.persistence.mapper.AccountMapper;
import tiger.common.dal.persistence.mapper.InvitationCodeMapper;
import tiger.common.util.ByteUtil;
import tiger.common.util.ShareCodeUtil;
import tiger.core.basic.constants.SystemConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.system.InvitationCodeDomain;
import tiger.core.domain.system.convert.InvitationCodeConvert;
import tiger.core.service.system.InvitationCodeService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午9:26 alfred.yx Exp $
 */
@Service
public class InvitationCodeServiceImpl implements InvitationCodeService {
    @Autowired
    InvitationCodeMapper invitationCodeMapper;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public InvitationCodeDomain readByCode(@NotNull String invitationCode) {
        return InvitationCodeConvert
                .convertToDomain(invitationCodeMapper.selectByCode(invitationCode));
    }

    @Override
    @Transactional
    public List<InvitationCodeDomain> generateInvitationCodes(long accountId, int number, InvitationCodeTypeEnum type) {
        if (null == type) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }
        List<InvitationCodeDO> invitationCodeDOList = new ArrayList<>(number);
        // 使用null获取到所有的邀请码
        List<String> existedCodes = invitationCodeMapper.getAllInvitationCodes(null);
        // 构造一个HashSet加快查找过程
        Set<String> existedCodeSet = new HashSet<>(existedCodes);

        String newCode;
        for (int i = 0; i < number; ++i) {
            // 生成新不存在的邀请码
            // TODO 生成新的邀请码策略问题
            do {
                newCode = ShareCodeUtil.toSerialCode(accountId, SystemConstants.INVITATION_CODE_LENGTH);
            } while (existedCodeSet.contains(newCode));
            invitationCodeDOList.add(new InvitationCodeDO(newCode, type.getCode(), ByteUtil.BYTE_ZERO, accountId));
            existedCodeSet.add(newCode);
        }
        if (invitationCodeMapper.batchInsert(invitationCodeDOList) > SystemConstants.SIZE_ZERO) {
            return InvitationCodeConvert.convertToDomains(invitationCodeDOList);
        }
        return null;
    }

    @Override
    public boolean isActive(String invitationCode) {
        InvitationCodeDO invitationCodeDO = invitationCodeMapper.selectByCode(invitationCode);
        if (null == invitationCodeDO || invitationCodeDO.getIsExpired().equals(ByteUtil.BYTE_ONE)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean useInvitationCode(long accountId, String invitationCode) {
        InvitationCodeDO invitationCodeDO = invitationCodeMapper.selectByCode(invitationCode);
        // 用户或者邀请码不存在
        if (null == accountMapper.selectByPrimaryKey(accountId) || null == invitationCodeDO) {
            return false;
        }
        // 邀请码已过期
        if (invitationCodeDO.getIsExpired().equals(ByteUtil.BYTE_ONE)) {
            return false;
        }
        // 设置邀请码过期，并绑定用户
        invitationCodeDO.setIsExpired(ByteUtil.BYTE_ONE);
        invitationCodeDO.setAccountId(accountId);
        // 返回邀请码使用情况
        return (invitationCodeMapper.updateByPrimaryKeySelective(invitationCodeDO) > SystemConstants.SIZE_ZERO);
    }

    @Override
    public int getUsedInvitationCodeNumber(Long accountId, InvitationCodeTypeEnum typeEnum) {
        if (null == accountId || null == typeEnum) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        return invitationCodeMapper.getUsedInvitationCodeNumber(accountId, typeEnum.getCode());
    }

    @Override
    public List<InvitationCodeDomain> getInvitationCodeList(Long accountId, InvitationCodeTypeEnum type) {
        if (null == accountId || null == type) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        return InvitationCodeConvert.convertToDomains(invitationCodeMapper.getInvitationCodeList(accountId, type.getCode()));
    }

    @Override
    public List<InvitationCodeDomain> getAllInvitationCodeList(Long accountId) {
        if (null == accountId) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        return InvitationCodeConvert.convertToDomains(invitationCodeMapper.getAllInvitationCodeList(accountId));
    }
}
