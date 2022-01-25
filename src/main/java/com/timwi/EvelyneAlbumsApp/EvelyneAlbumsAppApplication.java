package com.timwi.EvelyneAlbumsApp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEncryptableProperties
@PropertySource(name = "EncryptedProperties", value = "classpath:application.yml")
public class EvelyneAlbumsAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvelyneAlbumsAppApplication.class, args);
    }
}
