package tiger.web.api.form.system;

import tiger.web.api.form.BaseForm;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 提交异常所需字段
 * Created by Jaric Liao on 2016/3/28.
 */
public class ExceptionForm extends BaseForm {

    private Long id;

    // 平台信息
    @NotNull(message = "请输入平台信息")
    private String platform;

    // 异常发生时间
    @NotNull(message = "请输入异常发生时间")
    private Date date;

    // 异常描述
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
