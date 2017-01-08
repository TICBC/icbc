/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.system.convert;

import tiger.common.dal.persistence.system.FeedbackDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.system.FeedbackDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 16:14 alfred_yuan Exp $
 */
public class FeedbackConvert {

    private FeedbackConvert() {}

    public static FeedbackDomain convert2Domain(FeedbackDO source) {
        if (source == null) {
            return null;
        }
        FeedbackDomain target = new FeedbackDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        return  target;
    }

    public static List<FeedbackDomain> convert2Domains(List<FeedbackDO> sources) {
        if (sources == null) {
            return null;
        }
        List<FeedbackDomain> targets = new ArrayList<>(sources.size());
        sources.forEach(source -> targets.add(convert2Domain(source)));
        return targets;
    }

    public static FeedbackDO convert2DO(FeedbackDomain source) {
        if (source == null) {
            return null;
        }
        FeedbackDO target = new FeedbackDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        return target;
    }

}
