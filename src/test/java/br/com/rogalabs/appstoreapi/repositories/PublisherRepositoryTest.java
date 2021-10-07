package br.com.rogalabs.appstoreapi.repositories;

import br.com.rogalabs.appstoreapi.domain.App;
import br.com.rogalabs.appstoreapi.domain.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anderson on 07/10/2021
 * @project app-store-api
 */
@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
public class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() {
        Publisher publisher1 = new Publisher(1L, "First");
        Publisher publisher2 = new Publisher(2L, "Second");
        publisherRepository.saveAll(Arrays.asList(publisher1, publisher2));
    }

    @Test
    public void shouldSave() {
        Publisher publisher = new Publisher(null, "New");
        Publisher publisherAdded = publisherRepository.save(publisher);
        assertThat(publisherAdded).usingRecursiveComparison().ignoringFields("id").isEqualTo(publisher);
    }

    @Test
    public void shouldFindAll() {
        var allPublishers = publisherRepository.findAll();
        assertThat(allPublishers.size()).isEqualTo(2);
    }

    @Test
    public void shouldFindById() {
        Optional<Publisher> byId = publisherRepository.findById(2L);
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getName()).isEqualTo("Second");
    }

}
