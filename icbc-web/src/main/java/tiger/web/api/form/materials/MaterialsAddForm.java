/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.web.api.form.materials;

import tiger.common.util.BeanUtil;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/8 08:33]
 */
public class MaterialsAddForm implements FormInterface {

    @NotNull(message = "物料编码不能为空")
    private String code;

    private String name;

    @NotNull(message = "物料描述不能为空")
    private String description;

    @NotNull(message = "大类不能为空")
    private String majorcategory;

    @NotNull(message = "中类不能为空")
    private String subcategory;

    @NotNull(message = "明细类不能为空")
    private String detailclass;

    @NotNull(message = "生产公司不能为空")
    private String company;

    @NotNull(message = "产品线不能为空")
    private String productline;

    @NotNull(message = "面价不能为空")
    private Double marketprice;

    @NotNull(message = "折扣不能为空")
    private Double discount;

    private String remarks;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMajorcategory() {
        return majorcategory;
    }

    public void setMajorcategory(String majorcategory) {
        this.majorcategory = majorcategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getDetailclass() {
        return detailclass;
    }

    public void setDetailclass(String detailclass) {
        this.detailclass = detailclass;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProductline() {
        return productline;
    }

    public void setProductline(String productline) {
        this.productline = productline;
    }

    public Double getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(Double marketprice) {
        this.marketprice = marketprice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public MaterialsDomain convert2Domain() {
        MaterialsDomain target = new MaterialsDomain();
        BeanUtil.copyPropertiesWithIgnores(this, target);

        return target;
    }
}
