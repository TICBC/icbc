/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 ALL Rights Reserved
 */
package tiger.common.dal.enums;

/**
 * @author mi.li
 * @version v 0.1 2015年10月19日 23:37 mi.li Exp $
 */
public enum OrderStatusEnum implements BaseEnum {
    UNPAID("UNPAID", "待付款"),
    PAID("PAID", "已付款"),
    CLOSED("CLOSED", "订单关闭"),
    COMPLETED("COMPLETED", "订单完成");

    private String code;
    private String value;

    OrderStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static OrderStatusEnum getEnumByCode(String code) {
        for (OrderStatusEnum os : OrderStatusEnum.values()) {
            if (os.getCode().equals(code)) {
                return os;
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
