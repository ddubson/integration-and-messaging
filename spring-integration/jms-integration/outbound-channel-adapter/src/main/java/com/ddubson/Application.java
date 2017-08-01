package com.ddubson;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static com.ddubson.ANSIColor.ANSI_GREEN;
import static com.ddubson.ANSIColor.ANSI_YELLOW;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner {
    @Autowired
    PersonGateway personGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Sending message to inboundChannel", ANSI_GREEN);

        Person person = new Person(4, "Jane", "Doe");
        this.personGateway.save(person);

        logAdapter().info("[2] Person object transformed to string.", ANSI_CYAN);

        logAdapter().info("[3] Stringified person object stored on sample queue in ActiveMQ.", ANSI_YELLOW);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    @Bean
    @Qualifier("sampleQueue")
    public ActiveMQQueue sampleQueue() {
        return new ActiveMQQueue("sample.queue");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
