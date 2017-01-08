/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.message;

import tiger.common.util.StringUtil;
import tiger.core.basic.enums.ErrorCodeEnum;

import java.io.Serializable;

/**
 * 参数 check 的返回消息
 * ~ 一般 check 过后返回一组 list 该对象
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 23, 2015 9:35:31 AM yiliang.gyl Exp $
 */
public class CheckMessageDomain implements Serializable {

    /**  */
    private static final long serialVersionUID = 195700421741113017L;

    private String paramKey;

    private String paramName;

    private String message;

    public CheckMessageDomain(String paramKey, String paramName) {
        this.paramKey = paramKey;
        this.paramName = paramName;
        this.message = ErrorCodeEnum.PARAMETERS_IS_NULL.getMessage(paramName);
    }

    public CheckMessageDomain(String paramKey, String paramName, ErrorCodeEnum errorCodeEnum) {
        this.paramKey = paramKey;
        this.paramName = paramName;
        String message = errorCodeEnum.getMessage(paramName);
        if (StringUtil.isBlank(message)) {
            message = errorCodeEnum.getDefaultMessage();
        }
        this.message = message;
    }

    /**
     * Getter method for property <tt>paramName</tt>.
     *
     * @return property value of paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Setter method for property <tt>paramName</tt>.
     *
     * @param paramName value to be assigned to property paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property <tt>paramKey</tt>.
     *
     * @return property value of paramKey
     */
    public String getParamKey() {
        return paramKey;
    }

    /**
     * Setter method for property <tt>paramKey</tt>.
     *
     * @param paramKey value to be assigned to property paramKey
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

}
