package com.example.messageservice;
import com.pusher.rest.Pusher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class MessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        String app_id = "1784918";
//        String key = "2d21e5ca31cdaee85787";
//        String secret = "e7ff0b60aff91316a91e";
//        String cluster = "us2";
//        return args -> {
//            Pusher pusher = new Pusher(app_id, key, secret);
//            pusher.setCluster(cluster);
//            pusher.setEncrypted(true);
//            pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));
//        };
//    }

}