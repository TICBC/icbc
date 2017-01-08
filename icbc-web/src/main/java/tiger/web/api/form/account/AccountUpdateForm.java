/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.common.dal.enums.GenderEnum;
import tiger.common.util.BeanUtil;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.account.AccountDomain;
import tiger.web.api.form.FormInterface;

/**
 * @author zhangbin
 * @version v0.1 2015/9/19 15:17
 */
public class AccountUpdateForm implements FormInterface {

    /**
     * 头像URI
     */
    private String icon;

    /**
     * 用户性别
     */
    @CopyIgnore
    private String gender;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 自我介绍
     *
     */
    private String selfIntroduction;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    @Override
    public AccountDomain convert2Domain() {
        AccountDomain domain = new AccountDomain();
        BeanUtil.copyPropertiesWithIgnores(this, domain);
        domain.setGender(GenderEnum.getEnumByCode(this.gender));
        return domain;
    }

}
