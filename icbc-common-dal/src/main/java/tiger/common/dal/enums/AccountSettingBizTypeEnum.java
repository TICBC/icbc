/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author alfred_yuan
 * @version v 0.1 19:06 alfred_yuan Exp $
 */
public enum AccountSettingBizTypeEnum implements BaseEnum {
    ACCOUNT("ACCOUNT", "账户"),
    ;

    private String code;
    private String value;

    AccountSettingBizTypeEnum(String code, String value) {
        setCode(code);
        setValue(value);
    }

    public static AccountSettingBizTypeEnum getEnumByCode(String code) {
        for (AccountSettingBizTypeEnum bizTypeEnum : AccountSettingBizTypeEnum.values()) {
            if (bizTypeEnum.getCode().equals(code)) {
                return  bizTypeEnum;
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
