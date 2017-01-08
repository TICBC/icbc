/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.biz.results.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.results.support.ResultsManager;
import tiger.core.basic.PageResult;
import tiger.core.domain.results.ResultsDomain;
import tiger.core.domain.results.convert.ResultsConvert;
import tiger.core.service.results.ResultsService;

import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:54]
 */
@Service
public class ResultsManagerImpl implements ResultsManager {

    @Autowired
    ResultsService resultsService;

    @Override
    public PageResult<List<ResultsDomain>> getAll() {
        return new PageResult<>(resultsService.getAll());
    }

    @Override
    public Boolean insert(ResultsDomain resultsDomain) {
        if (resultsDomain == null) {
            return false;
        }

        return resultsService.insert(ResultsConvert.convert2DO(resultsDomain));
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        return resultsService.delete(id);
    }

    @Override
    public Boolean deleteAll() {
        return resultsService.deleteAll();
    }
}
