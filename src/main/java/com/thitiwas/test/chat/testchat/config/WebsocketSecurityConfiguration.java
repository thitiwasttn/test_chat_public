package com.thitiwas.test.chat.testchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebsocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {



    private final WebsocketInterceptor interceptors;
    
    @Autowired
    @Lazy
    public WebsocketSecurityConfiguration(WebsocketInterceptor interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public void customizeClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptors);
    }

    @Bean
    public WebsocketInterceptor websocketInterceptor() {
        return new WebsocketInterceptor();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
