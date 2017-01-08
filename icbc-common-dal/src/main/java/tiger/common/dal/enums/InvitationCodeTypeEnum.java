/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

import tiger.common.util.StringUtil;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午1:33 alfred.yx Exp $
 */
public enum InvitationCodeTypeEnum implements BaseEnum {
    REGISTER("REGISTER", "注册邀请码");

    private static final String paramSuffix = "_INVITATION_NUMBER";


    private String code;
    private String value;
    private String paramName;

    /**
     * 构造器
     *
     * @param code
     * @param value
     */
    private InvitationCodeTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
        this.paramName = StringUtil.capitalize(code) + paramSuffix;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static InvitationCodeTypeEnum getEnumByCode(String code) {
        for (InvitationCodeTypeEnum payWayEnum : InvitationCodeTypeEnum.values()) {
            if (payWayEnum.getCode().equals(code)) {
                return payWayEnum;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getParamName() {
        return paramName;
    }
}
