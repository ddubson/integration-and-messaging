package spring.jms.provider;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * Author: ddubson
 */
public interface JMSProvider {
    ConnectionFactory getConnectionFactory();

    Destination getDestination();
}
