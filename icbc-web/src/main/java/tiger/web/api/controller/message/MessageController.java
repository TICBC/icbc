/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.controller.message;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.message.support.MessageManager;
import tiger.biz.workspace.support.WorkspaceManager;
import tiger.common.dal.annotation.LoginRequired;
import tiger.common.dal.enums.MessageTypeEnum;
import tiger.common.dal.persistence.message.query.MessageQuery;
import tiger.core.basic.BaseResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountDomain;
import tiger.core.domain.message.MessageDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;
import tiger.web.api.form.message.MessageUpdateForm;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户消息接口
 *
 * @author alfred.yx
 * @version v 0.1 Sep 22, 2015 4:20:36 PM alfred Exp $
 */
@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/")
public class MessageController extends BaseController {

    private Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    private WorkspaceManager workspaceManager;

    @Autowired
    private MessageManager messageManager;

    /**
     * 根据id获取用户站内消息.
     *
     * @param id the id
     * @return the message by id
     */
    @RequestMapping(value = "message/{id}", method = RequestMethod.GET)
    @ResponseBody
    @LoginRequired
    public BaseResult<MessageDomain> getMessageById(@PathVariable("id") Long id) {
        messageManager.checkIsReceiver(id, currentAccount().getId());

        return messageManager.read(id);
    }

    /**
     * 更新用户站内消息状态.
     *
     * @param messageForm   the message form
     * @param bindingResult the binding result
     * @param id            the id
     * @return the base result
     */
    @RequestMapping(value = "message/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> updateMessageStatusById(@RequestBody @Valid MessageUpdateForm messageForm,
                                                       BindingResult bindingResult,
                                                       @PathVariable("id") long id) {
        messageManager.checkIsReceiver(id, currentAccount().getId());
        MessageDomain messageDomain = messageForm.convert2Domain();
        messageDomain.setId(id);

        return messageManager.update(messageDomain);
    }

    /**
     * 删除站内消息
     *
     * @param id the id
     * @return the base result
     */
    @RequestMapping(value = "message/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> deleteMessageById(@PathVariable("id") Long id) {
        messageManager.checkIsReceiver(id, currentAccount().getId());

        return messageManager.delete(id);
    }

    /**
     * 获取用户站内消息列表.
     *
     * @param query the query
     * @return the paginated messages
     */
    @RequestMapping(value = "messages", method = RequestMethod.GET, params = "operation=list")
    @ResponseBody
    @LoginRequired
    public BaseResult<List<MessageDomain>> getPaginatedMessages(@Valid MessageQuery query,
                                                                BindingResult bindingResult) {
        checkQueryValid(query);

        // 设置接收者
        query.setReceiverId(currentAccount().getId());

        return messageManager.listMessages(query);
    }

    /**
     * 获取消息数量
     *
     * @return
     */
    @RequestMapping(value = "messages", method = RequestMethod.GET, params = "operation=count")
    @ResponseBody
    @LoginRequired
    public BaseResult<Integer> countObjectBaseResultMessages(@Valid MessageQuery query,
                                                             BindingResult bindingResult) {
        checkQueryValid(query);

        // 设置接收者
        query.setReceiverId(currentAccount().getId());
        // 取消workspaceId
        query.setWorkspaceId(null);

        return messageManager.countMessages(query);
    }

    @RequestMapping(value = "messages", method = RequestMethod.PUT)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> updateMessageStatuses(@RequestBody @Valid MessageUpdateForm messageForm,
                                                     BindingResult bindingResult) {
        AccountDomain accountDomain = currentAccount();
        if (logger.isInfoEnabled()) {
            logger.info("用户 [" + accountDomain + "] 开始更新消息状态, 参数为 [" + messageForm + "]");
        }

        List<Long> messageIds = messageForm.getIds();
        //如果messagerIds为空则默认更改所有message的状态
        if (CollectionUtils.isEmpty(messageIds)) {
            //return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER, false);
            messageIds = messageManager.getUnreadMessageIds(currentAccount().getId());
            if (CollectionUtils.isEmpty(messageIds))
                return new BaseResult<>(true);
        } else {
            messageManager.checkIsReceiver(messageIds, accountDomain.getId());
        }
        MessageDomain messageDomain = messageForm.convert2Domain();

        return messageManager.updateAll(messageDomain, messageIds);
    }

    // ~ private methods
    private void checkQueryValid(MessageQuery query) {
        // 提前结束
        if (query == null) {
            return;
        }

        if (query.getBizType() != null && MessageTypeEnum.getEnumByCode(query.getBizType()) == null) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER, "所请求的业务类型不合法");
        }

        if (query.getWorkspaceId() != null) {
            workspaceManager.checkIsWorkspaceMember(currentAccount().getId(), query.getWorkspaceId());
        }
    }

}
