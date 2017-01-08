package tiger.core.service.system;

import tiger.common.dal.enums.InvitationCodeTypeEnum;
import tiger.core.domain.system.InvitationCodeDomain;

import java.util.List;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午8:08 alfred.yx Exp $
 */
public interface InvitationCodeService {
    /**
     * 根据id获取邀请码
     *
     * @param invitationCode
     * @return
     */
    InvitationCodeDomain readByCode(String invitationCode);

    /**
     * 为账户accoutnId生成number个类型为type的邀请码
     *
     * @param accountId
     * @param number
     * @param type
     * @return
     */
    List<InvitationCodeDomain> generateInvitationCodes(long accountId, int number, InvitationCodeTypeEnum type);

    /**
     * 判断邀请码是否可用
     *
     * @param invitationCode
     * @return
     */
    boolean isActive(String invitationCode);

    /**
     * 为用户accountId使用codeId
     *
     * @param accountId
     * @param invitationCode
     * @return
     */
    boolean useInvitationCode(long accountId, String invitationCode);

    /**
     * 查询用户已用邀请码数量
     *
     * @param accountId
     * @param codeTypeEnum
     * @return
     */
    int getUsedInvitationCodeNumber(Long accountId, InvitationCodeTypeEnum codeTypeEnum);

    /**
     * 获取用户type类型邀请码列表
     *
     * @param accountId
     * @param type
     * @return
     */
    List<InvitationCodeDomain> getInvitationCodeList(Long accountId, InvitationCodeTypeEnum type);

    /**
     * 获取用户所有类型邀请码列表
     *
     * @param accountId
     * @return
     */
    List<InvitationCodeDomain> getAllInvitationCodeList(Long accountId);
}
