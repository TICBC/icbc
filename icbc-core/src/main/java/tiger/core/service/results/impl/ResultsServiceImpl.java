/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.service.results.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.mapper.MaterialsMapper;
import tiger.common.dal.persistence.mapper.ResultsMapper;
import tiger.common.dal.persistence.materials.Materials;
import tiger.common.dal.persistence.results.Results;
import tiger.common.dal.persistence.results.ResultsExample;
import tiger.core.domain.results.ResultsDomain;
import tiger.core.domain.results.convert.ResultsConvert;
import tiger.core.service.results.ResultsService;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:46]
 */
@Service
public class ResultsServiceImpl implements ResultsService {
    @Autowired
    ResultsMapper resultsMapper;

    @Autowired
    MaterialsMapper materialsMapper;

    @Override
    public List<ResultsDomain> getAll() {
        List<Results> DOs = resultsMapper.selectByExample(new ResultsExample());
        List<ResultsDomain> domains = ResultsConvert.convert2Domains(DOs);
        if (domains == null) {
            return null;
        }
        Iterator<ResultsDomain> iterator = domains.iterator();

        while (iterator.hasNext()) {
            ResultsDomain resultsDomain = iterator.next();
            Materials temp = materialsMapper.selectByPrimaryKey(resultsDomain.getMid());
            resultsDomain.setRemarks(temp.getRemarks());
            resultsDomain.setName(temp.getName());
            resultsDomain.setDescription(temp.getDescription());
            resultsDomain.setDiscountprice(temp.getDiscount() * temp.getMarketprice());
        }

        return domains;
    }

    @Override
    public Boolean insert(Results results) {
        return resultsMapper.insert(results) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return resultsMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean deleteAll() {
        return resultsMapper.deleteByExample(new ResultsExample()) > 0;
    }
}
