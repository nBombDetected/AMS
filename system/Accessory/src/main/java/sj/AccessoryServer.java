package sj;

import js.utils.ResponseAOP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AccessoryServer {
    public static void main(String[] args) {
        SpringApplication.run(AccessoryServer.class, args);
    }
    @Bean
    public ResponseAOP responseAOP(){
        return new ResponseAOP();
    }
}
