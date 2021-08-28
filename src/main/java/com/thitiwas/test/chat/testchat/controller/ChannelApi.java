package com.thitiwas.test.chat.testchat.controller;

import com.thitiwas.test.chat.testchat.business.ChatBusiness;
import com.thitiwas.test.chat.testchat.entity.Channel;
import com.thitiwas.test.chat.testchat.model.CountChannel;
import com.thitiwas.test.chat.testchat.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelApi {

    private final ChannelService channelService;
    private final ChatBusiness chatBusiness;

    @Autowired
    public ChannelApi(ChannelService channelService, ChatBusiness chatBusiness) {
        this.channelService = channelService;
        this.chatBusiness = chatBusiness;
    }

    @GetMapping("/channels")
    public ResponseEntity<List<Channel>> findAll() {
        return ResponseEntity.ok(this.channelService.findAll());
    }

    @GetMapping("/channels/{channelId}/size")
    public ResponseEntity<CountChannel> getCount(@PathVariable("channelId") int channel) {
        return ResponseEntity.ok(chatBusiness.getCountChannel(channel));
    }
}
