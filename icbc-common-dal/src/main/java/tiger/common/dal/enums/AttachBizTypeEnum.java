/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:22 AM yiliang.gyl Exp $
 */
public enum AttachBizTypeEnum implements BaseEnum {

    LOAN("LOAN", "贷款合同附件"),

    CUSTOMER("CUSTOMER", "客户附件"),

    LOAN_PAWN("LOAN_PAWN", "贷款合同抵押物附件"),

    FEEDBACK("FEEDBACK", "客户反馈附件"),

    REGION_QRCODE("REGION_QRCODE", "地区二维码"),

    DEFAULT_ICON("DEFAULT_ICON", "默认头像"),

    CHECK_IN("CHECK_IN", "打卡附件"),
    ;

    private String code;
    private String value;
    AttachBizTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static AttachBizTypeEnum getEnumByCode(String code) {
        for (AttachBizTypeEnum attachTypeEnum : AttachBizTypeEnum.values()) {
            if (attachTypeEnum.getCode().equals(code)) {
                return attachTypeEnum;
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
