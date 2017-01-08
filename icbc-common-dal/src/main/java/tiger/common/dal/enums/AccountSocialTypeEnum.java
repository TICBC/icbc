/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * 第三方账号类型枚举
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:38 PM yiliang.gyl Exp $
 */
public enum AccountSocialTypeEnum implements BaseEnum {

    WECHAT("WECHAT", "微信"),
    WECHATMOBILE("WECHATMOBILE", "WECHATMOBILE微信"),
    QQ("QQ", "qq平台"),
    QQMOBILE("QQMOBILE", "QQMOBILE平台");

    private String code;
    private String value;

    AccountSocialTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
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

    public static AccountSocialTypeEnum getEnumByCode(String code) {
        for (AccountSocialTypeEnum attachTypeEnum : AccountSocialTypeEnum.values()) {
            if (attachTypeEnum.getCode().equals(code)) {
                return attachTypeEnum;
            }
        }
        return null;
    }
}
