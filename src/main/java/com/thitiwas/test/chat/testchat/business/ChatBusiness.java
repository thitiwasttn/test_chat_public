package com.thitiwas.test.chat.testchat.business;

import com.thitiwas.test.chat.testchat.model.ChatMessage;
import com.thitiwas.test.chat.testchat.model.ChatMessageRequest;
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

    @Autowired
    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageRequest chatMessageRequest) {
        log.debug("chatMessageRequest : {} " , chatMessageRequest);
        final String desination = "/topic/chat";
        // validation here
        ChatMessage chatMessage = ChatMessage
                .builder()
                .from(chatMessageRequest.getFrom())
                .message(chatMessageRequest.getMessage())
                .created(new Date())
                .build();
        log.debug("chatMessageRequest :{}" , chatMessageRequest);
        template.convertAndSend(desination, chatMessage);
    }
}
