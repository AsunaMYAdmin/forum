package me.asunamyadmin.forumloregard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ForumLoregardApplication {

    static void main(String[] args) {
        SpringApplication.run(ForumLoregardApplication.class, args);
    }

}
