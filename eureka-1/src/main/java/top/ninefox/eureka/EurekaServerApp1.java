package top.ninefox.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp1 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp1.class, args);
    }
}
