package tiger.web.api;

import org.apache.shiro.authc.AuthenticationToken;
import tiger.core.domain.account.AccountDomain;

public class StatelessToken implements AuthenticationToken {

    /**  */
    private static final long serialVersionUID = -8210358249576302789L;
    private AccountDomain account;
    private String accessToken;

    public StatelessToken() {

    }

    public StatelessToken(AccountDomain account, String accessToken) {
        this.accessToken = accessToken;
        this.account = account;
    }

    @Override
    public Object getPrincipal() {
        return account;
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(account.getUserName());
        sb.append(" - ");
        sb.append(accessToken);
        return sb.toString();
    }

}
