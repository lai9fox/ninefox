package top.ninefox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommandServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(CommandServiceApp.class, args);
    }

}
