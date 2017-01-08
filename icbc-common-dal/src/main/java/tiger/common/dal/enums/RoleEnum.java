/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * Created by HuPeng on 2015/9/1.
 */
public enum RoleEnum implements BaseEnum {
    EPA("EPA", "环保局", 4),
    CUSTOMER("CUSTOMER", "普通员工", 3),
    ADMIN("ADMIN", "管理员", 2),
    OWNER("OWNER", "所有者", 1),
    ;

    private String code;
    private String value;
    private Integer order;

    RoleEnum(String code, String value, Integer order) {
        this.code = code;
        this.value = value;
        this.order = order;
    }

    /**
     * 通过枚举<code>code</code>获取枚举
     *
     * @param code
     * @return
     */
    public static RoleEnum getEnumByCode(String code) {
        for (RoleEnum bt : RoleEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
