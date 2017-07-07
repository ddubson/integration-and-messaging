package spring.jms.templated;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import spring.jms.shared.provider.ActiveMqJMSProvider;
import spring.jms.shared.provider.JMSProvider;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Configuration
public class ActiveMqConfiguration {
    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${activemq.queue-name}")
    private String queueName;

    @Bean
    public JMSProvider jmsProvider() {
        return new ActiveMqJMSProvider(
                new ActiveMQConnectionFactory(brokerUrl),
                new ActiveMQQueue(queueName)
        );
    }

    @Bean
    LogAdapter log() {
        return new CommandLineLogAdapter();
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(jmsProvider().getConnectionFactory());
    }

    @Bean
    public Sender sender() {
        return new Sender(jmsTemplate(), jmsProvider());
    }

    @Bean
    public MessageListener receiver() {
        return (message) -> {
            try {
                log().info("Received msg: " + ((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        };
    }
    @Bean
    public DefaultMessageListenerContainer jmsContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(jmsProvider().getConnectionFactory());
        container.setDestination(jmsProvider().getDestination());
        container.setMessageListener(receiver());
        return container;
    }
}

