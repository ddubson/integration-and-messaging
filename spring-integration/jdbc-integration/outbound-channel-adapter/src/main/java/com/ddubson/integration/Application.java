package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import static com.ddubson.ANSIColor.ANSI_WHITE;
import static com.ddubson.ANSIColor.ANSI_YELLOW;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner {
    @Autowired
    private PersonGateway personGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Send message to gateway.", ANSI_WHITE);
        Person person = new Person(4, "Jane", "Doe");
        this.personGateway.save(person);
        logAdapter().info("[2] Message consumed off of input channel and inserted to db.",
                ANSI_YELLOW);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
