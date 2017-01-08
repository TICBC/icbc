/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.message;

import tiger.common.util.BeanUtil;
import tiger.core.domain.message.MessageDomain;
import tiger.web.api.form.BaseForm;
import tiger.web.api.form.FormInterface;

import java.util.List;

/**
 * 站内消息更新表单.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 23, 2015 9:40:52 AM alfred Exp $
 */
public class MessageUpdateForm extends BaseForm implements FormInterface {


    /**
     * 消息id列表
     */
    private List<Long> ids;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 是否归档
     */
    private Boolean isArchived;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    /**
     * Gets the checks if is read.
     *
     * @return the checks if is read
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * Sets the checks if is read.
     *
     * @param isRead the new checks if is read
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    /**
     * Gets the checks if is archived.
     *
     * @return the checks if is archived
     */
    public Boolean getIsArchived() {
        return isArchived;
    }

    /**
     * Sets the checks if is archived.
     *
     * @param isArchived the new checks if is archived
     */
    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    /**
     * @see FormInterface#convert2Domain()
     */
    @Override
    public MessageDomain convert2Domain() {
        MessageDomain target = new MessageDomain();
        BeanUtil.copyPropertiesWithIgnores(this, target);

        return target;
    }

}
