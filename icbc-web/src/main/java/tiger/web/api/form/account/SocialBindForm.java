/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import javax.validation.constraints.NotNull;

/**
 * 利用账号和密码绑定社交账户
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 5:04 PM yiliang.gyl Exp $
 */
public class SocialBindForm {

    @NotNull(message = "是否绑定不能为空")
    private Boolean bindOrNot;

    private String username;

    private String password;

    @NotNull(message = "绑定号不能为空")
    private Long bindId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBindId() {
        return bindId;
    }

    public void setBindId(Long bindId) {
        this.bindId = bindId;
    }

    public Boolean getBindOrNot() {
        return bindOrNot;
    }

    public void setBindOrNot(Boolean bindOrNot) {
        this.bindOrNot = bindOrNot;
    }
}
