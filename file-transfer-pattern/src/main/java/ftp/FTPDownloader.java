package ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Created by ddubs on 12/2/2016.
 */
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
}
