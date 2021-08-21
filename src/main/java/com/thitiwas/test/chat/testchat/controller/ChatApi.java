package com.thitiwas.test.chat.testchat.controller;

import com.thitiwas.test.chat.testchat.business.ChatBusiness;
import com.thitiwas.test.chat.testchat.entity.Message;
import com.thitiwas.test.chat.testchat.model.ChatMessageRequest;
import com.thitiwas.test.chat.testchat.model.MessageRequest;
import com.thitiwas.test.chat.testchat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatApi {

    private final ChatBusiness business;
    private final MessageService messageService;

    @Autowired
    public ChatApi(ChatBusiness business, MessageService messageService) {
        this.business = business;
        this.messageService = messageService;
    }

    @PutMapping("/message")
    public ResponseEntity<Void> post(@RequestBody ChatMessageRequest request) {
        business.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/messages")
    public ResponseEntity<List<Message>> getListChat(@RequestBody MessageRequest request) {
        // log.debug("request :{}", request);
        return ResponseEntity.ok(messageService.findByChannel(request.getChannelId(), PageRequest.of(request.getPage(), request.getLimit())));
    }

}
