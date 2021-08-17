package com.thitiwas.test.chat.testchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // ช่องทางในการสื่อสารระหว่างที่ สอง ที่
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
        //WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                // path
                .setAllowedOrigins("http://localhost:4200");
                //.withSockJS();
                // all origin
        //WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }

}
