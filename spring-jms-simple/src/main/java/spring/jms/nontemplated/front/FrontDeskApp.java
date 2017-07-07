package spring.jms.nontemplated.front;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.jms.shared.Mail;
import spring.jms.shared.provider.ActiveMqJMSProvider;
import spring.jms.shared.provider.JMSProvider;

@Configuration
@SpringBootApplication
public class FrontDeskApp implements CommandLineRunner {
    @Autowired
    FrontDesk frontDesk;

    public static void main(String[] args) {
        SpringApplication.run(FrontDeskApp.class, args);
    }

    @Bean
    public JMSProvider jmsProvider() {
        return new ActiveMqJMSProvider(
                new ActiveMQConnectionFactory("tcp://localhost:61616"),
                new ActiveMQQueue("mail.queue"));
    }

    @Bean
    public FrontDesk frontDesk() {
        return new FrontDeskProducer(jmsProvider());
    }

    @Override
    public void run(String... args) throws Exception {
        frontDesk.sendMail(new Mail("1234", "US", 1.5));
        System.out.println("Mail sent.");
    }
}
