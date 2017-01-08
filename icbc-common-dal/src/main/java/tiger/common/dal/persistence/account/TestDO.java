package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;

/**
 * Created by zhao on 2016/4/18.
 */
public class TestDO extends BaseDO{

    private static final long serialVersionUID = 3030454187134948274L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
