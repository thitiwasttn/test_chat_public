package com.thitiwas.test.chat.testchat.controller;

import com.thitiwas.test.chat.testchat.entity.Channel;
import com.thitiwas.test.chat.testchat.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelApi {

    private final ChannelService channelService;

    @Autowired
    public ChannelApi(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/channels")
    public ResponseEntity<List<Channel>> findAll() {
        return ResponseEntity.ok(this.channelService.findAll());
    }
}
