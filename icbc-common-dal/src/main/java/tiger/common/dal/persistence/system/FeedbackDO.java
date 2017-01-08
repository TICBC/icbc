package tiger.common.dal.persistence.system;


import tiger.common.dal.persistence.BaseDO;

public class FeedbackDO extends BaseDO {

    private static final long serialVersionUID = -4426397553129100388L;

    private Long accountId;

    private String title;

    private String email;

    private String mobile;

    private String content;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
