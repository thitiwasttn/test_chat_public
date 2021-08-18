package com.thitiwas.test.chat.testchat.business;

import com.thitiwas.test.chat.testchat.entity.Message;
import com.thitiwas.test.chat.testchat.model.ChatMessage;
import com.thitiwas.test.chat.testchat.model.ChatMessageRequest;
import com.thitiwas.test.chat.testchat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ChatBusiness {

    // ส่งข้อความผ่าน protocal websocket
    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @Autowired
    public ChatBusiness(SimpMessagingTemplate template, MessageService messageService) {
        this.template = template;
        this.messageService = messageService;
    }

    public void post(ChatMessageRequest chatMessageRequest) {
        log.debug("chatMessageRequest : {} ", chatMessageRequest);
        final String desination = chatMessageRequest.getChannel();
        // log.debug("channel :{}", channel);
        Message message = saveMessage(chatMessageRequest);
        // validation here
        /*ChatMessage chatMessage = ChatMessage
                .builder()
                .from(chatMessageRequest.getFrom())
                .message(chatMessageRequest.getMessage())
                .created(new Date())
                .build();
        log.debug("chatMessageRequest :{}", chatMessageRequest);*/
        template.convertAndSend(desination, message);
    }

    private Message saveMessage(ChatMessageRequest chat) {
        String channel = chat.getChannel().replaceAll("/topic/", "");
        Message message = new Message();
        message.setMessage(chat.getMessage());
        message.setChannelId(Integer.valueOf(channel));
        message.setCreateDate(new Date());
        message.setCreateBy(chat.getFrom());
        return messageService.save(message);
    }
}
