package com.thitiwas.test.chat.testchat.service;

import com.thitiwas.test.chat.testchat.entity.Channel;
import com.thitiwas.test.chat.testchat.model.ChannelResponse;
import com.thitiwas.test.chat.testchat.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public List<ChannelResponse> findAllChannelResponse() {
        return null;
    }
}
