package br.com.rogalabs.appstoreapi.repositories;

import br.com.rogalabs.appstoreapi.AbstractTest;
import br.com.rogalabs.appstoreapi.domain.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppRepositoryTest extends AbstractTest {

    @BeforeEach
    public void setUp() {
        App app1 = new App(1L, "Cheap App", "The Cheap App", 1);
        App app2 = new App(2L, "Expensive App", "The Expensive App", 50);
        appRepository.saveAll(Arrays.asList(app1, app2));
    }

    @Test
    public void shouldSave() {
        App app = new App(null, "New App", "The new App", 5);
        App appAdded = appRepository.save(app);
        assertThat(appAdded).usingRecursiveComparison().ignoringFields("id").isEqualTo(app);
    }

    @Test
    public void shouldFindAll() {
        var allApps = appRepository.findAll();
        assertThat(allApps.size()).isEqualTo(2);
    }

    @Test
    public void shouldFindById() {
        Optional<App> byId = appRepository.findById(2L);
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getName()).isEqualTo("Expensive App");
    }

    @Test
    public void findAllByNameContains() {
        var app = appRepository.findAllByNameContains("Expensive");
        assertThat(app.size()).isEqualTo(1);
        assertThat(app.get(0).getName()).isEqualTo("Expensive App");
    }

}