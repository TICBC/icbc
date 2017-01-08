package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;
import tiger.common.util.annotation.CopyIgnore;

/**
 * Created by Kris Chan on 4:09 PM 3/27/16 .
 * All right reserved.
 */
public class AccountBaseDO extends BaseDO {

    /**
     * The user name.
     */
    private String userName;

    /**
     * The ext params.
     */
    @CopyIgnore
    private String extParams;

    public String getExtParams() {
        return extParams;
    }

    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
/**
 * 测试推送
 * */
