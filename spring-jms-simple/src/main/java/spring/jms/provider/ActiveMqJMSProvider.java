package spring.jms.provider;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * Author: ddubson
 */
public class ActiveMqJMSProvider implements JMSProvider {
    private final String hostname;
    private final String queueName;

    public ActiveMqJMSProvider(String hostname,
                               String queueName) {
        this.hostname = hostname;
        this.queueName = queueName;
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return new ActiveMQConnectionFactory(hostname);
    }

    @Override
    public Destination getDestination() {
        return new ActiveMQQueue(queueName);
    }
}
