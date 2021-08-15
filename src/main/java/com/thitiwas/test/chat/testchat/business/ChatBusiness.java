package com.thitiwas.test.chat.testchat.business;

import com.thitiwas.test.chat.testchat.model.ChatMessage;
import com.thitiwas.test.chat.testchat.model.ChatMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatBusiness {

    // ส่งข้อความผ่าน protocal websocket
    private final SimpMessagingTemplate template;

    @Autowired
    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageRequest chatMessageRequest) {
        final String desination = "chat";
        // validation here
        ChatMessage chatMessage = ChatMessage
                .builder()
                .from("from")
                .message(chatMessageRequest.getMessage())
                .created(new Date())
                .build();
        template.convertAndSend(desination, chatMessage);
    }
}
