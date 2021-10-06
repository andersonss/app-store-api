package br.com.rogalabs.appstoreapi.repositories;

import br.com.rogalabs.appstoreapi.domain.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@DataJpaTest
@ActiveProfiles("test")
class AppRepositoryTest {

    @Autowired
    private AppRepository appRepository;

    @Test
    public void shouldAddNewApp() {
        App app = new App(null, "App 1", "The App 1", 1);
        App appAdded = appRepository.save(app);
        assertThat(appAdded).usingRecursiveComparison().ignoringFields("id").isEqualTo(app);
    }
}