/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.biz.message.support.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.message.support.MessageManager;
import tiger.common.dal.persistence.message.query.MessageQuery;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.basic.constants.SystemConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.message.MessageDomain;
import tiger.core.service.message.MessageService;


import java.util.ArrayList;
import java.util.List;

/**
 * The Message manager.
 *
 * @author alfred_yuan
 * @version v 0.1 11:32 alfred_yuan Exp $
 */
@Service
public class MessageManagerImpl implements MessageManager {

    private final Logger logger = Logger.getLogger(MessageManagerImpl.class);

    @Autowired
    private MessageService messageService;

    /**
     * @see MessageManager#sendMessages(List, MessageDomain)
     */
    @Override
    public boolean sendMessages(List<Long> receiverIDs, MessageDomain message) {
        if (CollectionUtils.isEmpty(receiverIDs) || message == null) {
            return false;
        }

        return messageService.sendMessages(receiverIDs, message);
    }

    /**
     * @see MessageManager#sendMessages(List, List)
     */
    @Override
    public boolean sendMessages(List<Long> receiverIds, List<MessageDomain> messages) {
        if (CollectionUtils.isEmpty(receiverIds) ||CollectionUtils.isEmpty(messages)) {
            return false;
        }
        List<MessageDomain> messageDomains = new ArrayList<>();
        //组装messageDomains
        try {
            for (Long receiverId : receiverIds) {
                for (MessageDomain message : messages) {
                    MessageDomain messageDomain = new MessageDomain();
                    BeanUtils.copyProperties(messageDomain, message);
                    messageDomain.setReceiverId(receiverId);
                    messageDomains.add(messageDomain);
                }
            }
        }catch(Exception e){
            logger.error("发送多条messages + [" + messages + "]给 [" + receiverIds + "] 失败");
        }
        return messageService.sendMessages(messageDomains);
    }

    /**
     * @see MessageManager#sendMessage(Long, MessageDomain)
     */
    @Override
    public boolean sendMessage(Long receiverID, MessageDomain message) {
        if (receiverID == null || message == null) {
            return false;
        }

        return messageService.sendMessage(receiverID, message);
    }

    /**
     * @see MessageManager#read(Long)
     */
    @Override
    public BaseResult<MessageDomain> read(Long id) {
        MessageDomain message = messageService.getMessageById(id);

        if (message == null) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND, "不存在的消息");
        }

        return new BaseResult<>(message);
    }

    /**
     * @see MessageManager#update(MessageDomain)
     */
    @Override
    public BaseResult<Boolean> update(MessageDomain message) {
        if (message == null || message.getId() == null) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }

        return new BaseResult<>(messageService.updateMessage(message));
    }

    /**
     * @see MessageManager#updateAll(MessageDomain, List)
     */
    @Override
    public BaseResult<Boolean> updateAll(MessageDomain message, List<Long> ids) {
        if (message == null || CollectionUtils.isEmpty(ids)) {
            return new BaseResult<>(true);
        }

        return new BaseResult<>(messageService.updateMessages(message, ids));
    }

    /**
     * @see MessageManager#delete(Long)
     */
    @Override
    public BaseResult<Boolean> delete(Long id) {
        if (id == null) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }

        return new BaseResult<>(messageService.deleteMessageByID(id));
    }

    /**
     * @see MessageManager#listMessages(MessageQuery)
     */
    @Override
    public PageResult<List<MessageDomain>> listMessages(MessageQuery query) {
        checkQuery(query);

        return messageService.listMessages(query);
    }

    /**
     * @see MessageManager#countMessages(MessageQuery)
     */
    @Override
    public BaseResult<Integer> countMessages(MessageQuery query) {
        checkQuery(query);

        return new BaseResult<>(messageService.countMessages(query));
    }

    /**
     * @see MessageManager#getUnreadMessageIds(Long)
     */
    @Override
    public List<Long> getUnreadMessageIds(Long accountId) {
        if (accountId == null) {
            return new ArrayList<>();
        }

        return messageService.getUnreadMessageIds(accountId);
    }

    /**
     * @see MessageManager#checkIsReceiver(Long, Long)
     */
    @Override
    public void checkIsReceiver(Long messageId, Long accountId) {
        List<Long> messageIds = new ArrayList<>(SystemConstants.SIZE_ONE);
        messageIds.add(messageId);

        checkIsReceiver(messageIds, accountId);
    }

    /**
     * @see MessageManager#checkIsReceiver(List, Long)
     */
    @Override
    public void checkIsReceiver(List<Long> messageIds, Long accountId) {
        if (CollectionUtils.isEmpty(messageIds) || accountId == null) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }

        if (!messageService.isReceiver(messageIds, accountId)) {
            throw new TigerException(ErrorCodeEnum.UNAUTHORIZED);
        }
    }

    // ~ Private method
    private void checkQuery(MessageQuery query) {
        if (query == null || query.getReceiverId() == null) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
    }
}
