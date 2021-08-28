package com.thitiwas.test.chat.testchat.service;

import com.thitiwas.test.chat.testchat.entity.Channel;
import com.thitiwas.test.chat.testchat.model.ChannelResponse;

import java.util.List;

public interface ChannelService {
    List<Channel> findAll();

    Channel save(Channel channel);

    List<ChannelResponse> findAllChannelResponse();
}
