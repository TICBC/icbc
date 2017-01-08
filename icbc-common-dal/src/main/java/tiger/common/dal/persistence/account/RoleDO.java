package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;

/**
 * Created by HuPeng on 2015/9/1.
 */
public class RoleDO extends BaseDO {


    /**  */
    private static final long serialVersionUID = 3030454187234958384L;
    /**
     * The name.
     */
    private String name;

    private Integer order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
