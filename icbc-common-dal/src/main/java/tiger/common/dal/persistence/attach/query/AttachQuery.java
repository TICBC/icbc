package tiger.common.dal.persistence.attach.query;

import tiger.common.dal.persistence.BaseQuery;

import java.util.List;

/**
 * The Class AttachQuery.
 */
public class AttachQuery extends BaseQuery {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3586222894525254049L;

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
