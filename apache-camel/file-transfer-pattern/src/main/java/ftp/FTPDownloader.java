package ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {FTPUploader.class, FTPUploaderRouteBuilder.class})
public class FTPDownloader implements CommandLineRunner{
    @Autowired
    Main camelMain;

    @Autowired
    RouteBuilder ftpDownloaderRouteBuilder;

    public static void main(String[] args) {
        SpringApplication.run(FTPDownloader.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        camelMain.addRouteBuilder(ftpDownloaderRouteBuilder);
        camelMain.run();
    }

    @Bean
    public Main camelMain() {
        return new Main();
    }
}
