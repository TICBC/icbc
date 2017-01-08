/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.service.results;

import tiger.common.dal.persistence.results.Results;
import tiger.core.domain.results.ResultsDomain;

import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:43]
 */
public interface ResultsService {
    /**
     * 获取所有的结果条目
     *
     * @return
     */
    List<ResultsDomain> getAll();

    /**
     * 插入一条结果
     *
     * @param results
     * @return
     */
    Boolean insert(Results results);

    /**
     * 删除一条结果
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 删除所有结果
     *
     * @return
     */
    Boolean deleteAll();
}
