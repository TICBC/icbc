/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.web.api.form.results;

import tiger.core.domain.results.ResultsDomain;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 12:01]
 */
public class ResultsAddForm implements FormInterface {

    @NotNull
    private Long mid;

    @NotNull
    private Integer count;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public ResultsDomain convert2Domain() {
        ResultsDomain resultsDomain = new ResultsDomain();

        resultsDomain.setMid(this.mid);
        resultsDomain.setCount(this.count);

        return resultsDomain;
    }
}
