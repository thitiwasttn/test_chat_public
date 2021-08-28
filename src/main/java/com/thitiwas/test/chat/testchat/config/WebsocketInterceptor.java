package com.thitiwas.test.chat.testchat.config;

import com.thitiwas.test.chat.testchat.business.ChatBusiness;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class WebsocketInterceptor implements ChannelInterceptor {

    private final ChatBusiness chatBusiness;

    public WebsocketInterceptor(ChatBusiness chatBusiness) {
        this.chatBusiness = chatBusiness;
    }


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (headerAccessor == null) {
            return ChannelInterceptor.super.preSend(message, channel);
        }

        switch (Objects.requireNonNull(headerAccessor.getCommand())) {
            case CONNECTED:
                //
                break;
            case DISCONNECT:
                //
                break;
            case SUBSCRIBE:
                // subscribeProcess(headerAccessor);
                break;
            case UNSUBSCRIBE:
                // unsubscribeProcess(headerAccessor);
                break;
        }

        return ChannelInterceptor.super.preSend(message, channel);
    }


    private void unsubscribeProcess(StompHeaderAccessor headerAccessor) {
        if (StompCommand.UNSUBSCRIBE.equals(headerAccessor.getCommand())) {
            int channelInt = chatBusiness.getChannelBySession(headerAccessor.getSessionId());
            chatBusiness.unsubscribeChannel(headerAccessor.getSessionId());
            int memberInRoom = getMemberInChannel(channelInt);
            // log.debug("memberIn channel:{} , number:{}", channelInt, memberInRoom);
        }
    }

    private int getMemberInChannel(int channelInt) {
        return chatBusiness.getCurrentMemberInRoom(channelInt);
    }

    private void subscribeProcess(StompHeaderAccessor headerAccessor) {
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            int channelInt = Integer.parseInt(chatBusiness.getChannelPattern(Objects.requireNonNull(headerAccessor.getDestination())));
            chatBusiness.subscribeToChannel(channelInt, headerAccessor.getSessionId());
            int memberInRoom = getMemberInChannel(channelInt);
            // log.debug("memberIn channel:{} , number:{}", channelInt, memberInRoom);
        }
    }
}
