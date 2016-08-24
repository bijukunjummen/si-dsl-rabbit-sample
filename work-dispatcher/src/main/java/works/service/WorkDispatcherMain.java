package works.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class WorkDispatcherMain {

    public static void main(String[] args) {
        SpringApplication.run(WorkDispatcherMain.class, args);
    }

}
