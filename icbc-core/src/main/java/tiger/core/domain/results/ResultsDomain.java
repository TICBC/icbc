/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.domain.results;

import tiger.core.domain.BaseDomain;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:31]
 */
public class ResultsDomain extends BaseDomain {

    /**
     * 物料对象id
     */
    private Long mid;

    /**
     * 物料数量
     */
    private Integer count;

    /**
     * 物料名称
     */
    private String name;

    /**
     * 物料描述
     */
    private String description;

    /**
     * 折扣价
     */
    private double discountprice;

    /**
     * 备注
     */
    private String remarks;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(double discountprice) {
        this.discountprice = discountprice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
