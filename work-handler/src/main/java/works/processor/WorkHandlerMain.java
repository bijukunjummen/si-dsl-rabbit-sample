package works.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class WorkHandlerMain {

    public static void main(String[] args) {
        SpringApplication.run(WorkHandlerMain.class, args);
    }

}
