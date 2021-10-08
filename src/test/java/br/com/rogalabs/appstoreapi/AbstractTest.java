package br.com.rogalabs.appstoreapi;

import br.com.rogalabs.appstoreapi.repositories.AppRepository;
import br.com.rogalabs.appstoreapi.repositories.CategoryRepository;
import br.com.rogalabs.appstoreapi.repositories.PublisherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/**
 * @author Anderson on 08/10/2021
 * @project app-store-api
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AbstractTest {

    @Autowired
    protected AppRepository appRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected PublisherRepository publisherRepository;

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
