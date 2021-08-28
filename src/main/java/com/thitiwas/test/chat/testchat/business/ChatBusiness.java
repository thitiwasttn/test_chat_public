package com.thitiwas.test.chat.testchat.business;

import com.thitiwas.test.chat.testchat.entity.Channel;
import com.thitiwas.test.chat.testchat.model.CountChannel;
import com.thitiwas.test.chat.testchat.entity.Message;
import com.thitiwas.test.chat.testchat.model.ChatMessageRequest;
import com.thitiwas.test.chat.testchat.service.ChannelService;
import com.thitiwas.test.chat.testchat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@Slf4j
public class ChatBusiness {

    // ส่งข้อความผ่าน protocal websocket
    private final SimpMessagingTemplate template;
    private final MessageService messageService;
    private final HashMap<Integer, Integer> memberInChannel;
    private final HashMap<String, Integer> sessionChannel;
    private final ChannelService channelService;

    @Autowired
    public ChatBusiness(SimpMessagingTemplate template, MessageService messageService, ChannelService channelService) {
        this.template = template;
        this.messageService = messageService;
        this.channelService = channelService;
        memberInChannel = new HashMap<>();
        sessionChannel = new HashMap<>();
        setChannel(memberInChannel);
    }

    private void setChannel(HashMap<Integer, Integer> memberInChannel) {
        for (Channel channel : channelService.findAll()) {
            memberInChannel.put(channel.getId(), 0);
        }
    }

    public void post(ChatMessageRequest chatMessageRequest) {
        // log.debug("chatMessageRequest : {} ", chatMessageRequest);
        final String desination = chatMessageRequest.getChannel();
        // log.debug("channel :{}", channel);
        Message message = saveMessage(chatMessageRequest);
        // validation here
        template.convertAndSend(desination, message);
    }

    public Message saveMessage(ChatMessageRequest chat) {
        String channel = getChannelPattern(chat.getChannel());
        Message message = new Message();
        message.setMessage(chat.getMessage());
        message.setChannelId(Integer.valueOf(channel));
        message.setCreateDate(new Date());
        message.setCreateBy(chat.getFrom());
        return messageService.save(message);
    }

    public String getChannelPattern(String destination) {
        return destination.replaceAll("/topic/", "");
    }

    public int getCurrentMemberInRoom(int channel) {
        return memberInChannel.get(channel);
    }

    public void subscribeToChannel(int channelInt, String sessionId) {
        Integer count = memberInChannel.get(channelInt);
        count = count + 1;
        memberInChannel.put(channelInt, count);
        sessionChannel.put(sessionId, channelInt);
    }

    public void unsubscribeChannel(String sessionId) {
        Integer channelInt = getChannelBySession(sessionId);
        Integer count = memberInChannel.get(channelInt);
        count = count - 1;
        memberInChannel.put(channelInt, count);
    }

    public int getChannelBySession(String sessionId) {
        return sessionChannel.get(sessionId);
    }

    public CountChannel getCountChannel(int channel) {
        return CountChannel.builder()
                .channelId(String.valueOf(channel))
                .size(getCurrentMemberInRoom(channel))
                .build();
    }

}
