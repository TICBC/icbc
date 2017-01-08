package tiger.common.dal.persistence.system;

import java.io.Serializable;
import java.util.Date;

public class SmsVerifyCodeDO implements Serializable {
    /**  */
    private static final long serialVersionUID = -1997952818356068185L;

    private Integer id;

    private String code;

    private String mobile;

    private Date createTime;

    private Date expireTime;

    private Boolean isActive;

    private String smsType;

    private Long accountId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Getter method for property <tt>smsType</tt>.
     *
     * @return property value of smsType
     */
    public String getSmsType() {
        return smsType;
    }

    /**
     * Setter method for property <tt>smsType</tt>.
     *
     * @param smsType value to be assigned to property smsType
     */
    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
