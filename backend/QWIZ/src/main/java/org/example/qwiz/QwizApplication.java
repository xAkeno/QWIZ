package org.example.qwiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class QwizApplication {

    private static final Logger log = Logger.getLogger(QwizApplication.class.getName());
    public static void main(String[] args) {
        SpringApplication.run(QwizApplication.class, args);
        log.info("Successfully started application");
    }

}
