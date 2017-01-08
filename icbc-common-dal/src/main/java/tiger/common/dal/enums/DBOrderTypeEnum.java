/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author alfred_yuan
 * @version v 0.1 15:34 alfred_yuan Exp $
 */
public enum DBOrderTypeEnum implements BaseEnum {
    ASC("asc", "顺序排序"),
    DESC("desc", "逆序排序");

    private String code;
    private String value;

    DBOrderTypeEnum(String code, String value) {
        setCode(code);
        setValue(value);
    }

    public static DBOrderTypeEnum getEnumByCode(String code) {
        for (DBOrderTypeEnum typeEnum : DBOrderTypeEnum.values()) {
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
