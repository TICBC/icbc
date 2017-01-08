package tiger.core.domain.account.convert;

import tiger.common.dal.enums.SystemEnum;
import tiger.common.dal.persistence.account.PermissionDO;
import tiger.core.domain.account.PermissionDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/10.
 */
public class PermissionConvert {

    public static PermissionDomain convert2Domain(PermissionDO source) {
        PermissionDomain target = new PermissionDomain();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setOperation(source.getOperation());
        target.setSystem(SystemEnum.getEnumByCode(source.getSystem()));
        return target;
    }

    public static List<PermissionDomain> convert2Domain(List<PermissionDO> sourceList) {
        List<PermissionDomain> targetList = new ArrayList<>();
        for (PermissionDO source : sourceList) {
            PermissionDomain target = convert2Domain(source);
            targetList.add(target);
        }
        return targetList;
    }
}
