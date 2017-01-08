package tiger.common.dal.persistence.system.query;

import tiger.common.dal.persistence.BaseQuery;

/**
 * Author: zhangbin
 * TIME: 15/12/27 上午10:36
 */
public class SystemParamsQuery extends BaseQuery {

    // 参数类型
    private String paramType;

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}
