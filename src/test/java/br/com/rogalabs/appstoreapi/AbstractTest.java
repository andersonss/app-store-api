package br.com.rogalabs.appstoreapi;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/**
 * @author Anderson on 08/10/2021
 * @project app-store-api
 */
public class AbstractTest {

    private static MySQLContainer container = (MySQLContainer) new MySQLContainer("mysql:5.6")
            .withReuse(true);

    @BeforeAll
    public static void init() {
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
