package tiger.core.service.Rules.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tiger.common.dal.persistence.icbc.RulesDO;
import tiger.common.dal.persistence.mapper.RulesDOMapper;
import tiger.core.service.Rules.RulseService;

/**
 * Created by CJ on 2017/4/13.
 */

@Service
public class RulesServiceImpl implements RulseService{

    @Autowired
    RulesDOMapper rulesDOMapper;
    public RulesDO selectByPrimaryKey(Integer id){
        RulesDO r = rulesDOMapper.selectByPrimaryKey(id);
        return r;
    }
}
