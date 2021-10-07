package br.com.rogalabs.appstoreapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anderson on 07/10/2021
 * @project app-store-api
 */
@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
class PublisherServiceTest {

    @BeforeEach
    void setUp() {
    }
}