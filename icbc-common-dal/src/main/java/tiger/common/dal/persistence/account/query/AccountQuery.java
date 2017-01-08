/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.persistence.account.query;

import tiger.common.dal.persistence.BaseQuery;

import java.util.List;

/**
 * @author HuPeng
 * @version v 0.1 2015年10月23日 下午3:35:47 HuPeng Exp $
 */
public class AccountQuery extends BaseQuery {

    /**  */
    private static final long serialVersionUID = -7896186273493623036L;

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

}
