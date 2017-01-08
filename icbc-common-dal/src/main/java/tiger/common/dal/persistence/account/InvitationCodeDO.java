package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;
import tiger.common.util.annotation.CopyIgnore;

public class InvitationCodeDO extends BaseDO {

    /**  */
    private static final long serialVersionUID = 5839592361412728261L;

    private String code;

    @CopyIgnore
    private String type;

    @CopyIgnore
    private Byte isExpired;

    private Long accountId;

    private Long inviterId;

    public InvitationCodeDO() {

    }

    public InvitationCodeDO(String code, String type, Byte isExpired, Long inviterId) {
        this.code = code;
        this.type = type;
        this.isExpired = isExpired;
        this.inviterId = inviterId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Byte getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Byte isExpired) {
        this.isExpired = isExpired;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }
}
