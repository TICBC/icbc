/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:43 AM yiliang.gyl Exp $
 */
public enum BizObjectTypeEnum implements BaseEnum{
    LOAN("LOAN", "贷款项目"),

    CUSTOMER("CUSTOMER","客户");

    private String code;

    private String value;


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


    /**
     * @param code
     * @param value
     */
    BizObjectTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static BizObjectTypeEnum getEnumByCode(String code) {
        for (BizObjectTypeEnum bt : BizObjectTypeEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }
}
