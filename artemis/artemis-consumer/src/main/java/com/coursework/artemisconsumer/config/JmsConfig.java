package com.coursework.artemisconsumer.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(SingleConnectionFactory connectionFactory,
                                                                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        connectionFactory.setClientId("first-consumer");
        jmsListenerContainerFactory.setPubSubDomain(true);
        jmsListenerContainerFactory.setSubscriptionDurable(true);
        configurer.configure(jmsListenerContainerFactory, connectionFactory);
        return jmsListenerContainerFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

}
