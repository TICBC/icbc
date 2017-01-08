/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.web.api.controller.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.results.support.ResultsManager;
import tiger.common.dal.annotation.LoginRequired;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.domain.results.ResultsDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;
import tiger.web.api.form.results.ResultsAddForm;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:58]
 */
@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/results")
public class ResultsController extends BaseController {

    @Autowired
    ResultsManager resultsManager;

    /**
     * 获取所有的结果信息
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @LoginRequired
    public PageResult<List<ResultsDomain>> getAll() {

        return resultsManager.getAll();
    }

    /**
     * 删除所有的结果信息
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> deleteAll() {

        return new BaseResult<>(resultsManager.deleteAll());
    }

    /**
     * 新增一条物料信息
     *
     * @param resultsAddForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> insertResults(@RequestBody @Valid ResultsAddForm resultsAddForm,
                                               BindingResult bindingResult) {

        ResultsDomain resultsDomain = resultsAddForm.convert2Domain();
        return new BaseResult<>(resultsManager.insert(resultsDomain));
    }

    /**
     * 删除一条物料信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> deleteResults(@RequestParam("id") Long id) {

        return new BaseResult<>(resultsManager.delete(id));
    }

}

