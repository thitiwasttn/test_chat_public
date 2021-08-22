package com.thitiwas.test.chat.testchat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    private final Environment env;

    @Autowired
    public WebsocketConfig(Environment env) {
        this.env = env;
    }

    @Override
    public void configureWebSocketTransport(final WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {
                return new WebSocketHandlerDecorator(handler) {
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                        // log.debug("afterConnectionEstablished::session :{}", session);
                        // quizSocketGlobalState.getSessionMap().put(session.getId(), session);
                        super.afterConnectionEstablished(session);
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        // log.debug("afterConnectionClosed::session :{}", session);
                        super.afterConnectionClosed(session, closeStatus);
                        // quizSocketGlobalState.getSessionMap().remove(session.getId());
                    }
                };
            }
        });
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // ช่องทางในการสื่อสารระหว่างที่ สอง ที่
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
        //WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String[] allow = new String[2];
        allow[0] = "http://localhost:4200";
        allow[1] = "http://61.19.242.56";
        registry.addEndpoint("/socket")
                // path
                .setAllowedOrigins(allow);
        //.withSockJS();
        // all origin
        //WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }

}
