package spring.jms.templated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import spring.jms.provider.ActiveMqJMSProvider;
import spring.jms.provider.JMSProvider;

import javax.jms.MessageListener;

/**
 * Author: ddubson
 */
@SpringBootApplication
public class Application {
    @Autowired
    Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner app() {
        return (args) -> sender.simpleSend();
    }

    @Bean
    public JMSProvider jmsProvider() {
        return new ActiveMqJMSProvider("tcp://localhost:61616", "mail.queue");
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
        return (message) -> System.out.println("Received msg: " + message.toString());
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
