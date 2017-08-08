package ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.springframework.stereotype.Component;

@Component("ftpUploaderRouteBuilder")
public class FTPUploaderRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // configure properties component
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:ftp.properties");

        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from("file:target/upload?moveFailed=../error")
                .log("Uploading file ${file:name}")
                .to("{{ftp.client}}")
                .log("Uploaded file ${file:name} complete.");

        // use system out so it stands out
        System.out.println("*********************************************************************************");
        System.out.println("Camel will route files from target/upload directory to the FTP server: "
                + getContext().resolvePropertyPlaceholders("{{ftp.server}}"));
        System.out.println("You can configure the location of the ftp server in the src/main/resources/ftp.properties file.");
        System.out.println("If the file upload fails, then the file is moved to the target/error directory.");
        System.out.println("Use ctrl + c to stop this application.");
        System.out.println("*********************************************************************************");
    }
}
