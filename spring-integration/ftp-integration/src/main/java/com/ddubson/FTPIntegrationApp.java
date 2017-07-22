package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class FTPIntegrationApp implements ApplicationRunner {
    @Autowired
    @Qualifier("gateway2")
    private FileWriterGateway outboundChannelGateway;

    @Autowired
    @Qualifier("gateway3")
    private FileWriterGateway outboundChannelAdapterGateway;

    public static void main(String[] args) {
        SpringApplication.run(FTPIntegrationApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.outboundChannelGateway.write("example.txt",
                "This is the test of FTP outbound channel adapter");

        this.outboundChannelAdapterGateway.read("ftp/shared/files/example.txt");
    }
}
