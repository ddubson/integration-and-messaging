package ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class FTPUploader implements CommandLineRunner {
    @Autowired
    Main camelMain;
    @Autowired
    RouteBuilder ftpUploaderRouteBuilder;

    public static void main(String[] args) {
        SpringApplication.run(FTPUploader.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        camelMain.addRouteBuilder(ftpUploaderRouteBuilder);
        camelMain.run();
    }
}
