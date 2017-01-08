/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * 业务类型
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 19, 2015 12:09:40 PM yiliang.gyl Exp $
 */
public enum BusinessTypeEnum implements BaseEnum {

    LOAN("LOAN", "贷款"),

    BORROW("BORROW", "融资");

    private String code;

    private String value;

    /**
     *
     */
    BusinessTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static BusinessTypeEnum getEnumByCode(String code) {
        for (BusinessTypeEnum bt : BusinessTypeEnum.values()) {
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
