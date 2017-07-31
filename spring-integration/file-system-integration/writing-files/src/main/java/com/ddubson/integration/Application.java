package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static com.ddubson.ANSIColor.ANSI_PURPLE;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Reading *.txt files from 'assets/read' directory.", ANSI_CYAN);
        logAdapter().info("[2] Writing *.txt files to 'assets/write' directory every 3 seconds.", ANSI_PURPLE);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }
}
