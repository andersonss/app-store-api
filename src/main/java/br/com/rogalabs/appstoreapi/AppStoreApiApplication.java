package br.com.rogalabs.appstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.rogalabs.appstoreapi.repositories")
@SpringBootApplication(scanBasePackages = "br.com.rogalabs.appstoreapi")
public class AppStoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppStoreApiApplication.class, args);
    }

}
