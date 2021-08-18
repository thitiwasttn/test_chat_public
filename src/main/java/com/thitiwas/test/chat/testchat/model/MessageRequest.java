package com.thitiwas.test.chat.testchat.model;

import lombok.Data;

@Data
public class MessageRequest {
    private int channelId;
    private int limit;
    private int page;

}
