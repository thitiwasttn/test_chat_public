package com.thitiwas.test.chat.testchat.service;

import com.thitiwas.test.chat.testchat.entity.Message;
import com.thitiwas.test.chat.testchat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findByChannel(int channelId, Pageable pageable) {
        return this.messageRepository.findByAndChannelIdOrderByIdDesc(channelId, pageable);
    }

    @Override
    public Message save(Message message) {
        return this.messageRepository.save(message);
    }
}
