package tiger.core.domain.socialnet;

import tiger.common.util.StringUtil;
import tiger.core.domain.BaseDomain;
import tiger.core.basic.constants.SystemConstants;

import java.util.Date;

/**
 * 统计trust
 */
public class socialnetDomain extends BaseDomain{
    private String user1;
    private String user2;
    private String time;
    private String money;
    private String trust;
    private String pass;

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTrust() {
        return trust;
    }

    public void setTrust(String trust) {
        this.trust = trust;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}