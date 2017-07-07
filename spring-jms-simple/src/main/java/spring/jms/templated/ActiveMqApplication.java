package spring.jms.templated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.jms.nontemplated.back.BackOfficeApp;
import spring.jms.nontemplated.front.FrontDeskApp;

@SpringBootApplication
public class ActiveMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class, args);
    }
}
