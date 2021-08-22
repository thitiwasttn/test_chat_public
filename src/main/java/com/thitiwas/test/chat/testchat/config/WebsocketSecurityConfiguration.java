package com.thitiwas.test.chat.testchat.config;

import com.thitiwas.test.chat.testchat.business.ChatBusiness;
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
    private final ChatBusiness chatBusiness;

    @Autowired
    @Lazy
    public WebsocketSecurityConfiguration(WebsocketInterceptor interceptors, ChatBusiness chatBusiness) {
        this.interceptors = interceptors;
        this.chatBusiness = chatBusiness;
    }

    @Override
    public void customizeClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptors);
    }

    @Bean
    public WebsocketInterceptor websocketInterceptor() {
        return new WebsocketInterceptor(chatBusiness);
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
