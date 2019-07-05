package com.study.droolscore;

import com.study.droolscore.config.DroolsAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(DroolsAutoConfiguration.class)
@SpringBootApplication
public class DroolsCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroolsCoreApplication.class, args);
    }

}
