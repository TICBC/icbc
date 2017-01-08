/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.system.support;


/**
 * @author zhangbin
 * @version v0.1 2015/10/6 1:53
 */
public interface SmsManager {


    /**
     * 发送短信到指定的号码
     *
     * @param mobile
     * @param type
     * @param accountId
     * @return
     */
    boolean sendVerifyCode(String mobile, String type, Long accountId);


}
