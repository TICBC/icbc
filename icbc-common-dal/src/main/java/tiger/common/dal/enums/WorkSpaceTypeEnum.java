/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:24 AM yiliang.gyl Exp $
 */
public enum WorkSpaceTypeEnum implements BaseEnum {

    PERSONAL("PERSONAL", "个人"),

    TEAM("TEAM", "团队");

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
    WorkSpaceTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static WorkSpaceTypeEnum getEnumByCode(String code) {
        for (WorkSpaceTypeEnum bt : WorkSpaceTypeEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }
}
