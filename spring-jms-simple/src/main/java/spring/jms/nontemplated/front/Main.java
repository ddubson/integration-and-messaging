package spring.jms.nontemplated.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.jms.Mail;
import spring.jms.provider.ActiveMqJMSProvider;
import spring.jms.provider.JMSProvider;

@Configuration
@SpringBootApplication
public class Main {
    @Autowired
    FrontDesk frontDesk;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner app() {
        return (args) -> {
            frontDesk.sendMail(new Mail("1234", "US", 1.5));
            System.out.println("Mail sent.");
        };
    }

    @Bean
    public JMSProvider jmsProvider() {
        return new ActiveMqJMSProvider("tcp://localhost:61616", "mail.queue");
    }

    @Bean
    public FrontDesk frontDesk() {
        return new FrontDeskProducer(jmsProvider());
    }
}
