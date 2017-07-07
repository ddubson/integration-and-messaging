package spring.jms.templated;

import org.springframework.jms.core.JmsTemplate;
import spring.jms.shared.provider.JMSProvider;

public class Sender {
    private final JMSProvider provider;
    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate, JMSProvider provider) {
        this.jmsTemplate = jmsTemplate;
        this.provider = provider;
    }

    public void send(final String message) {
        System.out.format("Sending message via %s: %s\n", provider.getType(), message);
        this.jmsTemplate.send(
                this.provider.getDestination(),
                session -> session.createTextMessage(message)
        );
    }
}
