package sj;

import js.utils.ResponseAOP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SupplierServer {
    public static void main(String[] args) {
        SpringApplication.run(SupplierServer.class, args);
    }

    @Bean
    public ResponseAOP responseAOP(){
        return new ResponseAOP();
    }
}
