package spring.jms.nontemplated.back;

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
public class BackOfficeApp implements CommandLineRunner {
    @Autowired
    BackOffice backOffice;

    public static void main(String[] args) {
        SpringApplication.run(BackOfficeApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mail mail = backOffice.receiveMail();
        System.out.println("Mail received. " + mail);
    }

    @Bean
    public JMSProvider jmsProvider() {
        return new ActiveMqJMSProvider(
                new ActiveMQConnectionFactory("tcp://localhost:61616"),
                new ActiveMQQueue("mail.queue"));
    }

    @Bean
    public BackOffice backOffice() {
        return new BackOfficeConsumer(jmsProvider());
    }
}
