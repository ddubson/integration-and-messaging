package spring.jms.nontemplated.back;

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
    BackOffice backOffice;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner app() {
        return (args) -> {
            Mail mail = backOffice.receiveMail();
            System.out.println("Mail received. " + mail);
        };
    }

    @Bean
    public JMSProvider jmsProvider() {
        return new ActiveMqJMSProvider("tcp://localhost:61616", "mail.queue");
    }

    @Bean
    public BackOffice backOffice() {
        return new BackOfficeConsumer(jmsProvider());
    }
}
