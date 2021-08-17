package com.thitiwas.test.chat.testchat.model;

import lombok.Data;

@Data
public class ChatMessageRequest {

    private String message;

    private String from;

    private String channel;
}
