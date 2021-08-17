package com.thitiwas.test.chat.testchat.service;

import com.thitiwas.test.chat.testchat.entity.Message;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    public List<Message> findByChannel(int channelId, Pageable pageable);

    Message save(Message message);
}
