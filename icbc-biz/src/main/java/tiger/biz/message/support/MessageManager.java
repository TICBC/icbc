/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.biz.message.support;


import tiger.common.dal.persistence.message.query.MessageQuery;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.domain.message.MessageDomain;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 11:21 alfred_yuan Exp $
 */
public interface MessageManager {

    /**
     * 发送message到.receiverIDs
     *
     * @param receiverIDs the receiver i ds
     * @param message     the message
     * @return true, if successful
     */
    boolean sendMessages(List<Long> receiverIDs, MessageDomain message);

    /**
     * 发送多条message到多个receivers
     *
     * @param receiverIds
     * @param messages
     * @return
     */
    boolean sendMessages(List<Long> receiverIds, List<MessageDomain> messages);

    /**
     * 发送一条消息
     *
     * @param receiverID
     * @param message
     * @return
     */
    boolean sendMessage(Long receiverID, MessageDomain message);

    /**
     * 获取一条消息
     *
     * @param id
     * @return
     */
    BaseResult<MessageDomain> read(Long id);

    /**
     * 更新一条消息
     *
     * @param message
     * @return
     */
    BaseResult<Boolean> update(MessageDomain message);

    /**
     * 更新ids里的消息
     *
     * @param message
     * @param ids
     * @return
     */
    BaseResult<Boolean> updateAll(MessageDomain message, List<Long> ids);

    /**
     * 删除一条消息
     *
     * @param id
     * @return
     */
    BaseResult<Boolean> delete(Long id);

    /**
     * 列出一页消息
     *
     * @param query
     * @return
     */
    PageResult<List<MessageDomain>> listMessages(MessageQuery query);

    /**
     * 获取消息数量
     *
     * @param query
     * @return
     */
    BaseResult<Integer> countMessages(MessageQuery query);

    /**
     * 获取未读消息的ids
     *
     * @param accountId
     * @return
     */
    List<Long> getUnreadMessageIds(Long accountId);

    /**
     * 判断是否为messageId消息的接收者.
     *
     * @param messageId the message id
     * @param accountId the account id
     */
    void checkIsReceiver(Long messageId, Long accountId);

    /**
     * 判断是否为messageIds消息列表的接受者
     *
     * @param messageIds
     * @param accountId
     */
    void checkIsReceiver(List<Long> messageIds, Long accountId);
}
