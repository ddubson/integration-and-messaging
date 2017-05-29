package spring.jms.templated;

import org.springframework.jms.core.JmsTemplate;
import spring.jms.provider.JMSProvider;

/**
 * Author: ddubson
 */
public class Sender {
    private final JMSProvider provider;
    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate, JMSProvider provider) {
        this.jmsTemplate = jmsTemplate;
        this.provider = provider;
    }

    public void simpleSend() {
        this.jmsTemplate.send(this.provider.getDestination(), session -> session.createTextMessage("hello queue world"));
    }
}
