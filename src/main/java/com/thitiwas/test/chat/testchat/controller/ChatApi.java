package com.thitiwas.test.chat.testchat.controller;

import com.thitiwas.test.chat.testchat.business.ChatBusiness;
import com.thitiwas.test.chat.testchat.model.ChatMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApi {

    private final ChatBusiness business;

    @Autowired
    public ChatApi(ChatBusiness business) {
        this.business = business;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody ChatMessageRequest request) {
        business.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
