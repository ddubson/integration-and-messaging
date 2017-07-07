package spring.jms.shared.provider;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

public interface JMSProvider {
    ConnectionFactory getConnectionFactory();

    Destination getDestination();

    String getType();
}
