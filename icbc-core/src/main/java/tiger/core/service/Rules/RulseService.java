package tiger.core.service.Rules;

import tiger.common.dal.persistence.icbc.RulesDO;

/**
 * Created by CJ on 2017/4/13.
 */
public interface RulseService {
    RulesDO selectByPrimaryKey(Integer id);
}
