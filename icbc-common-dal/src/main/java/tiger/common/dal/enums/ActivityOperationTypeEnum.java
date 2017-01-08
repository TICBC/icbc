/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

import tiger.common.util.StringUtil;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:39 AM yiliang.gyl Exp $
 */
public enum ActivityOperationTypeEnum implements BaseEnum {

    LOAN_PAY("LOAN_PAY", "收了{0}笔款"),

    LOAN_CANCEL("LOAN_CANCEL", "撤销了最近一笔收款"),

    LOAN_CREATE("LOAN_CREATE", "录入了新的贷款合同"),

    LOAN_LAUNCH("LOAN_LAUNCH", "放款"),

    LOAN_TEMPORARY_DELETE("LOAN_TEMPORARY_DELETE", "暂时删除了贷款合同"),

    LOAN_PERMANENTLY_DELETE("LOAN_PERMANENTLY_DELETE", "永久删除了贷款合同"),

    LOAN_RECOVERY("LOAN_RECOVERY", "恢复了贷款合同"),

    LOAN_REFINE("LOAN_REFINE", "调整了贷款合同"),

    LOAN_TRANSFER("LOAN_TRANSFER", "移交了贷款合同"),

    ADD_MODIFICATION("ADD_MODIFICATION", "添加了一笔异常收支"),

    UPDATE_MODIFICATION("UPDATE_MODIFICATION", "更新了一笔异常收支"),

    DELETE_MODIFICATION("DELETE_MODIFICATION", "删除了一笔异常收支"),

    LOAN_FINISH("LOAN_FINISH", "结清了一笔贷款合同"),

    ADD_CUSTOMER("ADD_CUSTOMER", "新增了客户"),

    DELETE_CUSTOMER("DELETE_CUSTOMER", "删除了客户"),

    UPDATE_CUSTOMER("UPDATE_CUSTOMER", "更新了客户");

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
    ActivityOperationTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>name</code>获取枚举
     *
     * @param code
     * @return
     */
    public static ActivityOperationTypeEnum getEnumByCode(String code) {
        for (ActivityOperationTypeEnum bt : ActivityOperationTypeEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }

    /**
     * 检查参数是否足够
     *
     * @param paramNum
     * @return
     */
    public boolean checkParamNum(int paramNum) {
        return paramNum >= StringUtil.countMatches(value, "{");
    }
}
