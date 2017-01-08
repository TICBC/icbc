/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tiger.common.dal.enums.GenderEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.BaseDomain;
import tiger.core.domain.workspace.AccountWorkspaceDomain;
import tiger.core.domain.workspace.WorkspaceDomain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * The Class AccountDomain.
 *
 * @author zhangbin
 * @version v0.1 2015/10/1 22:41
 */
public class AccountDomain extends BaseDomain {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -6803210431910905404L;

    /**
     * The user name.
     */
    private String userName;

    /**
     * The password.
     */
    @JsonIgnore
    private String password;

    /**
     * The gender.
     */
    @CopyIgnore
    private GenderEnum gender;

    /**
     * The birthday.
     */
    private Date birthday;


    /**
     * The mobile.
     */
    private String mobile;

    /**
     * The self-introduction
     */

    private String selfIntroduction;


    private String account;

    private String email;

    private Boolean isVerify;

    private String alipay;

    private String nickname;

    private String wechat;

    private String timezone;

    /**
     * 头像id
     */
    private Long iconId;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;


    /**
     * 用户额外参数
     */
    @CopyIgnore
    private HashMap<String, String> extParams;


    /**
     * 用户团队空间
     */
    @CopyIgnore
    private List<AccountWorkspaceDomain> accountWorkspaceDomains = new ArrayList<>();

    @CopyIgnore
    private List<WorkspaceDomain> workspaces;

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public GenderEnum getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    /**
     * Gets the birthday.
     *
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday.
     *
     * @param birthday the new birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * Gets the mobile.
     *
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile.
     *
     * @param mobile the new mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public HashMap<String, String> getExtParams() {
        return extParams;
    }

    public void setExtParams(HashMap<String, String> extParams) {
        this.extParams = extParams;
    }

    public List<AccountWorkspaceDomain> getAccountWorkspaceDomains() {
        return accountWorkspaceDomains;
    }

    public void setAccountWorkspaceDomains(List<AccountWorkspaceDomain> accountWorkspaceDomains) {
        this.accountWorkspaceDomains = accountWorkspaceDomains;
    }

    public List<WorkspaceDomain> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WorkspaceDomain> workspaces) {
        this.workspaces = workspaces;
    }
}
