/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.web.api.form.workspace;

import tiger.core.domain.workspace.WorkspaceDomain;
import tiger.web.api.form.BaseForm;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author alfred_yuan
 * @version v 0.1 15:52 alfred_yuan Exp $
 */
public class WorkspaceForm extends BaseForm implements FormInterface {

    @Size(min = 1, max = 32, message = "团队工作空间名称长度应至少为1, 且小于32")
    private String name;

    private String description;

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

    @Override
    public WorkspaceDomain convert2Domain() {
        WorkspaceDomain workspaceDomain = new WorkspaceDomain();

        workspaceDomain.setName(name);
        workspaceDomain.setDescription(description);

        return workspaceDomain;
    }
}
