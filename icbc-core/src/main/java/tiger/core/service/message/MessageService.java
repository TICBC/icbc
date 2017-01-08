/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.message;

import tiger.common.dal.persistence.message.query.MessageQuery;
import tiger.core.basic.PageResult;
import tiger.core.domain.message.MessageDomain;

import java.util.List;

/**
 * 站內消息服务.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 23, 2015 10:29:55 AM alfred Exp $
 */
public interface MessageService {

    /**
     * 根据id获取站内消息.
     *
     * @param id the id
     * @return the message by id
     */
    MessageDomain getMessageById(Long id);

    /**
     * 检查用户是否为该消息的接收者.
     *
     * @param messageIds the message id
     * @param accountId the account id
     * @return the boolean
     */
    boolean isReceiver(List<Long> messageIds, Long accountId);

    /**
     * 更新消息状态（ 是否已读 | 是否归档 | 是否删除 ）.
     *
     * @param messageDomain the message domain
     * @return the int
     */
    boolean updateMessage(MessageDomain messageDomain);

    /**
     * 更新消息状态（ 是否已读 | 是否归档 | 是否删除 ）.
     *
     * @param messageDomain
     * @param messageIds
     * @return
     */
    boolean updateMessages(MessageDomain messageDomain, List<Long> messageIds);
    /**
     * 删除站内消息.
     *
     * @param id
     * @return
     */
    boolean deleteMessageByID(Long id);

    /**
     * 分页列出站内消息.
     *
     * @param queryDomain the query domain
     * @return the list
     */
    PageResult<List<MessageDomain>> listMessages(MessageQuery queryDomain);

    /**
     * 发送消息到单个用户
     *
     * @param receiverID the receiver id
     * @param message    the message
     * @return true, if successful
     */
    boolean sendMessage(Long receiverID, MessageDomain message);

    /**
     * 发送消息到多个用户
     *
     * @param receiverIDs the receiver ids
     * @param message     the message
     * @return true, if successful
     */
    boolean sendMessages(List<Long> receiverIDs, MessageDomain message);

    /**
     * 发送多条消息
     *
     * @param messageDomains
     * @return
     */
    boolean sendMessages(List<MessageDomain> messageDomains);

    /**
     * 获取满足query的消息数目
     *
     * @param query
     * @return
     */
    int countMessages(MessageQuery query);

    /**
     * 获取当前用户所有未读消息ids
     *
     * @param accountId
     * @return
     */
    List<Long> getUnreadMessageIds(Long accountId);
}
