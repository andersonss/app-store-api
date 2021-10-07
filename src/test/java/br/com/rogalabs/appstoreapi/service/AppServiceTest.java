package br.com.rogalabs.appstoreapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
class AppServiceTest {

    @Autowired
    private AppService appService;

    @BeforeEach
    void setUp() {
    }

}