/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * 消息业务类型枚举
 *
 * @author alfred.yx
 * @version v 0.1 Sep 24, 2015 4:49:19 PM alfred Exp $
 */
public enum MessageTypeEnum implements BaseEnum {

    SYSTEM("SYSTEM", "系统消息"),

    LOAN_NOTIFICATION("LOAN_NOTIFICATION", "贷款提醒"),

    BORROW_NOTIFICATION("BORROW_NOTIFICATION", "融资提醒"),

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

    /**
     * The code.
     */
    private String code;

    /**
     * The value.
     */
    private String value;

    /**
     * Instantiates a new message biz type enum.
     *
     * @param code  the code
     * @param value the value
     */
    MessageTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>code</code>获取枚举
     *
     * @param code
     * @return
     */
    public static MessageTypeEnum getEnumByCode(String code) {
        for (MessageTypeEnum bizType : MessageTypeEnum.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
            }
        }
        return null;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 检查参数是否足够
     *
     * @param paramNum
     * @return
     */
    public boolean checkParamNum(int paramNum){
        int paramNumNeeded = getParamNum(value);
        if(paramNum >= paramNumNeeded)
            return true;
        else
            return false;
    }

    int getParamNum(String str){
        int count = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '{')
                count++;
        }
        return count;
    }
}
