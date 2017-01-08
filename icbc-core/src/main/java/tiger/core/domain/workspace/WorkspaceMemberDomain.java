/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace;

import tiger.common.dal.enums.RoleEnum;
import tiger.core.domain.account.AccountDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 5:16 PM yiliang.gyl Exp $
 */
public class WorkspaceMemberDomain {

    private AccountDomain account;

    private List<RoleEnum> roles = new ArrayList<>();

    public AccountDomain getAccount() {
        return account;
    }

    public void setAccount(AccountDomain account) {
        this.account = account;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
}
