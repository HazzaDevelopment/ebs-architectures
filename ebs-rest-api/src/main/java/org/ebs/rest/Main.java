package org.ebs.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.ebs.shared")
@EnableJpaRepositories("org.ebs.shared")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
