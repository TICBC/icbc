/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author zhangbin
 * @version v0.1 2015/10/7 12:03
 */
public enum SmsVerifyCodeTypeEnum implements BaseEnum {
    REGISTER("register", "用户注册"),
    RESET_PASSWORD("resetPsd", "密码重置"),
    RESET_MOBILE("resetMobile", "修改手机号")
    ;

    private String code;
    private String value;

    SmsVerifyCodeTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static SmsVerifyCodeTypeEnum getEnumByCode(String code) {
        for (SmsVerifyCodeTypeEnum smsVerifyCodeType : SmsVerifyCodeTypeEnum.values()) {
            if (smsVerifyCodeType.getCode().equals(code)) {
                return smsVerifyCodeType;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
