//package com.example.demo;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import java.util.Scanner;
@EnableJms
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter patient name:");
        String patientName = myObj.nextLine();  // Read user input
        System.out.println("Enter patient condition:");
        String patientCondition = myObj.nextLine();  // Read user input
        String msgArray[] = {patientName,patientCondition};
        jmsTemplate.convertAndSend("NewPatientTopic", new Email("Message1", "Name of patient is "+msgArray[0]+" ,and his condition is "+msgArray[1]), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSCorrelationID("15");
                return message;
            }
        });
        jmsTemplate.convertAndSend("InquireQueue", new Email("Message2", "Name of patient is "+msgArray[0]), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSCorrelationID("15");
                return message;
            }
        });
        jmsTemplate.convertAndSend("PatientRegistry", new Email("Message3", "Medical history is: diabetes, heart attack."), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSCorrelationID("15");
                return message;
            }
        });
    }
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
        JmsTemplate template = new JmsTemplate();
        template.setMessageConverter(converter);
        template.setConnectionFactory(connectionFactory);
        template.setPubSubDomain(true);
        return template;
    }
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
