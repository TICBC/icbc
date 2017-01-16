package tiger.core.service.socialnet;

import tiger.common.dal.persistence.socialnet.Socialnet;
import tiger.core.domain.socialnet.socialnetDomain;

import java.util.List;

public interface socialnetService{
    /**
     * 根据条件获取物料条目
     */
    List<socialnetDomain> getSome(String user1, String user2);
}