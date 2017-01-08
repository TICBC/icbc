/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * 系统参数类型
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 12, 2015 5:05:54 PM yiliang.gyl Exp $
 */
public enum SystemParamTypeEnum implements BaseEnum {

    DEFAULT("DEFAULT", "系统默认配置"),
    SYSTEM_CONFIG("SYSTEM_CONFIG", "系统基础配置"),
    MESSAGE("MESSAGE", "消息系统参数"),
    QINIU_SECRET_PARAM("QINIU_SECRET_PARAM", "七牛云私密附件参数"),
    QINIU_PUBLIC_PARAM("QINIU_PUBLIC_PARAM", "七牛云公开附件参数"),
    LOAN_CONFIG("LOAN_CONFIG", "贷款系统默认设置"),
    LOAN_SMS_OPTION("LOAN_SMS_OPTION", "贷款短息系统功能开关"),

    SOCIAL_CONFIG("SOCIAL_CONFIG", "社交功能配置"),
    SOCIAL_ACCOUNT_TOKEN("SOCIAL_ACCOUNT_SECRET", "社交账户SECRET"),
    SOCIAL_ACCOUNT_APP_ID("SOCIAL_ACCOUNT_APP_ID", "社交账户APP_ID"),
    SOCIAL_ACCOUNT_AUTH_URL("SOCIAL_ACCOUNT_AUTH_URL", "社交账户回调地址"),
    ACCOUNT_SMS_OPTION("ACCOUNT_SMS_OPTION", "账户短信限制参数"),


    //workspace参数配置
    WORKSPACE_OWNER_MAXIMUM("WORKSPACE_OWNER_MAXIMUM","个人创建团队空间最大限额")

    ;

    private String code;
    private String value;

    /**
     * @param code
     * @param value
     */
    SystemParamTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static SystemParamTypeEnum getEnumByCode(String code) {
        for (SystemParamTypeEnum bt : SystemParamTypeEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
