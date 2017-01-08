/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author alfred_yuan
 * @version v 0.1 19:11 alfred_yuan Exp $
 */
public enum AccountSettingTypeEnum implements BaseEnum {
    NOTIFICATION_PUSH("NOTIFICATION_PUSH", "消息推送"),
    PAY_BACK_NOTIFICATION("PAY_BACK_NOTIFICATION", "应收款提醒"),
    OVERDUE_NOTIFICATION("OVERDUE_NOTIFICATION", "逾期提醒"),
    ;

    private String code;
    private String value;

    AccountSettingTypeEnum(String code, String value) {
        setCode(code);
        setValue(value);
    }

    public static AccountSettingTypeEnum getEnumByCode(String code) {
        for (AccountSettingTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
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
