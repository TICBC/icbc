package tiger.web.api.form.socialnet;

import tiger.common.util.BeanUtil;
import tiger.core.domain.socialnet.socialnetDomain;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

public class socialnetForm implements FormInterface{
    @NotNull(message = "付款方ID不能为空")
    private String user1;

    @NotNull(message = "收款方ID不能为空")
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

    @Override
    public socialnetDomain convert2Domain(){
        socialnetDomain target = new socialnetDomain();
        BeanUtil.copyPropertiesWithIgnores(this,target);

        return target;
    }

}