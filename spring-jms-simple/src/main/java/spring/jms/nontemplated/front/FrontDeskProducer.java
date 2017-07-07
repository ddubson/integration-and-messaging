package spring.jms.nontemplated.front;

import spring.jms.shared.Mail;
import spring.jms.shared.provider.JMSProvider;

import javax.jms.*;

public class FrontDeskProducer implements FrontDesk {
	private final JMSProvider provider;

	public FrontDeskProducer(JMSProvider provider) {
		this.provider = provider;
	}

	public void sendMail(Mail mail) {
		Connection conn = null;
		Destination dest = provider.getDestination();
		ConnectionFactory cf = provider.getConnectionFactory();
		try {
			conn = cf.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(dest);
			MapMessage message = session.createMapMessage();
			message.setString("mailId", mail.getMailId());
			message.setString("country", mail.getCountry());
			message.setDouble("weight", mail.getWeight());
			producer.send(message);
			
			session.close();
		} catch(JMSException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn != null) {
				try { conn.close(); } catch(JMSException e) {}
			}
 		}

	}

}
