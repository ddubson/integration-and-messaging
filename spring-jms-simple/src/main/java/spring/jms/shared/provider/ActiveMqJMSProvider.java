package spring.jms.shared.provider;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

public class ActiveMqJMSProvider implements JMSProvider {
    private final ActiveMQConnectionFactory connectionFactory;
    private final ActiveMQQueue queue;

    public ActiveMqJMSProvider(ActiveMQConnectionFactory connectionFactory,
                               ActiveMQQueue queue) {
        this.connectionFactory = connectionFactory;
        this.queue = queue;
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public Destination getDestination() {
        return queue;
    }

    @Override
    public String getType() {
        return "ActiveMQ";
    }
}
