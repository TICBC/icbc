/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.workspace;

import javax.validation.constraints.NotNull;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:06 PM yiliang.gyl Exp $
 */
public class WorkspaceVerifyForm {

    @NotNull(message = "主体信息不能为空")
    private Long verifyId;

    @NotNull(message = "请选择是否同意")
    private Boolean isPermit;

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    public Boolean getIsPermit() {
        return isPermit;
    }

    public void setIsPermit(Boolean isPermit) {
        this.isPermit = isPermit;
    }
}
