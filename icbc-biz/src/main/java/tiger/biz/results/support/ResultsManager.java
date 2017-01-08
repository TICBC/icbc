/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.biz.results.support;

import tiger.core.basic.PageResult;
import tiger.core.domain.results.ResultsDomain;

import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:51]
 */
public interface ResultsManager {

    PageResult<List<ResultsDomain>> getAll();

    Boolean insert(ResultsDomain resultsDomain);

    Boolean delete(Long id);

    Boolean deleteAll();
}
