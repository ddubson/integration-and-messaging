package spring.jms.nontemplated.back;

import spring.jms.shared.Mail;
import spring.jms.shared.provider.JMSProvider;

import javax.jms.*;

public class BackOfficeConsumer implements BackOffice {
	private final JMSProvider provider;

	public BackOfficeConsumer(JMSProvider provider) {
		this.provider = provider;
	}

	public Mail receiveMail() {
		ConnectionFactory cf = provider.getConnectionFactory();
		Destination dest = provider.getDestination();

		Connection conn = null;
		try {
			conn = cf.createConnection();
			Session session = conn.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(dest);

			conn.start();
			MapMessage message = (MapMessage) consumer.receive();
			Mail mail = new Mail();
			mail.setMailId(message.getString("mailId"));
			mail.setCountry(message.getString("country"));
			mail.setWeight(message.getDouble("weight"));
			session.close();
			return mail;
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (JMSException e) {
			}
		}
	}
}
